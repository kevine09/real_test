package com.example.real_test

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var fullNameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var createAccountButton: Button
    private lateinit var welcomeMessageTextView: TextView
    private lateinit var edit_phone:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.account)

        fullNameEditText = findViewById(R.id.fullNameEditText)
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText)
        createAccountButton = findViewById(R.id.createAccountButton)
        welcomeMessageTextView = findViewById(R.id.welcomeMessageTextView)
        edit_phone=findViewById(R.id.edit_phone)

        createAccountButton.setOnClickListener {
            val fullName = fullNameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()
            saveProfileInfo()

            if (password == confirmPassword) {
                // Logique pour créer un compte
                welcomeMessageTextView.text = "Merci de nous rejoindre, $fullName ! Nous sommes ravis de vous accueillir dans notre communauté. Votre aventure commence ici. 😊"
                // Rediriger l'utilisateur vers un autre écran après 3 secondes
                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(Intent(this, SessionActivity::class.java))
                    finish()
                }, 3000)

            } else {
                welcomeMessageTextView.text = "Les mots de passe ne correspondent pas. Veuillez réessayer."
            }
        }
    }
    private fun saveProfileInfo() {
        val sharedPreferences = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("name", fullNameEditText.text.toString())
        editor.putString("email", emailEditText.text.toString())
        editor.putString("phone", edit_phone.text.toString())
        editor.apply()
    }
}
