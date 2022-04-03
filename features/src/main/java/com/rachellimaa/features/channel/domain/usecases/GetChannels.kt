package com.rachellimaa.features.channel.domain.usecases

import com.rachellimaa.config.exceptions.BusinessException
import com.rachellimaa.config.storage.ChannelStorage
import com.rachellimaa.features.channel.data.models.ChannelsResponse
import com.rachellimaa.features.channel.domain.mappers.ChannelMapper
import com.rachellimaa.features.channel.domain.model.ChannelModel
import com.rachellimaa.features.channel.domain.repositories.ChannelRepository

private const val SKIP_PAGINATION = 10

class GetCatalogChannels(
    private val repository: ChannelRepository,
    private val mapper: ChannelMapper,
    private val catalogStorage: ChannelStorage
) {

    suspend operator fun invoke(): Result<List<ChannelModel>> {
        val channels = repository.getCatalogChannels(catalogStorage.nextUrlSkipParams.toString())

        if (channels.isFailure)
            return Result.failure(BusinessException(message = channels.exceptionOrNull()?.message))

        return getProgramsByChannel(channels.getOrThrow()).also { updateSkipPaginationParams() }
    }

    private suspend fun getProgramsByChannel(channelResponse: ChannelsResponse): Result<List<ChannelModel>> {
        val channelsCatalog = arrayListOf<ChannelModel>()

        channelResponse.channels.forEach { channel ->
            val programs = repository.getCatalogPrograms(channel.callLetter)

            if (programs.isFailure)
                return Result.failure(BusinessException(message = programs.exceptionOrNull()?.message))

            channelsCatalog.add(
                mapper.mapperCatalogChannel(
                    channel = channel,
                    programs = programs.getOrThrow().programs
                )
            )
        }

        return Result.success(channelsCatalog)
    }

    private fun updateSkipPaginationParams() {
        catalogStorage.nextUrlSkipParams += SKIP_PAGINATION
    }
}