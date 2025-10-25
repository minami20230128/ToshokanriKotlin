package app

data class Book(
    val title: String,
    val publisher: String,
    val date: String,
    val authors: List<String>
)