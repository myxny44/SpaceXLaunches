package com.s4days.feature_launches.data.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.s4days.core_network.data.model.SpaceXLaunch
import com.s4days.feature_launches.data.repository.LaunchesRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import toothpick.InjectConstructor

@InjectConstructor
internal class LaunchesViewModel(
    application: Application,
    private val launchesRepository: LaunchesRepository,
): AndroidViewModel(application) {

    val loading = MutableLiveData(true)
    val error = MutableLiveData("")
    val launches = MutableLiveData<List<SpaceXLaunch>>()
    val favorites = MutableLiveData<List<String>>()

    fun getLaunches(){
        GlobalScope.launch {
            try {
                updateFavorites()
                launches.postValue(launchesRepository.getLaunches())
            } catch (e: Exception) {
                error.postValue(e.message)
            }
            loading.postValue(false)
        }
    }

    fun getLaunchInfo(id: String) = launches.value?.first { it.id == id }

    fun addFavorite(launch: String){
        GlobalScope.launch {
            launchesRepository.addFavorites(launch)
            favorites.postValue(launchesRepository.getFavorites())
        }
    }

    fun deleteFavorite(launch: String){
        GlobalScope.launch {
            launchesRepository.deleteFavorites(launch)
            favorites.postValue(launchesRepository.getFavorites())
        }
    }

    suspend fun updateFavorites(){
        favorites.postValue(launchesRepository.getFavorites())
    }

}