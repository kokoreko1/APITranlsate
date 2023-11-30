package com.example.apitranlsate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val translateBaseUrl = "https://api.funtranslations.com"

    private val retrofit = Retrofit.Builder()
        .baseUrl(translateBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val translationService: TranslationApi = retrofit.create(TranslationApi::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText1 = findViewById<EditText>(R.id.editText1)

        val buttonYoda = findViewById<Button>(R.id.buttonYoda)

        val buttonMorse = findViewById<Button>(R.id.buttonMorse)

        val textView1 = findViewById<TextView>(R.id.textView1)

        buttonYoda.setOnClickListener {
            //val textYoda = yodaTranslate(editText1.text)
            //textView1.setText("sdfsdfsf")
            yodaTranslate("sdfsdfsfsff")
        }

        buttonMorse.setOnClickListener {
            //val textMorse = morseTranslate(editText1.text)
            //textView1.setText("sdfsdfsdfsd")
            morseTranslate("sdfsdfsfsff")
        }


    }

    private fun yodaTranslate(text: String) {
        translationService
            .translateToYoda(TranslationRequest(text))
            .enqueue(object : Callback<TranslationResponse> {
                override fun onResponse(call: Call<TranslationResponse>,
                                        response: Response<TranslationResponse>){
                    Log.d("TRANSLATION_LOG", "Yoda says: ${response.body()?.contents?.translated}")
                }

                override fun onFailure(call: Call<TranslationResponse>, t: Throwable) {
                }

            })
    }

    private fun morseTranslate(text: String) {
        translationService
            .translateToMorse(TranslationRequest(text))
            .enqueue(object : Callback<TranslationResponse>{
                override fun onResponse(call: Call<TranslationResponse>,
                                        response: Response<TranslationResponse>){
                    Log.d("TRANSLATION_LOG", "in Morse code: ${response.body()?.contents?.translated}")
                }
                override fun onFailure(call: Call<TranslationResponse>, t: Throwable) {
                }
            })
    }
}
