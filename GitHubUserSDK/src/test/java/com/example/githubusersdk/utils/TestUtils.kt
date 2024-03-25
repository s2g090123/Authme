package com.example.githubusersdk.utils

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is

inline fun <reified T> Any.assertIsA(): T {
    MatcherAssert.assertThat(this, CoreMatchers.instanceOf(T::class.java))
    return this as T
}

inline fun <T> T.assertIsTrue(runnable: (T) -> Boolean) = apply {
    MatcherAssert.assertThat(runnable(this), Is.`is`(true))
}