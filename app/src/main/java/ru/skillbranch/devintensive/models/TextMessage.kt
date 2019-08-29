package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extensions.humanizeDiff
import ru.skillbranch.devintensive.models.data.Chat
import ru.skillbranch.devintensive.models.data.User
import java.util.*

class TextMessage(
    id: String,
    from: User,
    chat: Chat,
    isIncoming: Boolean = false,
    date: Date = Date(),
    isReaded: Boolean = false,
    var text: String?
) : BaseMessage(id, from, chat, isIncoming, date, isReaded) {

    override fun formatMessage() = "${from.firstName} " +
            "${if (isIncoming) "получил" else "отправил"} сообщение \"$text\" ${date.humanizeDiff()}"
}
