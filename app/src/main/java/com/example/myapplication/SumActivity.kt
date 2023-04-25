package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import kotlin.random.Random

class SumActivity : ComponentActivity() {

    private lateinit var countDownTimer: CountDownTimer
    private lateinit var timeLeftTextView: TextView
    private var startTime:Long = 0

    var attempts:Int = 0
    var success:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sum)

        startTime = intent.getLongExtra("elapsedTime", 0)
        var sumString = findViewById<TextView>(R.id.sum)
        var n1:Int = Random.nextInt(300)
        var n2:Int = Random.nextInt(300)
        var n3:Int = Random.nextInt(300)
        var n4:Int = Random.nextInt(300)
        var n5:Int = Random.nextInt(300)
        var n6:Int = Random.nextInt(300)
        var sum = "$n1 + $n2 + $n3 + $n4 + $n5 + $n6"
        sumString.text = sum
        var expectedRes:Int = n1 + n2 + n3 + n4 + n5 + n6
        Log.i("Result", "$expectedRes")

        val finalMins:Int = intent.getIntExtra("mins", 20)
        Log.i("Final mins", "$finalMins")
        val timeLeftInMillis:Int = finalMins * 60 * 1000
        attempts = intent.getIntExtra("attempts", 0)
        success = intent.getIntExtra("success", 0)

        // Initialize views
        timeLeftTextView = findViewById(R.id.count)

        val next = findViewById<Button>(R.id.button)
        val res = findViewById<EditText>(R.id.result)

        countDownTimer = object : CountDownTimer(timeLeftInMillis.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // Update time left TextView
                val timeLeft = millisUntilFinished / 1000
                timeLeftTextView.text = "Tiempo restante: ${timeLeft / 60}:${timeLeft % 60}"
            }

            override fun onFinish() {
                timeLeftTextView.text = "Se te acabó el tiempo, máquina"
            }
        }.start()

        next.setOnClickListener {
            if (res.text.isNotEmpty() && attempts < 19) {
                attempts += 1
                if (Integer.parseInt(res.text.toString()) == expectedRes) {
                    success += 1
                }
                Log.i("Attempts", "$attempts")
                n1 = Random.nextInt(300)
                n2 = Random.nextInt(300)
                n3 = Random.nextInt(300)
                n4 = Random.nextInt(300)
                n5 = Random.nextInt(300)
                n6 = Random.nextInt(300)
                sumString.text = "$n1 + $n2 + $n3 + $n4 + $n5 + $n6"
                expectedRes = n1 + n2 + n3 + n4 + n5 + n6
                res.text.clear()
                Log.i("Introduced result", "${res.text}")
            } else {
                val sender = Intent(this, FinalActivity::class.java)
                sender.putExtra("success", success)
                sender.putExtra("attempts", attempts)
                val elapsedTime:Long = System.currentTimeMillis() - startTime
                sender.putExtra("timeElapsed", elapsedTime)
                startActivity(sender)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
    }
}