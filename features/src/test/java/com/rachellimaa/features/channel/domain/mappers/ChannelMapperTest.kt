package com.rachellimaa.features.channel.domain.mappers

import com.rachellimaa.features.channel.channelInput
import com.rachellimaa.features.channel.catalog
import com.rachellimaa.features.channel.programsInput
import kotlin.test.Test
import kotlin.test.assertEquals

class ChannelMapperTest {

    @Test
    fun `when map catalog channel then assert return expected value`() {
        val result = ChannelMapper.mapperCatalogChannel(
            channel = channelInput,
            programs = programsInput
        )
        assertEquals(catalog, result)
    }
}