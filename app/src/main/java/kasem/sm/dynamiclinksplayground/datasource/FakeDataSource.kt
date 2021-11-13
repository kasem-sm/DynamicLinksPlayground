package kasem.sm.dynamiclinksplayground.datasource

import kasem.sm.dynamiclinksplayground.domain.model.Post

object FakeDataSource {

    val listOfPost = listOf(Post(
        id = 1034,
        title = "Random Title 1",
        author = "Admin",
        imageLink = "https://images.pexels.com/photos/9336141/pexels-photo-9336141.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"
    ),
        Post(
            id = 474,
            title = "Stock Image",
            author = "User 2727",
            imageLink = "https://images.pexels.com/photos/10163165/pexels-photo-10163165.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"
        ))
}
