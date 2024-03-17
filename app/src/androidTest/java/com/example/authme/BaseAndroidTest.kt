package com.example.authme

import com.example.authme.rule.KoinTestRule
import org.junit.Rule

open class BaseAndroidTest {
    @get:Rule
    val koinTestRule = KoinTestRule(testModule)
}