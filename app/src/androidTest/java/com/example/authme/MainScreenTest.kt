package com.example.authme

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.authme.rule.KoinTestRule
import com.example.authme.testing.TestTag
import com.example.authme.utils.onAllNodesWithUnmergedTree
import com.example.authme.utils.onNodeWithTag
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class MainScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val koinTestRule = KoinTestRule(testModule)

    @Before
    fun setup() {
        composeTestRule.setContent {
            val context = LocalContext.current
            val navController = TestNavHostController(context).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            MainScreen(navController)
        }
    }

    @Test
    fun userListScreen_checksFirstItemUI() = runTest {
        composeTestRule.apply {
            waitUntilAtLeastOneExists(hasTestTag(TestTag.UserListItem))
            onAllNodesWithUnmergedTree(TestTag.UserListItem)[0].apply {
                onNodeWithTag(TestTag.UserListItem_Text_Login)
                    .assertTextEquals("1")
                onNodeWithTag(TestTag.UserListItem_Text_Staff)
                    .assertIsDisplayed()
            }
        }
    }

    @Test
    fun userListScreen_filterName_checksFirstItemUI() = runTest {
        composeTestRule.apply {
            onNodeWithTag(TestTag.UserListScreen_TextField)
                .performTextInput("2")
            waitUntilAtLeastOneExists(hasTestTag(TestTag.UserListItem))
            onAllNodesWithUnmergedTree(TestTag.UserListItem)[0].apply {
                onNodeWithTag(TestTag.UserListItem_Text_Login)
                    .assertTextEquals("2")
                onNodeWithTag(TestTag.UserListItem_Text_Staff)
                    .assertDoesNotExist()
            }
        }
    }

    @Test
    fun userInfoScreen_checksInfoUI() = runTest {
        composeTestRule.apply {
            waitUntilAtLeastOneExists(hasTestTag(TestTag.UserListItem))
            onAllNodesWithUnmergedTree(TestTag.UserListItem)[0]
                .performClick()
            onNodeWithTag(TestTag.InfoBody_Text_Name)
                .assertTextEquals("1")
            onNodeWithTag(TestTag.InfoBody_Text_Bio)
                .assertTextEquals("bio")
            onNodeWithTag(TestTag.InfoBody_Text_Login)
                .assertTextEquals("1")
            onNodeWithTag(TestTag.InfoBody_Text_Staff)
                .assertIsDisplayed()
            onNodeWithTag(TestTag.InfoBody_Text_Location)
                .assertTextEquals("location")
            onNodeWithTag(TestTag.InfoBody_Text_Blog)
                .assertTextEquals("blog")
        }
    }

    @Test
    fun userInfoScreen_clickBack_returnListScreen() = runTest {
        composeTestRule.apply {
            waitUntilAtLeastOneExists(hasTestTag(TestTag.UserListItem))
            onAllNodesWithUnmergedTree(TestTag.UserListItem)[0]
                .performClick()
            onNodeWithTag(TestTag.UserInfoScreen_Btn_Back)
                .performClick()
            onAllNodesWithUnmergedTree(TestTag.UserListItem)[0]
                .assertIsDisplayed()
        }
    }
}