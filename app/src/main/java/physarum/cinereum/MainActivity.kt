package physarum.cinereum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var chatStringBuilder = ChatStringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bt1 = findViewById<Button>(R.id.button1)
        val bt2 = findViewById<Button>(R.id.button2)
        val bt3 = findViewById<Button>(R.id.button3)
        val bt4 = findViewById<Button>(R.id.button4)

        bt1.setOnClickListener(ButtonListener(0))
        bt2.setOnClickListener(ButtonListener(1))
        bt3.setOnClickListener(ButtonListener(2))
        bt4.setOnClickListener(ButtonListener(3))
    }

    private inner class ButtonListener(val btId: Int) : View.OnClickListener{
        override fun onClick(v: View?) {
            chatStringBuilder.send("Alice", btId)
            val output = findViewById<TextView>(R.id.tvChat)
            output.text = chatStringBuilder.getString("US")
        }
    }

    private inner class ChatStringBuilder{
        var chatString: String
        init {
            chatString = ""
        }
        fun send(sender: String, phraseId: Int){
            chatString += "\n$sender: $phraseId"
        }

        fun getString(lang: String): String{
            return chatString
        }
    }
}
