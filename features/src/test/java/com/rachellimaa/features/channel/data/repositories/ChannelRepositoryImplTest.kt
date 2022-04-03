package com.rachellimaa.features.channel.data.repositories

import com.rachellimaa.features.channel.channelResponse
import com.rachellimaa.features.channel.data.service.CatalogService
import com.rachellimaa.features.channel.domain.repositories.ChannelRepository
import com.rachellimaa.features.channel.programResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.mockito.ArgumentMatchers.anyString
import kotlin.test.BeforeTest
import kotlin.test.Test

class ChannelRepositoryImplTest {

    private var service: CatalogService = mockk(relaxed = true)

    lateinit var channelRepository: ChannelRepository

    @BeforeTest
    fun setup() {
        channelRepository = ChannelRepositoryImpl(service = service)
    }

    @Test
    fun `when request channels return result success with channel response then assert isSuccess`() =
        runBlocking {
            coEvery { service.getChannels(skip = any()) } returns channelResponse

            val channels = channelRepository.getCatalogChannels(anyString())

            assert(channels.isSuccess)
        }

    @Test
    fun `when request channels throw an exceptions then assert result failure`() = runBlocking {
        coEvery { service.getChannels(skip = any()) } throws Exception()

        val channels = channelRepository.getCatalogChannels(skipParam = anyString())

        assert(channels.isFailure)
    }

    @Test
    fun `when request programs return result success with channel response then assert isSuccess`() =
        runBlocking {

            coEvery { service.getPrograms(filter = any()) } returns programResponse

            val programs =
                channelRepository.getCatalogPrograms(anyString())

            assert(programs.isSuccess)
        }

    @Test
    fun `when request programs throw an exceptions then assert result failure`() = runBlocking {
        coEvery { service.getPrograms(filter = any()) } throws Exception()

        val programs = channelRepository.getCatalogPrograms(anyString())

        assert(programs.isFailure)
    }
}