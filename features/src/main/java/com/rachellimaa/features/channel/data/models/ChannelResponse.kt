package com.rachellimaa.features.channel.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChannelsResponse(
    @SerialName("value") val channels: List<Channel>,
)

@Serializable
data class Channel(
    @SerialName("Title") val title: String,
    @SerialName("CallLetter") val callLetter: String,
)