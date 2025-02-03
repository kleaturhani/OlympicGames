package it.programmazionemobile.olympicgames2024.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import it.programmazionemobile.olympicgames2024.data.model.ResultsEntity
import it.programmazionemobile.olympicgames2024.databinding.ItemResultsBinding

class ResultsAdapter(
    private var resultsList: List<ResultsEntity>,
    private val listener: Communicator,
    private val onLikeClick: (ResultsEntity) -> Unit
) : RecyclerView.Adapter<ResultsAdapter.ResultsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsViewHolder {
        val binding = ItemResultsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResultsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResultsViewHolder, position: Int) {
        val result = resultsList[position]
        holder.bind(result)
    }

    override fun getItemCount(): Int = resultsList.size

    fun updateData(newResults: List<ResultsEntity>) {
        resultsList = newResults
        notifyDataSetChanged()  // Notifica l'adapter che la lista è cambiata
    }

    inner class ResultsViewHolder(private val binding: ItemResultsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: ResultsEntity) {
            binding.resultsData = result
            binding.root.setOnClickListener {
                listener.passData(result.id)
            }
            binding.executePendingBindings()
        }
    }
}
