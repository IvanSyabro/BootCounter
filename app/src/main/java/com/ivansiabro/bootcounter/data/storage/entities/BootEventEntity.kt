package com.ivansiabro.bootcounter.data.storage.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "boot_events")
class BootEventEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val timestamp: Long
)
