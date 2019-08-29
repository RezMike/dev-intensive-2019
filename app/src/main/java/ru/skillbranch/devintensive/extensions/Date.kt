package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.extensions.TimeUnits.*
import java.text.SimpleDateFormat
import java.util.*

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.shortFormat(): String? {
    val pattern = if (this.isSameDate(Date())) "HH:mm" else "dd.MM.yy"
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.isSameDate(date: Date): Boolean {
    val day1 = this.time / DAY.size
    val day2 = date.time / DAY.size
    return day1 == day2
}

fun Date.add(value: Int, units: TimeUnits = SECOND): Date {
    time += value * units.size
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val diff = ((date.time + 200) / 1000 - (time + 200) / 1000) * 1000

    return if (diff >= 0) {
        when (diff) {
            in 0.sec..1.sec -> "только что"
            in 2.sec..45.sec -> "несколько секунд назад"
            in 46.sec..75.sec -> "минуту назад"
            in 76.sec..45.min -> "${MINUTE.plural(diff.asMin)} назад"
            in 46.min..75.min -> "час назад"
            in 76.min..22.hour -> "${HOUR.plural(diff.asHour)} назад"
            in 23.hour..26.hour -> "день назад"
            in 27.hour..360.day -> "${DAY.plural(diff.asDay)} назад"
            else -> "более года назад"
        }
    } else {
        when (diff) {
            in (-1).sec..0.sec -> "прямо сейчас"
            in (-45).sec..(-1).sec -> "через несколько секунд"
            in (-75).sec..(-45).sec -> "через минуту"
            in (-45).min..(-75).sec -> "через ${MINUTE.plural(diff.asMin)}"
            in (-75).min..(-45).min -> "через час"
            in (-22).hour..(-75).min -> "через ${HOUR.plural(diff.asHour)}"
            in (-26).hour..(-22).hour -> "через день"
            in (-360).day..(-26).hour -> "через ${DAY.plural(diff.asDay)}"
            else -> "более чем через год"
        }
    }
}
