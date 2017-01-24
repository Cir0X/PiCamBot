import org.telegram.telegrambots.ApiContextInitializer
import org.telegram.telegrambots.TelegramBotsApi

fun main(args: Array<String>) {
    println("PiCamBot for Telegram")
    ApiContextInitializer.init()
    val botsApi = TelegramBotsApi()
    botsApi.registerBot(PiCamBot())
}