package com.example.real_test

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.real_test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var logoName:TextView
    private lateinit var nextArrow:ImageView

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageView = binding.imageView
        logoName = binding.logoName
        nextArrow=binding.nextArrow

        // Exemple d'utilisation
        logoName.text = "anti gaspi"
        imageView.setImageResource(R.drawable.logo)

        // Rendre la flèche invisible au début
        nextArrow.visibility = View.INVISIBLE

        // Afficher la flèche après 3 secondes
        Handler(Looper.getMainLooper()).postDelayed({
            nextArrow.visibility = View.VISIBLE

            // Ajouter le clic listener à la flèche
            nextArrow.setOnClickListener {
                // Lancer la nouvelle activité
                startActivity(Intent(this, WelcomeActivity::class.java))
            }
        }, 3000) // 3 secondes
    }
    }

