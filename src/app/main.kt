package app

fun main() {
    val main = Main()
    main.addBook()
    main.showAllBooks()
}

class Main {
    val bookshelf = Bookshelf()

    fun showAllBooks() {
        bookshelf.books.forEachIndexed {
            index, b ->
            println("${index + 1}. ${b.title} (${b.publisher}, ${b.date})")
            println("   著者: ${b.authors.joinToString(", ")}")
        }
    }

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
}
