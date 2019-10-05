package ru.skillbranch.devintensive

import org.junit.Test

import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.toUserView
import ru.skillbranch.devintensive.models.*
import java.util.*

class ExampleUnitTest {

    @Test
    fun test_instance() {
        val user = User("1")
        val user2 = User("2", "John", "Wick")
        val user3 = User(
            "3",
            "John",
            "Silverhand",
            null,
            lastVisit = Date(),
            isOnline = true
        )

        println("$user $user2 $user3")
    }

    @Test
    fun test_factory() {
        val user = User.makeUser("John Cena")
        val user2 = user.copy(id = "2", lastName = "Cena")
        println(user)
        println(user2)
    }

    @Test
    fun test_decomposition() {
        val user = User.makeUser("John Wick")

        val (id, firstName, lastName) = user

        println("$id $firstName $lastName")
        println("${user.component1()} ${user.component2()} ${user.component3()}")
    }

    @Test
    fun test_copy() {
        val user = User.makeUser("John Wick")
        val user2 = user.copy(id = "2", lastName = "Doe", lastVisit = Date().add(-2, TimeUnits.MINUTE))

        println(
            (if (user == user2) "equals" else "not equals") + " data and hash\n" +
                    "${user.hashCode()} $user\n" +
                    "${user2.hashCode()} $user2"
        )

        println(
            (if (user === user2) "equals" else "not equals") + " address\n" +
                    "${System.identityHashCode(user)}\n" +
                    "${System.identityHashCode(user2)}"
        )
    }

    @Test
    fun test_data_mapping() {
        val user = User.makeUser("Макеев Михаил").copy(lastVisit = Date().add(-600, TimeUnits.DAY))
        println(user)

        val userView = user.toUserView()
        userView.printMe()
    }

    @Test
    fun test_abstract_factory() {
        val user = User.makeUser("Макеев Михаил")
        val textMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "any text message", type = "text")
        val imageMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "any image url", type = "image")

        println(textMessage.formatMessage())
        println(imageMessage.formatMessage())
    }
}
