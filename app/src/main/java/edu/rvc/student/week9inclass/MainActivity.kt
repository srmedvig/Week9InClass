package edu.rvc.student.week9inclass

import android.content.res.Resources
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import java.util.*
import kotlin.concurrent.timerTask
import kotlinx.android.synthetic.main.activity_main.*
import android.R.attr.start
import android.media.MediaPlayer
import edu.rvc.student.week9inclass.R.drawable.murloc


class MainActivity : AppCompatActivity() {
    //declare variables
    var y:Float = 0.00F
    var x:Float = 0.00F
    var random = Random()
    var score:Int = 0
    var timer = Timer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //bind objects
        var btnImgButton = findViewById<ImageButton>(R.id.imgMole)
        var btnControl = findViewById<Button>(R.id.btnControl)
        var GameBackGround = findViewById<ConstraintLayout>(R.id.GameCanvas)
        //set objects offscreen
        btnImgButton.setTranslationX(-300F)
        btnImgButton.setTranslationY(-300F)
        //
        btnControl.setOnClickListener{
            if (btnControl.text == "Start"){
                Toast.makeText (this, "Tap Murloc To Score¡", 3) .show ()
                btnControl.text = "Stop"
                score = 0
                txtScore.text = "Score: " + score.toString()
                btnImgButton.setTranslationX(-300F)
                btnImgButton.setTranslationY(-300F)
                timer = Timer()
                timer.schedule(timerTask {ChangeImage()},5000)

            }else{
                btnControl.text = "Start"
                btnImgButton.setTranslationX(-300F)
                btnImgButton.setTranslationY(-300F)
                timer.cancel()
            }

        }

        btnImgButton.setOnClickListener{
            score += 100
            if (score == 1000){
                timer.cancel()
                txtScore.text = "You Have Won!"
                score = 0
                btnControl.text = "Start"
                btnImgButton.setTranslationX(-300F)
                btnImgButton.setTranslationY(-300F)
            }else {
                txtScore.text = "Score: " + score.toString()

            }

        }

        GameBackGround.setOnClickListener{
            println("Click")
            score -= 100
            txtScore.text = "Score: " + score.toString()
            if (score == 0 || score == -100){
                Toast.makeText (this, "Game Over", 3) .show ()
                score = 0
                txtScore.text = "Score: " + score.toString()
                btnImgButton.setTranslationX(-300F)
                btnImgButton.setTranslationY(-300F)
                timer.cancel()
                btnControl.text = "Start"

            }
        }
    }

    fun ChangeImage(){
        y = ((Math.random () * getScreenHeight()) + 50) .toFloat ()
        x = ((Math.random () * getScreenWidth()) + 50) .toFloat ()
        imgMole.setTranslationX(x)
        imgMole.setTranslationY(y)
        timer.schedule(timerTask {ChangeImage()},1000)
    }
    fun getScreenWidth(): Float {
        return Resources.getSystem().getDisplayMetrics().widthPixels / 1.4F

    }

    fun getScreenHeight(): Float {
        return Resources.getSystem().getDisplayMetrics().heightPixels / 1.4F
    }

}
