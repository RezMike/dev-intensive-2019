package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils

data class Profile(
    val firstName: String,
    val lastName: String,
    val about: String,
    val repository: String,
    val rating: Int = 0,
    val respect: Int = 0
) {
    val rank = "Junior Android Developer"
}

val Profile.nickName
    get() = Utils.transliteration("$firstName $lastName", "_")

val Profile.initials
    get() = Utils.toInitials(firstName, lastName)

fun Profile.toMap() = mapOf(
    "nickName" to nickName,
    "rank" to rank,
    "firstName" to firstName,
    "lastName" to lastName,
    "about" to about,
    "repository" to repository,
    "rating" to rating,
    "respect" to respect
)
