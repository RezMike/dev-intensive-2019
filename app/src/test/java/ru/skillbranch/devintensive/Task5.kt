package ru.skillbranch.devintensive

import compare
import org.junit.Test
import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import java.text.SimpleDateFormat
import java.util.*

class Task5 {

    @Test
    fun `Задание 5, добавление секунд`() {
        val date = Date(1561735505750)

        val expDate1 = Date(1561735506750)
        val expDate2 = Date(1561735510750)
        val expDate3 = Date(1561735515750)
        val expDate4 = Date(1561735535750)
        val expDate5 = Date(1561735565750)

        val newDate1 = date.add(1, TimeUnits.SECOND)
        val newDate2 = date.add(5, TimeUnits.SECOND)
        val newDate3 = date.add(10, TimeUnits.SECOND)
        val newDate4 = date.add(30, TimeUnits.SECOND)
        val newDate5 = date.add(60, TimeUnits.SECOND)

        compare(newDate1 == expDate1, "\"${expDate1.format()}\"", "\"${newDate1.format()}\"")
        compare(newDate2 == expDate2, "\"${expDate2.format()}\"", "\"${newDate2.format()}\"")
        compare(newDate3 == expDate3, "\"${expDate3.format()}\"", "\"${newDate3.format()}\"")
        compare(newDate4 == expDate4, "\"${expDate4.format()}\"", "\"${newDate4.format()}\"")
        compare(newDate5 == expDate5, "\"${expDate5.format()}\"", "\"${newDate5.format()}\"")
    }

    @Test
    fun `Задание 5, добавление минут`() {
        val date = Date(1561735505750)

        val expDate1 = Date(1561735565750)
        val expDate2 = Date(1561735805750)
        val expDate3 = Date(1561736105750)
        val expDate4 = Date(1561737305750)
        val expDate5 = Date(1561739105750)

        val newDate1 = date.add(1, TimeUnits.MINUTE)
        val newDate2 = date.add(5, TimeUnits.MINUTE)
        val newDate3 = date.add(10, TimeUnits.MINUTE)
        val newDate4 = date.add(30, TimeUnits.MINUTE)
        val newDate5 = date.add(60, TimeUnits.MINUTE)

        compare(newDate1 == expDate1, "\"${expDate1.format()}\"", "\"${newDate1.format()}\"")
        compare(newDate2 == expDate2, "\"${expDate2.format()}\"", "\"${newDate2.format()}\"")
        compare(newDate3 == expDate3, "\"${expDate3.format()}\"", "\"${newDate3.format()}\"")
        compare(newDate4 == expDate4, "\"${expDate4.format()}\"", "\"${newDate4.format()}\"")
        compare(newDate5 == expDate5, "\"${expDate5.format()}\"", "\"${newDate5.format()}\"")
    }

    @Test
    fun `Задание 5, добавление часов`() {
        val date = Date(1561735505750)

        val expDate1 = Date(1561739105750)
        val expDate2 = Date(1561753505750)
        val expDate3 = Date(1561771505750)
        val expDate4 = Date(1561843505750)
        val expDate5 = Date(1561951505750)

        val newDate1 = date.add(1, TimeUnits.HOUR)
        val newDate2 = date.add(5, TimeUnits.HOUR)
        val newDate3 = date.add(10, TimeUnits.HOUR)
        val newDate4 = date.add(30, TimeUnits.HOUR)
        val newDate5 = date.add(60, TimeUnits.HOUR)

        compare(newDate1 == expDate1, "\"${expDate1.format()}\"", "\"${newDate1.format()}\"")
        compare(newDate2 == expDate2, "\"${expDate2.format()}\"", "\"${newDate2.format()}\"")
        compare(newDate3 == expDate3, "\"${expDate3.format()}\"", "\"${newDate3.format()}\"")
        compare(newDate4 == expDate4, "\"${expDate4.format()}\"", "\"${newDate4.format()}\"")
        compare(newDate5 == expDate5, "\"${expDate5.format()}\"", "\"${newDate5.format()}\"")
    }

    @Test
    fun `Задание 5, добавление дней`() {
        val date = Date(1561735505750)

        val expDate1 = Date(1561821905750)
        val expDate2 = Date(1562167505750)
        val expDate3 = Date(1562599505750)
        val expDate4 = Date(1564327505750)
        val expDate5 = Date(1566919505750)

        val newDate1 = date.add(1, TimeUnits.DAY)
        val newDate2 = date.add(5, TimeUnits.DAY)
        val newDate3 = date.add(10, TimeUnits.DAY)
        val newDate4 = date.add(30, TimeUnits.DAY)
        val newDate5 = date.add(60, TimeUnits.DAY)

        compare(newDate1 == expDate1, "\"${expDate1.format()}\"", "\"${newDate1.format()}\"")
        compare(newDate2 == expDate2, "\"${expDate2.format()}\"", "\"${newDate2.format()}\"")
        compare(newDate3 == expDate3, "\"${expDate3.format()}\"", "\"${newDate3.format()}\"")
        compare(newDate4 == expDate4, "\"${expDate4.format()}\"", "\"${newDate4.format()}\"")
        compare(newDate5 == expDate5, "\"${expDate5.format()}\"", "\"${newDate5.format()}\"")
    }

