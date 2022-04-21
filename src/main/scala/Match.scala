case class Match(id: Int, player1: Player, player2: Player):
    def winner: Player =
        if player1.ability >= player2.ability then 
            player1
        else
            player2

