package com.ivansiabro.bootcounter.data.repository

import com.ivansiabro.bootcounter.data.storage.dao.SettingsDao
import com.ivansiabro.bootcounter.data.storage.entities.BootEventEntity
import com.ivansiabro.bootcounter.data.storage.entities.SettingsEntity
import javax.inject.Inject

class DefaultSettingsRepository @Inject constructor(
    private val settingsDao: SettingsDao,
) : SettingsRepository {

    override suspend fun save(totalDismissalsAllowed: Long, interval: Long) {
        settingsDao.insertOrReplace(SettingsEntity(totalDismissalsAllowed, interval))
    }

    override suspend fun get(): SettingsEntity? {
        return settingsDao.get()
    }

}



