package com.example.timerappsample

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.DecimalFormat
import java.text.NumberFormat

class TimerViewModel : ViewModel() {
    lateinit var countDownTimer: CountDownTimer
    var duration = 60000L
    var countDown = MutableLiveData<String>()

            fun startTimerFun() {
            countDownTimer = object : CountDownTimer(duration, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val f: NumberFormat = DecimalFormat("00")
                    val min = millisUntilFinished / 60000 % 60
                    val sec = millisUntilFinished / 1000 % 60
                    println(countDown)
                    countDown.value = (f.format(min) + ":" + f.format(sec))
                }
                override fun onFinish() {
                    countDown.value = "00.00"
                }

            }.start()
        }
}