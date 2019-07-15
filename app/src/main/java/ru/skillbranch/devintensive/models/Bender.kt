package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.models.Bender.Question.NAME

class Bender(var status: Status = Status.NORMAL, var question: Question = NAME) {

    fun askQuestion() = question.question

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {
        val validError = question.validate(answer)
        if (validError != null) {
            return (validError + question.question) to status.color
        }
        if (question.answers.contains(answer.toLowerCase())) {
            question = question.nextQuestion()
            return "Отлично - ты справился\n${question.question}" to status.color
        }
        status = status.nextStatus()
        val result = if (status.ordinal != 0) {
            "Это неправильный ответ\n" + question.question
        } else {
            question = NAME
            "Это неправильный ответ. Давай все по новой\n" + question.question
        }
        return result to status.color
    }

    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus() =
            if (ordinal < values().lastIndex) {
                values()[ordinal + 1]
            } else {
                values()[0]
            }
    }

    enum class Question(val question: String, val answers: List<String>) {
        NAME("Как меня зовут?", listOf("бендер", "bender")) {
            override fun nextQuestion() = PROFESSION
            override fun validate(answer: String): String? =
                if (!answer[0].isUpperCase())
                    "Имя должно начинаться с заглавной буквы\n"
                else null
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")) {
            override fun nextQuestion() = MATERIAL
            override fun validate(answer: String): String? =
                if (!answer[0].isLowerCase())
                    "Профессия должна начинаться со строчной буквы\n"
                else null
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")) {
            override fun nextQuestion() = BDAY
            override fun validate(answer: String): String? =
                if (answer.contains(Regex("\\d")))
                    "Материал не должен содержать цифр\n"
                else null
        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun nextQuestion() = SERIAL
            override fun validate(answer: String): String? =
                if (answer.contains(Regex("\\D")))
                    "Год моего рождения должен содержать только цифры\n"
                else null
        },
        SERIAL("Мой серийный номер?", listOf("2716057")) {
            override fun nextQuestion() = IDLE
            override fun validate(answer: String): String? =
                if (!Regex("\\d{7}").matches(answer))
                    "Серийный номер содержит только цифры, и их 7\n"
                else null
        },
        IDLE("На этом все, вопросов больше нет", listOf()) {
            override fun nextQuestion() = IDLE
            override fun validate(answer: String): String? = ""
        };

        abstract fun nextQuestion(): Question
        abstract fun validate(answer: String): String?
    }
}