package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.core.view.isVisible
import org.json.JSONObject

class FinalActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)

        val success:Int = intent.getIntExtra("success", 0)
        val attempts:Int = intent.getIntExtra("attempts", 0)
        val resultsLayout = findViewById<LinearLayout>(R.id.resultsLayout)
        val resMap = intent.getStringExtra("resultMap")
        val resJson = JSONObject(resMap)
        val resIterator = resJson.keys()


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
        time.text = filledTime
        val showResultsButton = findViewById<Button>(R.id.showResults)
        val closeViewButton = findViewById<Button>(R.id.closeButton)
        val resultsView = findViewById<View>(R.id.view)
        val restartButton = findViewById<Button>(R.id.restartButton)
        while (resIterator.hasNext()) {
            val key = resIterator.next() as String
            val value = resJson.getBoolean(key)

            val textView = TextView(this)
            textView.text = key
            if (value) {
                textView.setTextColor(Color.GREEN)
            } else {
                textView.setTextColor(Color.RED)
            }
            resultsLayout.addView(textView)
        }

        showResultsButton.setOnClickListener {
            resultsView.isVisible = true
        }

        closeViewButton.setOnClickListener {
            resultsView.isVisible = false
        }

        restartButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}