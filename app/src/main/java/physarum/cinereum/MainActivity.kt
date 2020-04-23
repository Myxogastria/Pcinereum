package physarum.cinereum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var chatStringBuilder = ChatStringBuilder()
    private var lang = "US"
    public val phraseUs: Array<String> = arrayOf("Hello", "OK", "Help!", "Thank you")
    public val phraseJp: Array<String> = arrayOf("こんにちは", "OK", "助けて!", "ありがとう")

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

        setLang("JP")
    }

    private fun setLang(lang: String){
        this.lang = lang

        val bt1 = findViewById<Button>(R.id.button1)
        val bt2 = findViewById<Button>(R.id.button2)
        val bt3 = findViewById<Button>(R.id.button3)
        val bt4 = findViewById<Button>(R.id.button4)

        if(lang == "US"){
            bt1.text = phraseUs[0]
            bt2.text = phraseUs[1]
            bt3.text = phraseUs[2]
            bt4.text = phraseUs[3]
        }else if(lang == "JP"){
            bt1.text = phraseJp[0]
            bt2.text = phraseJp[1]
            bt3.text = phraseJp[2]
            bt4.text = phraseJp[3]
        }

        updateChat()
    }

    private fun updateChat(){
        val output = findViewById<TextView>(R.id.tvChat)
        output.text = chatStringBuilder.getString(lang)
    }

    private inner class ButtonListener(val btId: Int) : View.OnClickListener{
        override fun onClick(v: View?) {
            chatStringBuilder.send("Alice", btId)
            updateChat()
        }
    }

    private inner class ChatStringBuilder{
        var chatArrayList = ArrayList<Pair<String, Int>>()
        fun send(sender: String, phraseId: Int){
            chatArrayList.add(Pair(sender, phraseId))
        }

        fun getString(lang: String): String{
            var chatString = ""
            for(pair in chatArrayList){
                if(lang == "US"){
                    chatString += "${pair.first}: ${phraseUs[pair.second]}\n"
                }else if(lang == "JP"){
                    chatString += "${pair.first}: ${phraseJp[pair.second]}\n"
                }
            }
            return chatString
        }
    }
}
