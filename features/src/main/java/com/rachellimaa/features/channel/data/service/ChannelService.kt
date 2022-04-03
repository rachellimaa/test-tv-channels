package com.rachellimaa.features.channel.data.service

import com.rachellimaa.features.channel.data.models.ChannelsResponse
import com.rachellimaa.features.channel.data.models.ProgramsResponse
import retrofit2.http.GET
import retrofit2.http.Query

const val USER_AGENT = "AND"

const val CHANNELS_FILTER = "substringof('MEO_Mobile',AvailableOnChannels) and IsAdult eq false"
const val CHANNELS_ORDER_BY = "ChannelPosition asc"
const val CHANNELS_INLINE_COUNT = "allpages"

const val PROGRAMS_ORDER_BY = "StartDate asc"

interface CatalogService {
    @GET("catalog/v9/Channels")
    suspend fun getChannels(
        @Query("UserAgent") userAgent: String = USER_AGENT,
        @Query("\$filter") filter: String = CHANNELS_FILTER,
        @Query("\$orderby") orderBy: String = CHANNELS_ORDER_BY,
        @Query("\$inlinecount") inlineCount: String = CHANNELS_INLINE_COUNT,
        @Query("\$skip") skip: String,
    ): ChannelsResponse

    @GET("Program/v9/Programs/NowAndNextLiveChannelPrograms")
    suspend fun getPrograms(
        @Query("UserAgent") userAgent: String = USER_AGENT,
        @Query("\$filter") filter: String,
        @Query("\$orderby") orderBy: String = PROGRAMS_ORDER_BY,
    ): ProgramsResponse
}