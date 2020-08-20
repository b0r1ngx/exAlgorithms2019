@file:Suppress("UNUSED_PARAMETER")

package lesson1

import java.io.File

/**
 * Сортировка времён
 *
 * Простая
 * (Модифицированная задача с сайта acmp.ru)
 *
 * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
 * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
 *
 * Пример:
 *
 * 01:15:19 PM
 * 07:26:57 AM
 * 10:00:03 AM
 * 07:56:14 PM
 * 01:15:19 PM
 * 12:40:31 AM
 *
 * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
 * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
 *
 * 12:40:31 AM
 * 07:26:57 AM
 * 10:00:03 AM
 * 01:15:19 PM
 * 01:15:19 PM
 * 07:56:14 PM
 *
 * В случае обнаружения неверного формата файла бросить любое исключение.
 */
fun sortTimes(inputName: String, outputName: String) {
    //Трудоемкость - T = O(n * log(n))
    //Ресурсоемкость - R = O(n)
    try {
        File(outputName).bufferedWriter().use { it ->
            val daySecondAndInput = mutableListOf<Pair<Int, String>>()
            val nightSecondAndInput = mutableListOf<Pair<Int, String>>()

            for (each in File(inputName).readLines()) {
                val timeAndProblem = each.split(" ") // 01:15:19, PM
                val partsOfTime = timeAndProblem[0].split(":") // 01, 15, 19

                val hours = partsOfTime[0].toInt()
                val minutes = partsOfTime[1].toInt()
                val seconds = partsOfTime[2].toInt()

                if (hours !in 1..12 || minutes !in 0..59 || seconds !in 0..59)
                    throw Exception("Формат числовых значений не удовлетворяет 12-часовому формату времени")

                val result = hours % 12 * 3600 + minutes * 60 + seconds

                if (timeAndProblem[1] == "AM") daySecondAndInput.add(result to each)
                else nightSecondAndInput.add(result to each)

            }
            for (day in daySecondAndInput.sortedBy { it.first }) {
                it.write(day.second)
                it.newLine()
            }
            for (night in nightSecondAndInput.sortedBy { it.first }) {
                it.write(night.second)
                it.newLine()
            }
        }
    } catch (e: Exception) {
        throw Exception("Исходный формат момента времени имеет неверный формат")
    }
}

/**
 * Сортировка адресов
 *
 * Средняя
 *
 * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
 * где они прописаны. Пример:
 *
 * Петров Иван - Железнодорожная 3
 * Сидоров Петр - Садовая 5
 * Иванов Алексей - Железнодорожная 7
 * Сидорова Мария - Садовая 5
 * Иванов Михаил - Железнодорожная 7
 *
 * Людей в городе может быть до миллиона.
 *
 * Вывести записи в выходной файл outputName,
 * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
 * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
 *
 * Железнодорожная 3 - Петров Иван
 * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
 * Садовая 5 - Сидоров Петр, Сидорова Мария
 *
 * В случае обнаружения неверного формата файла бросить любое исключение.
 */
