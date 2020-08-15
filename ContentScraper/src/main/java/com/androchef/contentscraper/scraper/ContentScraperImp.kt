package com.androchef.contentscraper.scraper

import com.androchef.contentscraper.ContentScraper
import com.androchef.contentscraper.utils.addWithComma
import com.androchef.contentscraper.utils.isHavingLength
import com.androchef.contentscraper.utils.isValid
import java.lang.StringBuilder
import java.util.*

internal class ContentScraperImp : ContentScraper {

    /**
     * @param inputString string to character.
     * @param n nth position to get character.
     * @return string with 1 char.
     */
    override fun getNthCharacter(inputString: String, n: Int): String? {
        return if (inputString.isValid() && inputString.isHavingLength(n))
            return inputString[n - 1].toString()
        else
            null
    }

    /**
     * @param inputString string to characters.
     * @param n every nth position to get character.
     * @return every nth character separated by comma ex: a, b, c, d......
     */
    override fun getEveryNthCharacter(inputString: String, n: Int): String? {
        return if (inputString.isValid() && inputString.isHavingLength(n)) {

            var returningResult = ""

            var indexOfEveryNthChar = n - 1

            while (inputString.length > indexOfEveryNthChar) {
                returningResult = returningResult.addWithComma(inputString[indexOfEveryNthChar])
                indexOfEveryNthChar += n
            }

            returningResult
        } else
            null
    }

    /**
     * @param inputString string to get unique words from.
     * @return unique word with their count in readable format.
     */
    override fun getUniqueWordsAndTheirCounts(inputString: String): String? {

        // split string by space
        val allWordsList = inputString.split(" ")

        // HashMap saving unique words as key and count as value.
        val mapForWordAndCount = hashMapOf<String, Int>()

        allWordsList.forEach { word ->
            // for case insensitive converting word to lower case
            val wordInLowerCase = word.toLowerCase(Locale.getDefault())

            // if HashMap already has word increment it's count by 1
            // else add this new word to HashMap.
            if (mapForWordAndCount.containsKey(wordInLowerCase)) {
                val earlierCount = mapForWordAndCount[wordInLowerCase]!!
                mapForWordAndCount[wordInLowerCase] = earlierCount.plus(1)
            } else {
                mapForWordAndCount[wordInLowerCase] = 1
            }
        }

        //beautifying string
        val stringBuilder = StringBuilder()
        for (wordAndItsCount in mapForWordAndCount) {
            stringBuilder.append("* ${wordAndItsCount.key} = ${wordAndItsCount.value}\n")
        }

        return stringBuilder.toString()
    }
}