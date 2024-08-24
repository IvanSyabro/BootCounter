package com.ivansiabro.bootcounter.data.repository

import com.ivansiabro.bootcounter.data.storage.entities.BootEventEntity
import kotlinx.coroutines.flow.Flow

interface BootRepository {
    suspend fun saveEvent(timestamp: Long)
    fun getEvents() : Flow<List<BootEventEntity>>
}