//Если найден одинаковый человек в одиноком доме, тоже кинуть исключение
fun sortAddresses(inputName: String, outputName: String) {
    //Не успел доделать!
    //Трудоемкость - T = O()
    //Ресурсоемкость - R = O()
    File(outputName).bufferedWriter().use { it ->
        val streetAndNumberSecondAndFirstNames = mutableListOf<Pair<Pair<String, Int>, Pair<String, String>>>()
        val strAndNumSecAndFirstNames = mutableMapOf<Pair<String, Int>, String>()
        val strAndNumSecAndFirstName = mutableMapOf<String, String>()
        //val streetAndNumberSecondAndFirstNames =
        //    mutableListOf<Pair<Pair<String, Int>, MutableList<Pair<String, String>>>>()

        for (each in File(inputName).readLines()) {
            val parts = each.split(" - ")
            if (parts.size != 2) {
                throw Exception()
            }
            strAndNumSecAndFirstNames[Pair(parts[1].split(" ")[0], parts[1].split(" ")[1].toInt())] = parts[0]
        }

        println(strAndNumSecAndFirstNames)

        for (each in File(inputName).readLines()) {
            val parts = each.split(" ")
            streetAndNumberSecondAndFirstNames.add(Pair(Pair(parts[3], parts[4].toInt()), Pair(parts[0], parts[1])))
        }

        for (i in strAndNumSecAndFirstNames.keys.sortedWith(
            compareBy(
                { it.first },
                { it.second })
        )
//            .mapValues { (key, value) -> value.joinToString(", ") { it.second } }
//            .map { it.key + " - " + it.value })
        ) {

        }
        println(strAndNumSecAndFirstNames)

//        for (i in streetAndNumberSecondAndFirstNames.sortedWith(
//            compareBy(
//                { it.first.first },
//                { it.first.second })
//        )) {
//            it.write(i.toString())
//            it.newLine()
//        }

//        var first = 0
//        var last = 0
//        val isEven: (Int) -> Boolean = { it % 2 == 0 }
//        for (i in 0 until streetAndNumberAndSecondAndFirstNames.size) {
//            if (streetAndNumberAndSecondAndFirstNames[i].first != streetAndNumberAndSecondAndFirstNames[i + 1].first) {
//                last = i
//                streetAndNumberAndSecondAndFirstNames.listIterator()
//            }
//            first = last
////                    .sortedWith(
////                    compareBy(
////                        { it.second.first },
////                        { it.second.second })
////                )
//        }
    }
//            it.write(i.first.first)
//            it.write(" ")
//            it.write(i.first.second.toString())
//            it.write(" - ")
//            it.write(i.second.first)
//            it.write(" ")
//            it.write(i.second.second)
//            it.newLine()


//        for (i in streetAndNumberAndSecondAndFirstNames.sortedBy { it.first.first }) {
//
//        }
//        for (i in streetAndNumberAndSecondAndFirstNames) {
//            it.write(i.toString())
//            it.newLine()
//        }
}


fun main() {
    sortAddresses("input/fileForInput.txt", "input/fileForOutput.txt")
//    if ("a" > "b") println("true")
//    else println("false")
}

/**
 * Сортировка температур
 *
 * Средняя
 * (Модифицированная задача с сайта acmp.ru)
 *
 * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
 * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
 * Например:
 *
 * 24.7
 * -12.6
 * 121.3
 * -98.4
 * 99.5
 * -12.6
 * 11.0
 *
 * Количество строк в файле может достигать ста миллионов.
 * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
 * Повторяющиеся строки сохранить. Например:
 *
 * -98.4
 * -12.6
 * -12.6
 * 11.0
 * 24.7
 * 99.5
 * 121.3
 */
fun sortTemperatures(inputName: String, outputName: String) {
    //Трудоемкость - T = O(n)
    //Ресурсоемкость - R = O(n) - количество температур?
    File(outputName).bufferedWriter().use {
        val output = IntArray(7731)

        for (each in File(inputName).readLines()) {
            val t = each.toDouble()
            output[(t * 10 + 2730).toInt()]++
        }

        for (i in 0 until output.size) {
            while (output[i] > 0) {
                it.write("${(i.toDouble() - 2730) / 10}")
                output[i]--
                it.newLine()
            }
        }
    }
}

/**
 * Сортировка последовательности
 *
 * Средняя
 * (Задача взята с сайта acmp.ru)
 *
 * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
 *
 * 1
 * 2
 * 3
 * 2
 * 3
 * 1
 * 2
 *
 * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
 * а если таких чисел несколько, то найти минимальное из них,
 * и после этого переместить все такие числа в конец заданной последовательности.
 * Порядок расположения остальных чисел должен остаться без изменения.
 *
 * 1
 * 3
 * 3
 * 1
 * 2
 * 2
 * 2
 */
fun sortSequence(inputName: String, outputName: String) {
    TODO()
}

/**
 * Соединить два отсортированных массива в один
 *
 * Простая
 *
 * Задан отсортированный массив first и второй массив second,
 * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
 * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
 *
 * first = [4 9 15 20 28]
 * second = [null null null null null 1 3 9 13 18 23]
 *
 * Результат: second = [1 3 4 9 9 13 15 20 23 28]
 */
fun <T : Comparable<T>> mergeArrays(first: Array<T>, second: Array<T?>) {
    //Трудоемкость - O(n)
    //Ресурсоемкость - O(1)
    var index1 = 0
    var index2 = first.size

    for (i in 0 until second.size) {
        if (index2 >= second.size ||
            index1 < first.size && first[index1] <= second[index2]!!) {
            second[i] = first[index1]
            index1++
        } else {
            second[i] = second[index2]
            index2++
        }
    }
}

