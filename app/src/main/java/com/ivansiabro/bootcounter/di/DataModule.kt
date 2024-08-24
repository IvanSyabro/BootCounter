package com.ivansiabro.bootcounter.di

import com.ivansiabro.bootcounter.data.repository.BootRepository
import com.ivansiabro.bootcounter.data.repository.DefaultBootRepository
import com.ivansiabro.bootcounter.data.repository.DefaultSettingsRepository
import com.ivansiabro.bootcounter.data.repository.SettingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindsBootEventsRepository(
        bootEventsRepository: DefaultBootRepository,
    ): BootRepository

    @Binds
    internal abstract fun bindsSettingsRepository(
        settingsRepository: DefaultSettingsRepository,
    ): SettingsRepository

}