import scala.io.{BufferedSource, Source}

class Board() {
  // new board file path:
  val newBoardPath: String = "src/resources/newBoard.txt"

  // makes a new board:
  val source: BufferedSource = Source.fromFile(newBoardPath)
  var board: String = source.mkString // variable: board
  source.close

  // removes the unnecessary info (square numbers) from the specified board and then prints the board:
  def print(): Unit = {
    var printBoard = board
    for(i <- 1 to 9) printBoard = printBoard.replace(i.toString, " ")
    println(printBoard)
  }

  // replace function to save player moves on the board:
  def replace(square: Char, mark: Char): Unit = {
    board = board.replace(square, mark)
  }
}
