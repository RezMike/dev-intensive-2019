package ru.skillbranch.devintensive

import compare
import org.junit.Test
import ru.skillbranch.devintensive.extensions.format
import java.text.SimpleDateFormat

class Task4 {

    @Test
    fun `Задание 4, проверка метода format без параметра`() {
        val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:ss XXX")
        val date1 = sdf.parse("01-07-2019 14:00:00 +00:00")
        val date2 = sdf.parse("02-07-2019 15:11:00 +00:00")
        val date3 = sdf.parse("12-01-2020 16:11:22 +00:00")
        val expected1 = "14:00:00 01.07.19"
        val expected2 = "15:11:00 02.07.19"
        val expected3 = "16:11:22 12.01.20"

        val formatted1 = date1.format()
        val formatted2 = date2.format()
        val formatted3 = date3.format()

        compare(formatted1 == expected1, expected1, formatted1)
        compare(formatted2 == expected2, expected2, formatted2)
        compare(formatted3 == expected3, expected3, formatted3)
    }

    @Test
    fun `Задание 4, проверка метода format с параметром`() {
        val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:ss XXX")
        val date1 = sdf.parse("01-07-2019 14:00:00 +00:00")
        val date2 = sdf.parse("02-07-2019 15:11:00 +00:00")
        val date3 = sdf.parse("12-01-2020 16:11:22 +00:00")
        val expected1 = "14:00:00"
        val expected2 = "15:11:00"
        val expected3 = "16:11:22"
        val expected4 = "01.07.2019"
        val expected5 = "02.07.2019"
        val expected6 = "12.01.2020"

        val formatted1 = date1.format("HH:mm:ss")
        val formatted2 = date2.format("HH:mm:ss")
        val formatted3 = date3.format("HH:mm:ss")
        val formatted4 = date1.format("dd.MM.yyyy")
        val formatted5 = date2.format("dd.MM.yyyy")
        val formatted6 = date3.format("dd.MM.yyyy")

        compare(formatted1 == expected1, expected1, formatted1)
        compare(formatted2 == expected2, expected2, formatted2)
        compare(formatted3 == expected3, expected3, formatted3)
        compare(formatted4 == expected4, expected4, formatted4)
        compare(formatted5 == expected5, expected5, formatted5)
        compare(formatted6 == expected6, expected6, formatted6)
    }
}