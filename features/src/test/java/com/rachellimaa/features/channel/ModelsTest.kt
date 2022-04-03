package com.rachellimaa.features.channel

import com.rachellimaa.features.channel.data.models.Channel
import com.rachellimaa.features.channel.data.models.ChannelsResponse
import com.rachellimaa.features.channel.data.models.Program
import com.rachellimaa.features.channel.data.models.ProgramsResponse
import com.rachellimaa.features.channel.domain.model.ChannelModel

val channelResponse = ChannelsResponse(
    channels = listOf(Channel(title = "", callLetter = ""))
)

val programResponse = ProgramsResponse(
    programs = listOf(Program(title = ""))
)

val channelInput = Channel(
    title = "RT 1",
    callLetter = "RT 1"
)

val programsInput = listOf(
    Program(title = "Ep 01"),
    Program(title = "Ep 02"),
)

val catalog = ChannelModel(
    channelTitle = channelInput.title,
    callLetter = channelInput.callLetter,
    currentProgram = programsInput[0].title,
    nextProgram = programsInput[1].title,
    channelImage = "http://cdn-images.online.meo.pt/eemstb/ImageHandler.ashx?evTitle=${channelInput.title}&chCallLetter=${channelInput.callLetter}&profile=16_9&width=320",
    programImage = "http://cdn-images.online.meo.pt/eemstb/ImageHandler.ashx?evTitle=${programsInput[0].title}&chCallLetter=${channelInput.callLetter}&profile=16_9&width=320"
)