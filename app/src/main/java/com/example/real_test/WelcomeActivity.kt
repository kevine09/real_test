package com.example.real_test

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.real_test.databinding.WelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var welcomeMessage: TextView
    private lateinit var phrase_drole: TextView
    private lateinit var connection: Button
    private lateinit var createAccount: Button
    private lateinit var textView7: TextView
    private lateinit var gmailButton: Button
    private lateinit var facebookButton:Button

    private lateinit var binding: WelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        welcomeMessage = binding.welcomeMessage
        phrase_drole = binding.phraseDrole
        textView7=binding.textView7
        connection = binding.connection
        createAccount = binding.createAccount
        gmailButton=binding.gmailButton
        facebookButton=binding.facebookButton

        welcomeMessage.text = "Bienvenue sur Anti Gaspi 😊"
        phrase_drole.text = "Déjouer le gaspiège, un vrai jeu d'enfant"

        findViewById<Button>(R.id.connection).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        createAccount.setOnClickListener {
            startActivity(Intent(this, CreateAccountActivity::class.java))
        }
    }
}

