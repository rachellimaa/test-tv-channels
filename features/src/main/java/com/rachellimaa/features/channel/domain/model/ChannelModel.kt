package com.rachellimaa.features.channel.domain.model

data class ChannelModel(
    val channelTitle: String,
    val currentProgram: String,
    val nextProgram: String,
    val programImage: String,
    val channelImage: String,
    val callLetter: String,
)