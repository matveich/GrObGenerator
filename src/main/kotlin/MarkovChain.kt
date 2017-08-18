class MarkovChain(windowSize: Int) {
    var chains = mutableMapOf<List<String>, MutableMap<String, Int>>()

    init {
        val wb = loadWordsBag("texts", windowSize)
        wb.map {
            for (i in 0..it.size-windowSize-1) {
                val windowKey = it.slice(IntRange(i, i + windowSize - 1))
                val key = it[i + windowSize]
                chains.putIfAbsent(windowKey, mutableMapOf())
                chains[windowKey]!![key] = (chains[windowKey]!![key] ?: 0) + 1
            }
        }
        //for ((k, v) in chains)
        //   println("$k -> $v")
    }

    fun generate(): String {
        var genText = ""
        var currentWord = "*START*"
        while (currentWord != "*END*") {
            val key = getRandomKey(currentWord)
            genText += key.joinToString(separator = " ", postfix = " ")
            currentWord = getNextWord(key)
        }
        return genText
                .replace("%", "\n")
                .replace("*START*", "")
                .replace("*END*", "")
    }

    fun getRandomKey(word: String): List<String> {
        var tmpKeyArr = arrayOf<List<String>>()
        chains.map {
            if (word == it.key[0])
                tmpKeyArr += it.key
        }
        return tmpKeyArr[(Math.random() * tmpKeyArr.size).toInt()]
    }

    fun getNextWord(key: List<String>): String {
        val listOfPairs = chains[key]!!.toList()
        var randList = listOf<String>()
        for (pair in listOfPairs) {
            for (i in 0 until pair.second)
                randList += pair.first
        }
        val randNumber = Math.random()
        return randList[(randNumber * randList.size).toInt()]
    }
}