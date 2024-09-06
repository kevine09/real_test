package com.example.real_test

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.random.Random

class SessionActivity : AppCompatActivity() {

    private lateinit var bubble: View
    private var isMoving = false
    private lateinit var bubbleTip: TextView
    private var tips = arrayOf(
        "N'hésitez pas à faire des dons réguliers, c'est le meilleur moyen de soutenir une cause sur le long terme.",
        "Pensez à la déduction fiscale, vos dons peuvent vous permettre de réduire vos impôts.",
        "Vous pouvez choisir de faire un don en ligne, c'est simple et rapide.",
        "Impliquez vos amis et famille, ensemble on peut faire une grande différence.",
        "Votre don, quel qu'il soit, aura un impact positif sur la vie des bénéficiaires.",
        "planifier vos repas vous aide a reduire le gaspillage alimentaire.",
        "les restes peuveunt etre de nouveau repas ne pensez pas toujours a la poubelle ",
        "vos vieux vetements peuvent servir de torchons de cuisine"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.session_main)

        // Configurer la BottomNavigationView
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }
                R.id.don -> {
                    startActivity(Intent(this, FaireDonsActivity::class.java))
                    // Gérer le clic sur l'élément "Wallet"
                    true
                }
                R.id.don2 -> {
                    startActivity(Intent(this, DonActivity::class.java))
                    // Gérer le clic sur l'élément "Analytics"
                    true
                }
                R.id.navigation_settings -> {
                    startActivity(Intent(this, SettingsActivity::class.java))
                    true
                }
                else -> false
            }  }


        bubble = findViewById(R.id.bubble)
        bubbleTip = findViewById(R.id.bubble_tip)

        startBubbleAnimation()

        val buttonDonate: Button = findViewById(R.id.Button_faire_don)
        val buttonSearch: Button = findViewById(R.id.search_don)

        buttonDonate.setOnClickListener {
            startActivity(Intent(this, FaireDonsActivity::class.java))
        }

        buttonSearch.setOnClickListener {
            startActivity(Intent(this, DonActivity::class.java))
        }

        bubble.setOnClickListener {
            showRandomTip()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        return when (item.itemId) {
            R.id.don -> {
                startActivity(Intent(this, DonActivity::class.java))
                true
            }
            R.id.voir -> {
                startActivity(Intent(this, FaireDonsActivity::class.java))
                true
            }
            R.id.activity -> {
                startActivity(Intent(this, DonActivity::class.java))
                true
            }
            R.id.action_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            R.id.action_home -> {
                startActivity(Intent(this, MainActivity::class.java))
                true
            }
            R.id.action_profile -> {
                startActivity(Intent(this, profileActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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
}