package ru.skillbranch.devintensive

import compare
import org.junit.Test
import ru.skillbranch.devintensive.models.*
import java.util.*

class Task2 {

    @Test
    fun `Задание 2, создание TextMessage`() {
        val firstName = "Михаил"
        val lastName = "Макеев"
        val user = User("0", firstName, lastName)
        val text = "Any text message"

        val message = BaseMessage.makeMessage(user, Chat("0"), Date(), "text", text)

        compare(message is TextMessage, "TextMessage", message::class.java.simpleName)
        compare(
            message.from?.firstName == firstName && message.from?.lastName == lastName,
            "from=\"$firstName $lastName\"",
            "from=\"${message.from?.firstName} ${message.from?.lastName}\""
        )
        compare(
            (message as TextMessage).text == text,
            "text=\"$text\"",
            "text=\"${message.text}\""
        )
        compare(!message.isIncoming, "isIncoming=false", "isIncoming=${message.isIncoming}")
    }

    @Test
    fun `Задание 2, создание ImageMessage`() {
        val firstName = "Михаил"
        val lastName = "Макеев"
        val user = User("0", firstName, lastName)
        val image = "https://anyurl.com"

        val message = BaseMessage.makeMessage(user, Chat("0"), Date(), "image", image, true)

        compare(message is ImageMessage, "ImageMessage", message::class.java.simpleName)
        compare(
            message.from?.firstName == firstName && message.from?.lastName == lastName,
            "from=\"$firstName $lastName\"",
            "from=\"${message.from?.firstName} ${message.from?.lastName}\""
        )
        compare(
            (message as ImageMessage).image == image,
            "image=\"$image\"",
            "image=\"${message.image}\""
        )
        compare(message.isIncoming, "isIncoming=true", "isIncoming=${message.isIncoming}")
    }

    @Test
    fun `Задание 2, проверка метода formatMessage`() {
        val firstName = "Михаил"
        val lastName = "Макеев"
        val user = User("0", firstName, lastName)
        val text1 = "Any text message"
        val text2 = "Some message"
        val image1 = "https://anyurl.com"
        val image2 = "http://example.com"

        val message1 = TextMessage("0", user, Chat("0"), false, Date(), text1)
        val expected1 = "$firstName отправил сообщение \"$text1\""

        val message2 = TextMessage("0", user, Chat("0"), true, Date(), text2)
        val expected2 = "$firstName получил сообщение \"$text2\""

        val message3 = ImageMessage("0", user, Chat("0"), false, Date(), image1)
        val expected3 = "$firstName отправил изображение \"$image1\""

        val message4 = ImageMessage("0", user, Chat("0"), true, Date(), image2)
        val expected4 = "$firstName получил изображение \"$image2\""

        compare(
            message1.formatMessage().startsWith(expected1),
            "\"$expected1...\"",
            "\"${message1.formatMessage()}\""
        )
        compare(
            message2.formatMessage().startsWith(expected2),
            "\"$expected2...\"",
            "\"${message2.formatMessage()}\""
        )
        compare(
            message3.formatMessage().startsWith(expected3),
            "\"$expected3...\"",
            "\"${message3.formatMessage()}\""
        )
        compare(
            message4.formatMessage().startsWith(expected4),
            "\"$expected4...\"",
            "\"${message4.formatMessage()}\""
        )
    }
}