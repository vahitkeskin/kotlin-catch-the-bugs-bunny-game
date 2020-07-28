package com.vahitkeskin.kotlinbugsbunnyyakala

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    var score = 0
    var imageArray = ArrayList<ImageView>()
    var handler = Handler()
    var runnable = Runnable {  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageArray.add(bugsbunny1)
        imageArray.add(bugsbunny2)
        imageArray.add(bugsbunny3)
        imageArray.add(bugsbunny4)
        imageArray.add(bugsbunny5)
        imageArray.add(bugsbunny6)
        imageArray.add(bugsbunny7)
        imageArray.add(bugsbunny8)
        imageArray.add(bugsbunny9)
        hideBugsImage()

        object : CountDownTimer(20500,1000){
            override fun onFinish() {
                sure_textView.text = "Sure: 0"

                handler.removeCallbacks(runnable)
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }

                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Oyun Bitti!")
                alert.setMessage("Tekrar başlamak ister misiniz?")

                alert.setPositiveButton("Evet"){dialogInterface, i ->
                    val intent = intent
                    finish()
                    startActivity(intent)
                }

                alert.setNegativeButton("Hayır") {dialogInterface, i ->
                    Toast.makeText(applicationContext,"Oyun Bitti!",Toast.LENGTH_LONG).show()
                }

                alert.show()

            }

            override fun onTick(p0: Long) {
                sure_textView.text = "Sure: ${p0/1000}"
            }

        }.start()
    }

    fun hideBugsImage() {

        runnable = object : Runnable {
            override fun run() {
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }

                val random = Random()
                val randIndex = random.nextInt(9)
                imageArray[randIndex].visibility = View.VISIBLE

                handler.postDelayed(runnable,500)

            }

        }
        handler.post(runnable)
    }

    fun clickBugsBunny(view: View) {
        score++
        puan_textView.text = "Puan: $score"
    }
}