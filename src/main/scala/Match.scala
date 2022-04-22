case class Match(nbr: Int, player1: Player, player2: Player):
    val winner: Player =
        if player1.ability == player2.ability then
            if Math.random >= 0.5 then player1
            else player2

        else if player1.ability > player2.ability then 
            if Math.random > 0.2 then player1
            else player2
        else
            if Math.random > 0.2 then player2
            else player1
    
    override def toString(): String = 
        s"${player1.name}(${player1.ability})${if winner == player1 then "W" else " "} vs ${player2.name}(${player2.ability})${if winner == player2 then "W" else " "}"

