case class Player(playerName: String, var mark: Char) {
  // checks if the mark is an "x" or "o":
  while (mark.toLower != 'x' && mark.toLower != 'o') {
    println(s"ERROR: player \"$playerName\" has the wrong mark \"$mark\"")
    print("Enter an \"x\" or \"o\": ")
    mark = scala.io.StdIn.readLine().toCharArray.head
  }
}