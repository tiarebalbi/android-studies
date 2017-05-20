package com.tiarebalbi.geoquiz

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import butterknife.ButterKnife
import com.tiarebalbi.geoquiz.external.bindView

class QuizActivity : AppCompatActivity() {

    val trueButton: Button by bindView(R.id.true_button)
    val falseButton: Button by bindView(R.id.false_button)
    val nextButton: Button by bindView(R.id.next_button)

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

        nextButton.setOnClickListener(openNextQuestion())

        // Part 3 - Challenge
        questionTextView.setOnClickListener(openNextQuestion())


    }

    private fun openNextQuestion(): (View) -> Unit {
        return {
            currentIndex = (currentIndex + 1) % questions.size
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
