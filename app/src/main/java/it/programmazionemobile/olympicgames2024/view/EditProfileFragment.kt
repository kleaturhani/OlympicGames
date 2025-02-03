package it.programmazionemobile.olympicgames2024.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import it.programmazionemobile.olympicgames2024.R
import it.programmazionemobile.olympicgames2024.databinding.FragmentEditprofileBinding
import it.programmazionemobile.olympicgames2024.viewmodel.UserViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class EditProfileFragment : Fragment() {

    private lateinit var binding: FragmentEditprofileBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseDb: DocumentReference

    private val userModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = "Modifica Profilo"
        binding = FragmentEditprofileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDb = FirebaseFirestore.getInstance().collection("user")
            .document(firebaseAuth.currentUser?.uid ?: "")

        // Carica i dati iniziali
        lifecycleScope.launch {
            writeData(view)
        }

        // Gestisci il salvataggio delle modifiche
        view.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btnSaveModification)
            .setOnClickListener {
                lifecycleScope.launch {
                    updateProfile()
                }
            }
    }

    private suspend fun writeData(view: View) {
        try {
            val snapshot = firebaseDb.get().await()
            val data = snapshot.data
            if (data != null) {
                userModel.loadData(
                    data["username"].toString(),
                    data["email"].toString(),
                    data["nome"].toString(),
                    data["cognome"].toString()
                )
                binding.userData = userModel
            }
        } catch (e: Exception) {
            Log.e("ERROR", "Errore nel caricamento dei dati", e)
            Toast.makeText(activity, "Errore nel caricamento dei dati", Toast.LENGTH_SHORT).show()
        } finally {
            view.findViewById<ProgressBar>(R.id.waitingBar)?.visibility = View.GONE
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private suspend fun updateProfile() {
        val currentUser = firebaseAuth.currentUser ?: run {
            Toast.makeText(activity, "Utente non autenticato.", Toast.LENGTH_SHORT).show()
            return
        }

        val currentData = userModel

        val username = binding.newUsername.text.toString()
        val email = binding.newMail.text.toString()
        val nome = binding.newNome.text.toString()
        val cognome = binding.newCognome.text.toString()
        val currentPass = binding.currentPassword.text.toString()
        val newPass = binding.newPassword.text.toString()
        val checkNewPass = binding.checkNewPassword.text.toString()

        try {
            // Reautentica l'utente se necessario
            if (currentPass.isNotEmpty()) {
                val credential = EmailAuthProvider.getCredential(
                    currentUser.email ?: "", currentPass
                )
                currentUser.reauthenticate(credential).await()
            }

            // Aggiorna la password se necessario
            if (newPass.isNotEmpty() && checkNewPass.isNotEmpty() && newPass == checkNewPass) {
                currentUser.updatePassword(newPass).await()
            }

            // Aggiorna i dati solo se sono cambiati
            val updates = mutableMapOf<String, Any>()
            if (username.isNotEmpty() && username != currentData.username) updates["username"] =
                username
            if (email.isNotEmpty() && email != currentData.email) updates["email"] = email
            if (nome.isNotEmpty() && nome != currentData.nome) updates["nome"] = nome
            if (cognome.isNotEmpty() && cognome != currentData.cognome) updates["cognome"] = cognome

            if (updates.isNotEmpty()) {
                firebaseDb.update(updates).await()
                Toast.makeText(activity, "Modifiche applicate con successo!", Toast.LENGTH_SHORT)
                    .show()
            }

            // Torna al profilo
            val fragment = parentFragmentManager.beginTransaction()
            fragment.replace(R.id.nav_host_fragment, ProfileFragment()).commit()

        } catch (e: Exception) {
            Log.e("ERROR", "Errore durante l'aggiornamento del profilo", e)
            Toast.makeText(activity, "Errore: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}

