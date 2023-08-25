package com.example.elastictest.util

import org.slf4j.LoggerFactory

object Log {
    private val logger = LoggerFactory.getLogger(javaClass)

    fun info(message: String) {
        logger.info(message)
    }

    fun error(message: String, throwable: Throwable? = null) {
        if (throwable != null) {
            logger.error(message, throwable)
        } else {
            logger.error(message)
        }
    }


}