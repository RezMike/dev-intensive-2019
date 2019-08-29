package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.extensions.Plurals.*

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
