package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    val lastVisit: Date? = Date(),
    val isOnline: Boolean = false
) {

    constructor(id: String, firstName: String?, lastName: String?) : this(id, firstName, lastName, null)

    constructor(id: String) : this(id, "John", "Doe")

    init {
        println(
            "It's Alive!!!\n${
            if (lastName === "Doe")
                "His name is $firstName $lastName"
            else
                "And his name is $firstName $lastName!!!"
            }\n"
        )
    }

    companion object Factory {

        private var lastId: Int = -1

        fun makeUser(fullName: String?): User {
            lastId++

            val (firstName, lastName) = Utils.parseFullName(fullName)

            return User("$lastId", firstName, lastName)
        }
    }

    class Builder() {
        private var id: String? = null
        private var firstName: String? = null
        private var lastName: String? = null
        private var avatar: String? = null
        private var rating: Int = 0
        private var respect: Int = 0
        private var lastVisit: Date? = null
        private var isOnline: Boolean = false

        fun id(value: String): Builder {
            id = value
            return this
        }

        fun firstName(value: String): Builder {
            firstName = value
            return this
        }

        fun lastName(value: String): Builder {
            lastName = value
            return this
        }

        fun avatar(value: String): Builder {
            avatar = value
            return this
        }

        fun rating(value: Int): Builder {
            rating = value
            return this
        }

        fun respect(value: Int): Builder {
            respect = value
            return this
        }

        fun lastVisit(value: Date): Builder {
            lastVisit = value
            return this
        }

        fun isOnline(value: Boolean): Builder {
            isOnline = value
            return this
        }

        fun build() = User(
            id ?: (++lastId).toString(),
            firstName,
            lastName,
            avatar,
            rating,
            respect,
            lastVisit,
            isOnline
        )
    }
}
