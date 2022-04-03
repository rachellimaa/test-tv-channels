package com.rachellimaa.gradle

object Ver {
    object Android {
        const val appCompat = "1.3.1"
        const val constraint = "2.0.4"
        const val material = "1.1.0"
        const val recyclerview = "1.2.1"
    }

    object Kotlin {
        const val stdlib = "1.5.21"
        const val coroutines = "1.6.0"
        const val serialization = "1.2.2"
    }

    object Test {
        const val mockk = "1.12.1"
    }

    const val glide = "4.12.0"

    const val gradle = "7.0.2"

    const val retrofit = "2.9.0"
    const val retrofitKotlinxConverter = "0.8.0"
    const val okHttp = "4.9.0"

    const val jetPack = "2.2.0"

    const val koin = "2.1.6"

}

object Plugin {
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Ver.Kotlin.stdlib}"
    const val kotlinSerialization =
        "org.jetbrains.kotlin:kotlin-serialization:${Ver.Kotlin.stdlib}"
    const val gradle = "com.android.tools.build:gradle:${Ver.gradle}"
}

object Depends {

    object Android {
        const val appCompat = "androidx.appcompat:appcompat:${Ver.Android.appCompat}"
        const val constraint =
            "androidx.constraintlayout:constraintlayout:${Ver.Android.constraint}"
        const val material = "com.google.android.material:material:${Ver.Android.material}"
        const val recyclerview = "androidx.recyclerview:recyclerview:${Ver.Android.recyclerview}"
    }

    object Glide {
        const val core = "com.github.bumptech.glide:glide:${Ver.glide}"
        const val compiler = "com.github.bumptech.glide:compiler:${Ver.glide}"
    }

    object Kotlin {
        const val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Ver.Kotlin.coroutines}"
        const val serialization =
            "org.jetbrains.kotlinx:kotlinx-serialization-json:${Ver.Kotlin.serialization}"

        object Test {
            const val junit = "org.jetbrains.kotlin:kotlin-test-junit:${Ver.Kotlin.stdlib}"
        }
    }

    object Koin {
        const val koin =  "org.koin:koin-core:${Ver.koin}"
        const val koinExt =  "org.koin:koin-core-ext:${Ver.koin}"
        const val koinAndroid =  "org.koin:koin-android:${Ver.koin}"
        const val koinViewModel = "org.koin:koin-android-viewmodel:${Ver.koin}"
    }

    object JetPack {
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Ver.jetPack}"
    }

    object Retrofit {
        const val core = "com.squareup.retrofit2:retrofit:${Ver.retrofit}"
        const val kotlinxConverter =
            "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Ver.retrofitKotlinxConverter}"
    }

    object OkHttp {
        const val core = "com.squareup.okhttp3:okhttp:${Ver.okHttp}"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Ver.okHttp}"
    }

    object Test {
        object Mockk {
            const val core = "io.mockk:mockk:${Ver.Test.mockk}"
        }
    }

}