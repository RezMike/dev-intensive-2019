package ru.skillbranch.devintensive

import compareEquals
import org.junit.Test
import ru.skillbranch.devintensive.utils.Utils

class Task7 {

    @Test
    fun `Задание 7, проверка алфавита`() {
        compareEquals(Utils.transliteration("аАбБвВгГдДеЕёЁ"), "aAbBvVgGdDeEeE")
        compareEquals(Utils.transliteration("жЖзЗиИйЙкКлЛ"), "zhZhzZiIiIkKlL")
        compareEquals(Utils.transliteration("мМнНоОпПрРсСтТуУ"), "mMnNoOpPrRsStTuU")
        compareEquals(Utils.transliteration("фФхХцЦшШщЩ"), "fFhHcCshShsh'Sh'")
        compareEquals(Utils.transliteration("ъЪьЬэЭюЮяЯ"), "eEyuYuyaYa")
        compareEquals(Utils.transliteration("aAbBcCdDeEfFgG"), "aAbBcCdDeEfFgG")
        compareEquals(Utils.transliteration("hHiIjJkKlLmMnN"), "hHiIjJkKlLmMnN")
        compareEquals(Utils.transliteration("oOpPqQrRsStTuU"), "oOpPqQrRsStTuU")
        compareEquals(Utils.transliteration("vVwWxXyYzZ"), "vVwWxXyYzZ")
    }

    @Test
    fun `Задание 7, проверка цифр и символов`() {
        val digits = "1234567890"
        val symbols = "!@#$%^&*().,-=+><"
        compareEquals(Utils.transliteration(digits), digits)
        compareEquals(Utils.transliteration(symbols), symbols)
    }

    @Test
    fun `Задание 7, проверка слов`() {
        compareEquals(Utils.transliteration("женя стереотипов"), "zhenya stereotipov")
        compareEquals(Utils.transliteration("ЖенЯ СтереотипоВ"), "ZhenYa StereotipoV")
        compareEquals(Utils.transliteration("ЖЕНЯ СТЕРЕОТИПОВ"), "ZhENYa STEREOTIPOV")
        compareEquals(Utils.transliteration("Женя Волченко"),"Zhenya Volchenko")
        compareEquals(Utils.transliteration("Amazing Петр"), "Amazing Petr")
        compareEquals(Utils.transliteration("AMAZING ПЕТР"), "AMAZING PETR")
    }

    @Test
    fun `Задание 7, проверка разделителя`() {
        compareEquals(Utils.transliteration("женя стереотипов", "_"), "zhenya_stereotipov")
        compareEquals(Utils.transliteration("ЖенЯ СтереотипоВ", "---"), "ZhenYa---StereotipoV")
        compareEquals(Utils.transliteration("ЖЕНЯ СТЕРЕОТИПОВ", " "), "ZhENYa STEREOTIPOV")
        compareEquals(Utils.transliteration("Amazing Петр", "12345"), "Amazing12345Petr")
        compareEquals(Utils.transliteration("Собака dog", " is a "), "Sobaka is a dog")
    }
}