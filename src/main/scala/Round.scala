case class Round(nbr: Int):
    private var matches = Vector[Match]()

    def getMatches: Vector[Match] = matches

    def addMatch(m: Match): Unit =
        matches = matches :+ m

    def printMatches(): Unit =
        for m <- matches do
            println(m)
        println()