import java.lang.UnsupportedOperationException
import kotlin.math.*

fun main() {
    part1()
    part2()
}

private fun part1() {
    val mem = inputD2.split(',').map(String::toInt).toIntArray()
    mem[1] = 12
    mem[2] = 2

    run(mem)
    println(mem[0])
}

private fun part2() {
    for (a in 0 until 100) {
        for (b in 0 until 100) {
            val mem = inputD2.split(',').map(String::toInt).toIntArray()

            mem[1] = a
            mem[2] = b
            run(mem)

            if (mem[0] == 19690720) {
                println(100 * a + b)
                return
            }
        }
    }
}

private fun run(mem: IntArray) {
    var pc = 0

    while (mem[pc] != 99) {
        val a = mem[mem[pc + 1]]
        val b = mem[mem[pc + 2]]

        when {
            mem[pc] == 1 -> mem[mem[pc  + 3]] = a + b
            mem[pc] == 2 -> mem[mem[pc  + 3]] = a * b
            else -> throw UnsupportedOperationException()
        }

        pc += 4
    }
}
