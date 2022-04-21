import scala.util.Random
import scala.collection.mutable.ArrayBuffer

class Tournament(val nbrEntrants: Int):
    var currentRound: Int = 1
    val finalRound: Int = (Math.log10(nbrEntrants) / Math.log10(2.0) - 2).toInt

    val players: Vector[Player] =
        (for i <- 0 until nbrEntrants yield
            val name = s"${(65+(i / 10)).toChar}${i % 10}"
            val ability = (Random.nextGaussian()*50).toInt.abs

            Player(name, ability)
        ).toVector

    val matches: ArrayBuffer[Match] =
        val temp = ArrayBuffer[Match]()

        for i <- 0 until nbrEntrants / 2 do
            temp += Match(i, players(i), players(nbrEntrants - (1 + i)))
        temp
    
    def simulateRound(start: Int, finish: Int): Unit =
        for i <- start to finish by 2 do
            val nextMatch = Match(matches.length, matches(i).winner, matches(i + 1).winner)
            println(nextMatch)
            matches += nextMatch
        println()

        currentRound += 1
    
    def simulateTournament(): Unit = 
        var start: Int = 0
        var finish: Int = (nbrEntrants / 2) - 1
        for i <- 0 to finalRound do
            simulateRound(2*start, finish)
            val nbrRounds = (Math.pow(2, finalRound - i)).toInt
            start += nbrRounds
            finish += nbrRounds
        