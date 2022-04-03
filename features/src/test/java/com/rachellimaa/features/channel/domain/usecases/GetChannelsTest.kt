package com.rachellimaa.features.channel.domain.usecases

import com.rachellimaa.config.storage.ChannelStorage
import com.rachellimaa.features.channel.channelResponse
import com.rachellimaa.features.channel.domain.mappers.ChannelMapper
import com.rachellimaa.features.channel.domain.repositories.ChannelRepository
import com.rachellimaa.features.channel.programResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

class GetChannelsTest {

    private var repository: ChannelRepository = mockk(relaxed = true)

    private lateinit var getCatalogChannels: GetCatalogChannels

    private val storage = ChannelStorage

    @BeforeTest
    fun setup() {
        getCatalogChannels = GetCatalogChannels(
            repository = repository,
            mapper = ChannelMapper,
            catalogStorage = storage
        )
    }

    @Test
    fun `when invoke getCatalogChannels then assert return success`() = runBlocking {
        coEvery { repository.getCatalogPrograms(callLetter = any()) } returns Result.success(
            programResponse
        )
        coEvery { repository.getCatalogChannels(skipParam = any()) } returns Result.success(
            channelResponse
        )
        val result = getCatalogChannels.invoke()
        assert(result.isSuccess)
    }

    @Test
    fun `when invoke getCatalogChannels and getCatalogPrograms return error then assert return result failure`() =
        runBlocking {
            coEvery { repository.getCatalogPrograms(callLetter = any()) } returns Result.failure(Exception())
            coEvery { repository.getCatalogChannels(skipParam = any()) } returns Result.success(
                channelResponse
            )
            val result = getCatalogChannels.invoke()
            assert(result.isFailure)
        }

    @Test
    fun `when invoke getCatalogChannels and getCatalogChannels return error then assert return result failure`() =
        runBlocking {
            coEvery { repository.getCatalogPrograms(callLetter = any()) } returns Result.success(
                programResponse
            )
            coEvery { repository.getCatalogChannels(skipParam = any()) } returns Result.failure(Exception())
            val result = getCatalogChannels.invoke()
            assert(result.isFailure)
        }

    @Test
    fun `when invoke getCatalogChannels and getCatalogChannels return success then assert update next url skip`() =
        runBlocking {

            val initialSkipParam = storage.nextUrlSkipParams

            coEvery { repository.getCatalogPrograms(callLetter = any()) } returns Result.success(
                programResponse
            )
            coEvery { repository.getCatalogChannels(skipParam = any()) } returns Result.success(
                channelResponse
            )

            getCatalogChannels.invoke()

            assert(storage.nextUrlSkipParams == initialSkipParam.plus(10))
        }
}