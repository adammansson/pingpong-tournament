object Test:
    def main(args: Array[String]): Unit =
        val players: Vector[Player] =
            (for i <- 0 until args.headOption.getOrElse("8").toInt yield
                val name = s"${(65 + (i / 10)).toChar}${i % 10}"
                Player(name, i)
            ).toVector

        val tournament = Tournament(players)
        tournament.simulateTournament()
