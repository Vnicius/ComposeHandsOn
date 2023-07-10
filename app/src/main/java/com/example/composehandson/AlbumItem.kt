package com.example.composehandson

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.composehandson.ui.theme.ComposeHandsOnTheme

@Composable
fun AlbumItem(album: Album, modifier: Modifier = Modifier) {
    var isExpanded by remember { mutableStateOf(false) }
    val shadowElevation by animateDpAsState(
        if (isExpanded) {
            4.dp
        } else {
            1.dp
        },
    )
    val backgroundColor by animateColorAsState(
        if (isExpanded) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            MaterialTheme.colorScheme.secondaryContainer
        },
    )

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        shadowElevation = shadowElevation,
    ) {
        Row(
            modifier = Modifier
                .clickable { isExpanded = !isExpanded }
                .fillMaxWidth()
                .background(backgroundColor)
                .padding(8.dp),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(album.cover)
                    .crossfade(true)
                    .diskCachePolicy(CachePolicy.DISABLED)
                    .memoryCachePolicy(CachePolicy.DISABLED)
                    .build(),
                contentDescription = "",
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.primary)
                    .size(50.dp),
            )
            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(
                    text = album.title,
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = album.artist,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.secondary,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = album.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.animateContentSize(),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlbumItemPreview() {
    ComposeHandsOnTheme {
        AlbumItem(
            album =
            Album(
                cover = "",
                title = "Title",
                artist = "Artist",
                description = "Description Description Description Description Description Description Description",
            ),
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun AlbumItemPreviewDark() {
    ComposeHandsOnTheme {
        AlbumItem(
            album =
            Album(
                cover = "",
                title = "Title",
                artist = "Artist",
                description = "Description",
            ),
        )
    }
}
