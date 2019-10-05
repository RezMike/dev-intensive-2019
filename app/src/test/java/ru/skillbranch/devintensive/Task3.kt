package ru.skillbranch.devintensive

import compare
import org.junit.Test
import ru.skillbranch.devintensive.utils.Utils

class Task3 {

    @Test
    fun `Задание 3, парсинг null`() {
        comparePair(Utils.parseFullName(null), null to null)
    }

    @Test
    fun `Задание 3, парсинг пустой строки`() {
        comparePair(Utils.parseFullName(""), null to null)
    }

    @Test
    fun `Задание 3, парсинг пробельной строки`() {
        comparePair(Utils.parseFullName(" "), null to null)
        comparePair(Utils.parseFullName("  "), null to null)
        comparePair(Utils.parseFullName("   "), null to null)
        comparePair(Utils.parseFullName("    "), null to null)
        comparePair(Utils.parseFullName("     "), null to null)
    }

    @Test
    fun `Задание 3, парсинг строки с одним именем`() {
        comparePair(Utils.parseFullName("Михаил"), "Михаил" to null)
    }

    @Test
    fun `Задание 3, парсинг строки с именем и пробелами`() {
        comparePair(Utils.parseFullName("Макеев "), "Макеев" to null)
        comparePair(Utils.parseFullName("Макеев  "), "Макеев" to null)
        comparePair(Utils.parseFullName("Макеев   "), "Макеев" to null)
        comparePair(Utils.parseFullName(" Михаил"), "Михаил" to null)
        comparePair(Utils.parseFullName("  Михаил"), "Михаил" to null)
        comparePair(Utils.parseFullName("   Михаил"), "Михаил" to null)
    }

    @Test
    fun `Задание 3, парсинг полной строки`() {
        comparePair(Utils.parseFullName("Михаил Макеев"), "Михаил" to "Макеев")
        comparePair(Utils.parseFullName("John Lennon"), "John" to "Lennon")
        comparePair(Utils.parseFullName("John Doe"), "John" to "Doe")
    }

    private fun comparePair(pair: Pair<String?, String?>, expPair: Pair<String?, String?>) {
        compare(
            pair == expPair,
            "\"${expPair.first} ${expPair.second}\"",
            "\"${pair.first} ${pair.second}\""
        )
    }
}