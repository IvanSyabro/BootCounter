package com.ivansiabro.bootcounter.data.storage.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
class SettingsEntity(
    @PrimaryKey() val id: Int = 0,
    val totalDismissalsAllowed: Long,
    //minutes
    val interval: Long,
) {
    constructor(totalDismissalsAllowed: Long, interval: Long,) : this(0, totalDismissalsAllowed, interval)
}
