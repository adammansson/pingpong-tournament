import scala.util.Random
object Main:
    def main(args: Array[String]): Unit =
        val players: Vector[Player] =
            (for i <- 0 until args.headOption.getOrElse("8").toInt yield
                val name = s"${(65+(i / 10)).toChar}${i % 10}"
                val ability = (Random.nextGaussian()*20 + 50).toInt.abs

                Player(name, ability)
            ).sortBy(player => player.ability).reverse.toVector

        val tournament = Tournament(players)
        tournament.simulateTournament()