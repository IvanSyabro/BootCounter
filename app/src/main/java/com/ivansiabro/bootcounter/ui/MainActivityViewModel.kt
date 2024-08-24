package com.ivansiabro.bootcounter.ui

import androidx.lifecycle.ViewModel
import com.ivansiabro.bootcounter.data.repository.BootRepository
import com.ivansiabro.bootcounter.data.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    var bootRepository: BootRepository,
    var settingsRepository: SettingsRepository,
) : ViewModel() {

    val uiState = MutableStateFlow<MainActivityUiState>(MainActivityUiState.Success(SuccessData()))

    init {
        viewModelScope.launch {
            bootRepository.getEvents().collect {
                var text: String? = null
                if (it.isEmpty()){
                    text = "No boots detected"
                } else {
                    text = "Total detected events count: ${it.size.toString()}"
                }
                uiState.emit(MainActivityUiState.Success(SuccessData(text = text)))
            }
        }
    }

    sealed interface MainActivityUiState {
        data class Success(val data: SuccessData) : MainActivityUiState
    }

}

data class SuccessData(
    val totalDismissalsAllowed: Long = 0,
    val intervalBetweenDismissals: Long = 0,
    val text: String = ""
)