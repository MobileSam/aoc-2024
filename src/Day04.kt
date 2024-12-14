fun main() {
    fun part1(input: List<String>): Int {
        val lines = mutableListOf<String>()
        lines.addAll(input)

        for (index in 0..<input.first().length) {
            val verticalLine = input.joinToString("") { line -> line.substring(index, index + 1) }
            lines.add(verticalLine)
        }

        for (x in 0..<input.first().length) {
            var diagonalLine = ""   // Lines that looks like \
            var reverseDiagonalLine = "" // Lines that looks like /

            for (y in 0..<minOf(input.first().length - x, input.size - x)) {
                diagonalLine += input[y][x + y]
                reverseDiagonalLine += input[y][input.first().length - (x + y) - 1]
            }

            lines.add(diagonalLine)
            lines.add(reverseDiagonalLine)
        }

        for (y in 1..<input.size) {
            var diagonalLine = ""   // Lines that looks like \
            var reverseDiagonalLine = "" // Lines that looks like /

            for (x in 0..<minOf(input.first().length - y, input.size - y)) {
                diagonalLine += input[y + x][x]
                reverseDiagonalLine += input[y + x][input.first().length - x - 1]
            }

            lines.add(diagonalLine)
            lines.add(reverseDiagonalLine)
        }

        lines.addAll(lines.map { it.reversed() })

        val xmasRegex = """XMAS""".toRegex()
        return lines.sumOf { line ->
            xmasRegex.findAll(line).count()
        }
    }

    fun part2(input: List<String>): Int {
        val groups = mutableListOf<String>()
        for (y in 0..<input.size - 2) {
            for (x in 0..<input.first().length - 2) {
                var group = ""

                repeat(3) { dy ->
                    repeat(3) { dx ->
                        group += input[y + dy][x + dx]
                    }
                }

                groups.add(group)
            }
        }

        return groups.sumOf { group ->
            // M.M
            // .A.
            // S.S
            Regex("M.M.A.S.S").findAll(group).count() +

            // S.M
            // .A.
            // S.M
            Regex("S.M.A.S.M").findAll(group).count() +

            // M.S
            // .A.
            // M.S
            Regex("M.S.A.M.S").findAll(group).count() +

            // S.S
            // .A.
            // M.M
            Regex("S.S.A.M.M").findAll(group).count()
        }
    }

    // Or read a large test input from the `src/Day04_test.txt` file:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 18)
    check(part2(testInput) == 9)

    // Read the input from the `src/Day04.txt` file.
    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
