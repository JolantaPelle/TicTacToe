import scala.io.{BufferedSource, Source}

class Board() {
  // new board file path:
  val newBoardPath: String = "src/resources/newBoard.txt"

  // makes a new board:
  val source: BufferedSource = Source.fromFile(newBoardPath)
  var board: String = source.mkString // variable: board
  source.close

  // a place to save board values:
  var boardValues: Array[Char] = Array('1', '2', '3', '4', '5', '6', '7', '8', '9')

  // removes the unnecessary info (square numbers) from the specified board and then prints the board:
  def printBoard(): Unit = {
    var printBoard = board
    for(i <- 1 to 9) printBoard = printBoard.replace(i.toString, " ")
    println(printBoard)
  }

  // replace function to save player moves on the board:
  def replaceSquare(square: Char, mark: Char): Unit = {
    var s = square
    var loop = true
    while (loop) {
      if ((boardValues contains s) && s != 'x' && s != 'o') {
        board = board.replace(s, mark) // updates the board
        boardValues.update(boardValues.indexOf(s), mark) // updates the boardValues
        loop = false
      }
      else {
        println(s"ERROR: the square \"$s\" cannot be changed or doesn't exist")
        print("Enter a different square (1 to 9): ")
        s = scala.io.StdIn.readLine().toCharArray.head
      }
    }
  }
}