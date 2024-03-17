package com.example.authme.utils

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.SemanticsNodeInteractionCollection
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.filter
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.performScrollTo

fun SemanticsNodeInteractionsProvider.onAllNodesWithUnmergedTree(
    testTag: String
): SemanticsNodeInteractionCollection = onAllNodesWithTag(testTag, true)

fun SemanticsNodeInteraction.tryScrollTo() = apply {
    try {
        performScrollTo()
    } catch (_: Throwable) {
    }
}

fun SemanticsNodeInteraction.onNodeWithTag(tag: String, index: Int): SemanticsNodeInteraction {
    return findNode(tag, index) ?: onNodeWithTag2(tag, index)
}

fun SemanticsNodeInteraction.onNodeWithTag2(tag: String, index: Int): SemanticsNodeInteraction {
    return onChildren()
        .filter(hasTestTag(tag))[index]
        .tryScrollTo()
}

fun SemanticsNodeInteraction.onNodeWithTag(tag: String) = onNodeWithTag(tag, 0)

private fun SemanticsNodeInteraction.findNode(tag: String, index: Int): SemanticsNodeInteraction? {
    val children = onChildren()
    val finding = try {
        children
            .filter(hasTestTag(tag))[index]
            .assertExists()
            .tryScrollTo()
    } catch (e: AssertionError) {
        null
    }
    return finding ?: run {
        val size = children.fetchSemanticsNodes().size
        repeat(size) {
            children[it].findNode(tag, index)?.also { node ->
                return@run node
            }
        }
        null
    }
}