class Tournament(players: Vector[Player]):
    private val _nbrRounds =
        (Math.log10(players.length) / Math.log10(2.0)).toInt

    private val _rounds = (for i <- 0 until _nbrRounds yield Round(i)).toVector

    def simulateTournament(): Unit =
        for p <- players do _rounds(0).addPlayer(p)
        _rounds(0).createMatches()

        for i <- _rounds.indices.slice(0, _rounds.length - 1) do
            for p <- _rounds(i).matches.map(_.winner) do
                _rounds(i + 1).addPlayer(p)
            _rounds(i + 1).createMatches()

        for r <- _rounds do println(r)
        println(s"\n${_rounds.last.matches(0).winner.name} is the winner!")
