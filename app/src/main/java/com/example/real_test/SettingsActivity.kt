package com.example.real_test

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SettingsActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    private lateinit var text1: TextView
    private lateinit var text2: TextView
    private lateinit var text3: TextView
    private lateinit var textView3: TextView
    private lateinit var switchTheme: Switch
    private lateinit var text5: TextView
    private lateinit var text6: TextView
    private lateinit var textView2: TextView
    private lateinit var switchNotifications: Switch
    private lateinit var switchPromotions: Switch
    private lateinit var switchUpdates: Switch
    private lateinit var textView5: TextView
    private lateinit var text10: TextView
    private lateinit var text11: TextView
    private lateinit var text12: TextView
    private lateinit var text13: TextView
    private lateinit var text14: TextView
    private lateinit var text15: TextView
    private lateinit var text16: TextView
    private lateinit var text17: TextView
    private lateinit var text18: TextView
    private lateinit var text19: TextView
    private lateinit var text20: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.parametre)
        // Récupération des vues
        imageView = findViewById(R.id.imageView)
        textView = findViewById(R.id.textView)
        text1 = findViewById<TextView>(R.id.text1)
        text2 = findViewById<TextView>(R.id.text2)
        text3 = findViewById<TextView>(R.id.text3)
        textView3 = findViewById(R.id.textView3)
        switchTheme = findViewById(R.id.switch_theme)
        text5 = findViewById<TextView>(R.id.text5)
        text6 = findViewById<TextView>(R.id.text6)
        textView2 = findViewById(R.id.textView2)
        switchNotifications = findViewById(R.id.switch_notifications)
        switchPromotions = findViewById(R.id.switch_promotions)
        switchUpdates = findViewById(R.id.switch_updates)
        textView5 = findViewById(R.id.textView5)
        text10 = findViewById<TextView>(R.id.text10)
        text11 = findViewById<TextView>(R.id.text11)
        text12 = findViewById<TextView>(R.id.text12)
        text13 = findViewById<TextView>(R.id.text13)
        text14 = findViewById<TextView>(R.id.text14)
        text15 = findViewById<TextView>(R.id.text15)
        text16 = findViewById<TextView>(R.id.text16)
        text17 = findViewById<TextView>(R.id.text17)
        text18 = findViewById<TextView>(R.id.text18)
        text19 = findViewById<TextView>(R.id.text19)
        text20 = findViewById<TextView>(R.id.text20)

        // Exemple d'utilisation des vues
        textView.text = "Paramètres du compte"
        text1.text = "Modifier le profil"
        text2.text = "Modifier le mot de passe"
        text3.text = "Sécurité et confidentialité"
        textView3.text = "Préférences d'applications"
        text5.text = "Langue"
        text6.text = "Evaluez nous"
        textView2.text = "Paramètres de notifications"
        textView5.text = "Paramètres avancés"
        text10.text = "Gestion des données"
        text11.text = "Paramètres de synchronisation"
        text12.text = "Paramètres de sauvegarde"
        text13.text = "Activité du compte"
        text14.text = "Historique des connexions"
        text15.text = "Historique des dons fait"
        text16.text = "Aide et support"
        text17.text = "FAQ"
        text18.text = "Contactez-nous"
        text19.text = "Politique de confidentialité"
        text20.text = "Conditions générales"
    }

    // Liste des TextViews
    val textViews: List<TextView> = listOf(
        text1, text2, text3, text5, text6, text10, text11, text12, text13, text14, text15, text16, text17, text18, text19, text20
    )

    // Définir l'écouteur de clic pour chaque TextView
    val expandListener: View.OnClickListener = View.OnClickListener { view ->
        val textView = view as TextView
        if (textView.maxLines == 1) {
            textView.maxLines = Int.MAX_VALUE // Expand
        } else {
            textView.maxLines = 1 // Collapse
        }

        val expandListener: View.OnClickListener = View.OnClickListener { view ->
            // ...
        }
// Appliquer l'écouteur de clic à chaque TextView
    textViews.forEach { it.setOnClickListener(expandListener) }
}
}