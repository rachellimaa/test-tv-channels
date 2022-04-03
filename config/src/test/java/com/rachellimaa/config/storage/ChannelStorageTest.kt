package com.rachellimaa.config.storage

import kotlin.test.Test

class ChannelStorageTest {
    @Test
    fun `assert nextUrlSkipParams initial value is zero`() {
        assert(ChannelStorage.nextUrlSkipParams == 0)
    }
}