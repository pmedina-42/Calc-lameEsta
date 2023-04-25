package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class FinalActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)

        val success:Int = intent.getIntExtra("success", 0)
        val attempts:Int = intent.getIntExtra("attempts", 0) + 1
        var result = findViewById<TextView>(R.id.result)
        var time = findViewById<TextView>(R.id.time)
        val filledResult = result.text.toString() + success + "/" + attempts
        result.text = filledResult
        var timeElapsed:Long = intent.getLongExtra("timeElapsed", 0)
        var min:Int = 0
        var sec:Long = timeElapsed / 1000
        while (sec >= 60) {
            min++
            sec -= 60
        }
        var filledTime = time.text.toString()
        if (min > 0) {
            filledTime += "$min min "
        }
        if (sec > 0) {
            filledTime += "$sec s"
        }
        Log.i("Filled time ", "$filledTime")
        time.text = filledTime
        val restartButton = findViewById<Button>(R.id.restartButton)
        restartButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}