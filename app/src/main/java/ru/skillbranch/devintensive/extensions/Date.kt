package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.extensions.Plurals.*
import ru.skillbranch.devintensive.extensions.TimeUnits.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.absoluteValue

enum class TimeUnits(val size: Long) {
    SECOND(1000L),
    MINUTE(60 * SECOND.size),
    HOUR(60 * MINUTE.size),
    DAY(24 * HOUR.size);

    fun plural(value: Long) = "$value ${pluralStrings[value.asPlurals]}"
}

val TimeUnits.pluralStrings
    get() = when (this) {
        SECOND -> mapOf(ONE to "секунду", FEW to "секунды", MANY to "секунд")
        MINUTE -> mapOf(ONE to "минуту", FEW to "минуты", MANY to "минут")
        HOUR -> mapOf(ONE to "час", FEW to "часа", MANY to "часов")
        DAY -> mapOf(ONE to "день", FEW to "дня", MANY to "дней")
    }

val Int.sec get() = this * SECOND.size
val Int.min get() = this * MINUTE.size
val Int.hour get() = this * HOUR.size
val Int.day get() = this * DAY.size

val Long.asMin get() = this.absoluteValue / MINUTE.size
val Long.asHour get() = this.absoluteValue / HOUR.size
val Long.asDay get() = this.absoluteValue / DAY.size

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
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
            in 76.sec..45.min -> "${TimeUnits.MINUTE.plural(diff.asMin)} назад"
            in 46.min..75.min -> "час назад"
            in 76.min..22.hour -> "${TimeUnits.HOUR.plural(diff.asHour)} назад"
            in 23.hour..26.hour -> "день назад"
            in 27.hour..360.day -> "${TimeUnits.DAY.plural(diff.asDay)} назад"
            else -> "более года назад"
        }
    } else {
        when (diff) {
            in (-1).sec..0.sec -> "прямо сейчас"
            in (-45).sec..(-1).sec -> "через несколько секунд"
            in (-75).sec..(-45).sec -> "через минуту"
            in (-45).min..(-75).sec -> "через ${TimeUnits.MINUTE.plural(diff.asMin)}"
            in (-75).min..(-45).min -> "через час"
            in (-22).hour..(-75).min -> "через ${TimeUnits.HOUR.plural(diff.asHour)}"
            in (-26).hour..(-22).hour -> "через день"
            in (-360).day..(-26).hour -> "через ${TimeUnits.DAY.plural(diff.asDay)}"
            else -> "более чем через год"
        }
    }
}

enum class Plurals {
    ONE,
    FEW,
    MANY
}

val Long.asPlurals
    get() = when {
        this % 100L in 5L..20L -> MANY
        this % 10L == 1L -> ONE
        this % 10L in 2L..4L -> FEW
        else -> MANY
    }
