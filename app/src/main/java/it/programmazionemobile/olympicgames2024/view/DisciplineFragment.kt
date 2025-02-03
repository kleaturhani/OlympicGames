package it.programmazionemobile.olympicgames2024.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import it.programmazionemobile.olympicgames2024.R
import it.programmazionemobile.olympicgames2024.adapter.EventsAdapter
import it.programmazionemobile.olympicgames2024.data.model.Events
import it.programmazionemobile.olympicgames2024.data.model.ResultsEntity
import it.programmazionemobile.olympicgames2024.databinding.FragmentDisciplineBinding


class DisciplineFragment : Fragment() {

    private lateinit var binding: FragmentDisciplineBinding
    private lateinit var eventsAdapter: EventsAdapter

    private var itemImage: Int = 0
    private var itemText: String? = null
    private var events: ArrayList<Events> = arrayListOf()

    private var resultsMap: Map<String, List<ResultsEntity>> = mapOf()

    @Suppress("DEPRECATION")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDisciplineBinding.inflate(inflater, container, false)

        // Recupera i dati dalla navigazione o da un'altra fonte
        arguments?.let {
            itemImage = it.getInt("Image_resource")
            itemText = it.getString("Discipline_name")
            events = it.getSerializable("Events_List") as ArrayList<Events>
        }

        //Imposta il titolo e l'immagine
        binding.toolbarTitle.text = itemText
        binding.disciplineImage.setImageResource(itemImage)

