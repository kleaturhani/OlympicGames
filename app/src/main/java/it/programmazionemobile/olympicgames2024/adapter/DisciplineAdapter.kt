package it.programmazionemobile.olympicgames2024.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import it.programmazionemobile.olympicgames2024.R
import it.programmazionemobile.olympicgames2024.data.model.Discipline


class DisciplineAdapter(
    private val disciplines: ArrayList<Discipline>,
    private val onItemClick: (Discipline) -> Unit
) :
    RecyclerView.Adapter<DisciplineAdapter.DisciplineViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DisciplineAdapter.DisciplineViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_discipline,
            parent, false
        )
        return DisciplineViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: DisciplineViewHolder, position: Int) {
        val currentItem = disciplines[position]
        holder.ItemImage.setImageResource(currentItem.imageResId)
        holder.ItemText.text = currentItem.name

        // Gestisci il click
        holder.itemView.setOnClickListener {
            onItemClick(currentItem)
        }

    }

    override fun getItemCount(): Int {
        return disciplines.size
    }

    class DisciplineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ItemImage: ImageView = itemView.findViewById(R.id.discipline_image)
        var ItemText: TextView = itemView.findViewById(R.id.discipline_name)

        init {
            ItemImage = itemView.findViewById(R.id.discipline_image)
            ItemText = itemView.findViewById(R.id.discipline_name)
        }

    }
}