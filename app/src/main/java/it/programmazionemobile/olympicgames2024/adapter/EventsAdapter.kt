package it.programmazionemobile.olympicgames2024.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import it.programmazionemobile.olympicgames2024.R
import it.programmazionemobile.olympicgames2024.data.model.Events

class EventsAdapter(
    private val events: ArrayList<Events>,
    private val onItemClick: (Events) -> Unit
) :
    RecyclerView.Adapter<EventsAdapter.EventsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventsViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_card,
            parent, false
        )
        return EventsViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        val currentItem = events[position]
        holder.emojiEvent.text = currentItem.emojiText
        holder.titleEvent.text = currentItem.eventTitle

        // Gestisci il click
        holder.itemView.setOnClickListener {
            onItemClick(currentItem)
        }

    }

    override fun getItemCount(): Int {
        return events.size
    }


    class EventsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var emojiEvent: TextView = itemView.findViewById(R.id.event_emoji)
        var titleEvent: TextView = itemView.findViewById(R.id.event_text)

    }
}
