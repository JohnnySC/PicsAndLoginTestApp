package com.github.johnnysc.picsandlogintestapp.core

import android.content.Context
import java.lang.RuntimeException

/**
 * Провайдит нужные инстансы обьектов в зависимости от конфигурации
 *
 * @author Asatryan on 10.04.21
 **/
class ServiceLocator(
    configuration: String,
    context: Context
) {

    private companion object {
        const val CONFIGURATION_BASIC = "BASIC"
        const val CONFIGURATION_TEST = "TEST"
    }

    val instanceProvider: InstanceProvider =
        when (configuration) {
            CONFIGURATION_BASIC -> BaseInstancesProvider(context, NetworkModule())
            CONFIGURATION_TEST -> TestInstancesProvider()
            else -> throw RuntimeException("UNKNOWN CONFIGURATION TYPE")
        }
}