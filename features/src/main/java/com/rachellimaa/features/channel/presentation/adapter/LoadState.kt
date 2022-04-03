package com.rachellimaa.features.channel.presentation.adapter

sealed class LoadState {
    object Loading: LoadState()
    object Done: LoadState()
}