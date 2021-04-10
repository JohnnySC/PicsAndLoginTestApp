package com.github.johnnysc.picsandlogintestapp.core

/**
 * Тестовая реализация ресурс менеджера
 *
 * @author Asatryan on 10.04.21
 **/
class TestResourceManagerImpl : ResourceManager {

    override fun getString(resId: Int): String {
        return "stub"
    }

    override fun getString(resId: Int, vararg args: Any?): String {
        return "stub with args"
    }

    override fun getErrorMessage(exceptionType: ExceptionType): String {
        return when (exceptionType) {
            ExceptionType.NETWORK_UNAVAILABLE -> "network is not available!"
            else -> "just generic error"
        }
    }
}