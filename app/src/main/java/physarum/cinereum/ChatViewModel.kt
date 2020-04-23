package physarum.cinereum

import androidx.lifecycle.ViewModel

class ChatViewModel : ViewModel(){
    var lang: String = "US"
    var userName: String = "Alice"
    var chatStringBuilder: ChatStringBuilder = ChatStringBuilder()
}