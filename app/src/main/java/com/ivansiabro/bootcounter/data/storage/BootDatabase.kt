package com.ivansiabro.bootcounter.data.storage

import com.ivansiabro.bootcounter.data.storage.entities.BootEventEntity
import com.ivansiabro.bootcounter.data.storage.entities.SettingsEntity
import com.ivansiabro.bootcounter.data.storage.dao.SettingsDao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ivansiabro.bootcounter.data.storage.dao.BootEventsDao

@Database(entities = [BootEventEntity::class, SettingsEntity::class], version = 1)
abstract class BootDatabase : RoomDatabase() {

    abstract fun bootEventDao(): BootEventsDao
    abstract fun settingsDao(): SettingsDao

}