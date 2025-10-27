package app

import java.io.FileWriter
import java.io.PrintWriter
import java.io.BufferedWriter

fun main() {
    val main = Main()
    main.addBook()
    main.showAllBooks()
    main.saveBooks()
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

    //書籍情報を、ユーザが入力したテキストファイルに保存する。
    //「タイトル,出版社名,出版年月日,著者名」とカンマ区切りで保存。
    //指定したテキストファイルが存在しない場合は新たに作成。
    //すでに指定したファイルに何か書き込まれている場合は上書き。
    fun saveBooks() {
        println("書籍情報を保存するファイルの名前を入力してください。")
        var title = readLine()?: ""
        val fileWriter = FileWriter(title, false)
        val printWriter = PrintWriter(BufferedWriter(fileWriter))
        
        bookshelf.books.forEach { book ->
            val str = "${book.title},${book.publisher},${book.date},${book.authors.joinToString(",")}"
            printWriter.println(str)
        }

        printWriter.close()
    }

    private fun showBook(book: Book) {
        println("${book.title} (${book.publisher}, ${book.date})")
        println("   著者: ${book.authors.joinToString(", ")}")
    }
}