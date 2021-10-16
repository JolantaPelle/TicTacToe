class Game(player1: String, player2: String) {
  // creating player 1 and 2:
  val p1: Player = Player(player1, 'x')
  val p2: Player = Player(player2, 'o')

  // creating a board:
  val board: Board = new Board()

  // other variables (winner and ongoing)
  var winner: String = ""
  var ongoing: Boolean = true

  // checks if the specified player has won:
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

  // checks if the game has ended:
  def hasEnded: Boolean = {
    var result = true

    // checking for a winner:
    if (hasWinner(p1)) winner = p1.playerName
    else if (hasWinner(p2)) winner = p2.playerName

    // checking if all of the board squares are filled:
    for(i <- 1 to 9) if (board.boardValues contains i.toString.toCharArray.head) result = false

    // if there is a winner - the game has ended:
    if (winner.nonEmpty) result = true

    result
  }

  var nextMove: Player = p1
  var move: Char = ' '

  // info (displaying game name and showing square numbers):
  println("TIC TAC TOE\n")
  println(s"${p1.playerName} vs ${p2.playerName}\n")
  println("Square numbers:")
  println(board.board)


  // running the actual game:
  while (ongoing) {
    // player moves:
    if (nextMove == p1) {
      print(s"P1 \"${p1.playerName}\" - choose a square: ")
      move = scala.io.StdIn.readLine().toCharArray.head
      board.replaceSquare(move, p1.mark)
      nextMove = p2
    }
    else if (nextMove == p2) {
      print(s"P2 \"${p2.playerName}\" - choose a square: ")
      move = scala.io.StdIn.readLine().toCharArray.head
      board.replaceSquare(move, p2.mark)
      nextMove = p1
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
        println(s"---------------------------${p1.playerName.replaceAll(".", "-")}-")
        println(s"GAME RESULT: The winner is ${p1.playerName}!")
        println(s"---------------------------${p1.playerName.replaceAll(".", "-")}-")
      }
      ongoing = false
    }
    // prints out the board:
    board.printBoard()
  }
}