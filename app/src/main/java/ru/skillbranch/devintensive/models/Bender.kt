package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.models.Bender.Question.*

class Bender(var status: Status = Status.NORMAL, var question: Question = NAME) {

    fun askQuestion() = question.question

    fun listenAnswer(answer: String) =
        if (question.answers.contains(answer)) {
            question = question.nextQuestion()
            "Отлично - ты справился\n${question.question}" to status.color
        } else {
            status = status.nextStatus()
            val result = if (status.ordinal != 0) {
                "Это неправильный ответ\n" + question.question
            } else {
                question = NAME
                "Это неправильный ответ. Давай все по новой\n" + question.question
            }
            result to status.color
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
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")) {
            override fun nextQuestion() = MATERIAL
        },
        MATERIAL("Из чего я сделан", listOf("металл", "дерево", "metal", "iron", "wood")) {
            override fun nextQuestion() = BDAY
        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun nextQuestion() = SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2716057")) {
            override fun nextQuestion() = IDLE
        },
        IDLE("На этом все, вопросов больше нет", listOf()) {
            override fun nextQuestion() = IDLE
        };

        abstract fun nextQuestion(): Question
    }
}