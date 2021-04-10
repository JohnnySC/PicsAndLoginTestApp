package com.github.johnnysc.picsandlogintestapp.core

/**
 * Класс для определения типа исключения
 *
 * @author Asatryan on 10.04.21
 **/
interface ExceptionHandler {

    /**
     * @param e исключение
     *
     * @return вид исключения
     * @see ExceptionType
     */
    fun defineExceptionType(e: Exception): ExceptionType
}