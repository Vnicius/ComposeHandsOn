package com.example.composehandson

data class Album(
    val cover: String,
    val title: String,
    val artist: String,
    val description: String,
)

val mockData = buildList {
    repeat(20) {
        add(
            Album(
                cover = "https://picsum.photos/200",
                title = "Title",
                artist = "Artist",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean ultricies at lectus et tristique. Duis eu nisl dolor. Vivamus quam.",
            ),
        )
    }
}
