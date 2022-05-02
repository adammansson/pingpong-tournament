final case class Round(nbr: Int):
    private var _players = Vector[Player]()
    private var _matches = Vector[Match]()
    def players          = _players
    def matches          = _matches

    def addPlayer(p: Player): Unit =
        _players = (_players :+ p).sortBy(_.seed)

    def createMatches(): Unit =
        for i <- 0 until _players.length / 2 do
            val m = Match(i)
            m.addPlayer(_players(i))
            _matches = _matches :+ m

        for i <- _matches.indices do
            _matches(i).addPlayer(_players(_players.length - i - 1))

    override def toString(): String =
        s"Round $nbr\n${_matches.mkString("\n")}"
