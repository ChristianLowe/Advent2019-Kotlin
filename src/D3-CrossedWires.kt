import java.lang.UnsupportedOperationException
import kotlin.math.*

fun main() {
    part1()
    part2()
}


enum class Direction {
    UP, DOWN, LEFT, RIGHT;

    companion object {
        fun from(letter: Char) = when (letter) {
            'U' -> UP
            'D' -> DOWN
            'L' -> LEFT
            'R' -> RIGHT
            else -> throw UnsupportedOperationException()
        }
    }
}

typealias Point = Pair<Int, Int>
operator fun Point.plus(other: Point) = Point(this.first + other.first, this.second + other.second)
fun Point.manhattan() = abs(first) + abs(second)
fun Point.travel(distance: Int, direction: Direction) = when (direction) {
    Direction.UP -> Point(this.first, this.second + distance)
    Direction.DOWN -> Point(this.first, this.second - distance)
    Direction.LEFT -> Point(this.first - distance, this.second)
    Direction.RIGHT -> Point(this.first + distance, this.second)
}


class Segment(private val from: Point, private val to: Point, private val direction: Direction) {
    fun intersects(point: Point): Boolean = when (direction) {
        Direction.UP -> point.first == from.first && point.second in (from.second .. to.second)
        Direction.DOWN -> point.first == from.first && point.second in (to.second .. from.second)
        Direction.LEFT -> point.second == from.second && point.first in (to.first .. from.first)
        Direction.RIGHT -> point.second == from.second && point.first in (from.first .. to.first)
    }
}


private fun part1() {
    val collisions = mutableListOf<Point>()
    val lineA = inputD3.lines()[0].split(',')
    val lineB = inputD3.lines()[1].split(',')

    var currentLocation = Point(0, 0)
    for (opA in lineA) {
        val distance = opA.substring(1).toInt()
        val direction = Direction.from(opA[0])

        val nextLocation = currentLocation.travel(distance, direction)
        val segment = Segment(currentLocation, nextLocation, direction)

        var locationB = Point(0, 0)
        for (opB in lineB) {
            var distanceB = opB.substring(1).toInt()
            val directionB = Direction.from(opB[0])

            while (distanceB != 0) {
                locationB = locationB.travel(1, directionB)

                if (segment.intersects(locationB)) {
                    collisions.add(locationB)
                }

                distanceB--
            }
        }

        currentLocation = nextLocation
    }

    val result = collisions.map(Point::manhattan).min()
    println(result)
}

private fun part2() {

}
