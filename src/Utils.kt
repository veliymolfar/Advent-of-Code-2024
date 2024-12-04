import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.math.abs

/**
 * Reads lines from the given input txt file.
 */
fun readInputLines(name: String) = Path("src/$name.txt").readText().trim().lines()

/**
 * Reads text from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readText().trim()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

/**
 * Splits [String] line to list of numbers.
 */
fun String.splitToNumbers(): List<Int> {
    return this.split("\\s+".toRegex()).map { it.toInt() }
}

fun List<Int>.isReportSafe(withTolerance: Boolean = false): Boolean {
    fun isSafe(list: List<Int>): Boolean {
        if (list.size < 2) return true

        val isDecreasing = list[0] > list[1]

        for (i in 1 until list.size) {
            val current = list[i]
            val previous = list[i - 1]
            if ((isDecreasing != (current < previous)) || abs(current - previous) !in 1..3) return false
        }
        return true
    }

    if (isSafe(this)) return true

    if (withTolerance) {
        for (i in this.indices) {
            val modifiedList = this.toMutableList().apply { removeAt(i) }
            if (isSafe(modifiedList)) return true
        }
    }

    return false
}