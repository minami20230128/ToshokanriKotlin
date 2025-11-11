package app

class Bookshelf {
    private val _books = mutableListOf<Book>()

    fun add(book: Book) = _books.add(book)

    fun delete(books: List<Book>) = _books.removeAll(books)

    fun find(title: String) :List<Book> {
        val books =_books.filter {
            it.title.contains(title)
        }
        return books
    }

    val books: List<Book>
        get() = _books.toList()
}