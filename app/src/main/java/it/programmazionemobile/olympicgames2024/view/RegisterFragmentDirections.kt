package it.programmazionemobile.olympicgames2024.view

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import it.programmazionemobile.olympicgames2024.R

public class RegisterFragmentDirections private constructor() {
    public companion object {
        public fun actionRegisterFragmentToLoginFragment(): NavDirections =
            ActionOnlyNavDirections(R.id.action_registerFragment_to_loginFragment)
    }
}