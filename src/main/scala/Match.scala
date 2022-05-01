final case class Match(nbr: Int):
  private var players = Array.ofDim[Player](2)

  def assignPlayer(i: Int, player: Player): Unit =
    players(i) = player

  def emptySlot: Int =
    if players(0) == null then 0
    else if players(1) == null then 1
    else -1

  private var winner: Option[Player] = None
  private var loser: Option[Player] = None

  def getWinner: Option[Player] = winner
  def getLoser: Option[Player] = loser

  def simulateMatch(): Unit =
    if winner.isDefined || loser.isDefined || players(0) == null || players(1) == null then
      throw new IllegalArgumentException

    if players(0).ability == players(1).ability then
        if Math.random >= 0.5 then
            winner = Some(players(0))
            loser = Some(players(1))
        else
            winner = Some(players(1))
            loser = Some(players(0))
    else if players(0).ability > players(1).ability then
        winner = Some(players(0))
        loser = Some(players(1))
    else
        winner = Some(players(1))
        loser = Some(players(0))

  override def toString(): String =
    if winner.isEmpty || loser.isEmpty then s"${players(0)} vs ${players(1)}"
    else s"${winner.get} W vs ${loser.get}"
