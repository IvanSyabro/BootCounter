package com.ivansiabro.bootcounter.di

import com.ivansiabro.bootcounter.data.storage.BootDatabase
import com.ivansiabro.bootcounter.data.storage.dao.BootEventsDao
import com.ivansiabro.bootcounter.data.storage.dao.SettingsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {

    @Provides
    fun providesBootEventsDao(
        database: BootDatabase,
    ): BootEventsDao = database.bootEventDao()

    @Provides
    fun providesSettingsDao(
        database: BootDatabase,
    ): SettingsDao = database.settingsDao()

}