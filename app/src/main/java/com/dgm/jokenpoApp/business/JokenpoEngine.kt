package com.dgm.jokenpoApp.business;

import com.dgm.jokenpoApp.business.Result.*
import kotlin.random.Random

class JokenpoEngine(private val plays: Array<String>) {

    private lateinit var aiSelectedPlay: String

    fun calculateResult(playerSelection: String): Result {
        val aiPlay = getAIPlay()
        aiSelectedPlay = aiPlay

        return when {
            playerSelection == aiPlay -> DRAW
            playerSelection == "Pedra" && aiPlay == "Tesoura" -> WIN
            playerSelection == "Pedra" && aiPlay == "Papel" -> LOSS
            playerSelection == "Papel" && aiPlay == "Pedra" -> WIN
            playerSelection == "Papel" && aiPlay == "Tesoura" -> LOSS
            playerSelection == "Tesoura" && aiPlay == "Papel" -> WIN
            else -> LOSS
        }
    }

    fun getAISelectedPlay(): String {
        return aiSelectedPlay
    }

    private fun getAIPlay(): String {
        val playIndex = Random.nextInt(0, 2)
        return plays[playIndex]
    }
}

