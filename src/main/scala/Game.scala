/**
 * Runs an instance of the game with the help of the Player case class and Board class
 * @author Jolanta Pelle
 * @param player1 - player name (for player 1)
 * @param player2 - player name (for player 2)
 */
class Game(player1: String, player2: String) {
  // creating player 1 and 2:
  /**
   * Player 1 (Player case class instance)
   */
  val p1: Player = Player(player1, 'x')
  /**
   * Player 2 (Player case class instance)
   */
  val p2: Player = Player(player2, 'o')

  // creating a board:
  /**
   * Board (Board class instance)
   */
  val board: Board = new Board()

  // other variables (winner and ongoing)
  /**
   * Name of the winner (if there is one)
   */
  var winner: String = ""
  /**
   * A value that is used for loops while the game is ongoing
   */
  var ongoing: Boolean = true

  /**
   * Checks if the specified player has won
   * @param player - the player (class instance) that is gonna get checked
   * @return true if the specified player is a winner, false if the specified player is not a winner
   */
  def hasWinner(player: Player): Boolean = {
    // horizontal line:
    if (player.mark == board.boardValues(0) && player.mark == board.boardValues(1) && player.mark == board.boardValues(2)) true
    else if (player.mark == board.boardValues(3) && player.mark == board.boardValues(4) && player.mark == board.boardValues(5)) true
    else if (player.mark == board.boardValues(6) && player.mark == board.boardValues(7) && player.mark == board.boardValues(8)) true
    // vertical line:
    else if (player.mark == board.boardValues(0) && player.mark == board.boardValues(3) && player.mark == board.boardValues(6)) true
    else if (player.mark == board.boardValues(1) && player.mark == board.boardValues(4) && player.mark == board.boardValues(7)) true
    else if (player.mark == board.boardValues(2) && player.mark == board.boardValues(5) && player.mark == board.boardValues(8)) true
    // diagonal line:
    else if (player.mark == board.boardValues(0) && player.mark == board.boardValues(4) && player.mark == board.boardValues(8)) true
    else if (player.mark == board.boardValues(2) && player.mark == board.boardValues(4) && player.mark == board.boardValues(6)) true

    else false
  }

  /**
   * Checks if the game has ended
   * @return true if there is a winner or all of the squares are taken, false if there is no winner and there is a free square to make a move on
   */
  def hasEnded: Boolean = {
    var result = true

    // checking for a winner:
    if (hasWinner(p1)) winner = p1.name
    else if (hasWinner(p2)) winner = p2.name

    // checking if all of the board squares are filled:
    for(i <- 1 to 9) if (board.boardValues contains i.toString.toCharArray.head) result = false

    // if there is a winner - the game has ended:
    if (winner.nonEmpty) result = true

    result
  }

  /**
   * A Player (Player class) that will be doing the next move on the board
   */
  var nextMove: Player = p1
  /**
   * Player move (square number from 1 to 9)
   */
  var move: Char = ' '
  /**
   * Player input for the player move (helps check if the input is not empty to avoid an error)
   */
  var readMove: String = ""

  // info (displaying game name and showing square numbers):
  println("TIC TAC TOE\n")
  println(s"${p1.name} vs ${p2.name}\n")
  println("Square numbers:")
  println(board.board)


  // running the actual game:
  while (ongoing) {
    // player moves:
    if (nextMove == p1) {
      print(s"P1 \"${p1.name}\" - choose a square: ")
      readMove = scala.io.StdIn.readLine()
      // checks if the input (move) is empty:
      if (readMove.isEmpty) {
        nextMove = p1
      }
      else {
        move = readMove.toCharArray.head
        board.replaceSquare(move, p1.mark)
        nextMove = p2
      }
    }
    else if (nextMove == p2) {
      print(s"P2 \"${p2.name}\" - choose a square: ")
      readMove = scala.io.StdIn.readLine()
      // checks if the input (move) is empty:
      if (readMove.isEmpty) {
        nextMove = p2
      }
      else {
        move = readMove.toCharArray.head
        board.replaceSquare(move, p2.mark)
        nextMove = p1
      }
    }
    // checking if the game has ended/printing out the results:
    if(hasEnded) {
      // tie:
      if (winner.isEmpty) {
        println("------------------------")
        println("GAME RESULT: It's a TIE!")
        println("------------------------")
      }
      // winner:
      else {
        println(s"---------------------------${p1.name.replaceAll(".", "-")}-")
        println(s"GAME RESULT: The winner is ${p1.name}!")
        println(s"---------------------------${p1.name.replaceAll(".", "-")}-")
      }
      ongoing = false
    }
    // checks if the input (move) is NOT empty:
    if (readMove.nonEmpty) board.printBoard() // prints out the board
    else println("ERROR: square cannot be empty") // prints an error
  }
}