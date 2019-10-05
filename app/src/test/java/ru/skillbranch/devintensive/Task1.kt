package ru.skillbranch.devintensive

import compare
import org.junit.Test
import ru.skillbranch.devintensive.models.User

class Task1 {

    @Test
    fun `Задание 1, проверка метода makeUser`() {
        val firstName = "Михаил"
        val lastName = "Макеев"

        val user = User.makeUser("$firstName $lastName")

        compare(
            firstName == user.firstName && lastName == user.lastName,
            """User("$firstName", "$lastName")""",
            """User("${user.firstName}", "${user.lastName}")"""
        )
        compare(
            user.avatar == null,
            "user.avatar == null",
            "user.avatar == \"${user.avatar}\""
        )
        compare(
            user.avatar == null,
            "user.avatar == null",
            "user.avatar == \"${user.avatar}\""
        )
        compare(
            user.rating == 0,
            "user.rating == 0",
            "user.rating == ${user.rating}"
        )
        compare(
            user.respect == 0,
            "user.respect == 0",
            "user.respect == ${user.respect}"
        )
        compare(
            !user.isOnline,
            "user.isOnline == false",
            "user.isOnline == ${user.isOnline}"
        )
    }
}