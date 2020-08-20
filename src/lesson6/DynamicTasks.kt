@file:Suppress("UNUSED_PARAMETER")

package lesson6

import java.io.File
import kotlin.math.max


/**
 * Наибольшая общая подпоследовательность.
 * Средняя
 *
 * Дано две строки, например "nematode knowledge" и "empty bottle".
 * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
 * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
 * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
 * Если общей подпоследовательности нет, вернуть пустую строку.
 * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
 * При сравнении подстрок, регистр символов *имеет* значение.
 */
fun longestCommonSubSequence(first: String, second: String): String {
    //Трудоемкость - O(n*m) , n - длина строки 1, m - длина строки 2
    //Ресурсоемкость - O(n*m)
    if (first == second) return first
    if (first.isEmpty() || second.isEmpty()) return ""
    var firsts = first.length
    var seconds = second.length
    val counter = Array(firsts + 1) { IntArray(seconds + 1) }
    for (i in 1..firsts) {
        for (j in 1..seconds) {
            counter[i][j] = (
                    if (first[i - 1] == second[j - 1])
                        counter[i - 1][j - 1] + 1
                    else
                        max(counter[i - 1][j], counter[i][j - 1]))
        }
    }

    return buildString {
        while (firsts != 0 && seconds != 0) {
            if (first[firsts - 1] == second[seconds - 1]) {
                append(first[firsts - 1])
                firsts--
                seconds--
            } else {
                when {
                    counter[firsts][seconds - 1] > counter[firsts - 1][seconds] -> seconds--
                    else -> firsts--
                }
            }
        }
    }.reversed()
}

/**
 * Наибольшая возрастающая подпоследовательность
 * Сложная
 *
 * Дан список целых чисел, например, [2 8 5 9 12 6].
 * Найти в нём самую длинную возрастающую подпоследовательность.
 * Элементы подпоследовательности не обязаны идти подряд,
 * но должны быть расположены в исходном списке в том же порядке.
 * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
 * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
 * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
 */
fun longestIncreasingSubSequence(list: List<Int>): List<Int> {
    TODO()
}

/**
 * Самый короткий маршрут на прямоугольном поле.
 * Средняя
 *
 * В файле с именем inputName задано прямоугольное поле:
 *
 * 0 2 3 2 4 1
 * 1 5 3 4 6 2
 * 2 6 2 5 1 3
 * 1 4 3 2 6 2
 * 4 2 3 1 5 0
 *
 * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
 * В каждой клетке записано некоторое натуральное число или нуль.
 * Необходимо попасть из верхней левой клетки в правую нижнюю.
 * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
 * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
 *
 * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
 */

fun shortestPathOnField(inputName: String): Int {
    val reading = File(inputName).readLines()
    if (reading.isEmpty()) return -1
    if (reading.toString() == "0" || reading.toString().split(" ").size == 4) return 0
    val row = reading.size
    val col = reading[0].split(" ").size
    val values = Array(row) { IntArray(col) }
    val paths = Array(row) { IntArray(col) }

    for (i in 0 until row)
        for (j in 0 until col)
            values[i][j] = reading[i].split(" ")[j].toInt()

    fun minV(i: Int, j: Int): Int = minOf(
        if (i != 0) values[i - 1][j] + values[i][j] + minV(i - 1, j) else values[i][j] + minV(i - 1, j),
        if (j != 0) values[i][j - 1] + values[i][j] + minV(i, j - 1) else values[i][j] + minV(i, j - 1),
        if (i != 0 && j != 0) values[i - 1][j - 1] + values[i][j] + minV(i - 1, j - 1) else values[i][j] + minV(i - 1, j - 1)
    )

    fun minVreverse(i: Int, j: Int): Int = minOf(
        if (i != row - 1) values[i + 1][j] + values[i][j] + minVreverse(i + 1, j) else values[i + 1][j],
        if (j != col - 1) values[i][j + 1] + values[i][j] + minVreverse(i, j + 1) else values[i][j + 1],
        if (i != row - 1 && j != col - 1) values[i + 1][j + 1] + values[i][j] + minVreverse(i + 1, j + 1) else values[i][j]
    )
//Запутался
    for (i in 0 until row)
        for (j in 0 until col) {
            paths[i][j] = minVreverse(i, j)
        }

    return values[row][col]
}

fun main() {
    println(shortestPathOnField("input/field_in2.txt"))
}

// Задачу "Максимальное независимое множество вершин в графе без циклов"
// смотрите в уроке 5