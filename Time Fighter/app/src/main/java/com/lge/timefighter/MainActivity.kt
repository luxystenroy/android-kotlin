package com.lge.timefighter

import android.annotation.SuppressLint
import android.content.IntentSender
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    internal lateinit var tapMeButton: Button
    internal lateinit var gameScoreTextView: TextView
    internal lateinit var timeLeftTextView: TextView
    internal var score = 0
    internal var gameStarted = false;
    internal lateinit var countDownTimer: CountDownTimer
    internal val initialCountDown: Long = 60000
    internal val countDownInternal: Long = 1000
    internal val TAG = MainActivity::class.java.simpleName
    internal var timeLeftOnTimer: Long = 6000

    companion object {
        private val SCORE_KEY = "SCORE_KEY"
        private val TIME_LEFT_KEY = "TIME_LEFT_KEY"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)
        return true;

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_about){

            showInfo();

        }

        return true
    }

    private fun showInfo() {

        val dialogTitle = getString(R.string.about_title, BuildConfig.VERSION_NAME)
        val dialogMessage = getString(R.string.about_message)
        val builder = AlertDialog.Builder(this);
        builder.setTitle(dialogTitle);
        builder.setMessage(dialogMessage)
        builder.create().show();
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate called, Score is $score" +
                "")

        tapMeButton = findViewById<Button>(R.id.tap_me_button)
        gameScoreTextView = findViewById<TextView>(R.id.game_score_text_view)
        timeLeftTextView = findViewById<TextView>(R.id.time_left_text_view)


        if(savedInstanceState != null){
            score = savedInstanceState.getInt(SCORE_KEY)
            timeLeftOnTimer = savedInstanceState.getLong(TIME_LEFT_KEY)
            restoreGame()
        }else{
            resetGame()
        }

        resetGame()

        tapMeButton.setOnClickListener{


            view ->
            val bounceAnimation = AnimationUtils.loadAnimation(this,R.anim.bounce)
            view.startAnimation(bounceAnimation)
            incrementScore()
        }
    }

    private fun restoreGame() {

        gameScoreTextView.text = getString(R.string.your_score,score.toString())

        val restoredTime = timeLeftOnTimer / 1000

        timeLeftTextView.text = getString(R.string.time_leg, restoredTime.toString())

        countDownTimer = object : CountDownTimer(timeLeftOnTimer, countDownInternal){

            override fun onTick(millisUntilFinished: Long) {

                var timeLeft = millisUntilFinished / 1000

                timeLeftTextView.text = getString(R.string.time_leg, timeLeft.toString())

            }

            override  fun onFinish() {
                endGame()
            }
        }

    }

    private fun incrementScore() {

        if(!gameStarted){
            startGame()
        }

        score = score + 1

        val newScore = getString(R.string.your_score, score.toString())

        gameScoreTextView.text = newScore


    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(SCORE_KEY, score)
        outState.putLong(TIME_LEFT_KEY, timeLeftOnTimer)
        countDownTimer.cancel()
        Log.d(TAG, "onSaveInstanceState: saving score: $score & time left: $timeLeftOnTimer")
    }


    override fun onDestroy() {
        super.onDestroy()

        Log.d(TAG,"onDestroy called")
    }

    private fun resetGame(){



        score = 0
        gameScoreTextView.text = getString(R.string.your_score, score.toString())

        val initialTimeLeft = initialCountDown / 1000
        timeLeftTextView.text = getString(R.string.time_leg,initialTimeLeft.toString())


        countDownTimer = object: CountDownTimer(initialCountDown, countDownInternal){
            override fun onFinish() {

                endGame()

            }

            override fun onTick(millisUntilFinished: Long) {
                timeLeftOnTimer = millisUntilFinished
                val timeLeft  = millisUntilFinished /1000
                timeLeftTextView.text = getString(R.string.time_leg,timeLeft.toString())

            }

        }

        gameStarted = false


    }


    private fun startGame(){

        countDownTimer.start()
        gameStarted = true

    }

    @SuppressLint("StringFormatInvalid")
    private fun endGame() {
        Toast.makeText(this, getString(R.string.game_over_message, score.toString()), Toast.LENGTH_SHORT).show()
        resetGame()
    }



}
