fun main() {

    fun part1(input: List<String>): Int {
        val word = "XMAS"
        val wordLength = word.length
        val rowCount = input.size
        val colCount = input[0].length
        var count = 0

        val directions = listOf(
            0 to 1,    // Right
            0 to -1,   // Left
            1 to 0,    // Down
            -1 to 0,   // Up
            1 to 1,    // Diagonal Down-Right
            1 to -1,   // Diagonal Down-Left
            -1 to 1,   // Diagonal Up-Right
            -1 to -1   // Diagonal Up-Left
        )

        fun isValid(row: Int, col: Int) = row in 0 until rowCount && col in 0 until colCount

        fun findWord(row: Int, col: Int, dir: Pair<Int, Int>): Boolean {
            for (i in 0 until wordLength) {
                val newRow = row + i * dir.first
                val newCol = col + i * dir.second
                if (!isValid(newRow, newCol) || input[newRow][newCol] != word[i]) {
                    return false
                }
            }
            return true
        }

        for (row in input.indices) {
            for (col in input[row].indices) {
                directions.forEach { dir ->
                    if (findWord(row, col, dir)) {
                        count++
                    }
                }
            }
        }

        return count
    }

    fun part2(input: List<String>): Int {
        val word = "XMAS"
            val wordLength = word.length
            val rowCount = input.size
            val colCount = input[0].length
            val visited = mutableSetOf<Pair<Int, Int>>()
            var count = 0

            val directions = listOf(
                0 to 1,    // Right
                0 to -1,   // Left
                1 to 0,    // Down
                -1 to 0,   // Up
                1 to 1,    // Diagonal Down-Right
                1 to -1,   // Diagonal Down-Left
                -1 to 1,   // Diagonal Up-Right
                -1 to -1   // Diagonal Up-Left
            )

            fun isValid(row: Int, col: Int) = row in 0 until rowCount && col in 0 until colCount

            fun findWord(row: Int, col: Int, dir: Pair<Int, Int>): Boolean {
                val cells = mutableListOf<Pair<Int, Int>>()
                for (i in 0 until wordLength) {
                    val newRow = row + i * dir.first
                    val newCol = col + i * dir.second
                    if (!isValid(newRow, newCol) || input[newRow][newCol] != word[i] || (newRow to newCol) in visited) {
                        return false
                    }
                    cells.add(newRow to newCol)
                }
                visited.addAll(cells) // Mark the cells as visited
                return true
            }

            for (row in input.indices) {
                for (col in input[row].indices) {
                    for (dir in directions) {
                        if (findWord(row, col, dir)) {
                            count++
                        }
                    }
                }
            }

        return count
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(listOf("MMMSXXMASM")) == 1)

    // Or read a large test input from the `src/Day04_test.txt` file:
    val testInput = readInputLines("Day04_test")
    check(part1(testInput) == 18)
    check(part2(testInput) == 9)

    // Read the input from the `src/Day04.txt` file.
    val input = readInputLines("Day04")
    part1(input).println()
    part2(input).println()
}
