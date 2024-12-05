fun main() {
    fun part1(input: List<String>): Int {
        val matches = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex().findAll(input.joinToString())

        return matches.map {
            it.groupValues[1].toInt() * it.groupValues[2].toInt()
        }.sum()
    }

    fun part2(input: List<String>): Int {
        val cleanInput = """don't\(\)(.*?)do\(\)""".toRegex().replace(input.joinToString(), "")
        val endCleanedInput = """don't\(\).*$""".toRegex().replace(cleanInput, "")
        val matches = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex().findAll(endCleanedInput)

        return matches.map {
            it.groupValues[1].toInt() * it.groupValues[2].toInt()
        }.sum()
    }

    // Or read a large test input from the `src/Day03_test.txt` file:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 161)
    check(part2(testInput) == 48)

    // Read the input from the `src/Day03.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
