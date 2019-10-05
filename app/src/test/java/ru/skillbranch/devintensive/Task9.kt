package ru.skillbranch.devintensive

import compare
import org.junit.Test
import ru.skillbranch.devintensive.models.User
import java.text.SimpleDateFormat
import java.util.*

class Task9 {

    @Test
    fun `Задание 9, вызов Builder без парамеров`() {
        val user = User.Builder().build()

        compare(
            user.firstName == null,
            "User(firstName = null)",
            "User(firstName = \"${user.firstName}\")"
        )
        compare(
            user.lastName == null,
            "User(lastName = null)",
            "User(lastName = \"${user.lastName}\")"
        )
        compare(user.avatar == null, "User(avatar = null)", "User(avatar = \"${user.avatar}\")")
        compare(user.rating == 0, "User(rating = 0)", "User(rating = ${user.rating})")
        compare(user.respect == 0, "User(respect = 0)", "User(respect = ${user.respect})")
        compare(
            user.lastVisit == null,
            "User(lastVisit = null)",
            "User(lastVisit = ${user.lastVisit?.format()})"
        )
        compare(!user.isOnline, "User(isOnline = false)", "User(isOnline = ${user.isOnline})")
    }

    @Test
    fun `Задание 9, вызов Builder с параметрами`() {
        val id = "123"
        val firstName = "Михаил"
        val lastName = "Макеев"
        val avatar = "https://anyurl.com/image"
        val rating = 1250
        val respect = 50
        val lastVisit = Date()
        val isOnline = true

        val user = User.Builder()
            .id(id)
            .firstName(firstName)
            .lastName(lastName)
            .avatar(avatar)
            .rating(rating)
            .respect(respect)
            .lastVisit(lastVisit)
            .isOnline(isOnline)
            .build()

        compare(user.id == id, "User(id = \"$id\")", "User(id = \"${user.id}\")")
        compare(
            user.firstName == firstName,
            "User(firstName = \"$firstName\")",
            "User(firstName = \"${user.firstName}\")"
        )
        compare(
            user.lastName == lastName,
            "User(lastName = \"$lastName\")",
            "User(lastName = \"${user.lastName}\")"
        )
        compare(
            user.avatar == avatar,
            "User(avatar = \"$avatar\")",
            "User(avatar = \"${user.avatar}\")"
        )
        compare(user.rating == rating, "User(rating = $rating)", "User(rating = ${user.rating})")
        compare(
            user.respect == respect,
            "User(respect = $respect)",
            "User(respect = ${user.respect})"
        )
        compare(
            user.lastVisit?.time == lastVisit.time,
            "User(lastVisit = \"${lastVisit.format()}\")",
            "User(lastVisit = \"${user.lastVisit?.format()}\""
        )
        compare(
            user.isOnline == isOnline,
            "User(isOnline = $isOnline)",
            "User(isOnline = ${user.isOnline})"
        )
    }

    private fun Date.format(): String {
        val dateFormat = SimpleDateFormat("HH:mm:ss.SSS dd.MM.yy", Locale("ru"))
        return dateFormat.format(this)
    }
}