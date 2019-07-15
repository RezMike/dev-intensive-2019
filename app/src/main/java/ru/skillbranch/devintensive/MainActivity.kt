package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.extensions.hideKeyboard
import ru.skillbranch.devintensive.models.Bender

class MainActivity : AppCompatActivity() {

    lateinit var benderImage: ImageView
    lateinit var textTv: TextView
    lateinit var messageEt: EditText
    lateinit var sendBtn: ImageView
    lateinit var benderObj: Bender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        benderImage = iv_bender
        textTv = tv_text
        messageEt = et_message
        sendBtn = iv_send

        val status = savedInstanceState?.getString(SAVED_STATUS) ?: Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString(SAVED_QUESTION) ?: Bender.Question.NAME.name
        benderObj = Bender(Bender.Status.valueOf(status), Bender.Question.valueOf(question))

        textTv.text = benderObj.askQuestion()
        setBenderColor(benderObj.status.color)
        sendBtn.setOnClickListener { sendAnswer() }
        messageEt.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                event != null &&
                event.action == KeyEvent.ACTION_DOWN &&
                event.keyCode == KeyEvent.KEYCODE_ENTER
            ) {
                sendAnswer()
                true
            } else {
                false
            }
        }
    }

    private fun sendAnswer() {
        hideKeyboard()
        val answer = messageEt.text.toString()
        if (answer.isEmpty()) return
        val (phrase, color) = benderObj.listenAnswer(answer)
        messageEt.setText("")
        setBenderColor(color)
        textTv.text = phrase
    }

    private fun setBenderColor(color: Triple<Int, Int, Int>) {
        val (r, g, b) = color
        benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.run {
            putString(SAVED_STATUS, benderObj.status.name)
            putString(SAVED_QUESTION, benderObj.question.name)
        }
    }

    companion object {
        private const val SAVED_STATUS = "STATUS"
        private const val SAVED_QUESTION = "QUESTION"
    }
}
