package com.tiarebalbi.geoquiz

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import butterknife.ButterKnife
import com.tiarebalbi.geoquiz.external.bindView

class QuizActivity : AppCompatActivity() {

    val trueButton: Button by bindView(R.id.true_button)
    val falseButton: Button by bindView(R.id.false_button)
    val prevButton: ImageButton by bindView(R.id.previous_button)
    val nextButton: ImageButton by bindView(R.id.next_button)

    val TAG: String = "QuizActivity"

    val questionTextView: TextView by bindView(R.id.question_text_view)

    val questions = arrayOf(
            Question(R.string.question_australia, true),
            Question(R.string.question_oceans, true),
            Question(R.string.question_mideast, false),
            Question(R.string.question_africa, false),
            Question(R.string.question_americas, true),
            Question(R.string.question_asia, true)
    )

    var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle) called")

        setContentView(R.layout.activity_quiz)
        ButterKnife.bind(this)

        // Part 1 - Added event to the button
        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        // Part 2 - Adding questions
        updateQuestion()

        prevButton.setOnClickListener(navToTheQuestion(0))
        nextButton.setOnClickListener(navToTheQuestion())

        // Part 3 - Challenge
        questionTextView.setOnClickListener(navToTheQuestion())


    }

    // Chapter 3 - Activity Lifecycle
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }


    private fun navToTheQuestion(order: Int = 1): (View) -> Unit {
        return {
            when (order) {
                1 -> currentIndex = (currentIndex + 1) % questions.size
                0 -> currentIndex = (currentIndex - 1) % questions.size
            }

            if (currentIndex < 0) currentIndex = questions.size - 1

            updateQuestion()
        }
    }

    private fun updateQuestion() {
        val question = questions[currentIndex].textResId
        questionTextView.setText(question)
    }

    private fun checkAnswer(userPressedTrue: Boolean) {
        val answerIsTrue = questions[currentIndex].answerTrue

        var messageResId = 0

        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast
        } else {
            messageResId = R.string.incorrect_toast
        }

        Toast.makeText(this@QuizActivity, messageResId, Toast.LENGTH_SHORT).show()
    }
}
