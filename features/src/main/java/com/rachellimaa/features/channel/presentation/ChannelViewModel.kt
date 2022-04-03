package com.rachellimaa.features.channel.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rachellimaa.features.channel.domain.model.ChannelModel
import com.rachellimaa.features.channel.domain.usecases.GetCatalogChannels
import kotlinx.coroutines.launch

internal class ChannelViewModel(
    private val getCatalogChannels: GetCatalogChannels
) : ViewModel() {

    val channels: MutableLiveData<List<ChannelModel>> = MutableLiveData()
    val onError: MutableLiveData<Unit> = MutableLiveData()

    fun getChannels() {
        viewModelScope.launch {
            getCatalogChannels()
                .onSuccess { channels.value = it }
                .onFailure { onError.value = Unit }
        }
    }
}