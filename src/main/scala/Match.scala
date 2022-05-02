final case class Match(nbr: Int):
    private var _players: Vector[Player] = Vector()
    private var _result: Vector[Int]     = Vector(0, 0)
    def players                          = _players
    def result                           = _result
    def winner =
        require(_players.length == 2 && _result.exists(_ != 0))
        _players(_result.indexWhere(_ != 0))

    def addPlayer(player: Player): Unit =
        require(_players.length < 2 && _result.forall(_ == 0))
        _players = _players :+ player

        if _players.length == 2 then
            _players(0).seed - _players(1).seed match
                case x if x < 0 => _result = _result.updated(0, 3)
                case _          => _result = _result.updated(1, 3)

    override def toString(): String =
        if _result == Vector(0, 0) then
            s"$nbr ${_players.find(p => _players.indexOf(p) == 0).getOrElse("none")} vs ${_players
                .find(p => _players.indexOf(p) == 1)
                .getOrElse("none")}"
        else
            s"$nbr ${_players(0)} ${if _players(0) == winner then "W"
            else " "} vs ${if _players(1) == winner then "W" else " "} ${_players(1)}"
