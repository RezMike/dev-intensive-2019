package ru.skillbranch.devintensive.models.data

import ru.skillbranch.devintensive.extensions.humanizeDiff
import ru.skillbranch.devintensive.utils.Utils

data class UserItem (
    val id: String,
    val fullName: String,
    val initials : String?,
    val avatar: String?,
    var lastActivity:String,
    var isSelected : Boolean = false,
    var isOnline: Boolean = false
)

fun User.toUserItem(): UserItem {
    val lastActivity = when {
        lastVisit == null -> "Еще ни разу не заходил"
        isOnline -> "online"
        else -> "Последний раз был ${lastVisit.humanizeDiff()}"
    }

    return UserItem(
        id,
        "${firstName.orEmpty()} ${lastName.orEmpty()}",
        Utils.toInitials(firstName, lastName),
        avatar,
        lastActivity,
        false,
        isOnline
    )
}
