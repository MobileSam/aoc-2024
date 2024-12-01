import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val leftColumn = input.map {
            it.substringBefore("   ").toInt()
        }.sorted()

        val rightColumn = input.map {
            it.substringAfter("   ").toInt()
        }.sorted()

        val distance = leftColumn.zip(rightColumn).sumOf { row ->
            abs(row.first - row.second)
        }

        return distance
    }

    fun part2(input: List<String>): Int {
        val leftColumn = input.map {
            it.substringBefore("   ").toInt()
        }.sorted()

        val rightColumn = input.map {
            it.substringAfter("   ").toInt()
        }.sorted()

        val score = leftColumn.sumOf { left ->
            left * rightColumn.count { it == left }
        }

        return score
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
