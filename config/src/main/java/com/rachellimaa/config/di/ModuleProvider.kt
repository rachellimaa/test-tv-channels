package com.rachellimaa.config.di

import org.koin.core.module.Module

interface ModuleProvider {
    val module: Module
    fun provideModule() = module
}
