/*
import com.pengrad.telegrambot.TelegramBotAdapter
import com.pengrad.telegrambot.request.SetWebhook
import spark.Request
import spark.Response
import spark.Route
import spark.Spark
import com.pengrad.telegrambot.BotUtils
*/

fun main(args: Array<String>) {
    println("in main")
    //val chain = MarkovChain(3)
    //println(chain.generate())
    //val bot = Bot()
    //Spark.post(Config.PATH, bot)
}

/*
class Bot(windowSize: Int = 3): Route {
    private val bot = TelegramBotAdapter.buildDebug(Config.TOKEN)
    init {
        println("in init")
        bot.execute(SetWebhook().url(Config.URL))
    }

    override fun handle(request: Request, response: Response) {
        println("request -> " + request)
        val update = BotUtils.parseUpdate(request.body())
        println(update)
    }
}
        */
