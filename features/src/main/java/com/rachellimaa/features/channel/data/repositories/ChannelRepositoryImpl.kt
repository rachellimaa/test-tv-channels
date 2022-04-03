package com.rachellimaa.features.channel.data.repositories

import com.rachellimaa.features.channel.data.service.CatalogService
import com.rachellimaa.features.channel.data.models.ChannelsResponse
import com.rachellimaa.features.channel.data.models.ProgramsResponse
import com.rachellimaa.features.channel.domain.repositories.ChannelRepository

class ChannelRepositoryImpl(
    val service: CatalogService
) : ChannelRepository {
    override suspend fun getCatalogChannels(skipParam: String): Result<ChannelsResponse> = try {
        val result = service.getChannels(skip = skipParam)
        Result.success(result)
    } catch (error: Exception) {
        Result.failure(error)
    }

    override suspend fun getCatalogPrograms(callLetter: String): Result<ProgramsResponse> = try {
        val result = service.getPrograms(filter = "CallLetter eq '${callLetter}'")
        Result.success(result)
    } catch (error: Exception) {
        Result.failure(error)
    }
}