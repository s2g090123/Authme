package com.example.authme.screen.info

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.authme.testing.TestTag
import com.example.githubusersdk.models.UserInfo
import org.koin.androidx.compose.koinViewModel

@Composable
fun UserInfoScreen(
    viewModel: UserInfoViewModel = koinViewModel(),
    onBack: () -> Unit,
) {
    val state = viewModel.state
    Scaffold(
        topBar = {
            IconButton(
                modifier = Modifier.testTag(TestTag.UserInfoScreen_Btn_Back),
                onClick = onBack
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = null,
                )
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when {
                state.loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                state.error != null || state.data == null -> {
                    Text(
                        modifier = Modifier
                            .testTag(TestTag.UserInfoScreen_Text_Error)
                            .align(Alignment.Center),
                        text = state.error ?: "Not Found"
                    )
                }
                else -> {
                    state.data?.let {
                        InfoBody(info = it)
                    }
                }
            }
        }
    }
}

@Composable
private fun InfoBody(
    info: UserInfo
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 12.dp),
    ) {
        AsyncImage(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(100.dp)
                .clip(CircleShape),
            model = info.avatarUrl,
            contentDescription = null,
        )
        Text(
            modifier = Modifier
                .testTag(TestTag.InfoBody_Text_Name)
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp),
            text = info.name,
            style = MaterialTheme.typography.titleMedium
        )
        if (info.bio.isNotBlank()) {
            Text(
                modifier = Modifier
                    .testTag(TestTag.InfoBody_Text_Bio)
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp),
                text = info.bio,
                style = MaterialTheme.typography.titleSmall
            )
        }
        Divider(
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
        )
        Row(
            modifier = Modifier.padding(top = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = null,
            )
            Column(
                modifier = Modifier.padding(start = 12.dp)
            ) {
                Text(
                    modifier = Modifier.testTag(TestTag.InfoBody_Text_Login),
                    text = info.login
                )
                if (info.siteAdmin) {
                    Text(
                        modifier = Modifier
                            .testTag(TestTag.InfoBody_Text_Staff)
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color.Blue)
                            .padding(4.dp),
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        text = "STAFF",
                    )
                }
            }
        }
        if (info.location.isNotBlank()) {
            Row(
                modifier = Modifier.padding(top = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = null,
                )
                Text(
                    modifier = Modifier
                        .testTag(TestTag.InfoBody_Text_Location)
                        .padding(start = 12.dp),
                    text = info.location
                )
            }
        }
        if (info.blog.isNotBlank()) {
            Row(
                modifier = Modifier.padding(top = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Filled.ExitToApp,
                    contentDescription = null,
                )
                ClickableText(
                    modifier = Modifier
                        .testTag(TestTag.InfoBody_Text_Blog)
                        .padding(start = 12.dp),
                    text = AnnotatedString(info.blog),
                    style = TextStyle(color = Color.Blue),
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(info.blog))
                        context.startActivity(intent)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun InfoBodyPreview() {
    InfoBody(
        info = UserInfo(
            avatarUrl = "",
            name = "1",
            bio = "bio",
            login = "1",
            siteAdmin = false,
            location = "location",
            blog = "blog",
        ),
    )
}