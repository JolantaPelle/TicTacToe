object TicTacToe extends App {
  // test:
  val b = new Board // makes a new board
  b.print() // prints the board (should be empty)
  b.replace('1','x')
  b.print() // prints the board (square 1 should be "x")
  b.replace('5','o')
  b.print() // prints the board (square 5 should be "o")
}
