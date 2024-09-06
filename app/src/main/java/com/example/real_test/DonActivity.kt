package com.example.real_test

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.real_test.databinding.ActivityMainBinding
import kotlin.random.Random


// Assurez-vous d'avoir une classe Item
data class Item(val imageResId: Int, val description: String, val contact: String)

class DonActivity : AppCompatActivity() {

    private lateinit var expandableListView: ExpandableListView
    private lateinit var listAdapter: ExpandableListAdapter
    private lateinit var listDataHeader: List<String>
    private lateinit var listDataChild: HashMap<String, List<String>>
    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerAdapter
    private lateinit var bubble: View
    private lateinit var filterDrawer: FrameLayout
    private lateinit var useCurrentLocationSwitch: Switch
    private lateinit var distanceSeekBar: SeekBar
    private lateinit var distanceTextView: TextView
    private lateinit var applyFilterButton: Button
    private lateinit var closeFilterDrawer: ImageButton
    private lateinit var filterButton: FloatingActionButton
    private var isMoving = false
    private lateinit var bubbleTip: TextView
    private var tips = arrayOf(
        "N'hésitez pas à faire des dons réguliers, c'est le meilleur moyen de soutenir une cause sur le long terme.",
        "Pensez à la déduction fiscale, vos dons peuvent vous permettre de réduire vos impôts.",
        "Vous pouvez choisir de faire un don en ligne, c'est simple et rapide.",
        "Impliquez vos amis et famille, ensemble on peut faire une grande différence.",
        "Votre don, quel qu'il soit, aura un impact positif sur la vie des bénéficiaires.",
        "Planifier vos repas vous aide à réduire le gaspillage alimentaire.",
        "Les restes peuvent être de nouveaux repas, ne pensez pas toujours à la poubelle.",
        "Vos vieux vêtements peuvent servir de torchons de cuisine."
    )
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chercher_dons)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialisation des vues
        filterDrawer = findViewById(R.id.filterDrawer)
        expandableListView = findViewById(R.id.expandableListView)
        useCurrentLocationSwitch = findViewById(R.id.useCurrentLocationSwitch)
        distanceSeekBar = findViewById(R.id.distanceSeekBar)
        distanceTextView = findViewById(R.id.distanceTextView)
        applyFilterButton = findViewById(R.id.applyFilterButton)
        closeFilterDrawer = findViewById(R.id.closeFilterDrawer)
        filterButton = findViewById(R.id.filterButton)
        searchView = findViewById(R.id.searchView)
        recyclerView = findViewById(R.id.recyclerView)

        // Gestion de l'ouverture/fermeture du filtre latéral
        filterButton.setOnClickListener { filterDrawer.visibility = View.VISIBLE }
        closeFilterDrawer.setOnClickListener { filterDrawer.visibility = View.INVISIBLE }

        // Gestion du changement de distance
        distanceSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                distanceTextView.text = "$progress km"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        // Gestion de l'application du filtre
        applyFilterButton.setOnClickListener {
            // Récupérer les valeurs sélectionnées et les appliquer au filtre
            val useCurrentLocation = useCurrentLocationSwitch.isChecked
            val distance = distanceSeekBar.progress
            val selectedCategories = getSelectedCategories()
            // Appliquer le filtre ici
            filterDrawer.visibility = View.INVISIBLE
        }

        // Initialize listDataHeader and listDataChild
        prepareListData()

        // Convert listDataChild to HashMap<String, MutableList<String>>
        val mutableListDataChild = HashMap(listDataChild.mapValues { it.value.toMutableList() })

        // Préparez les données des éléments à afficher dans le RecyclerView
        val items = prepareItems()

        // Initialize recyclerAdapter and set it to the RecyclerView
        recyclerAdapter = RecyclerAdapter(listDataHeader.toMutableList(), mutableListDataChild, items)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerAdapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Perform the search and update the results
                performSearch(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Perform the search in real-time and update the results
                performSearch(newText)
                return true
            }
        })
    }

    private fun prepareItems(): MutableList<Item> {
        // Créez et retournez une liste d'éléments Item
        return mutableListOf(
            Item(R.drawable.boite_denrees, "Fruits", "Contact: 123-456-7890"),
            Item(R.drawable.boites2, "Vêtements", "Contact: 098-765-4321"),
            // Ajoutez d'autres éléments ici
        )
    }

    private fun performSearch(query: String?) {
        val filteredDataHeader = mutableListOf<String>()
        val filteredDataChild = HashMap<String, MutableList<String>>()

        if (query.isNullOrEmpty()) {
            // If the query is empty, display all items
            filteredDataHeader.addAll(listDataHeader)
            listDataChild.forEach { (key, value) ->
                filteredDataChild[key] = value.toMutableList()
            }
        } else {
            // Filter items based on the query
            listDataHeader.forEach { header ->
                if (header.lowercase().contains(query.lowercase())) {
                    filteredDataHeader.add(header)
                    filteredDataChild[header] =
                        listDataChild[header]?.toMutableList() ?: mutableListOf()
                } else {
                    // Check child items
                    val filteredChildren = mutableListOf<String>()
                    listDataChild[header]?.forEach { child ->
                        if (child.lowercase().contains(query.lowercase())) {
                            filteredChildren.add(child)
                        }
                    }
                    if (filteredChildren.isNotEmpty()) {
                        filteredDataHeader.add(header)
                        filteredDataChild[header] = filteredChildren
                    }
                }
            }
        }

        // Update the RecyclerView display
        fun updateData(filteredDataHeader: MutableList<String>, filteredDataChild: HashMap<String, MutableList<String>>, filteredItems: MutableList<Item>) {
            recyclerAdapter.updateData(filteredItems, filteredDataHeader, filteredDataChild)
        }
    }
    private fun prepareListData() {
        listDataHeader = listOf(
            "nourriture",
            "vêtements",
            "chaussures",
            "ustensiles de cuisine",
            "meubles",
            "appareils électroniques",
            "autres"
        )
        listDataChild = hashMapOf(
            "nourriture" to listOf(
                "fruits",
                "viandes et poissons",
                "pains et pâtisseries",
                "légumes",
                "produits laitiers et œufs",
                "épices et assaisonnements",
                "huiles",
                "autres"
            ),
            "vêtements" to listOf(
                "hommes",
                "femmes",
                "enfants filles",
                "enfants garçons",
                "nouveau-nés"
            ),
            "chaussures" to listOf("hommes", "femmes", "enfants"),
            "ustensiles de cuisine" to listOf(""),
            "meubles" to listOf(
                "chaises de bureau",
                "canapé",
                "lit",
                "matelas",
                "table",
                "autre"
            ),
            "appareils électroniques" to listOf(
                "frigo",
                "PC",
                "télévision",
                "ventilateur",
                "fer à repasser",
                "autre"
            )
        )
    }


    private fun startBubbleAnimation() {
        val animation = AnimationUtils.loadAnimation(this, R.anim.bubble_animation)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                isMoving = true
            }

            override fun onAnimationEnd(animation: Animation?) {
                isMoving = false
                bubble.startAnimation(animation)
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })
        bubble.startAnimation(animation)
    }

    private fun handleBubbleTouch(v: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (!isMoving) {
                    // L'utilisateur a appuyé sur la bulle, mais elle n'est pas en mouvement
                    showRandomTip()
                } else {
                    // L'utilisateur a appuyé sur la bulle pendant qu'elle est en mouvement
                    bubble.clearAnimation()
                    isMoving = false
                }
            }

            MotionEvent.ACTION_MOVE -> {
                if (!isMoving) {
                    // Déplacez la bulle manuellement en fonction des mouvements de l'utilisateur
                    val newX = (event.rawX - bubble.width / 2).toInt()
                    val newY = (event.rawY - bubble.height / 2).toInt()
                    val params = bubble.layoutParams as FrameLayout.LayoutParams
                    params.leftMargin = newX
                    params.topMargin = newY
                    bubble.layoutParams = params
                }
            }

            MotionEvent.ACTION_UP -> {
                if (!isMoving) {
                    // L'utilisateur a relâché la bulle, vous pouvez relancer l'animation
                    startBubbleAnimation()
                }
            }
        }
        return true
    }

    private fun showRandomTip() {
        val randomIndex = Random.nextInt(tips.size)
        bubbleTip.text = tips[randomIndex]
        bubbleTip.visibility = View.VISIBLE
    }

    private fun getSelectedCategories(): List<String> {
        val selectedCategories = mutableListOf<String>()
        for (i in 0 until expandableListView.count) {
            if (expandableListView.isGroupExpanded(i)) {
                selectedCategories.add(listDataHeader[i])
            }
        }
        return selectedCategories
    }

}



