package com.example.githubusersdk.utils

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is

inline fun <reified V> assertIsA(item: Any): V {
    MatcherAssert.assertThat(item, CoreMatchers.instanceOf(V::class.java))
    return item as V
}

inline fun <T> T.assertIsTrue(runnable: (T) -> Boolean) = apply {
    MatcherAssert.assertThat(runnable(this), Is.`is`(true))
}