        // Recupera gli eventi e i risultati dalla navigazione o da altre fonti
        arguments?.let {
            events = it.getSerializable("Events_List") as ArrayList<Events>

            // Esempio di risultati per ogni evento
            resultsMap = mapOf(
                "Partita Semifinale Maschile" to listOf(
                    ResultsEntity(
                        id = "1",
                        discipline = "3x3 Basketball",
                        matchName = "Partita Semifinale Maschile",
                        matchSquadsName = "USA vs FRA",
                        finalPoints = "21 - 19",
                        eventStatus = "Finished",
                        matchFlags = "🇺🇸, 🇫🇷",
                        gender = "Maschile",
                        formattedDate = "2024-07-27"
                    )
                ),
                "Partita Finale Femminile" to listOf(
                    ResultsEntity(
                        id = "2",
                        discipline = "3x3 Basketball",
                        matchName = "Partita Finale Femminile",
                        matchSquadsName = "CAN vs JPN",
                        finalPoints = "18 - 15",
                        eventStatus = "Finished",
                        matchFlags = "🇨🇦, 🇯🇵",
                        gender = "Femminile",
                        formattedDate = "2024-07-28"
                    )
                ),
                "Partita per il Bronzo Maschile" to listOf(
                    ResultsEntity(
                        id = "3",
                        discipline = "3x3 Basketball",
                        matchName = "Partita per il Bronzo Maschile",
                        matchSquadsName = "AUS vs ESP",
                        finalPoints = "17 - 15",
                        eventStatus = "Finished",
                        matchFlags = "🇦🇺, 🇪🇸",
                        gender = "Maschile",
                        formattedDate = "2024-07-26"
                    )
                ),
                "Individuale Maschile" to listOf(
                    ResultsEntity(
                        id = "4",
                        discipline = "Tiro con l'arco",
                        matchName = "Individuale Maschile",
                        matchSquadsName = "USA vs KOR",
                        finalPoints = "6 - 4",
                        eventStatus = "Finished",
                        matchFlags = "🇺🇸, 🇰🇷",
                        gender = "Maschile",
                        formattedDate = "2024-07-25"
                    )
                ),
                "Individuale Femminile" to listOf(
                    ResultsEntity(
                        id = "5",
                        discipline = "Tiro con l'arco",
                        matchName = "Individuale Femminile",
                        matchSquadsName = "MEX vs CHN",
                        finalPoints = "7 - 3",
                        eventStatus = "Finished",
                        matchFlags = "🇲🇽, 🇨🇳",
                        gender = "Femminile",
                        formattedDate = "2024-07-26"
                    )
                ),
                "Squadra Mista" to listOf(
                    ResultsEntity(
                        id = "6",
                        discipline = "Tiro con l'arco",
                        matchName = "Squadra Mista",
                        matchSquadsName = "JPN vs ITA",
                        finalPoints = "5 - 5 (Shoot-off)",
                        eventStatus = "Finished",
                        matchFlags = "🇯🇵, 🇮🇹",
                        gender = "Mista",
                        formattedDate = "2024-07-27"
                    )
                ),
                "Squadra Maschile e Femminile" to listOf(
                    ResultsEntity(
                        id = "7",
                        discipline = "Tiro con l'arco",
                        matchName = "Squadra Maschile e Femminile",
                        matchSquadsName = "FRA vs GBR",
                        finalPoints = "6 - 2",
                        eventStatus = "Finished",
                        matchFlags = "🇫🇷, 🇬🇧",
                        gender = "Femminile",
                        formattedDate = "2024-07-28"
                    )
                ),
                "Corpo Libero Maschile" to listOf(
                    ResultsEntity(
                        id = "8",
                        discipline = "Ginnastica artistica",
                        matchName = "Corpo Libero Maschile",
                        matchSquadsName = "USA, JPN, GBR",
                        finalPoints = "14.700",
                        eventStatus = "Finished",
                        matchFlags = "🇺🇸, 🇯🇵, 🇬🇧",
                        gender = "Maschile",
                        formattedDate = "2024-07-28"
                    )
                ),
                "Volteggio Femminile" to listOf(
                    ResultsEntity(
                        id = "9",
                        discipline = "Ginnastica artistica",
                        matchName = "Volteggio Femminile",
                        matchSquadsName = "BRA, RUS, CHN",
                        finalPoints = "15.100",
                        eventStatus = "Finished",
                        matchFlags = "🇧🇷, 🇷🇺, 🇨🇳",
                        gender = "Femminile",
                        formattedDate = "2024-07-29"
                    )
                ),
                "Parallele Maschili" to listOf(
                    ResultsEntity(
                        id = "10",
                        discipline = "Ginnastica artistica",
                        matchName = "Parallele Maschili",
                        matchSquadsName = "GER, FRA, ITA",
                        finalPoints = "15.600",
                        eventStatus = "Finished",
                        matchFlags = "🇩🇪, 🇫🇷, 🇮🇹",
                        gender = "Maschile",
                        formattedDate = "2024-07-30"
                    )
                ),
                "Trave Femminile" to listOf(
                    ResultsEntity(
                        id = "11",
                        discipline = "Ginnastica artistica",
                        matchName = "Trave Femminile",
                        matchSquadsName = "USA, RUS, CHN",
                        finalPoints = "14.800",
                        eventStatus = "Finished",
                        matchFlags = "🇺🇸, 🇷🇺, 🇨🇳",
                        gender = "Femminile",
                        formattedDate = "2024-07-31"
                    )
                ),
                "Partita Finale Maschile" to listOf(
                    ResultsEntity(
                        id = "12",
                        discipline = "Pallavolo",
                        matchName = "Partita Finale Maschile",
                        matchSquadsName = "BRA vs ITA",
                        finalPoints = "3 - 2",
                        eventStatus = "Finished",
                        matchFlags = "🇧🇷, 🇮🇹",
                        gender = "Maschile",
                        formattedDate = "2024-08-04"
                    )
                ),
                "Partita Semifinale Femminile" to listOf(
                    ResultsEntity(
                        id = "13",
                        discipline = "Pallavolo",
                        matchName = "Partita Semifinale Femminile",
                        matchSquadsName = "USA vs CHN",
                        finalPoints = "3 - 1",
                        eventStatus = "Finished",
                        matchFlags = "🇺🇸, 🇨🇳",
                        gender = "Femminile",
                        formattedDate = "2024-08-03"
                    )
                ),
                "Partita per il Bronzo Femminile" to listOf(
                    ResultsEntity(
                        id = "14",
                        discipline = "Pallavolo",
                        matchName = "Partita per il Bronzo Femminile",
                        matchSquadsName = "NED vs RUS",
                        finalPoints = "3 - 0",
                        eventStatus = "Finished",
                        matchFlags = "🇳🇱, 🇷🇺",
                        gender = "Femminile",
                        formattedDate = "2024-08-02"
                    )
                ),
                "100m Stile Libero Maschile" to listOf(
                    ResultsEntity(
                        id = "15",
                        discipline = "Nuoto",
                        matchName = "100m Stile Libero Maschile",
                        matchSquadsName = "USA, AUS, FRA",
                        finalPoints = "47.58",
                        eventStatus = "Finished",
                        matchFlags = "🇺🇸, 🇦🇺, 🇫🇷",
                        gender = "Maschile",
                        formattedDate = "2024-07-25"
                    )
                ),
                "200m Rana Femminile" to listOf(
                    ResultsEntity(
                        id = "16",
                        discipline = "Nuoto",
                        matchName = "200m Rana Femminile",
                        matchSquadsName = "JPN, CHN, RUS",
                        finalPoints = "2:19.64",
                        eventStatus = "Finished",
                        matchFlags = "🇯🇵, 🇨🇳, 🇷🇺",
                        gender = "Femminile",
                        formattedDate = "2024-07-26"
                    )
                ),
                "400m Misti Individuali Femminile" to listOf(
                    ResultsEntity(
                        id = "17",
                        discipline = "Nuoto",
                        matchName = "400m Misti Individuali Femminile",
                        matchSquadsName = "HUN, USA, AUS",
                        finalPoints = "4:31.12",
                        eventStatus = "Finished",
                        matchFlags = "🇭🇺, 🇺🇸, 🇦🇺",
                        gender = "Femminile",
                        formattedDate = "2024-07-27"
                    )
                ),
                "Staffetta 4x100m Mista Maschile" to listOf(
                    ResultsEntity(
                        id = "18",
                        discipline = "Nuoto",
                        matchName = "Staffetta 4x100m Mista Maschile",
                        matchSquadsName = "USA, GBR, AUS",
                        finalPoints = "3:27.45",
                        eventStatus = "Finished",
                        matchFlags = "🇺🇸, 🇬🇧, 🇦🇺",
                        gender = "Maschile",
                        formattedDate = "2024-07-28"
                    )
                ),
                "Individuale Maschile" to listOf(
                    ResultsEntity(
                        id = "19",
                        discipline = "Golf",
                        matchName = "Individuale Maschile",
                        matchSquadsName = "USA, GBR, JPN",
                        finalPoints = "-12",
                        eventStatus = "Finished",
                        matchFlags = "🇺🇸, 🇬🇧, 🇯🇵",
                        gender = "Maschile",
                        formattedDate = "2024-08-01"
                    )
                ),
                "Individuale Femminile" to listOf(
                    ResultsEntity(
                        id = "20",
                        discipline = "Golf",
                        matchName = "Individuale Femminile",
                        matchSquadsName = "KOR, MEX, GER",
                        finalPoints = "-10",
                        eventStatus = "Finished",
                        matchFlags = "🇰🇷, 🇲🇽, 🇩🇪",
                        gender = "Femminile",
                        formattedDate = "2024-08-02"
                    )
                ),
                "Singolare Maschile" to listOf(
                    ResultsEntity(
                        id = "21",
                        discipline = "Tennis",
                        matchName = "Singolare Maschile",
                        matchSquadsName = "ESP vs ITA",
                        finalPoints = "6-4, 7-6",
                        eventStatus = "Finished",
                        matchFlags = "🇪🇸, 🇮🇹",
                        gender = "Maschile",
                        formattedDate = "2024-08-04"
                    )
                ),
                "Doppio Femminile" to listOf(
                    ResultsEntity(
                        id = "22",
                        discipline = "Tennis",
                        matchName = "Doppio Femminile",
                        matchSquadsName = "USA vs CZE",
                        finalPoints = "6-3, 6-4",
                        eventStatus = "Finished",
                        matchFlags = "🇺🇸, 🇨🇿",
                        gender = "Femminile",
                        formattedDate = "2024-08-03"
                    )
                ),
                "Doppio Misto" to listOf(
                    ResultsEntity(
                        id = "23",
                        discipline = "Tennis",
                        matchName = "Doppio Misto",
                        matchSquadsName = "FRA vs AUS",
                        finalPoints = "6-7, 7-5, 10-8",
                        eventStatus = "Finished",
                        matchFlags = "🇫🇷, 🇦🇺",
                        gender = "Mista",
                        formattedDate = "2024-08-02"
                    )
                ),
                "Lotta Libera Maschile 57kg" to listOf(
                    ResultsEntity(
                        id = "24",
                        discipline = "Lotta",
                        matchName = "Lotta Libera Maschile 57kg",
                        matchSquadsName = "USA vs IRN",
                        finalPoints = "5-3",
                        eventStatus = "Finished",
                        matchFlags = "🇺🇸, 🇮🇷",
                        gender = "Maschile",
                        formattedDate = "2024-08-05"
                    )
                ),
                "Lotta Greco-Romana Femminile 53kg" to listOf(
                    ResultsEntity(
                        id = "25",
                        discipline = "Lotta",
                        matchName = "Lotta Greco-Romana Femminile 53kg",
                        matchSquadsName = "JPN vs CHN",
                        finalPoints = "6-2",
                        eventStatus = "Finished",
                        matchFlags = "🇯🇵, 🇨🇳",
                        gender = "Femminile",
                        formattedDate = "2024-08-06"
                    )
                ),
                "Lotta Libera Maschile 74kg" to listOf(
                    ResultsEntity(
                        id = "26",
                        discipline = "Lotta",
                        matchName = "Lotta Libera Maschile 74kg",
                        matchSquadsName = "RUS vs USA",
                        finalPoints = "8-5",
                        eventStatus = "Finished",
                        matchFlags = "🇷🇺, 🇺🇸",
                        gender = "Maschile",
                        formattedDate = "2024-08-07"
                    )
                ),
                "Maschile" to listOf(
                    ResultsEntity(
                        id = "27",
                        discipline = "Judo",
                        matchName = "Maschile",
                        matchSquadsName = "FRA vs JPN",
                        finalPoints = "10-0",
                        eventStatus = "Finished",
                        matchFlags = "🇫🇷, 🇯🇵",
                        gender = "Maschile",
                        formattedDate = "2024-08-07"
                    )
                ),
                "Femminile" to listOf(
                    ResultsEntity(
                        id = "28",
                        discipline = "Judo",
                        matchName = "Femminile",
                        matchSquadsName = "CHN vs KOR",
                        finalPoints = "7-3",
                        eventStatus = "Finished",
                        matchFlags = "🇨🇳, 🇰🇷",
                        gender = "Femminile",
                        formattedDate = "2024-08-08"
                    )
                ),
                "Maschile" to listOf(
                    ResultsEntity(
                        id = "29",
                        discipline = "Taekwondo",
                        matchName = "Maschile",
                        matchSquadsName = "ESP vs ITA",
                        finalPoints = "15-12",
                        eventStatus = "Finished",
                        matchFlags = "🇪🇸, 🇮🇹",
                        gender = "Maschile",
                        formattedDate = "2024-08-09"
                    )
                ),
                "Femminile" to listOf(
                    ResultsEntity(
                        id = "30",
                        discipline = "Taekwondo",
                        matchName = "Femminile",
                        matchSquadsName = "GBR vs CHN",
                        finalPoints = "14-10",
                        eventStatus = "Finished",
                        matchFlags = "🇬🇧, 🇨🇳",
                        gender = "Femminile",
                        formattedDate = "2024-08-10"
                    )
                ),
                "Partita Finale Maschile" to listOf(
                    ResultsEntity(
                        id = "31",
                        discipline = "Rugby a 7",
                        matchName = "Partita Finale Maschile",
                        matchSquadsName = "NZL vs AUS",
                        finalPoints = "21-19",
                        eventStatus = "Finished",
                        matchFlags = "🇳🇿, 🇦🇺",
                        gender = "Maschile",
                        formattedDate = "2024-08-11"
                    )
                ),
                "Partita Finale Femminile" to listOf(
                    ResultsEntity(
                        id = "32",
                        discipline = "Rugby a 7",
                        matchName = "Partita Finale Femminile",
                        matchSquadsName = "FRA vs USA",
                        finalPoints = "28-14",
                        eventStatus = "Finished",
                        matchFlags = "🇫🇷, 🇺🇸",
                        gender = "Femminile",
                        formattedDate = "2024-08-10"
                    )
                ),
                "Partita per il Bronzo Maschile" to listOf(
                    ResultsEntity(
                        id = "33",
                        discipline = "Rugby a 7",
                        matchName = "Partita per il Bronzo Maschile",
                        matchSquadsName = "FIJ vs GBR",
                        finalPoints = "24-17",
                        eventStatus = "Finished",
                        matchFlags = "🇫🇯, 🇬🇧",
                        gender = "Maschile",
                        formattedDate = "2024-08-09"
                    )
                )
            )
        }

