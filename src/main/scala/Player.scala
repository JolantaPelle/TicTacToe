/**
 * Stores info about the player and checks if the player mark is valid
 * @author Jolanta Pelle
 * @param name - player name
 * @param mark - player mark ("x" or "o")
 */
case class Player(name: String, var mark: Char) {
  /**
   * Player input for the player mark (helps check if the input is not empty to avoid an error)
   */
  var readMark: String = ""
  // checks if the mark is an "x" or "o":
  while (mark.toLower != 'x' && mark.toLower != 'o') {
    println(s"ERROR: player \"$name\" has the wrong mark \"$mark\"")
    print("Enter an \"x\" or \"o\": ")
    readMark = scala.io.StdIn.readLine()
    // checks if the input (mark) is empty:
    if (readMark.isEmpty) mark = ' '
    else mark = readMark.toCharArray.head
  }
}