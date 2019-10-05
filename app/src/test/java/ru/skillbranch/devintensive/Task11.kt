package ru.skillbranch.devintensive

import compareEquals
import org.junit.Test
import ru.skillbranch.devintensive.extensions.truncate

class Task11 {

    @Test
    fun `Задание 11, усечение достаточно коротких строк`() {
        compareEquals("".truncate(5), "")
        compareEquals("too short".truncate(20), "too short")
        compareEquals("12345".truncate(5), "12345")
        compareEquals("123456789".truncate(9), "123456789")
    }

    @Test
    fun `Задание 11, усечение коротких строк с пробелами`() {
        compareEquals(" ".truncate(3), "")
        compareEquals("   ".truncate(3), "")
        compareEquals("      ".truncate(3), "")
        compareEquals("12     ".truncate(3), "12")
        compareEquals("A     ".truncate(3), "A")
        compareEquals("tab    ".truncate(5), "tab")
        compareEquals("12345  ".truncate(5), "12345")
        compareEquals("abc    ".truncate(15), "abc")
    }

    @Test
    fun `Задание 11, усечение длинных строк`() {
        compareEquals(
            "Bender Bending Rodriguez — дословно «Сгибальщик Сгибающий Родригес»".truncate(),
            "Bender Bending R..."
        )
        compareEquals(
            "Bender Bending Rodriguez — дословно «Сгибальщик Сгибающий Родригес»".truncate(15),
            "Bender Bending..."
        )
        compareEquals(
            "Bender Bending Rodriguez — дословно «Сгибальщик Сгибающий Родригес»".truncate(7),
            "Bender..."
        )
        compareEquals("too long line".truncate(6), "too lo...")
        compareEquals("12345".truncate(4), "1234...")
        compareEquals("dots... a lot".truncate(7), "dots......")
        compareEquals("123456789".truncate(6), "123456...")
    }
}