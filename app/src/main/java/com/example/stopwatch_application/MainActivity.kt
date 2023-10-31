package com.example.stopwatch_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var handler: Handler
    private lateinit var textView: TextView
    private var running: Boolean = false
    private var seconds: Int = 0
    private var minutes: Int = 0
    private var hours: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView2)
        handler = Handler()

        val startButton = findViewById<Button>(R.id.startCountdown)
        startButton.setOnClickListener {
            startTimer()
        }

        val stopButton = findViewById<Button>(R.id.stopCountdown)
        stopButton.setOnClickListener {
            stopTimer()
        }

        val resetButton = findViewById<Button>(R.id.resetButton)
        resetButton.setOnClickListener {
            resetTimer()
        }
    }

    private fun startTimer() {
        running = true
        handler.post(updateTimer)
    }

    private fun stopTimer() {
        running = false
    }

    private fun resetTimer() {
        running = false
        seconds = 0
        minutes = 0
        hours = 0
        updateDisplay()
    }

    private val updateTimer = object : Runnable {
        override fun run() {
            seconds++
            if (seconds == 60) {
                seconds = 0
                minutes++
                if (minutes == 60) {
                    minutes = 0
                    hours++
                }
            }
            updateDisplay()
            if (running) {
                handler.postDelayed(this, 1000)
            }
        }
    }

    private fun updateDisplay() {
        val time = String.format("%02d:%02d:%02d", hours, minutes, seconds)
        textView.text = time
    }
}
