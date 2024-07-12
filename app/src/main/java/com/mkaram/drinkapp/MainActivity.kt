package com.mkaram.drinkapp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var selectDrinkEt : AutoCompleteTextView
    lateinit var pricebtn : Button
    lateinit var pricetext : TextView
    var result :Int? = 15
    val map = mapOf("Orange Juice" to 15,
        "Apple Juice" to 20,
        "Mango Juice" to 25,
        "Kiwi Juice" to 30)



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initailizeView()
        populateDropDownMenu()

        selectDrinkEt.setOnItemClickListener { parent, view, position, id ->
            result = when (selectDrinkEt.text.toString()){
                "Orange Juice" -> map.get("Orange Juice")
                "Apple Juice" -> map.get("Apple Juice")
                "Mango Juice" -> map.get("Mango Juice")
                else -> {map.get("Kiwi Juice")}
            }

            pricetext.setText(result.toString())

        }

        pricebtn.setOnClickListener{
            val sendIntent = Intent(Intent.ACTION_SENDTO)
            sendIntent.data = Uri.parse("mailto:")
            sendIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("mhmdntf@gmail.com"))
            sendIntent.putExtra(Intent.EXTRA_SUBJECT,"ORDER")
            sendIntent.putExtra(Intent.EXTRA_TEXT,"price of one of  ${selectDrinkEt.text} is $result")
            startActivity(sendIntent)
        }
    }

    private fun populateDropDownMenu() {
        val drinks = listOf("Orange Juice","Apple Juice","Mango Juice","Kiwi Juice")
        val adapter = ArrayAdapter(this, R.layout.menu_text_style, drinks)
        selectDrinkEt.setAdapter(adapter)
    }

    private fun initailizeView (){
         selectDrinkEt = findViewById(R.id.select_field)
         pricebtn = findViewById(R.id.button)
         pricetext = findViewById(R.id.textView2)
    }
}