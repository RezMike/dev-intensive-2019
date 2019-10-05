package ru.skillbranch.devintensive

import compareEquals
import org.junit.Test
import ru.skillbranch.devintensive.extensions.stripHtml

class Task12 {

    @Test
    fun `Задание 12, удаление пробелов`() {
        compareEquals(
            "Образовательное       IT-сообщество Skill Branch".stripHtml(),
            "Образовательное IT-сообщество Skill Branch"
        )
        compareEquals("  one   two ".stripHtml(), " one two ")
    }

    @Test
    fun `Задание 12, удаление html тегов`() {
        compareEquals(
            "<p class=\"title\">Образовательное IT-сообщество Skill Branch</p>".stripHtml(),
            "Образовательное IT-сообщество Skill Branch"
        )
        compareEquals(
            "<path fill=\"Color\" d=\"M11.63 10z\"></svg><span>1978</span>".stripHtml(),
            "1978"
        )
    }

    @Test
    fun `Задание 12, удаление escape последовательностей`() {
        compareEquals("&amp;&lt;&gt;single&#39;&quot;".stripHtml(), "single")
        compareEquals("&amp;&lt;&gt;&#39;&quot;".stripHtml(), "")
        compareEquals(
            "&игра; amp lt &gt 39; meters ()quot;".stripHtml(),
            "&игра; amp lt &gt 39; meters ()quot;"
        )
    }

    @Test
    fun `Задание 12, удаление различных комбинаций элементов`() {
        compareEquals(
            "<p>Образовательное       IT-сообщество Skill Branch</p>".stripHtml(),
            "Образовательное IT-сообщество Skill Branch"
        )
        compareEquals("&amp;&lt;&gt;    &#39;&quot;".stripHtml(), " ")
        compareEquals("&gt;<head>&#39;&quot;</head>".stripHtml(), "")
        compareEquals("&gt;<head> &quot; </head>".stripHtml(), " ")
        compareEquals("null".stripHtml(), "null")
        val longHtml = """
            <TD valign="top" style="padding-bottom:15px;"> <b>line1<b> </TD>
            <TD valign="top"> <span class="HeadTitleNews"> line2</span>
            <img src='http://2011WaterpoloF.jpg' >
            <div style="margin: 0in 0in 0pt">line3</div>
        """.trimIndent()
        compareEquals(longHtml.stripHtml(), " line1 \n line2\n\nline3")
    }
}