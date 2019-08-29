package ru.skillbranch.devintensive.models.data

import androidx.annotation.VisibleForTesting
import ru.skillbranch.devintensive.extensions.shortFormat
import ru.skillbranch.devintensive.models.BaseMessage
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class Chat(
    val id: String,
    val title: String,
    val members: List<User> = listOf(),
    var messages: MutableList<BaseMessage> = mutableListOf(),
    var isArchived: Boolean = false
) {

    private fun isSingle() = members.size == 1

    fun toChatItem() = if (isSingle()) {
        val user = members.first()
        ChatItem(
            id,
            user.avatar,
            Utils.toInitials(user.firstName, user.lastName) ?: "??",
            "${user.firstName.orEmpty()} ${user.lastName.orEmpty()}",
            lastMessageShort().first,
            unreadableMessageCount(),
            lastMessageDate()?.shortFormat(),
            user.isOnline
        )
    } else {
        ChatItem(
            id,
            null,
            "",
            title,
            lastMessageShort().first,
            unreadableMessageCount(),
            lastMessageDate()?.shortFormat(),
            false,
            ChatType.GROUP,
            lastMessageShort().second
        )
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun unreadableMessageCount(): Int {
        return 0
        // TODO
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun lastMessageDate(): Date? {
        return Date()
        // TODO
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun lastMessageShort(): Pair<String, String?> = "Сообщений еще нет" to "@John_Doe"
    /*when (val lastMessage = messages.lastOrNull()) {
        else -> TODO()
    }*/
}

enum class ChatType {
    SINGLE,
    GROUP,
    ARCHIVE
}
