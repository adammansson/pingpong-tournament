final case class Round(nbr: Int):
    private var matches = Vector[Match]()

    def getMatches = matches

    def nbrMatches = matches.length

    def emptySlot: Match =
        matches.find(m => m.emptySlot != -1).get

    def addMatch(m: Match): Unit =
        matches = matches :+ m

    def printMatches(): Unit =
        for m <- matches do
            println(m)
        println()

case object Round:
    def withMatches(nbr: Int, nbrMatches: Int): Round = 
        val round = Round(nbr)
        for i <- 0 until nbrMatches do
            round.addMatch(Match(i))
        round
    def withPlayers(nbr: Int, players: Vector[Player]): Round =
        val round = Round(nbr)
        round.matches = 
            (for i <- 0 until players.length / 2 yield
                val m = Match(i)
                m.assignPlayer(0, players(i))
                m.assignPlayer(1, players(players.length - i - 1))
                m
            ).toVector
        round
