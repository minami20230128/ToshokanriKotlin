package app

class Bookshelf {
    private val _books = mutableListOf<Book>()

    fun add(book: Book) = _books.add(book)

    fun delete(book: Book) = _books.remove(book)

    fun find(title: String) :List<Book> {
        val books =_books.filter {
            it.title.contains(title)
        }
        return books
    }

    fun findIndexed(title: String): List<Pair<Int, Book>> =
        _books.mapIndexedNotNull { index, book ->
            if (book.title.contains(title)) index to book else null
    }

    fun replace(index: Int, book: Book) {
        _books.set(index, book)
    }

    fun findByIndex(index: Int): Book {
        return _books.get(index)
    }

    val books: List<Book>
        get() = _books.toList()
}