        // Configura la RecyclerView
        setupEventsRecyclerView()

        return binding.root
    }


    private fun setupEventsRecyclerView() {
        eventsAdapter = EventsAdapter(events) { event ->
            val transaction = parentFragmentManager.beginTransaction()

            // Recupera i risultati per l'evento selezionato
            val results = resultsMap[event.eventTitle] ?: listOf()

            // Crea un'istanza di ResultsDisciplineFragment e passa i risultati
            val resultsDisciplineFragment = ResultsDisciplineFragment()
            val bundle = Bundle().apply {
                putSerializable("Results_List", ArrayList(results))
            }
            resultsDisciplineFragment.arguments = bundle

            transaction.replace(R.id.nav_host_fragment, resultsDisciplineFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        binding.eventRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.eventRecyclerView.adapter = eventsAdapter
    }


    companion object {
        fun newInstance(
            itemImage: Int,
            itemText: String,
            events: List<Events>
        ): DisciplineFragment {
            val fragment = DisciplineFragment()
            val args = Bundle()
            args.putInt("Image_resource", itemImage)
            args.putString("Discipline_name", itemText)
            args.putSerializable(
                "Events_List",
                ArrayList(events)
            ) // Passa la lista di eventi come stringhe
            fragment.arguments = args
            return fragment
        }
    }

}