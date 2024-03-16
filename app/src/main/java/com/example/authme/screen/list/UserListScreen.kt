package com.example.authme.screen.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.authme.screen.list.event.ListEvent
import com.example.githubusersdk.models.User
import org.koin.androidx.compose.koinViewModel

@Composable
fun UserListScreen(
    viewModel: UserListViewModel = koinViewModel(),
    onClick: (User) -> Unit,
) {
    val search by viewModel.search.collectAsState()
    val items = viewModel.users.collectAsLazyPagingItems()
    Column {
        OutlinedTextField(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
                .height(48.dp),
            value = search,
            onValueChange = {
                viewModel.onEvent(ListEvent.Search(it))
            }
        )
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items.itemCount) { index ->
                items[index]?.let {
                    UserListItem(
                        user = it,
                        onClick = onClick,
                    )
                }
            }
        }
    }
}

@Composable
private fun UserListItem(
    user: User,
    onClick: (User) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 4.dp)
            .fillMaxWidth()
            .clickable { onClick(user) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                model = user.avatarUrl,
                contentDescription = null,
            )
            Column(
                modifier = Modifier.padding(start = 12.dp)
            ) {
                Text(text = user.login)
                if (user.siteAdmin) {
                    Text(
                        modifier = Modifier
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
    }
}

@Preview
@Composable
private fun UserListItemPreview() {
    UserListItem(
        user = User(
            avatarUrl = "",
            login = "s2g090123",
            siteAdmin = true
        ),
        onClick = {}
    )
}