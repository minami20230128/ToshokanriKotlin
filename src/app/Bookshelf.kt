package app

class Bookshelf {
    private val _books: MutableList<Book>

    init {
        this._books = mutableListOf<Book>()
    }

    fun addBook(book: Book) = _books.add(book)

    fun getBooks(): List<Book> = _books.toList()
}