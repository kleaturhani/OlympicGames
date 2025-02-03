package it.programmazionemobile.olympicgames2024.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import it.programmazionemobile.olympicgames2024.R
import it.programmazionemobile.olympicgames2024.adapter.DisciplineAdapter
import it.programmazionemobile.olympicgames2024.data.model.Discipline
import it.programmazionemobile.olympicgames2024.data.model.Events
import it.programmazionemobile.olympicgames2024.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: DisciplineAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configura il RecyclerView
        val disciplines = arrayListOf(
            Discipline(R.drawable.basketball_img, "3x3 Basketball"),
            Discipline(R.drawable.archery_img, "Tiro con l'arco"),
            Discipline(R.drawable.ginnastica_img, "Ginnastica artistica"),
            Discipline(R.drawable.pallavolo_img, "Pallavolo"),
            Discipline(R.drawable.nuoto_img, "Nuoto"),
            Discipline(R.drawable.golf_img, "Golf"),
            Discipline(R.drawable.tennis_img, "Tennis"),
            Discipline(R.drawable.lotta_img, "Lotta"),
            Discipline(R.drawable.judo_img, "Judo"),
            Discipline(R.drawable.taekwondo_img, "Taekwondo"),
            Discipline(R.drawable.rugbya7_img, "Rugby a 7")
        )

        // Definisci gli eventi per ciascuna disciplina
        val basketballEvents = arrayListOf(
            Events("🏀", "Partita Semifinale Maschile", R.drawable.basketball3x3_img),
            Events("🏀", "Partita Finale Femminile", R.drawable.basketball3x3_img),
            Events("🏀", "Partita per il Bronzo Maschile", R.drawable.basketball3x3_img)
        )
        val archeryEvents = arrayListOf(
            Events("🏹", "Individuale Maschile", R.drawable.individualemaschile_img),
            Events("🏹", "Individuale Femminile", R.drawable.individualefemminile),
            Events("🏹", "Squadra Mista", R.drawable.tiroconlarco),
            Events("🏹", "Squadra Maschile e Femminile", R.drawable.tiroconlarco)
        )
        val gymnasticsEvents = arrayListOf(
            Events("🤸‍♂️", "Corpo Libero Maschile", R.drawable.corpoliberomaschile),
            Events("🤸‍♀️", "Volteggio Femminile", R.drawable.volteggiof),
            Events("🤸‍♂️", "Parallele Maschili", R.drawable.corpoliberom),
            Events("🤸‍♀️", "Trave Femminile", R.drawable.travef)
        )

        val volleyballEvents = arrayListOf(
            Events("🏐", "Partita Finale Maschile", R.drawable.volleyball),
            Events("🏐", "Partita Semifinale Femminile", R.drawable.volleyball),
            Events("🏐", "Partita per il Bronzo Femminile", R.drawable.volleyball)
        )

        val swimEvents = arrayListOf(
            Events("🏊‍♂️", "100m Stile Libero Maschile", R.drawable.swim100m),
            Events("🏊‍♀️", "200m Rana Femminile", R.drawable.swim100mf),
            Events("🏊‍♀️", "400m Misti Individuali Femminile", R.drawable.swim400m),
            Events("🏊‍♂️", "Staffetta 4x100m Mista Maschile", R.drawable.swim4x100m)
        )

        val golfEvents = arrayListOf(
            Events("⛳", "Individuale Maschile", R.drawable.golfm),
            Events("⛳", "Individuale Femminile", R.drawable.golff),
        )

        val tennisEvents = arrayListOf(
            Events("🎾", "Singolare Maschile", R.drawable.tennism),
            Events("🎾", "Doppio Femminile", R.drawable.tennisf),
            Events("🎾", "Doppio Misto", R.drawable.tennisdm),
        )

        val lottaEvents = arrayListOf(
            Events("🤼‍♂️", "Lotta Libera Maschile 57kg", R.drawable.lottam),
            Events("🤼‍♀️", "Lotta Greco-Romana Femminile 53kg", R.drawable.lottaf),
            Events("🤼‍♂️", "Lotta Libera Maschile 74kg", R.drawable.lottam)
        )

        val judoEvents = arrayListOf(
            Events("🥋", "Maschile", R.drawable.judom),
            Events("🥋", "Femminile", R.drawable.judof)
        )

        val taekwondoEvents = arrayListOf(
            Events("🥋", "Maschile", R.drawable.taekwondom),
            Events("🥋", "Femminile", R.drawable.taekwondom)
        )

        val rugbyEvents = arrayListOf(
            Events("🏉", "Partita Finale Maschile", R.drawable.rugbym),
            Events("🏉", "Partita Finale Femminile", R.drawable.rugbyf),
            Events("🏉", "Partita per il Bronzo Maschile", R.drawable.rugbym)

        )



        adapter = DisciplineAdapter(disciplines) { discipline ->
            val transaction = parentFragmentManager.beginTransaction()

            // Crea un'istanza del DisciplineFragment in base alla disciplina selezionata
            val disciplineFragment = when (discipline.name) {
                "3x3 Basketball" -> {
                    DisciplineFragment.newInstance(
                        itemImage = R.drawable.basketball_img,
                        itemText = "3X3 Basketball",
                        events = basketballEvents
                    )
                }

                "Tiro con l'arco" -> {
                    DisciplineFragment.newInstance(
                        itemImage = R.drawable.archery_img,
                        itemText = "Tiro con l'arco",
                        events = archeryEvents
                    )
                }

                "Ginnastica artistica" -> {
                    DisciplineFragment.newInstance(
                        itemImage = R.drawable.ginnastica_img,
                        itemText = "Ginnastica artistica",
                        events = gymnasticsEvents
                    )
                }

                "Pallavolo" -> {
                    DisciplineFragment.newInstance(
                        itemImage = R.drawable.pallavolo_img,
                        itemText = "Pallavolo",
                        events = volleyballEvents
                    )
                }

                "Nuoto" -> {
                    DisciplineFragment.newInstance(
                        itemImage = R.drawable.nuoto_img,
                        itemText = "Nuoto",
                        events = swimEvents
                    )
                }

                "Golf" -> {
                    DisciplineFragment.newInstance(
                        itemImage = R.drawable.golf_img,
                        itemText = "Golf",
                        events = golfEvents
                    )
                }

                "Tennis" -> {
                    DisciplineFragment.newInstance(
                        itemImage = R.drawable.tennis_img,
                        itemText = "Tennis",
                        events = tennisEvents
                    )
                }

                "Lotta" -> {
                    DisciplineFragment.newInstance(
                        itemImage = R.drawable.lotta_img,
                        itemText = "Lotta",
                        events = lottaEvents
                    )
                }

                "Judo" -> {
                    DisciplineFragment.newInstance(
                        itemImage = R.drawable.judo_img,
                        itemText = "Judo",
                        events = judoEvents
                    )
                }

                "Taekwondo" -> {
                    DisciplineFragment.newInstance(
                        itemImage = R.drawable.taekwondo_img,
                        itemText = "Taekwondo",
                        events = taekwondoEvents
                    )
                }

                "Rugby a 7" -> {
                    DisciplineFragment.newInstance(
                        itemImage = R.drawable.rugbya7_img,
                        itemText = "Rugby a 7",
                        events = rugbyEvents
                    )
                }

                else -> {
                    null
                }
            }

            // Se la disciplina esiste, sostituisci il fragment
            disciplineFragment?.let {
                transaction.replace(R.id.nav_host_fragment, it)
                transaction.addToBackStack(null)
                transaction.commit()
            }

        }

        binding.disciplineRecyclerView.adapter = adapter
        binding.disciplineRecyclerView.layoutManager = LinearLayoutManager(requireContext())

    }

}