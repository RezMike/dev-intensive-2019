package ru.skillbranch.devintensive.extensions

fun String.truncate(size: Int = 16): String {
    val result = this.trimEnd()
    if (result.length <= size)
        return result
    return result.substring(0, size).trimEnd() + "..."
}

fun String.stripHtml() = this
        .replace(Regex("<[\\s\\d\\w\"=/]*>"), "")
        .replace(Regex("&amp;|&lt;|&gt;|&quot;|&apos;"), "")
        .replace(Regex("\\s+"), " ")
