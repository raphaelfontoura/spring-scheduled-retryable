package com.scheduleretry.demo.test

import org.slf4j.LoggerFactory
import org.springframework.retry.annotation.Backoff
import org.springframework.retry.annotation.Retryable
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class ScheduleTestRetry {

    val logger = LoggerFactory.getLogger(this::class.java)

    companion object {
        var contador = 1;
    }

    @Scheduled(fixedDelay = 20000)
    @Retryable(maxAttempts = 3, backoff = Backoff(maxDelay = 15000))
    fun printTask() {
        println("contador em $contador")
        if (contador%3 == 0) {
            logger.info("Teste run scheduled. Contador = ${contador++}")
            return
        }
        throw RuntimeException("Error: contador = ${contador++}")
    }
}