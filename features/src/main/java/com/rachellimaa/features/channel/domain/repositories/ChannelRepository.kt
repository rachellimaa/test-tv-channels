package com.rachellimaa.features.channel.domain.repositories

import com.rachellimaa.features.channel.data.models.ChannelsResponse
import com.rachellimaa.features.channel.data.models.ProgramsResponse

interface ChannelRepository {
    suspend fun getCatalogChannels(skipParam: String): Result<ChannelsResponse>
    suspend fun getCatalogPrograms(callLetter: String): Result<ProgramsResponse>
}