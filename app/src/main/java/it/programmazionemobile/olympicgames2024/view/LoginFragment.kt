package it.programmazionemobile.olympicgames2024.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import it.programmazionemobile.olympicgames2024.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseDb: DocumentReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Ottengo un riferimento al NavigationController
        val navController = requireView().findNavController()

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnlogin.setOnClickListener {

            val email = binding.username.text.toString()
            val password = binding.password.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                firebaseDb = FirebaseFirestore.getInstance().collection("user").document(email)
                firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            navController.navigate(
                                LoginFragmentDirections.actionLoginFragmentToMainActivity()
                            )
                        } else {
                            Toast.makeText(
                                activity,
                                "Username e/o password errati!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                Toast.makeText(activity, "Campi vuoti non ammessi!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.textBottomBar.setOnClickListener {
            navController.navigate(
                LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            )
        }


    }

}

