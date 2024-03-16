package com.example.authme

import org.junit.Rule
import org.koin.test.KoinTestRule

open class BaseUnitTest {
    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(testModule)
    }
}