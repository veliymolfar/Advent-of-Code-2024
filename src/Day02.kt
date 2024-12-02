fun main() {
    fun part1(input: List<String>): Int {
        val safeCount = input.filter { report -> report.splitToNumbers().isReportSafe() }.size
        return safeCount
    }

    fun part2(input: List<String>): Int {
        val safeCount = input.filter { report -> report.splitToNumbers().isReportSafe(true) }.size
        return safeCount
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(listOf("7 6 4 2 1")) == 1)
    check(part1(listOf("1 2 7 8 9")) == 0)
    check(part2(listOf("9 7 6 2 1")) == 0)

    // Or read a large test input from the `src/Day02_test.txt` file:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    // Read the input from the `src/Day02.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
