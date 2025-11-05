package app

import java.io.FileWriter
import java.io.PrintWriter
import java.io.BufferedWriter
import java.io.File
import java.io.FileReader
import java.io.FileNotFoundException

fun main() {
    val main = Main()
    main.addBook()
    main.showAllBooks()
}

class Main {
    val bookshelf = Bookshelf()

    //すべての書籍情報を確認できる。
    fun showAllBooks() {
        bookshelf.books.forEach {
            it.show()
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
        val authors = generateSequence { readLine()?.takeIf { it.isNotBlank() } }.toList()

        val book = Book(title, publisher, date, authors)
        bookshelf.add(book)
    }

    //タイトルを部分一致で書籍検索し、該当する書籍を消去。
    //複数該当する書籍がある場合、どの書籍を消去するかを一つだけ番号で指定してもらう。（保留。いったん全部削除する方向で実装）
    fun deleteBooks() {
        println("消去する書籍のタイトルを入力してください。")
        val title = readLine()?: ""
        val deletingBooks = bookshelf.books.filter {
            it.title.contains(title)
        }

        println("これらの書籍を削除しますか？ y/n")
        deletingBooks.forEach {
            it.show()
        }

        val yn = readLine()?: ""
        if(yn == "y") {
            bookshelf.delete(deletingBooks)
            println("書籍を削除しました。")
        } else {
            println("書籍の削除を中止しました。")
        }
    }

    //タイトルを部分一致で書籍検索。複数該当する書籍があれば複数表示。
    fun searchBooks() {
        println("探したい書籍のタイトルを入力してください。")
        val title = readLine()?: ""
        val searchedBooks = bookshelf.books.filter {
            it.title.contains(title)
        }

        searchedBooks.forEach {
            it.show()
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

    //ユーザが指定したテキストファイルから書籍情報を読み出す。
    //以下の場合はエラーメッセージを出す。
    //・指定したファイルがない
    //・ファイルの形式が不正（split後の要素数が4つ未満）
    //  不正な行の処理を飛ばして次の行の処理を実行する。
    fun loadBooks() {
        println("書籍情報を読み出すファイルの名前を入力してください。")
        var fileTitle = readLine()?: ""
        try {
            val file = File(fileTitle)
            file.bufferedReader().use { reader -> //.useと書くと自動的に閉じてくれる
                reader.forEachLine line@{ 
                    val list = it.split(",")
                    if (list.size < 4) {
                        println("行の形式が不正です")
                        return@line
                    }

                    val title = list[0]
                    val publisher = list[1]
                    val date = list[2]
                    val authors = list.drop(3)
                    val book = Book(title, publisher, date, authors)
                    this.bookshelf.add(book)

                    println("ファイルをロードしました。")
                }
            } 
        } catch (e: FileNotFoundException) {
            println("指定したファイルが見つかりません。")
        } 
    }
    

    private fun Book.show() {
        println("$title ($publisher, $date)")
        println("   著者: ${authors.joinToString(", ")}")
    }
}