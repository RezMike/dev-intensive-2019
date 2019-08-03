package ru.skillbranch.devintensive.utils

object Utils {

    private val translitMap = mapOf(
        'а' to "a", 'б' to "b", 'в' to "v", 'г' to "g", 'д' to "d", 'е' to "e", 'ё' to "e", 'ж' to "zh", 'з' to "z",
        'и' to "i", 'й' to "i", 'к' to "k", 'л' to "l", 'м' to "m", 'н' to "n", 'о' to "o", 'п' to "p", 'р' to "r",
        'с' to "s", 'т' to "t", 'у' to "u", 'ф' to "f", 'х' to "h", 'ц' to "c", 'ч' to "ch", 'ш' to "sh", 'щ' to "sh",
        'ъ' to "", 'ы' to "i", 'ь' to "", 'э' to "e", 'ю' to "yu", 'я' to "ya"
    )

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.trim()?.replace(Regex(" +"), " ")?.split(" ")

        val firstName = parts?.notEmptyOrNullAt(0)
        val lastName = parts?.notEmptyOrNullAt(1)

        return firstName to lastName
    }

    private fun List<String>.notEmptyOrNullAt(index: Int) = getOrNull(index).let {
        if ("" == it) null
        else it
    }

    fun transliteration(payload: String, divider: String = " "): String {
        var result = ""
        payload.forEach {
            result += when {
                it == ' ' -> divider
                it.isUpperCase() -> translitMap[it.toLowerCase()]?.capitalize() ?: it.toString()
                else -> translitMap[it] ?: it.toString()
            }
        }
        return result
    }

    fun toInitials(firstName: String?, lastName: String?): String? = when {
        firstName.isNullOrBlank() && lastName.isNullOrBlank() -> null
        !firstName.isNullOrBlank() && lastName.isNullOrBlank() -> firstName[0].toUpperCase().toString()
        firstName.isNullOrBlank() && !lastName.isNullOrBlank() -> lastName[0].toUpperCase().toString()
        !firstName.isNullOrBlank() && !lastName.isNullOrBlank() -> firstName[0].toUpperCase() + lastName[0].toUpperCase().toString()
        else -> throw IllegalStateException("Incorrect state in 'when' expression")
    }

    fun isRepositoryValid(repository: String): Boolean {
        if (repository.isEmpty()) return true
        var repo = repository

        if (repo.startsWith("https://")) {
            repo = repo.replace("https://", "")
        }
        if (repo.startsWith("www.")) {
            repo = repo.replace("www.", "")
        }

        if (!repo.startsWith("github.com/")) {
            return false
        }

        repo = repo.replace("github.com/", "")

        repoExcludeSet.forEach {
            if (repo.startsWith(it) && (repo.length == it.length || repo[it.length] == '/')) return false
        }

        return Regex("^[A-Za-z0-9]+(-[A-Za-z0-9]+)*/?$").matches(repo)
    }

    private val repoExcludeSet = setOf(
        "enterprise",
        "features",
        "topics",
        "collections",
        "trending",
        "events",
        "marketplace",
        "pricing",
        "nonprofit",
        "customer-stories",
        "security",
        "login",
        "join"
    )
}
