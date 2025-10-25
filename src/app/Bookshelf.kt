package app

class Bookshelf {
    private val _books = mutableListOf<Book>()

    fun add(book: Book) = _books.add(book)

    val books: List<Book>
        get() = _books.toList()
}