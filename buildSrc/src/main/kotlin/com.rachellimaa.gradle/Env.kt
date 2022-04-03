package com.rachellimaa.gradle

object Env {
    object Android {
        const val targetSdk = 30
        const val compileSdk = 30
        const val minSupportedSDK = 22
        const val buildToolsVersion = "30.0.3"
    }

    // General build properties
    object Build {

        private const val major = 1
        private const val minor = 0
        private const val patch = 0
        private const val build = 0 // bump for dogfood builds, public betas, internal test track, etc.
        private const val rc = 1

        const val versionCode = (major * 1000000) + (minor * 10000) + (patch * 100) + build
        val versionName = "$major.$minor.$patch${getRC()}"

        private fun getRC(): String = if (rc > 0) "-rc$rc" else ""
    }
}
