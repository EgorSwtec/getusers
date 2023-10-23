package com.example.getusers.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.getusers.GetUsersApi
import com.example.getusers.UserInfo

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var phrases by remember { mutableStateOf(emptyList<UserInfo>()) }
                    LaunchedEffect(true) {
                        phrases = GetUsersApi().getUsersInfo()
                    }
                    GreetingView(phrases)
                }
            }
        }
    }
}

@Composable
fun GreetingView(users: List<UserInfo>) {
    LazyColumn {
        items(users.size) {
            item -> UserItem(user = users[item])
        }
    }
}

@Composable
fun UserItem(user: UserInfo) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable(enabled = true) {}
        .padding(vertical = dimensionResource(id = R.dimen.list_vertical_padding))) {
        UserImage(url = user.avatarUrl)
        UserLogin(login = user.login)
    }

}

@Composable
fun UserImage(url: String) {
    AsyncImage(modifier = Modifier
        .padding(horizontal = dimensionResource(id = R.dimen.list_side_padding))
        .size(
            dimensionResource(id = R.dimen.list_icon_size),
            dimensionResource(id = R.dimen.list_icon_size)
        )
        .clip(shape = CircleShape),
        model = url,
        contentScale = ContentScale.Crop,
        contentDescription = null)
}

@Composable
fun RowScope.UserLogin(login: String) {
    Text(modifier = Modifier
        .padding(start = dimensionResource(id = R.dimen.list_side_padding))
        .align(Alignment.CenterVertically), text = login)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView(emptyList())
    }
}
