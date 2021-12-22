package hu.unideb.inf.f1uptodate.model.raceresult

class RaceResult(

    val round : String,
    val name : String,
    val date : String,
    val winner : String,

)
{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RaceResult

        if (round != other.round) return false
        if (name != other.name) return false
        if (date != other.date) return false
        if (winner != other.winner) return false

        return true
    }

    override fun hashCode(): Int {
        var result = round.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + date.hashCode()
        result = 31 * result + winner.hashCode()
        return result
    }
}