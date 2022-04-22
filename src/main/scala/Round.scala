case class Round(nbr: Int):
    private var matches = Vector[Match]()

    def viewMatches: Vector[Match] = matches

    def addMatch(matchNbr: Int, player1: Player, player2: Player): Unit =
        matches = matches :+ Match(matchNbr, player1, player2)

    def printMatches(): Unit =
        for m <- matches do
            println(m)
        println()