import org.jsoup.Jsoup
import java.io.File


fun getTextsArray() {
    val textsPage = Jsoup.connect("http://www.gr-oborona.ru/texts/").get()
    val links = textsPage.select("li > a")
    var counter = 1
    links.map { link ->
        val textUrl = link.attr("abs:href")
        val textInfo = extractTextFromPage(textUrl)
        writeTextToFile("texts/$counter.txt", textInfo.first, textInfo.second)
        counter += 1
    }
}

fun extractTextFromPage(url: String): Pair<String, String> {  //title, text
    val nl = " % "
    val page = Jsoup.connect(url).get()
    val title = page.title().split("( / )+".toRegex())[0]
    page.select("p").remove()
    page.select("ul").remove()
    val text = page.select("div[id='cont']").html().replace("<br>", nl).replace("&nbsp;", " ")
    return Pair(title, Jsoup.parse(text).text())
}

fun writeTextToFile(filename: String, title: String, text: String, newLineDelimiter: String = "%") {
    File(filename).printWriter().use { out ->
        out.println(title + "\n")
        out.println(text.replace(newLineDelimiter, "\n"))
    }
}