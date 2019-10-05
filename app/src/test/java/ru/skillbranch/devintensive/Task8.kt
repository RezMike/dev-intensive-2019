package ru.skillbranch.devintensive

import `Ошибка сравнения`
import compareEquals
import org.junit.Test
import ru.skillbranch.devintensive.extensions.humanizeDiff
import java.util.*

class Task8 {

    @Test
    fun `Задание 8, проверка будущих дат`() {
        val curDate = Date()
        val curTime = curDate.time
        val mesDate = Date(curTime)
        compareEquals(mesDate.humanizeDiff(curDate), "только что")
        if (curDate.time != curTime || mesDate.time != curTime)
            throw `Ошибка сравнения`("При вызове humanizeDiff даты не должны изменяться!")

        compareEquals(mesDate.add(1, TimeUnits.SECOND).humanizeDiff(curDate), "только что")
        compareEquals(
            mesDate.add(2, TimeUnits.SECOND).humanizeDiff(curDate),
            "через несколько секунд"
        )
        compareEquals(
            mesDate.add(45, TimeUnits.SECOND).humanizeDiff(curDate),
            "через несколько секунд"
        )
        compareEquals(mesDate.add(46, TimeUnits.SECOND).humanizeDiff(curDate), "через минуту")
        compareEquals(mesDate.add(75, TimeUnits.SECOND).humanizeDiff(curDate), "через минуту")
        compareEquals(mesDate.add(76, TimeUnits.SECOND).humanizeDiff(curDate), "через 1 минуту")
        compareEquals(mesDate.add(2, TimeUnits.MINUTE).humanizeDiff(curDate), "через 2 минуты")
        compareEquals(mesDate.add(45, TimeUnits.MINUTE).humanizeDiff(curDate), "через 45 минут")
        compareEquals(mesDate.add(46, TimeUnits.MINUTE).humanizeDiff(curDate), "через час")
        compareEquals(mesDate.add(75, TimeUnits.MINUTE).humanizeDiff(curDate), "через час")
        compareEquals(mesDate.add(76, TimeUnits.MINUTE).humanizeDiff(curDate), "через 1 час")
        compareEquals(mesDate.add(4, TimeUnits.HOUR).humanizeDiff(curDate), "через 4 часа")
        compareEquals(mesDate.add(6, TimeUnits.HOUR).humanizeDiff(curDate), "через 6 часов")
        compareEquals(mesDate.add(11, TimeUnits.HOUR).humanizeDiff(curDate), "через 11 часов")
        compareEquals(mesDate.add(22, TimeUnits.HOUR).humanizeDiff(curDate), "через 22 часа")
        compareEquals(mesDate.add(23, TimeUnits.HOUR).humanizeDiff(curDate), "через день")
        compareEquals(mesDate.add(26, TimeUnits.HOUR).humanizeDiff(curDate), "через день")
        compareEquals(mesDate.add(27, TimeUnits.HOUR).humanizeDiff(curDate), "через 1 день")
        compareEquals(mesDate.add(12, TimeUnits.DAY).humanizeDiff(curDate), "через 12 дней")
        compareEquals(mesDate.add(112, TimeUnits.DAY).humanizeDiff(curDate), "через 112 дней")
        compareEquals(mesDate.add(360, TimeUnits.DAY).humanizeDiff(curDate), "через 360 дней")
        compareEquals(mesDate.add(361, TimeUnits.DAY).humanizeDiff(curDate), "более чем через год")
        compareEquals(
            mesDate.add(12345, TimeUnits.DAY).humanizeDiff(curDate),
            "более чем через год"
        )
    }

    @Test
    fun `Задание 8, проверка прошедших дат`() {
        val curDate = Date()
        val curTime = curDate.time
        val mesDate = Date(curTime)

        compareEquals(mesDate.add(-1, TimeUnits.SECOND).humanizeDiff(curDate), "только что")
        compareEquals(
            mesDate.add(-2, TimeUnits.SECOND).humanizeDiff(curDate),
            "несколько секунд назад"
        )
        compareEquals(
            mesDate.add(-45, TimeUnits.SECOND).humanizeDiff(curDate),
            "несколько секунд назад"
        )
        compareEquals(mesDate.add(-46, TimeUnits.SECOND).humanizeDiff(curDate), "минуту назад")
        compareEquals(mesDate.add(-75, TimeUnits.SECOND).humanizeDiff(curDate), "минуту назад")
        compareEquals(mesDate.add(-76, TimeUnits.SECOND).humanizeDiff(curDate), "1 минуту назад")
        compareEquals(mesDate.add(-3, TimeUnits.MINUTE).humanizeDiff(curDate), "3 минуты назад")
        compareEquals(mesDate.add(-45, TimeUnits.MINUTE).humanizeDiff(curDate), "45 минут назад")
        compareEquals(mesDate.add(-46, TimeUnits.MINUTE).humanizeDiff(curDate), "час назад")
        compareEquals(mesDate.add(-75, TimeUnits.MINUTE).humanizeDiff(curDate), "час назад")
        compareEquals(mesDate.add(-76, TimeUnits.MINUTE).humanizeDiff(curDate), "1 час назад")
        compareEquals(mesDate.add(-4, TimeUnits.HOUR).humanizeDiff(curDate), "4 часа назад")
        compareEquals(mesDate.add(-6, TimeUnits.HOUR).humanizeDiff(curDate), "6 часов назад")
        compareEquals(mesDate.add(-11, TimeUnits.HOUR).humanizeDiff(curDate), "11 часов назад")
        compareEquals(mesDate.add(-22, TimeUnits.HOUR).humanizeDiff(curDate), "22 часа назад")
        compareEquals(mesDate.add(-23, TimeUnits.HOUR).humanizeDiff(curDate), "день назад")
        compareEquals(mesDate.add(-26, TimeUnits.HOUR).humanizeDiff(curDate), "день назад")
        compareEquals(mesDate.add(-27, TimeUnits.HOUR).humanizeDiff(curDate), "1 день назад")
        compareEquals(mesDate.add(-12, TimeUnits.DAY).humanizeDiff(curDate), "12 дней назад")
        compareEquals(mesDate.add(-112, TimeUnits.DAY).humanizeDiff(curDate), "112 дней назад")
        compareEquals(mesDate.add(-360, TimeUnits.DAY).humanizeDiff(curDate), "360 дней назад")
        compareEquals(mesDate.add(-361, TimeUnits.DAY).humanizeDiff(curDate), "более года назад")
        compareEquals(mesDate.add(-12345, TimeUnits.DAY).humanizeDiff(curDate), "более года назад")
    }

    private fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
        return Date(time + value * units.size)
    }

    private enum class TimeUnits(val size: Long) {
        SECOND(1000L),
        MINUTE(60 * SECOND.size),
        HOUR(60 * MINUTE.size),
        DAY(24 * HOUR.size)
    }
}