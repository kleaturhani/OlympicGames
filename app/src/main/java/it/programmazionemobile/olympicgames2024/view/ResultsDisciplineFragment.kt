package it.programmazionemobile.olympicgames2024.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import it.programmazionemobile.olympicgames2024.R
import it.programmazionemobile.olympicgames2024.adapter.ItemResultsAdapter
import it.programmazionemobile.olympicgames2024.data.model.Events
import it.programmazionemobile.olympicgames2024.data.model.ResultsEntity
import it.programmazionemobile.olympicgames2024.database.ResultsDatabase
import it.programmazionemobile.olympicgames2024.databaseInterface.ResultsDao
import it.programmazionemobile.olympicgames2024.databinding.FragmentResultsBinding
import it.programmazionemobile.olympicgames2024.databinding.ItemCardBinding
import it.programmazionemobile.olympicgames2024.databinding.ItemResultsBinding
import it.programmazionemobile.olympicgames2024.viewmodel.ItemResultsViewModel
import it.programmazionemobile.olympicgames2024.viewmodel.ItemResultsViewModelFactory

class ResultsDisciplineFragment : Fragment() {

    private lateinit var resultsDao: ResultsDao
    private lateinit var binding: FragmentResultsBinding
    private lateinit var bindingcard: ItemCardBinding
    private lateinit var bindingresults: ItemResultsBinding
    private lateinit var adapter: ItemResultsAdapter
    private var results: ArrayList<ResultsEntity> = arrayListOf()
    private val itemResultsViewModel: ItemResultsViewModel by lazy {
        // Factory class constructor
        ItemResultsViewModelFactory(resultsDao).create(ItemResultsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inizializza il database e il DAO
        val database = ResultsDatabase.getDatabase(requireContext())
        resultsDao = database.resultsDao()

        arguments?.let {
            val selectedEvent = it.getSerializable("Selected_Event") as? Events
            selectedEvent?.let { event ->
                // Imposta l'immagine nel layout
                binding.disciplineImage.setImageResource(event.eventImage)
                binding.toolbarTitle.text = event.eventTitle  // Aggiorna il titolo della toolbar
            }
        }
    }


    @Suppress("DEPRECATION")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentResultsBinding.inflate(inflater, container, false)
        bindingcard = ItemCardBinding.inflate(inflater, container, false)
        bindingresults = ItemResultsBinding.inflate(inflater, container, false)
        binding.toolbarTitle.text = bindingcard.eventText.text
        binding.viewModel = itemResultsViewModel
        binding.lifecycleOwner = viewLifecycleOwner


        arguments?.let {
            results =
                it.getSerializable("Results_List") as? ArrayList<ResultsEntity> ?: arrayListOf()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupResultsRecyclerView()
    }


    private fun setupResultsRecyclerView() {
        adapter = ItemResultsAdapter(
            mList = results, // Lista di dati
            viewModel = itemResultsViewModel // ViewModel
        )

        binding.resultsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.resultsRecyclerView.adapter = adapter // Imposta l'adapter
    }


    private fun navigateToDetails(id: String) {
        // Navigazione al dettaglio del risultato selezionato
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





