package com.github.johnnysc.picsandlogintestapp.core

import java.net.UnknownHostException

/**
 * Основная реализация обработчика исключений
 *
 * @author Asatryan on 10.04.21
 **/
class ExceptionHandlerImpl : ExceptionHandler {

    override fun defineExceptionType(e: Exception) = when (e) {
        is UnknownHostException -> ExceptionType.NETWORK_UNAVAILABLE
        else -> ExceptionType.GENERIC
    }
}