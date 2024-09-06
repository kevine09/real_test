package com.example.real_test

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EditProfileActivity : AppCompatActivity() {

    private lateinit var editName: EditText
    private lateinit var editPhone: EditText
    private lateinit var editEmail: EditText
    private lateinit var editLocation: EditText
    private lateinit var buttonUpdate: Button
    private lateinit var text1: TextView
    private lateinit var profileImage: ImageView
    private lateinit var buttonUploadPhoto: ImageButton
    private val PICK_IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_profile)

        editName = findViewById(R.id.edit_name)
        editPhone = findViewById(R.id.edit_phone)
        editEmail = findViewById(R.id.edit_email)
        text1 = findViewById<TextView>(R.id.text1)
        editLocation = findViewById(R.id.edit_location)
        buttonUpdate = findViewById(R.id.button_update)
        profileImage = findViewById(R.id.profile_image)
        buttonUploadPhoto = findViewById(R.id.button_upload_photo)

        buttonUploadPhoto.setOnClickListener {
            openGallery()
        }

        // Charger les informations actuelles dans les champs de texte
        loadCurrentProfileInfo()

        buttonUpdate.setOnClickListener {
            // Code pour enregistrer les modifications
            saveProfileInfo()
            finish()
        }
    }


    private fun loadCurrentProfileInfo() {
        val sharedPreferences = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        editName.setText(sharedPreferences.getString("name", ""))
        editPhone.setText(sharedPreferences.getString("phone", ""))
        editEmail.setText(sharedPreferences.getString("email", ""))
        editLocation.setText(sharedPreferences.getString("location", ""))
    }
    private fun saveProfileInfo() {
        val sharedPreferences = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("name", editName.text.toString())
        editor.putString("phone", editPhone.text.toString())
        editor.putString("email", editEmail.text.toString())
        editor.putString("location", editLocation.text.toString())
        editor.apply()
    }
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImage: Uri? = data.data
            profileImage.setImageURI(selectedImage)
        }

    }}