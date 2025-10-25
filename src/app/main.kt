package app

fun main(args: Array<String>) {
    val main = Main()
    main.addBook()
    main.showAllBooks()
}

class Main {
    var bookshelf = Bookshelf()

    fun showAllBooks() {
        val books = bookshelf.getBooks()
        for (book in books) {
            println(book.title)
        }
    }

    fun addBook() {
        val book = Book("title", "publisher", "2025-08-29", arrayOf("author"))
        bookshelf.addBook(book)
    }
}
