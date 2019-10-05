class `Ошибка сравнения`(message: String) : Exception(message)

fun compare(success: Boolean, expected: String, actual: String) {
    if (!success)
        throw `Ошибка сравнения`("Ожидалось - $expected. Получено - $actual.")
}

fun <T> compareEquals(actual: T, expected: T) {
    compare(
        expected == actual,
        if (expected is String) "\"$expected\"" else expected.toString(),
        if (actual is String) "\"$actual\"" else actual.toString()
    )
}