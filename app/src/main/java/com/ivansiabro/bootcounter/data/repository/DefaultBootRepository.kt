package com.ivansiabro.bootcounter.data.repository

import com.ivansiabro.bootcounter.data.storage.dao.BootEventsDao
import com.ivansiabro.bootcounter.data.storage.entities.BootEventEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultBootRepository @Inject constructor(
    private val bootEventsDao: BootEventsDao,
) : BootRepository {

    override suspend fun saveEvent(timestamp: Long) {
        val bootEvent = BootEventEntity(timestamp = timestamp)
        bootEventsDao.insert(bootEvent)
    }

    override fun getEvents(): Flow<List<BootEventEntity>> {
        return bootEventsDao.getAll()
    }

}



