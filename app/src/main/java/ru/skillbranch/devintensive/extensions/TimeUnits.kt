package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.extensions.TimeUnits.*
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
        SECOND -> mapOf(Plurals.ONE to "секунду", Plurals.FEW to "секунды", Plurals.MANY to "секунд")
        MINUTE -> mapOf(Plurals.ONE to "минуту", Plurals.FEW to "минуты", Plurals.MANY to "минут")
        HOUR -> mapOf(Plurals.ONE to "час", Plurals.FEW to "часа", Plurals.MANY to "часов")
        DAY -> mapOf(Plurals.ONE to "день", Plurals.FEW to "дня", Plurals.MANY to "дней")
    }

val Int.sec get() = this * SECOND.size
val Int.min get() = this * MINUTE.size
val Int.hour get() = this * HOUR.size
val Int.day get() = this * DAY.size

val Long.asMin get() = this.absoluteValue / MINUTE.size
val Long.asHour get() = this.absoluteValue / HOUR.size
val Long.asDay get() = this.absoluteValue / DAY.size
