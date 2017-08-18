import java.nio.file.Files
import java.nio.file.Paths

fun loadWordsBag(folder: String, windowSize: Int): Array<Array<String>> {
    var wordsBag = arrayOf<Array<String>>()
    Files.walk(Paths.get(folder)).use { paths ->
        paths
                .filter { path -> Files.isRegularFile(path) }
                .forEach { path ->
                    val lines = path.toFile().readLines().toTypedArray()
                    val words = getWords(lines, windowSize)
                    wordsBag += words
                }
    }
    return wordsBag
}

fun getWords(_lines: Array<String>, windowSize: Int): Array<String> {
    var wordsArray = arrayOf<String>()
    var lines = _lines
    lines = lines.filter { it.contains("\\S+".toRegex()) }.toTypedArray()
    lines = lines.sliceArray(IntRange(1, lines.size - 1))
    lines[0] = "*START* " + lines[0]
    for (i in 0..windowSize)
        lines[lines.size-1] += " *END* "
    for (i in 0 until lines.size) {
        ",.!?\";:')".map {
            lines[i] = lines[i].replace(it.toString(), it + " ")
        }
        wordsArray += (lines[i] + " %")
                .split("\\s+".toRegex())
                .filter { it.contains("\\S+".toRegex()) }
    }
    return wordsArray
}