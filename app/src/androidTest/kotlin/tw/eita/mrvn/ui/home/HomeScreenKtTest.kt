package tw.eita.mrvn.ui.home

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test
import tw.eita.mrvn.data.News

internal class HomeScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun newsCard() {
        composeTestRule.setContent {
            NewsCard(
                news = News(
                    title = "TestNews",
                    img = "https://media.istockphoto.com/photos/man-climbing-indoor-picture-id1360346398",
                    description = "just random img"
                )
            )
        }
        composeTestRule.onNodeWithText("TestNews")
            .assertIsDisplayed()
            .assertExists()
    }

    @Test
    fun selection() {
        composeTestRule.setContent {
            Selection(buttonText = "Test")
        }
        composeTestRule.onNodeWithText("Test")
            .assertIsEnabled()
            .assertHasClickAction()
            .assertExists()
    }
}