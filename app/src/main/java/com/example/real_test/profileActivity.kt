package com.example.real_test

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.content.Intent
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class profileActivity: AppCompatActivity()  {
    private lateinit var profileImage: ImageView
    private lateinit var textName: TextView
    private lateinit var textPhone: TextView
    private lateinit var textEmail: TextView
    private lateinit var textLocation: TextView
    private lateinit var buttonEdit: Button
    private lateinit var buttonDelete: ImageButton
    private lateinit var buttonShare: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        profileImage = findViewById(R.id.profile_image)
        textName = findViewById(R.id.text_name)
        textPhone = findViewById(R.id.text_phone)
        textEmail = findViewById(R.id.text_email)
        textLocation = findViewById(R.id.text_location)
        buttonEdit = findViewById(R.id.buttonEdit)
        buttonDelete = findViewById(R.id.button_delete)
        buttonShare = findViewById(R.id.button_share)

        // Charger les informations de profil depuis les préférences partagées ou une base de données
        loadProfileInfo()

        buttonEdit.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }

        buttonDelete.setOnClickListener {
            deleteProfile()
        }

        buttonShare.setOnClickListener {
            // Code pour partager le profil
        }
    }

    private fun loadProfileInfo() {
        val sharedPreferences = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        textName.text = sharedPreferences.getString("name", "")
        textPhone.text = sharedPreferences.getString("phone", "")
        textEmail.text = sharedPreferences.getString("email", "")
        textLocation.text = sharedPreferences.getString("location", "")
    }
    private fun deleteProfile() {
        val sharedPreferences = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
        finish()
    }
}
