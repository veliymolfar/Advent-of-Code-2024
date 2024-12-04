import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val leftList = mutableListOf<Int>()
        val rightList = mutableListOf<Int>()

        input.map { line ->
            val (leftNumber, rightNumber) = line.splitToNumbers()
            leftList.add(leftNumber)
            rightList.add(rightNumber)
        }

        leftList.sort()
        rightList.sort()

        val sum = leftList.mapIndexed { index, leftNumber ->
            abs(leftNumber - rightList[index])
        }.sum()

        return sum
    }

    fun part2(input: List<String>): Int {
        val leftList = mutableListOf<Int>()
        val rightList = mutableListOf<Int>()

        input.map { line ->
            val (leftNumber, rightNumber) = line.splitToNumbers()
            leftList.add(leftNumber)
            rightList.add(rightNumber)
        }

        val similarity = leftList.sumOf { leftNumber ->
            leftNumber * rightList.filter { rightNumber -> rightNumber == leftNumber }.size
        }

        return similarity
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(listOf("3   7")) == 4)
    check(part1(listOf("9   3")) == 6)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInputLines("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInputLines("Day01")
    part1(input).println()
    part2(input).println()
}
