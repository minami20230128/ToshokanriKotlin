package app

fun main() {
    val main = Main()
    main.addBook()
    main.showAllBooks()
    main.searchBooks()
}

class Main {
    val bookshelf = Bookshelf()

    //すべての書籍情報を確認できる。
    fun showAllBooks() {
        bookshelf.books.forEach {
            this.showBook(it)
        }
    }

    //書籍を追加する。著者の入力を終了したいときはEnterを押す。
    fun addBook() {
        println("タイトルを入力してください。")
        val title = readLine()?: ""
        println("出版社名を入力してください。")
        val publisher = readLine()?: ""
        println("出版年月日を入力してください。")
        val date = readLine()?: ""
        println("著者名を入力してください。入力を終了したいときは、何も入力せずEnterを押してください。")
        val authors = mutableListOf<String>()
        while(true) {
            val author = readLine() ?: break //nullなら終了
            if (author.isEmpty()) break
            authors.add(author)
        }

        val book = Book(title, publisher, date, authors)
        bookshelf.add(book)
    }

    //タイトルを部分一致で書籍検索。複数該当する書籍があれば複数表示。
    fun searchBooks() {
        println("探したい書籍のタイトルを入力してください。")
        val title = readLine()?: ""
        val searchedBooks = bookshelf.books.filter {
            it.title.contains(title)
        }

        searchedBooks.forEach {
            this.showBook(it)
        }
    }

    private fun showBook(book: Book) {
        println("${book.title} (${book.publisher}, ${book.date})")
        println("   著者: ${book.authors.joinToString(", ")}")
    }
}