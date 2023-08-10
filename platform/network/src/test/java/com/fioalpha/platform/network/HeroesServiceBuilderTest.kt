package com.fioalpha.platform.network

import com.fioalpha.platform.network.di.HeroesFactory
import com.fioalpha.testhelper.loadFile
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test

class HeroesServiceBuilderTest {

    private val mockFile = "result_wrapper.json"
    private val mockServer = MockWebServer()

    @After
    fun `after each test`() {
        mockServer.shutdown()
    }

    @Test
    fun `when execute call retrofit`() = runTest {
        mockServer.start()
        mockServer.enqueue(MockResponse().setBody(loadFile(mockFile)))

        val result = HeroesFactory.Impl.create(
                mockServer.url("/").toString()
        ).charactersHeroes()

        val url = mockServer.takeRequest().requestUrl
        assertThat(url?.queryParameter("hash")).isNotEmpty()
        assertThat(result.code).isEqualTo(200)
    }

}
