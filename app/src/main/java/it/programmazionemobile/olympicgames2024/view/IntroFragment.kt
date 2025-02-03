package it.programmazionemobile.olympicgames2024.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import it.programmazionemobile.olympicgames2024.R
import it.programmazionemobile.olympicgames2024.databinding.FragmentIntroBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class IntroFragment : Fragment() {

    private lateinit var binding: FragmentIntroBinding


    override fun onStart() {
        super.onStart()
        val activity = activity as? IntroActivity
        activity?.supportActionBar?.hide()
    }


    override fun onStop() {
        super.onStop()
        // Safe casting with null check
        val activity = activity as? IntroActivity
        activity?.supportActionBar?.show()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIntroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

// Ottengo un riferimento al NavigationController
        val navController = requireView().findNavController()

        viewLifecycleOwner.lifecycleScope.launch {
            delay(2000)
            if (isAdded && findNavController().currentDestination?.id == R.id.introFragment) {
                findNavController().navigate(R.id.action_introFragment_to_loginFragment)
            }
        }
    }


}