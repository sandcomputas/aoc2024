package dev.sondre.day32

class Race(
    private val time: Long,
    private val distance: Long
) {
    companion object {
        fun fromPair(p: Pair<Long, Long>): Race {
            return Race(p.first, p.second)
        }
    }

    fun numberOfWaysToWin(): Int {
        return (0..time)
            .filter { timeToHold ->
                val speed = timeToHold
                val timeLeft = time - timeToHold
                val distanceTraveled = speed * timeLeft
                distanceTraveled > distance
            }.size
    }
}