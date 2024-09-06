package com.example.real_test

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FaireDonsActivity  : AppCompatActivity() {
    private lateinit var imageDonation: ImageView
    private lateinit var buttonUploadPhoto: ImageButton
    private lateinit var editDescription: EditText
    private lateinit var editLocation: EditText
    private lateinit var editContact: EditText
    private lateinit var buttonAddDonation: Button
    private val imageUris: ArrayList<Uri> = ArrayList()
    private val PICK_IMAGE_REQUEST = 1
    private val REQUEST_CODE_SELECT_IMAGES = 2
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.faire_dons)

        imageDonation = findViewById(R.id.image_donation)
        buttonUploadPhoto = findViewById(R.id.button_upload_photo)
        editDescription = findViewById(R.id.edit_donation_description)
        editLocation = findViewById(R.id.edit_donation_location)
        editContact = findViewById(R.id.edit_donation_contact)
        buttonAddDonation = findViewById(R.id.button_add_donation)

        buttonUploadPhoto.setOnClickListener {
            // Masquer l'image au clic sur le bouton
            imageDonation.visibility = View.GONE
            openGallery()
        }

        buttonAddDonation.setOnClickListener {
            addDonation()
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true) // Permettre la sélection multiple
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGES)
    }



    // Gestion des résultats de la galerie
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_SELECT_IMAGES && resultCode == Activity.RESULT_OK) {
            if (data?.clipData != null) {
                // Si plusieurs images sont sélectionnées
                val count = data.clipData?.itemCount ?: 0
                for (i in 0 until count) {
                    val imageUri = data.clipData?.getItemAt(i)?.uri
                    imageUri?.let { imageUris.add(it) }
                }
                Toast.makeText(this, "${imageUris.size} images sélectionnées", Toast.LENGTH_SHORT).show()
            } else if (data?.data != null) {
                // Si une seule image est sélectionnée
                val imageUri = data.data
                imageUri?.let { imageUris.add(it) }
                Toast.makeText(this, "1 image sélectionnée", Toast.LENGTH_SHORT).show()
            }
        }
    }
                private fun addDonation() {
                    val description = editDescription.text.toString()
                    val location = editLocation.text.toString()
                    val contact = editContact.text.toString()

                    // Vérifier que tous les champs sont remplis et qu'au moins une image est sélectionnée
                    if (imageUris.isNotEmpty() && description.isNotEmpty() && location.isNotEmpty() && contact.isNotEmpty()) {
                        // Code pour ajouter le don à la base de données ou à la liste des dons
                        Toast.makeText(this, "Don ajouté avec succès", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this, "Veuillez remplir tous les champs et ajouter une photo", Toast.LENGTH_SHORT).show()
                    }
                }
            }


