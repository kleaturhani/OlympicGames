package it.programmazionemobile.olympicgames2024.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import it.programmazionemobile.olympicgames2024.databinding.ItemCardBinding

class EventsFragment : Fragment() {

    private lateinit var binding: ItemCardBinding
    private var emojiText: String? = null
    private var titleText: String? = null
    private var itemeventImage: Int = 0

    @Suppress("DEPRECATION")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ItemCardBinding.inflate(inflater, container, false)

        // Recupera i dati dalla navigazione o da un'altra fonte
        arguments?.let {
            emojiText = it.getString("Emoji")
            titleText = it.getString("Title")
            itemeventImage = it.getInt("Image_resource")

        }

        //Imposta il titolo e l'immagine
        binding.eventEmoji.text = emojiText
        binding.eventText.text = titleText


        return binding.root
    }


    companion object {
        fun newInstance(
            emojiText: String,
            titleText: String,
            itemeventImage: Int,
        ): EventsFragment {
            val fragment = EventsFragment()
            val args = Bundle()
            args.putString("Emoji", emojiText)
            args.putString("Title", titleText)
            args.putInt("Image", itemeventImage)
            fragment.arguments = args
            return fragment
        }
    }
}