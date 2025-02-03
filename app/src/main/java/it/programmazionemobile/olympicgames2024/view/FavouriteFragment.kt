package it.programmazionemobile.olympicgames2024.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import it.programmazionemobile.olympicgames2024.R
import it.programmazionemobile.olympicgames2024.adapter.ItemResultsAdapter
import it.programmazionemobile.olympicgames2024.database.ResultsDatabase
import it.programmazionemobile.olympicgames2024.databaseInterface.ResultsDao
import it.programmazionemobile.olympicgames2024.databinding.FragmentFavouriteBinding
import it.programmazionemobile.olympicgames2024.viewmodel.ItemResultsViewModel
import it.programmazionemobile.olympicgames2024.viewmodel.ItemResultsViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavouriteFragment : Fragment() {

    private lateinit var favouriteAdapter: ItemResultsAdapter
    private val resultsDao: ResultsDao by lazy {
        ResultsDatabase.getDatabase(requireContext())
            .resultsDao() // Inizializza il database e ottieni resultsDao
    }

    // Passa ResultsDao alla factory
    private val itemResultsViewModel: ItemResultsViewModel by viewModels {
        ItemResultsViewModelFactory(resultsDao) // Passa il resultsDao
    }

    private lateinit var binding: FragmentFavouriteBinding

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouriteBinding.inflate(inflater, container, false)

        // Configura la RecyclerView
        setupFavouriteRecyclerView()

        // Osserva la lista dei preferiti nel ViewModel
        itemResultsViewModel.favouriteResults.observe(viewLifecycleOwner) { favourites ->
            if (favourites.isEmpty()) {
                binding.tvEmptyFavouriteList.visibility = View.VISIBLE
                binding.rvFavouriteList.visibility = View.GONE
            } else {
                binding.tvEmptyFavouriteList.visibility = View.GONE
                binding.rvFavouriteList.visibility = View.VISIBLE

                // Fetch data in the background
                lifecycleScope.launch(Dispatchers.IO) {
                    val manualFavourites = resultsDao.getFavouriteResults()
                    withContext(Dispatchers.Main) {
                        favouriteAdapter.submitList(manualFavourites)
                        favouriteAdapter.notifyDataSetChanged()  // Force RecyclerView to refresh
                    }
                }
            }
        }
        return binding.root
    }

    private fun setupFavouriteRecyclerView() {
        favouriteAdapter = ItemResultsAdapter(emptyList(), itemResultsViewModel)

        binding.rvFavouriteList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFavouriteList.adapter = favouriteAdapter
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

