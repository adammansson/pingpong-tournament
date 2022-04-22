object Main:
    def main(args: Array[String]): Unit =
        val tournament = Tournament(args.headOption.getOrElse("64").toInt)
        tournament.simulateTournament()