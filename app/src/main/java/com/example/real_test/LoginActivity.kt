package com.example.real_test

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var welcomeBack: TextView
    private lateinit var editTextText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var textView3: TextView
    private lateinit var textView4:TextView
    private lateinit var button_login: Button
    private lateinit var textButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        welcomeBack = findViewById(R.id.welcomeBack)
        editTextText = findViewById(R.id.editTextText)
        passwordEditText = findViewById(R.id.passwordEditText)
        textView4 = findViewById(R.id.textView4)
        confirmPasswordEditText= findViewById(R.id.confirmPasswordEditText)
        textView3 = findViewById(R.id.textView3)
       button_login  = findViewById<Button>(R.id.button_login)
        textButton = findViewById(R.id.textButton)

        welcomeBack.text = "Bienvenue parmi nous!"
        textView3.text = "Login"
        textView4.text ="vous avez oubliez votre mot de passe?"

        textButton.setOnClickListener {
            startActivity(Intent(this, CreateAccountActivity::class.java))
        }

        button_login.setOnClickListener {
            val EditText=editTextText.text.toString()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            if (password == confirmPassword) {
                // Logique pour créer un compte
                welcomeBack.text = "Bienvenue parmi nous!"
                // Rediriger l'utilisateur vers un autre écran après 2 secondes
                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(Intent(this, SessionActivity::class.java))
                    finish()
                }, 2000)

            } else {
                welcomeBack.text = "Les mots de passe ne correspondent pas. Veuillez réessayer."
            }
        }
    }
}
