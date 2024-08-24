package com.ivansiabro.bootcounter.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ivansiabro.bootcounter.data.storage.entities.SettingsEntity

@Dao
interface SettingsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(settings: SettingsEntity)

    @Query("SELECT * FROM settings WHERE id = 1 LIMIT 1")
    suspend fun get(): SettingsEntity?
}