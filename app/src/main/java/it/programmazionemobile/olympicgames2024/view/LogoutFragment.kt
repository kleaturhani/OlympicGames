package it.programmazionemobile.olympicgames2024.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import it.programmazionemobile.olympicgames2024.R

class LogoutFragment : Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()

        val navView = activity?.findViewById<NavigationView>(R.id.nav_view)
        navView!!.menu.clear()
        navView.inflateMenu(R.menu.menu_home)

        (activity as AppCompatActivity).supportActionBar?.title = "Login"

        val fragment = activity?.supportFragmentManager?.beginTransaction()
        fragment?.replace(R.id.fragment_container, LoginFragment())?.commit()
        return inflater.inflate(R.layout.fragment_login, container, false)
    }
}