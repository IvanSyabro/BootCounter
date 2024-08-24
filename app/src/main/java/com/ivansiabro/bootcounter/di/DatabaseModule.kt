package com.ivansiabro.bootcounter.di

import android.content.Context
import androidx.room.Room
import com.ivansiabro.bootcounter.data.storage.BootDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun providesBootDatabase(
        @ApplicationContext context: Context,
    ): BootDatabase = Room.databaseBuilder(
        context,
        BootDatabase::class.java,
        "boot_db",
    ).build()
}
