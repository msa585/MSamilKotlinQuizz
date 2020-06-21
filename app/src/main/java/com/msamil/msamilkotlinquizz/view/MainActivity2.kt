package com.msamil.msamilkotlinquizz.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.msamil.msamilkotlinquizz.R
import com.msamil.msamilkotlinquizz.activities.MainActivity
import com.msamil.msamilkotlinquizz.activities.QuizzActivity
import com.msamil.msamilkotlinquizz.util.selectedButton
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private var xUrl = "api.php?amount=1"
    private var difficulty = ""
    private var category = ""
    private var type = "&type=multiple"
    private var i = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)



        auth = FirebaseAuth.getInstance()

        anyButton.setOnClickListener        { i=1  ;changeBack(i);category  = ""             }
        generalButton.setOnClickListener    { i=2  ; changeBack(i);category = "&category=9"  }
        sportButton.setOnClickListener      { i=3  ; changeBack(i);category = "&category=21" }
        historyButton.setOnClickListener    { i=4  ; changeBack(i);category = "&category=23" }
        filmButton.setOnClickListener       { i=5  ; changeBack(i);category = "&category=11" }
        musicButton.setOnClickListener      { i=6  ; changeBack(i);category = "&category=12" }
        mathButton.setOnClickListener       { i=7  ; changeBack(i);category = "&category=19" }
        computerButton.setOnClickListener   { i=8  ; changeBack(i);category = "&category=18" }
        geographyButton.setOnClickListener  { i=9  ; changeBack(i);category = "&category=22" }
        vehiclesButton.setOnClickListener   { i=10 ; changeBack(i);category = "&category=28" }

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.radioEasy){
                difficulty = "&difficulty=easy"
            } else if (checkedId == R.id.radioNormal){
                difficulty = "&difficulty=medium"
            } else if(checkedId == R.id.radioHard){
                difficulty = "&difficulty=hard"
            }
        }

        startTest.setOnClickListener {
            if (difficulty.equals("") && category.equals("")){
                Toast.makeText(this,"Please Select Category/Difficulty",Toast.LENGTH_LONG).show()
            } else {
                val URL = xUrl + category + difficulty + type
                val db = this.getSharedPreferences("com.msamil.kotlinquizz", Context.MODE_PRIVATE)
                db.edit().putString("url", URL).apply()
                val intent = Intent(this, QuizzActivity::class.java)
                startActivity(intent)
            }
        }

        toExit.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this,
                MainActivity::class.java))
            finish()
        }

    }

    private fun changeBack(i:Int){
        selectedButton().btn(i,anyButton,generalButton,sportButton,historyButton,filmButton,musicButton,mathButton,computerButton,geographyButton,vehiclesButton)
    }


}