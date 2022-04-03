package com.rachellimaa.features.channel.domain.mappers

import com.rachellimaa.config.BuildConfig
import com.rachellimaa.features.channel.data.models.Channel
import com.rachellimaa.features.channel.data.models.Program
import com.rachellimaa.features.channel.domain.model.ChannelModel

object ChannelMapper {

    fun mapperCatalogChannel(
        channel: Channel,
        programs: List<Program>
    ): ChannelModel {
        return ChannelModel(
            channelTitle = channel.title,
            callLetter = channel.callLetter,
            currentProgram = programs.getOrNull(0)?.title ?: "",
            nextProgram = programs.getOrNull(1)?.title ?: "",
            channelImage = buildUrlImage(channel.title, channel.callLetter),
            programImage = buildUrlImage(programs.getOrNull(0)?.title ?: "", channel.callLetter)
        )
    }

    private fun buildUrlImage(baseImage: String, callLetter: String) =
        "${BuildConfig.IMAGE_URL}?evTitle=${baseImage}&chCallLetter=${callLetter}&profile=16_9&width=320"
}