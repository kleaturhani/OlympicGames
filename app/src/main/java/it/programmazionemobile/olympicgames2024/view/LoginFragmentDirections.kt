package it.programmazionemobile.olympicgames2024.view

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import it.programmazionemobile.olympicgames2024.R.*


public class LoginFragmentDirections private constructor() {
    public companion object {
        public fun actionLoginFragmentToRegisterFragment(): NavDirections =
            ActionOnlyNavDirections(id.action_loginFragment_to_registerFragment)

        public fun actionLoginFragmentToMainActivity(): NavDirections =
            ActionOnlyNavDirections(id.action_loginFragment_to_mainActivity)
    }
}
