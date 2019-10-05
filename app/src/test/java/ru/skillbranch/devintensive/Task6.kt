package ru.skillbranch.devintensive

import compareEquals
import org.junit.Test
import ru.skillbranch.devintensive.utils.Utils

class Task6 {

    @Test
    fun `Задание 6, проверка для имени и фамилии`() {
        compareEquals(Utils.toInitials("михаил", "макеев"), "ММ")
        compareEquals(Utils.toInitials("Михаил", "макеев"), "ММ")
        compareEquals(Utils.toInitials("михаил", "Макеев"), "ММ")
        compareEquals(Utils.toInitials("Михаил", "Макеев"), "ММ")
        compareEquals(Utils.toInitials("МИХАИЛ", "МАКЕЕВ"), "ММ")

        compareEquals(Utils.toInitials("john", "doe"), "JD")
        compareEquals(Utils.toInitials("John", "doe"), "JD")
        compareEquals(Utils.toInitials("john", "Doe"), "JD")
        compareEquals(Utils.toInitials("John", "Doe"), "JD")
        compareEquals(Utils.toInitials("JOHN", "DOE"), "JD")
    }

    @Test
    fun `Задание 6, поверка для имени или фамилии (null, пустые, пробельные строки)`() {
        compareEquals(Utils.toInitials("Михаил", null), "М")
        compareEquals(Utils.toInitials("Михаил", ""), "М")
        compareEquals(Utils.toInitials("М", null), "М")
        compareEquals(Utils.toInitials("М", ""), "М")
        compareEquals(Utils.toInitials(null, "Макеев"), "М")
        compareEquals(Utils.toInitials("", "Макеев"), "М")
        compareEquals(Utils.toInitials(null, "М"), "М")
        compareEquals(Utils.toInitials("", "М"), "М")

        compareEquals(Utils.toInitials("Михаил", " "), "М")
        compareEquals(Utils.toInitials("Михаил", "  "), "М")
        compareEquals(Utils.toInitials(" ", "Макеев"), "М")
        compareEquals(Utils.toInitials("  ", "Макеев"), "М")
    }

    @Test
    fun `Задание 6, поверка для null, пустых, пробельных строк`() {
        compareEquals(Utils.toInitials("", ""), null)
        compareEquals(Utils.toInitials(" ", " "), null)
        compareEquals(Utils.toInitials("  ", "  "), null)
        compareEquals(Utils.toInitials("", null), null)
        compareEquals(Utils.toInitials(" ", null), null)
        compareEquals(Utils.toInitials("  ", null), null)
        compareEquals(Utils.toInitials(null, ""), null)
        compareEquals(Utils.toInitials(null, " "), null)
        compareEquals(Utils.toInitials(null, "  "), null)
        compareEquals(Utils.toInitials(null, null), null)
    }
}