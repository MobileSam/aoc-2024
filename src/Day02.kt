import kotlin.math.abs

fun main() {
    fun isValidReport(levels: List<Int>): Boolean {
        val diffs = levels.mapIndexed { index, value ->
            if (index == 0) {
                0
            } else {
                value - levels[index - 1]
            }
        }.drop(1)

        return if (diffs.any { abs(it) > 3 }) {
            false
        } else if (diffs.any { it >= 0 } && diffs.any { it <= 0 }) {
            false
        } else {
            true
        }
    }

    fun part1(input: List<String>): Int {
        val validReports = input.filter { line ->
            val levels = line.split(" ").map { it.toInt() }
            isValidReport(levels)
        }
        return validReports.size
    }

    fun part2(input: List<String>): Int {
        val validReports = input.filter { line ->
            val levels = line.split(" ").map { it.toInt() }
            if (isValidReport(levels)) {
                return@filter true
            }

            levels.forEachIndexed { index, i ->
                val levelsSubset = levels.filterIndexed { currentIndex, i -> index != currentIndex }
                if (isValidReport(levelsSubset)) {
                    return@filter true
                }
            }

            false
        }
        return validReports.size
    }

    // Or read a large test input from the `src/Day02_test.txt` file:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    // Read the input from the `src/Day02.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
