package it.programmazionemobile.olympicgames2024.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import it.programmazionemobile.olympicgames2024.R
import it.programmazionemobile.olympicgames2024.adapter.Communicator
import it.programmazionemobile.olympicgames2024.adapter.ResultsAdapter
import it.programmazionemobile.olympicgames2024.database.ResultsDatabase
import it.programmazionemobile.olympicgames2024.databinding.FragmentSearchBinding
import it.programmazionemobile.olympicgames2024.repository.ResultsRepository
import it.programmazionemobile.olympicgames2024.viewmodel.DisciplineResultsViewModel
import it.programmazionemobile.olympicgames2024.viewmodel.ViewModelFactory


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: DisciplineResultsViewModel
    private lateinit var resultsAdapter: ResultsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        // Setup ViewModel
        val database = ResultsDatabase.getDatabase(requireContext())
        val repository = ResultsRepository(database.resultsDao())
        val factory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[DisciplineResultsViewModel::class.java]

        // Setup RecyclerView
        setupRecyclerView()

        // Button Search Click Listener
        binding.btnSearch.setOnClickListener {
            val selectedGender = when (binding.rgGenderFilter.checkedRadioButtonId) {
                R.id.rbMale -> "Maschile"
                R.id.rbFemale -> "Femminile"
                R.id.rbAll -> "Tutti"
                else -> ""
            }

            // Usa il metodo con MockList per cercare i risultati
            viewModel.searchByGenderWithMockList(selectedGender)

        }

        // Observe Search Results from ViewModel
        viewModel.searchResults.observe(viewLifecycleOwner) { results ->
            if (results.isNotEmpty()) {
                resultsAdapter.updateData(results)
            } else {
                Toast.makeText(requireContext(), "Nessun risultato trovato.", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        return binding.root
    }

    private fun setupRecyclerView() {
        resultsAdapter = ResultsAdapter(emptyList(),
            object : Communicator {
                override fun passData(id: String) {
                    navigateToDetails(id)
                }
            },
            onLikeClick = {}
        )

        binding.rvSearchResultsrecyclerview.apply {
            adapter = resultsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun navigateToDetails(id: String) {
        val fragment = ResultsDisciplineFragment()
        val bundle = Bundle()
        bundle.putString("result_id", id)
        fragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
