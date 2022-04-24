import scala.util.Random
import scala.collection.mutable.ArrayBuffer

class Tournament(val nbrEntrants: Int):
    private var currentRound: Int = 1
    private val finalRound: Int = (Math.log10(nbrEntrants) / Math.log10(2.0)).toInt

    private val players: Vector[Player] =
        (for i <- 0 until nbrEntrants yield
            val name = s"${(65+(i / 10)).toChar}${i % 10}"
            val ability = (Random.nextGaussian()*20 + 50).toInt.abs

            Player(name, ability)
        ).sortBy(player => player.ability).reverse.toVector

    private val firstRound = Round(0)
    for i <- 0 until nbrEntrants / 2 do
        val m = Match(i, players(i), players(nbrEntrants - (i + 1)))
        firstRound.addMatch(m)
    
    private var rounds = Array.ofDim[Round](finalRound + 1)
    rounds(0) = firstRound
    
    private def simulateRound(): Unit =
        val round = Round(currentRound)
        val prevRoundMatches = rounds(currentRound - 1).getMatches

        for i <- 0 until (prevRoundMatches.length - 1) by 2 do
            val m = Match(i, prevRoundMatches(i).winner, prevRoundMatches(prevRoundMatches.length - i - 1).winner)
            round.addMatch(m)

        round.printMatches()
        rounds(currentRound) = round
        currentRound += 1
    
    def simulateTournament(): Unit = 
        rounds(0).printMatches()
        for i <- 0 until finalRound do
            simulateRound()
        