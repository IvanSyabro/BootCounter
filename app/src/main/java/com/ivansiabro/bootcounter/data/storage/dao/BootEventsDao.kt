package com.ivansiabro.bootcounter.data.storage.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ivansiabro.bootcounter.data.storage.entities.BootEventEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BootEventsDao {

    @Insert
    suspend fun insert(bootEvent: BootEventEntity)

    @Query("SELECT * FROM boot_events ORDER BY timestamp DESC")
    fun getAll(): Flow<List<BootEventEntity>>

}