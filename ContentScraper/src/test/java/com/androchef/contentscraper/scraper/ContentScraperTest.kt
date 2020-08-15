package com.androchef.contentscraper.scraper

import com.androchef.contentscraper.ContentScraper
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.internal.runners.JUnit4ClassRunner
import org.junit.runner.RunWith

@RunWith(JUnit4ClassRunner::class)
class ContentScraperTest {

    lateinit var contentScraper: ContentScraper

    @Before
    fun setUp() {
        contentScraper = ContentScraper.get()
    }

    @Test
    fun getNthCharacter_emptyString_fail() {
        // Arrange
        val emptyString = ""

        // Act
        val response = contentScraper.getNthCharacter(emptyString, 10)

        // Assert
        assertEquals(null,response)
    }

    @Test
    fun getNthCharacter_stringLessThanGivenN_fail() {
        // Arrange
        val inputString = "1234567"

        // Act
        val response = contentScraper.getNthCharacter(inputString, 10)

        // Assert
        assertEquals(null,response)
    }

    @Test
    fun getNthCharacter_success() {
        // Arrange
        val inputString = "123456789"

        // Act
        val response = contentScraper.getNthCharacter(inputString, 5)

        // Assert
        assertEquals("5",response)
    }

    @Test
    fun getEveryNthCharacter_emptyString_fail() {
        // Arrange
        val emptyString = ""

        // Act
        val response = contentScraper.getEveryNthCharacter(emptyString, 2)

        // Assert
        assertEquals(null,response)
    }

    @Test
    fun getEveryNthCharacter_stringLessThanGivenN_fail() {
        // Arrange
        val inputString = "1"

        // Act
        val response = contentScraper.getEveryNthCharacter(inputString, 2)

        // Assert
        assertEquals(null,response)
    }

    @Test
    fun getEveryNthCharacter_success() {
        // Arrange
        val inputString = "12345678901234567890123456789"

        // Act
        val response = contentScraper.getEveryNthCharacter(inputString, 10)

        // Assert
        assertEquals("0, 0",response)
    }

    @Test
    fun geUniqueWord_emptyString_fail() {
        // Arrange
        val emptyString = ""

        // Act
        val response = contentScraper.getUniqueWordsAndTheirCounts(emptyString)

        // Assert
        assertEquals(null,response)
    }

    @Test
    fun geUniqueWord_only1char_Success() {
        // Arrange
        val emptyString = "1"

        // Act
        val response = contentScraper.getUniqueWordsAndTheirCounts(emptyString)

        // Assert
        assertEquals("* 1 = 1\n",response)
    }

    @Test
    fun geUniqueWord_Success() {
        // Arrange
        val emptyString = "1 2 3 3 4 4 5 6 7 7"

        // Act
        val response = contentScraper.getUniqueWordsAndTheirCounts(emptyString)

        // Assert
        assertEquals("* 1 = 1\n" +
                "* 2 = 1\n" +
                "* 3 = 2\n" +
                "* 4 = 2\n" +
                "* 5 = 1\n" +
                "* 6 = 1\n" +
                "* 7 = 2\n",response)
    }

}