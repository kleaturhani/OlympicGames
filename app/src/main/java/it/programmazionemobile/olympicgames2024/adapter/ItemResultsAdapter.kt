package it.programmazionemobile.olympicgames2024.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import it.programmazionemobile.olympicgames2024.R
import it.programmazionemobile.olympicgames2024.data.model.ResultsEntity
import it.programmazionemobile.olympicgames2024.database.ResultsDatabase
import it.programmazionemobile.olympicgames2024.databaseInterface.ResultsDao
import it.programmazionemobile.olympicgames2024.databinding.ItemResultsBinding
import it.programmazionemobile.olympicgames2024.viewmodel.ItemResultsViewModel
import it.programmazionemobile.olympicgames2024.viewmodel.ItemResultsViewModelFactory

class ItemResultsAdapter(
    private var mList: List<ResultsEntity>,
    private val viewModel: ItemResultsViewModel
) : RecyclerView.Adapter<ItemResultsAdapter.ViewHolder>() {

    private lateinit var resultsDao: ResultsDao
    private val itemResultsViewModel: ItemResultsViewModel by lazy {
        ItemResultsViewModelFactory(resultsDao).create(ItemResultsViewModel::class.java)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemResultsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        // Inizializzo il database e il DAO
        val database = ResultsDatabase.getDatabase(parent.context)
        resultsDao = database.resultsDao()

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentResult = mList[position]
        holder.bind(currentResult)
    }

    override fun getItemCount(): Int = mList.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<ResultsEntity>) {
        mList = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemResultsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: ResultsEntity) {
            binding.resultsData = result
            binding.viewModel = viewModel

            updateLikeButton(binding.likeButton, result.isFavourite)

            // Gestisci il click sul pulsante "Mi Piace"
            binding.likeButton.setOnClickListener {
                viewModel.toggleLikeButtonPressed(result) // Cambia lo stato
                //updateLikeButton(binding.likeButton, result.isFavourite)
                notifyItemChanged(adapterPosition)
            }
            updateLikeButton(binding.likeButton, result.isFavourite)
            binding.executePendingBindings()
        }
    }

    // Funzione per aggiornare il colore o l'icona del pulsante "Mi Piace"
    private fun updateLikeButton(likeButton: View, isFavourite: Boolean) {
        if (isFavourite) {
            likeButton.setBackgroundResource(R.drawable.icon_heart_red)
        } else {
            likeButton.setBackgroundResource(R.drawable.icon_heart_empty)
        }
    }


}
