package com.tiarebalbi.geoquiz

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.Button
import android.widget.Toast

class QuizActivity : AppCompatActivity() {

    lateinit private var mTrueButton: Button
    lateinit private var mFalseButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        setTitle(R.string.app_name)

        this.mTrueButton = findViewById(R.id.true_button) as Button
        this.mTrueButton.setOnClickListener {
            Toast.makeText(this@QuizActivity, R.string.correct_toast, Toast.LENGTH_SHORT)
                    .also { toast ->
                        toast.setGravity(Gravity.TOP, 0, 0)
                    }
                    .show()
        }

        this.mFalseButton = findViewById(R.id.false_button) as Button
        this.mFalseButton.setOnClickListener {
            Toast.makeText(this@QuizActivity, R.string.incorrect_toast, Toast.LENGTH_SHORT)
                    .also { toast ->
                        toast.setGravity(Gravity.TOP, 0, 0)
                    }
                    .show()
        }
    }
}
