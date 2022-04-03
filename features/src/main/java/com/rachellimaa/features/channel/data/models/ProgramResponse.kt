package com.rachellimaa.features.channel.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProgramsResponse(
    @SerialName("value") val programs: List<Program>,
)

@Serializable
data class Program(
    @SerialName("Title") val title: String,
)

