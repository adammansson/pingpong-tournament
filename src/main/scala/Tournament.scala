class Tournament(players: Vector[Player]):
    private val nbrEntrants = players.length
    private val nbrRounds = (Math.log10(nbrEntrants) / Math.log10(2.0)).toInt

    def simulateTournament(): Unit = 
        val firstWinnersRound: Round = Round.withPlayers(0, players)

        val firstLosersRound: Round = Round.withMatches(0, nbrEntrants / 4)

        val lastWinnersRound: Round = Round.withMatches(nbrRounds, 1)
        val lastLosersRound: Round = Round.withMatches(nbrRounds, 1)

        var winnersRounds: Vector[Round] = 
            firstWinnersRound +: (for i <- 1 until nbrRounds yield 
                val round = Round(i)
                val nbrMatches = (Math.pow(2, nbrRounds - i - 1)).toInt
                for j <- 0 until nbrMatches do
                    round.addMatch(Match(j))
                round
            ).toVector

        val losersRounds: Vector[Round] =
            firstLosersRound +: (for i <- 1 until nbrRounds yield
                val round = Round(i)
                val nbrMatches = (Math.pow(2, nbrRounds - i - 1)).toInt
                for j <- 0 until nbrMatches do
                    round.addMatch(Match(j))
                round
            ).toVector :+ lastLosersRound
        
        for i <- 0 until winnersRounds.length - 1 do
            for m <- winnersRounds(i).getMatches do
                m.simulateMatch()
                val winnersM: Match = winnersRounds(i + 1).emptySlot 
                val losersM: Match = losersRounds(i).emptySlot 
                winnersM.assignPlayer(winnersM.emptySlot, m.getWinner.get)
                losersM.assignPlayer(losersM.emptySlot, m.getLoser.get)

        for i <- 0 until losersRounds.length - 1 do
            for m <- losersRounds(i).getMatches do
                m.simulateMatch()
                val losersM: Match = losersRounds(i + 1).emptySlot
                losersM.assignPlayer(losersM.emptySlot, m.getWinner.get)

        val wf = winnersRounds.last.getMatches(0)
        winnersRounds = winnersRounds :+ lastWinnersRound
        val gf = winnersRounds.last.getMatches(0)
        println("Winners final")
        println(wf)
        wf.simulateMatch()
        gf.assignPlayer(0, wf.getWinner.get)
        println("Losers final")
        val lf = losersRounds.last.getMatches(0)
        lf.assignPlayer(1, wf.getLoser.get)
        println(lf)
        lf.simulateMatch()
        gf.assignPlayer(1, lf.getWinner.get)

        println("Grand final")
        gf.simulateMatch()
        println(gf)
