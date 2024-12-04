fun main() {
    val mulRegex1 = """mul\(\d{1,3},\d{1,3}\)""".toRegex()
    val mulRegex2 = """mul\(\d{1,3},\d{1,3}\)|do\(\)|don't\(\)""".toRegex()
    val numRegex = """\d+""".toRegex()
    val doRegex = """do\(\)""".toRegex()
    val dontRegex = """don't\(\)""".toRegex()

    fun part1(input: String): Long {
        val sum = mulRegex1.findAll(input)
            .map { it.value }
            .map { mulResults -> numRegex.findAll(mulResults).map { it.value.toLong() }.toList() }
            .map { it.reduce { acc, l -> acc * l } }
            .sum()
        return sum
    }

    fun part2(input: String): Long {
        val foo = mulRegex2.findAll(input).map { it.value }.toList()

        var dont = false
        var sum = 0L

        foo.forEach {
            if (dontRegex.matches(it)) {
                dont = true
            } else if (doRegex.matches(it)) {
                dont = false
            } else if (!dont) {
                sum += numRegex.findAll(it).map { it.value.toLong() }.toList().reduce { acc, l -> acc * l }
            }
        }

        return sum
    }

    // Test if implementation meets criteria from the description, like:
    check(part1("mul(44,46)") == 2024L)

    // Or read a large test input from the `src/Day03_test.txt` file:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 161L)

    // Read the input from the `src/Day03.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
