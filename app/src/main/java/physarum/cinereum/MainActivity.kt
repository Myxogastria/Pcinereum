package physarum.cinereum

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Display
import android.view.Surface
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {
    private var viewModel = ChatViewModel()
    companion object{
        val phraseUs: Array<String> = arrayOf("Hello", "OK", "Help!", "Thank you")
        val phraseJp: Array<String> = arrayOf("こんにちは", "OK", "助けて!", "ありがとう")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ChatViewModel::class.java)

        val bt1 = findViewById<Button>(R.id.button1)
        val bt2 = findViewById<Button>(R.id.button2)
        val bt3 = findViewById<Button>(R.id.button3)
        val bt4 = findViewById<Button>(R.id.button4)

        bt1.setOnClickListener(ButtonListener(0))
        bt2.setOnClickListener(ButtonListener(1))
        bt3.setOnClickListener(ButtonListener(2))
        bt4.setOnClickListener(ButtonListener(3))

        val windowManager: WindowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display: Display = windowManager.defaultDisplay

        Log.i("rotation", "${display.rotation}")

        if((display.rotation == Surface.ROTATION_0) || (display.rotation == Surface.ROTATION_90)){
            viewModel.userName = "なおこ"
            viewModel.lang = "JP"
            setLang("JP")
        }else if((display.rotation == Surface.ROTATION_180) || (display.rotation == Surface.ROTATION_270)){
            viewModel.userName  = "Alice"
            viewModel.lang = "US"
            setLang("US")
        }

        updateChat()
    }

    private fun setLang(lang: String){
        viewModel.lang = lang

        val bt1 = findViewById<Button>(R.id.button1)
        val bt2 = findViewById<Button>(R.id.button2)
        val bt3 = findViewById<Button>(R.id.button3)
        val bt4 = findViewById<Button>(R.id.button4)

        if(viewModel.lang == "US"){
            bt1.text = phraseUs[0]
            bt2.text = phraseUs[1]
            bt3.text = phraseUs[2]
            bt4.text = phraseUs[3]
        }else if(viewModel.lang == "JP"){
            bt1.text = phraseJp[0]
            bt2.text = phraseJp[1]
            bt3.text = phraseJp[2]
            bt4.text = phraseJp[3]
        }

        updateChat()
    }

    private fun updateChat(){
        val output = findViewById<TextView>(R.id.tvChat)
        output.text = viewModel.chatStringBuilder.getString(viewModel.lang)
    }

    private inner class ButtonListener(val btId: Int) : View.OnClickListener{
        override fun onClick(v: View?) {
            viewModel.chatStringBuilder.send(viewModel.userName, btId)
            updateChat()
        }
    }

}
