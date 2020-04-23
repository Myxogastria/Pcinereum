package physarum.cinereum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bt1 = findViewById<Button>(R.id.button1)
        val listener = ButtonListener()
        bt1.setOnClickListener(listener)
    }

    private inner class ButtonListener : View.OnClickListener{
        override fun onClick(v: View?) {
            val output = findViewById<TextView>(R.id.tvChat)
            output.text = output.text.toString() + "\nAlice: Hello"
        }
    }
}
