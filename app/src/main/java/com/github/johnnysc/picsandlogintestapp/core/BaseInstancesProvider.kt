package com.github.johnnysc.picsandlogintestapp.core

import android.content.Context
import com.github.johnnysc.picsandlogintestapp.data.login.LoginRepositoryImpl
import com.github.johnnysc.picsandlogintestapp.data.pics.PicsRepositoryImpl
import com.github.johnnysc.picsandlogintestapp.domain.login.*
import com.github.johnnysc.picsandlogintestapp.domain.pics.*

/**
 * Предоставляем базовые инстансы
 *
 * @author Asatryan on 10.04.21
 **/
class BaseInstancesProvider(
    private val context: Context,
    private val networkModule: NetworkModule
) : AbstractInstanceProvider() {

    private var resourceManager: ResourceManager? = null

    override fun provideResourceManager(): ResourceManager {
        return resourceManager ?: ResourceManagerImpl(context).also {
            resourceManager = it
        }
    }

    override fun provideLoginRepository(): LoginRepository {
        return LoginRepositoryImpl(networkModule.getLoginService())
    }

    override fun providePicsRepository(): PicsRepository {
        return PicsRepositoryImpl(networkModule.getPicsService())
    }
}