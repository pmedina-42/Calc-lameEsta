package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    private var startTime:Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var success:Int = 0

        val button = findViewById<Button>(R.id.startButton)
        val minutes = findViewById<EditText>(R.id.minutes)
        button.setOnClickListener {
            val finalMins:String = minutes.text.toString()
            if (finalMins.isNotEmpty()) {
                val intent = Intent(this, SumActivity::class.java)
                startTime = System.currentTimeMillis()
                intent.putExtra("elapsedTime", startTime)
                intent.putExtra("attempts", 0)
                intent.putExtra("mins", Integer.parseInt(finalMins))
                startActivity(intent)
                if (intent.hasExtra("ok")) {
                    success = intent.getIntExtra("ok", 0)
                }
            }
        }
    }
}