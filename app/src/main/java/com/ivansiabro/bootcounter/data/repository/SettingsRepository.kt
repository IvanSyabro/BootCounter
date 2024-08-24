package com.ivansiabro.bootcounter.data.repository

import com.ivansiabro.bootcounter.data.storage.entities.SettingsEntity

interface SettingsRepository {
    suspend fun save(totalDismissalsAllowed: Long, interval: Long)
    suspend fun get(): SettingsEntity?
}