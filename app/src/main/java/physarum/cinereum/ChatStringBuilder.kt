package physarum.cinereum

import physarum.cinereum.MainActivity.Companion.phraseJp
import physarum.cinereum.MainActivity.Companion.phraseUs

class ChatStringBuilder {
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