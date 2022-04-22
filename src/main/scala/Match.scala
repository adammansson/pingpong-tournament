case class Match(nbr: Int, player1: Player, player2: Player):
    override def toString(): String = 
        s"${player1.name} vs ${player2.name}"

    def winner: Player =
        if player1.ability >= player2.ability then 
            player1
        else
            player2

