import scala.util.Random
import scala.collection.mutable.ArrayBuffer

class Tournament(val nbrEntrants: Int):
    private var currentRound: Int = 1
    private val finalRound: Int = (Math.log10(nbrEntrants) / Math.log10(2.0)).toInt

    private val players: Vector[Player] =
        (for i <- 0 until nbrEntrants yield
            val name = s"${(65+(i / 10)).toChar}${i % 10}"
            val ability = (Random.nextGaussian()*50).toInt.abs

            Player(name, ability)
        ).toVector

    private val firstRound = Round(0)
    for i <- 0 until nbrEntrants / 2 do
        firstRound.addMatch(i, players(i), players(nbrEntrants - (i + 1)))
    
    private var rounds = Array.ofDim[Round](finalRound + 1)
    rounds(0) = firstRound
    
    private def simulateRound(): Unit =
        rounds(currentRound) = Round(currentRound)
        val previousMatches = rounds(currentRound - 1).viewMatches
        val nbrMatches = (Math.pow(2, finalRound - currentRound)).toInt

        for i <- 0 until (previousMatches.length - 1) by 2 do
            rounds(currentRound).addMatch(i, previousMatches(i).winner, previousMatches(i + 1).winner)

        rounds(currentRound).printMatches()
        currentRound += 1
    
    def simulateTournament(): Unit = 
        rounds(0).printMatches()
        for i <- 0 until finalRound do
            simulateRound()
        