package com.rachellimaa.features.channel.di

import com.rachellimaa.config.di.ModuleProvider
import com.rachellimaa.config.network.RETROFIT_QUALIFIER
import com.rachellimaa.config.storage.ChannelStorage
import com.rachellimaa.features.channel.data.service.CatalogService
import com.rachellimaa.features.channel.data.repositories.ChannelRepositoryImpl
import com.rachellimaa.features.channel.domain.mappers.ChannelMapper
import com.rachellimaa.features.channel.domain.repositories.ChannelRepository
import com.rachellimaa.features.channel.domain.usecases.GetCatalogChannels
import com.rachellimaa.features.channel.presentation.ChannelViewModel
import kotlinx.coroutines.MainScope
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

class ChannelModule : ModuleProvider {
    override val module: Module by lazy {
        module {
            single { get<Retrofit>(RETROFIT_QUALIFIER).create(CatalogService::class.java) }

            single<ChannelRepository> {
                ChannelRepositoryImpl(service = get())
            }

            factory { ChannelMapper }

            single { ChannelStorage }

            factory { GetCatalogChannels(repository = get(), catalogStorage = get(), mapper = get()) }

            factory { MainScope() }

            factory { ChannelViewModel(getCatalogChannels = get()) }

        }
    }
}