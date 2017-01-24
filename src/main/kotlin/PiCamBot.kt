import org.telegram.telegrambots.api.methods.ActionType
import org.telegram.telegrambots.api.methods.send.SendChatAction
import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.methods.send.SendPhoto
import org.telegram.telegrambots.api.objects.Update
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import java.io.InputStream
import java.util.*

class PiCamBot : TelegramLongPollingBot() {

    private val camera = Camera()

    override fun getBotUsername() = "PiCamBot"

    override fun getBotToken(): String = System.getenv("TELEGRAM_PICAM_BOT_TOKEN")

    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage() && update.message.hasText()) {
            val chatId = update.message.chatId
            val text = update.message.text
            val keyboard = getKeyboard()
            val message = SendMessage()
                    .setChatId(update.message.chatId)
                    .setReplyMarkup(keyboard)

            if (text == "📷") {
                sendUploadStatus(chatId)
                println("Taking Photo")
                val photo = camera.takePhoto()
                sendUploadStatus(chatId)
                println("Uploading Photo")
                sendImageUploadingAFile(photo, chatId)
            } else if (text == "/start") {
                message.text = "Press the Camera Button below to take a Photo."
                sendMessage(message)
            } else {
                message.text = "Unknown Command"
                sendMessage(message)
            }
        }
    }

    fun sendImageUploadingAFile(photo: InputStream, chatId: Long) {
        val sendPhotoRequest = SendPhoto()
        sendPhotoRequest.chatId = "" + chatId
        sendPhotoRequest.setNewPhoto("test.png", photo)
        sendPhoto(sendPhotoRequest)
    }

    fun sendUploadStatus(chatId: Long) {
        val action = SendChatAction()
                .setChatId(chatId)
                .setAction(ActionType.UPLOADPHOTO)
        sendChatActionAsync(action, SentActionCallback(action, this))
    }

    fun getKeyboard(): ReplyKeyboardMarkup {
        val keyboardMarkup = ReplyKeyboardMarkup()

        val keyboard = ArrayList<KeyboardRow>()
        val row = KeyboardRow()

        val button = KeyboardButton()
        button.text = "📷"

        row.add(button)
        keyboard.add(row)

        keyboardMarkup.keyboard = keyboard
        return keyboardMarkup
    }
}