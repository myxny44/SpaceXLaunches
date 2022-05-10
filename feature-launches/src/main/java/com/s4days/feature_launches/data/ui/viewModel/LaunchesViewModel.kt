package com.s4days.feature_launches.data.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.s4days.core_network.data.model.SpaceXLaunch
import com.s4days.feature_launches.data.repository.LaunchesRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import toothpick.InjectConstructor
import java.lang.Exception

@InjectConstructor
internal class LaunchesViewModel(
    application: Application,
    private val launchesRepository: LaunchesRepository
): AndroidViewModel(application) {

    val loading = MutableLiveData(true)
    val error = MutableLiveData("")
    val launches = MutableLiveData<List<SpaceXLaunch>>()

    fun getLaunches(){
        GlobalScope.launch {
            try {
                launches.postValue(launchesRepository.getLaunches())
            } catch (e: Exception) {
                error.postValue(e.message)
            }
            loading.postValue(false)
        }
    }

    fun getLaunchInfo(id: String) = launches.value?.first { it.id == id }

}