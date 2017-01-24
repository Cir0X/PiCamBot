import org.telegram.telegrambots.api.methods.BotApiMethod
import org.telegram.telegrambots.api.methods.send.SendChatAction
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.exceptions.TelegramApiRequestException
import org.telegram.telegrambots.updateshandlers.SentCallback

class SentActionCallback(val action: SendChatAction, val bot: TelegramLongPollingBot) : SentCallback<Boolean> {

    override fun onException(method: BotApiMethod<Boolean>?, exception: Exception?) {
        println("Exception occurred")
    }

    override fun onResult(method: BotApiMethod<Boolean>?, response: Boolean?) {
        println("Got result")
    }

    override fun onError(method: BotApiMethod<Boolean>?, apiException: TelegramApiRequestException?) {
        println("Error occurred")
    }

}