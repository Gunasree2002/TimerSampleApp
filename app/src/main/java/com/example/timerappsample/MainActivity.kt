package com.example.timerappsample

import android.animation.ObjectAnimator
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: TimerViewModel
   lateinit var timerImage:ImageView
    lateinit var countDown: TextView
    lateinit var startTimer: FloatingActionButton
    lateinit var pauseTimer: FloatingActionButton
    var rotation = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        timerImage = findViewById(R.id.timer_image)
        countDown = findViewById(R.id.timer_text)
        startTimer = findViewById(R.id.start_timer)
        pauseTimer = findViewById(R.id.stop_timer)

        viewModel = ViewModelProvider(this).get(TimerViewModel::class.java)

        startTimer.setOnClickListener {
            print(" start clicked...........")
            viewModel.startTimerFun()
            startTimer.isEnabled = false
            pauseTimer.isEnabled = true

        }
        pauseTimer.setOnClickListener {
            viewModel.countDownTimer.cancel()
            viewModel.duration =  (( ( countDown.text.toString()).substring(3)).toLong()) * 1000
            pauseTimer.isEnabled = false
            startTimer.isEnabled = true

        }

        viewModel.countDown.observe(this, androidx.lifecycle.Observer { timerValue ->
            countDown.setText(timerValue)
            var newTimerValue = timerValue.substring(3)
            if ((newTimerValue.toInt()%2 == 0)){
                val rotate = ObjectAnimator.ofFloat(timerImage, "rotation", 0f, 180f)
                rotate.duration = 1000
                rotate.start()
            }
            else{
                val rotate = ObjectAnimator.ofFloat(timerImage, "rotation", 180f, 0f)
                rotate.duration = 1000
                rotate.start()
            }
           if ((newTimerValue.toInt() == 0)){
               startTimer.isEnabled = true
               viewModel.duration = 60000L
           }

        }
        )

    }



}