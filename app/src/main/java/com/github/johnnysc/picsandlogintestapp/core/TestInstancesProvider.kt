package com.github.johnnysc.picsandlogintestapp.core

import com.github.johnnysc.picsandlogintestapp.data.login.TestLoginRepositoryImpl
import com.github.johnnysc.picsandlogintestapp.data.pics.TestPicsRepositoryImpl
import com.github.johnnysc.picsandlogintestapp.domain.login.*
import com.github.johnnysc.picsandlogintestapp.domain.pics.*

/**
 * Провайдит тестовые инстансы
 *
 * @author Asatryan on 10.04.21
 **/
class TestInstancesProvider : AbstractInstanceProvider() {

    override fun provideResourceManager(): ResourceManager {
        return TestResourceManagerImpl()
    }

    override fun provideLoginRepository(): LoginRepository {
        return TestLoginRepositoryImpl()
    }

    override fun providePicsRepository(): PicsRepository {
        return TestPicsRepositoryImpl()
    }
}