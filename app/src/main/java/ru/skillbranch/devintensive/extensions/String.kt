package ru.skillbranch.devintensive.extensions

fun String.truncate(size: Int = 16): String {
    val result = this.trim()
    if (result.length <= size)
        return result
    return result.substring(0, size).trimEnd() + "..."
}

fun String.stripHtml() = this
        .replace(Regex("<[^>]*>"), "")
        .replace(Regex("&amp;|&lt;|&gt;|&quot;|&apos;|&#\\d+;"), "")
        .replace(Regex(" +"), " ")
