import scala.io.{BufferedSource, Source}

/**
 * Stores functions that relate to the board (like printing or making moves on it) and stores info about the board
 * @author Jolanta Pelle
 */
class Board() {
  /**
   * New board file path (file location and name)
   */
  val newBoardPath: String = "src/resources/newBoard.txt"

  // makes a new board:
  /**
   * Source from the new board file (used for reading the new board file)
   */
  val source: BufferedSource = Source.fromFile(newBoardPath)
  /**
   * Board saved as a string
   */
  var board: String = source.mkString
  source.close

  /**
   * A place to save board values as a char array
   */
  var boardValues: Array[Char] = Array('1', '2', '3', '4', '5', '6', '7', '8', '9')

  /**
   * Removes the unnecessary info (square numbers) from the board and then prints the board
   */
  def printBoard(): Unit = {
    var printBoard = board
    for(i <- 1 to 9) printBoard = printBoard.replace(i.toString, " ")
    println(printBoard)
  }

  /**
   * Saves player moves on the board
   * @param square - square number on the board
   * @param mark - player mark ("x" or "o")
   */
  def replaceSquare(square: Char, mark: Char): Unit = {
    var s = square
    var loop = true
    var readSquare = ""
    while (loop) {
      if ((boardValues contains s) && s != 'x' && s != 'o') {
        board = board.replace(s, mark) // updates the board
        boardValues.update(boardValues.indexOf(s), mark) // updates the boardValues
        loop = false
      }
      else {
        println(s"ERROR: the square \"$s\" cannot be changed or doesn't exist")
        print("Enter a different square (1 to 9): ")
        readSquare = scala.io.StdIn.readLine()
        // checks if the input (square) is empty:
        if (readSquare.isEmpty) s = ' '
        else s = readSquare.toCharArray.head
      }
    }
  }
}