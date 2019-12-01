import kotlin.math.*

fun main() {
    part1()
    part2()
}

private fun part1() {
    val result = inputD1
        .lines()
        .map(String::toInt)
        .map { it / 3 }
        .map { it - 2 }
        .sum()

    println(result)
}

private fun part2() {
    var input = inputD1.lines().map(String::toInt)
    var result = 0

    while (input.sum() != 0) {
        input = input
            .map { it / 3 }
            .map { it - 2 }
            .map { max(it, 0) }

        result += input.sum()
    }

    println(result)
}
