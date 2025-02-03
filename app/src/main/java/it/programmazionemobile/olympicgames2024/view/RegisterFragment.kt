package it.programmazionemobile.olympicgames2024.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import it.programmazionemobile.olympicgames2024.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseDb: CollectionReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentRegisterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Ottengo un riferimento al NavigationController
        val navController = requireView().findNavController()

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDb = FirebaseFirestore.getInstance().collection("user")

        binding.register.setOnClickListener {
            val username = binding.usernameRegister.text.toString()
            val email = binding.email.text.toString()
            val nome = binding.nomeRegister.text.toString()
            val cognome = binding.cognomeRegister.text.toString()
            val newpassword = binding.newpassword.text.toString()
            val reinsertpassword = binding.reinsertnewpassword.text.toString()

            if (username.isNotEmpty() && email.isNotEmpty() &&
                nome.isNotEmpty() && cognome.isNotEmpty() &&
                newpassword.isNotEmpty() && reinsertpassword.isNotEmpty()
            ) {
                if (isValidEmail(email)) {
                    if (newpassword == reinsertpassword) {
                        firebaseAuth.createUserWithEmailAndPassword(email, newpassword)
                            .addOnSuccessListener {
                                createDbData(username, email, nome, cognome)

                                navController.navigate(
                                    RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                                )
                            }.addOnFailureListener { exception: java.lang.Exception ->
                                Toast.makeText(activity, exception.toString(), Toast.LENGTH_LONG)
                                    .show()
                            }
                    } else {
                        Toast.makeText(
                            activity,
                            "Le password non corrispondono!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(activity, "Inserisci una mail valida!", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(activity, "Riempi tutti i campi", Toast.LENGTH_SHORT).show()
            }
        }

        binding.textBottomBar.setOnClickListener {
            navController.navigate(
                RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            )
        }

    }

    private fun isValidEmail(mail: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()
    }


    private fun createDbData(username: String, email: String, nome: String, cognome: String) {
        try {
            val user = HashMap<String, Any>()
            user["username"] = username
            user["email"] = email
            user["nome"] = nome
            user["cognome"] = cognome

            val currentUser = firebaseAuth.currentUser
            if (currentUser != null) {
                firebaseDb.document(currentUser.uid).set(user)
                    .addOnSuccessListener {
                        context?.let {
                            Toast.makeText(it, "Account creato!!", Toast.LENGTH_SHORT).show()
                        }
                    }
                    .addOnFailureListener { exception ->
                        context?.let {
                            Toast.makeText(it, "Errore: ${exception.message}", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
            } else {
                context?.let {
                    Toast.makeText(it, "Utente non autenticato!", Toast.LENGTH_LONG).show()
                }
            }

        } catch (e: Exception) {
            context?.let {
                Toast.makeText(it, "Errore: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