    @Test
    fun `Задание 5, вычитание секунд`() {
        val date = Date(1561735505750)

        val expDate1 = Date(1561735504750)
        val expDate2 = Date(1561735500750)
        val expDate3 = Date(1561735495750)
        val expDate4 = Date(1561735475750)
        val expDate5 = Date(1561735445750)

        val newDate1 = date.add(-1, TimeUnits.SECOND)
        val newDate2 = date.add(-5, TimeUnits.SECOND)
        val newDate3 = date.add(-10, TimeUnits.SECOND)
        val newDate4 = date.add(-30, TimeUnits.SECOND)
        val newDate5 = date.add(-60, TimeUnits.SECOND)

        compare(newDate1 == expDate1, "\"${expDate1.format()}\"", "\"${newDate1.format()}\"")
        compare(newDate2 == expDate2, "\"${expDate2.format()}\"", "\"${newDate2.format()}\"")
        compare(newDate3 == expDate3, "\"${expDate3.format()}\"", "\"${newDate3.format()}\"")
        compare(newDate4 == expDate4, "\"${expDate4.format()}\"", "\"${newDate4.format()}\"")
        compare(newDate5 == expDate5, "\"${expDate5.format()}\"", "\"${newDate5.format()}\"")
    }

    @Test
    fun `Задание 5, вычитание минут`() {
        val date = Date(1561735505750)

        val expDate1 = Date(1561735445750)
        val expDate2 = Date(1561735205750)
        val expDate3 = Date(1561734905750)
        val expDate4 = Date(1561733705750)
        val expDate5 = Date(1561731905750)

        val newDate1 = date.add(-1, TimeUnits.MINUTE)
        val newDate2 = date.add(-5, TimeUnits.MINUTE)
        val newDate3 = date.add(-10, TimeUnits.MINUTE)
        val newDate4 = date.add(-30, TimeUnits.MINUTE)
        val newDate5 = date.add(-60, TimeUnits.MINUTE)

        compare(newDate1 == expDate1, "\"${expDate1.format()}\"", "\"${newDate1.format()}\"")
        compare(newDate2 == expDate2, "\"${expDate2.format()}\"", "\"${newDate2.format()}\"")
        compare(newDate3 == expDate3, "\"${expDate3.format()}\"", "\"${newDate3.format()}\"")
        compare(newDate4 == expDate4, "\"${expDate4.format()}\"", "\"${newDate4.format()}\"")
        compare(newDate5 == expDate5, "\"${expDate5.format()}\"", "\"${newDate5.format()}\"")
    }

    @Test
    fun `Задание 5, вычитание часов`() {
        val date = Date(1561735505750)

        val expDate1 = Date(1561731905750)
        val expDate2 = Date(1561717505750)
        val expDate3 = Date(1561699505750)
        val expDate4 = Date(1561627505750)
        val expDate5 = Date(1561519505750)

        val newDate1 = date.add(-1, TimeUnits.HOUR)
        val newDate2 = date.add(-5, TimeUnits.HOUR)
        val newDate3 = date.add(-10, TimeUnits.HOUR)
        val newDate4 = date.add(-30, TimeUnits.HOUR)
        val newDate5 = date.add(-60, TimeUnits.HOUR)

        compare(newDate1 == expDate1, "\"${expDate1.format()}\"", "\"${newDate1.format()}\"")
        compare(newDate2 == expDate2, "\"${expDate2.format()}\"", "\"${newDate2.format()}\"")
        compare(newDate3 == expDate3, "\"${expDate3.format()}\"", "\"${newDate3.format()}\"")
        compare(newDate4 == expDate4, "\"${expDate4.format()}\"", "\"${newDate4.format()}\"")
        compare(newDate5 == expDate5, "\"${expDate5.format()}\"", "\"${newDate5.format()}\"")
    }

    @Test
    fun `Задание 5, вычитание дней`() {
        val date = Date(1561735505750)

        val expDate1 = Date(1561649105750)
        val expDate2 = Date(1561303505750)
        val expDate3 = Date(1560871505750)
        val expDate4 = Date(1559143505750)
        val expDate5 = Date(1556551505750)

        val newDate1 = date.add(-1, TimeUnits.DAY)
        val newDate2 = date.add(-5, TimeUnits.DAY)
        val newDate3 = date.add(-10, TimeUnits.DAY)
        val newDate4 = date.add(-30, TimeUnits.DAY)
        val newDate5 = date.add(-60, TimeUnits.DAY)

        compare(newDate1 == expDate1, "\"${expDate1.format()}\"", "\"${newDate1.format()}\"")
        compare(newDate2 == expDate2, "\"${expDate2.format()}\"", "\"${newDate2.format()}\"")
        compare(newDate3 == expDate3, "\"${expDate3.format()}\"", "\"${newDate3.format()}\"")
        compare(newDate4 == expDate4, "\"${expDate4.format()}\"", "\"${newDate4.format()}\"")
        compare(newDate5 == expDate5, "\"${expDate5.format()}\"", "\"${newDate5.format()}\"")
    }

    private fun Date.format(): String {
        val dateFormat = SimpleDateFormat("HH:mm:ss.SSS dd.MM.yy", Locale("ru"))
        return dateFormat.format(this)
    }
}