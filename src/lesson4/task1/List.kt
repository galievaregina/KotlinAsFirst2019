@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import lesson3.task1.isPrime
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.Char as Char1

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var sum = 0.0
    for (element in v) {
        sum += sqr(element)
    }
    return sqrt(sum)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    val a = list.isEmpty()
    return when {
        a -> 0.0
        else -> list.sum() / list.size
    }
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val b = mean(list)
    for ((index, element) in list.withIndex()) {
        list[index] = element - b
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var c = 0
    for (i in a.indices) {
        c += a[i] * b[i]
    }
    return c
}


/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var px = 0
    for ((index, element) in p.withIndex()) {
        px += element * (x.toDouble()).pow(index).toInt()
    }
    return px
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    return when {
        list.isEmpty() -> list
        else -> {
            var sum = list.first()
            for (i in 1 until list.size) {
                sum += list[i]
                list[i] = sum
            }
            return list
        }
    }
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val result = mutableListOf<Int>()
    var primeD = 2
    var m = n
    while (m > 1) {
        if (m % primeD == 0) {
            while (m % primeD == 0) {
                result.add(primeD)
                m /= primeD
            }
        }
        primeD++
    }
    return result
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val result1 = mutableListOf<Int>()
    val result2 = mutableListOf<Int>()
    var m = n
    if (n < base) return listOf(n)
    while (m >= base) {
        result1.add(m % base)
        m /= base
    }
    result1.add(m)
    for (i in result1.size - 1 downTo 0) {
        result2.add(result1[i])
    }
    return result2
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    val string = convert(n, base)
    var result = ""
    for (i in string.indices) {
        if (string[i] <= 9) {
            val element = string[i]
            val char = "$element"
            result += char
        } else result += (string[i] + 87).toChar()
    }

    return result
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var dex = 0
    for ((a, i) in (digits.size - 1 downTo 0).withIndex()) {
        dex += digits[i] * (base.toDouble()).pow(a).toInt()
    }
    return dex
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    val list = mutableListOf<Int>()
    for (i in str.indices) {
        if (str[i] <= '9') {
            list.add(str[i].toString().toInt())
        } else list.add(str[i].toInt() - 87)
    }
    return decimal(list, base)
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var m = n
    var res = ""
    val rom = listOf("I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M")
    val count = listOf(1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000)
    var i = 12
    while (m != 0) {
        while (m >= count[i]) {
            m -= count[i]
            res += rom[i]
        }
        i -= 1
    }
    return res
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    if (n / 1000 == 0) return hundred(n).trim()
    val a = n / 1000
    val b = n % 1000
    val c = hundred2(a)
    return when (a % 10) {
        1 -> (c + " тысяча" + hundred(b)).trim()
        2, 3, 4 -> (c + " тысячи" + hundred(b)).trim()
        else -> (c + " тысяч" + hundred(b)).trim()
    }
}

fun one(n: Int): String {
    return when (n) {
        0 -> ""
        1 -> " один"
        2 -> " два"
        3 -> " три"
        4 -> " четыре"
        5 -> " пять"
        6 -> " шесть"
        7 -> " семь"
        8 -> " восемь"
        else -> " девять"
    }
}

fun ten(n: Int): String {
    return when (n % 10) {
        2 -> " двенадцать"
        0 -> " десять"
        1, 3 -> one(n % 10) + "надцать"
        4 -> " четырнадцать"
        else -> one(n % 10).replace("ь", "") + "надцать"
    }
}

fun dex(n: Int): String {
    return when (n / 10) {
        0 -> one(n)
        1 -> ten(n)
        2 -> " двадцать${one(n % 10)}"
        3 -> one(n / 10) + "дцать" + one(n % 10)
        in 5..8 -> one(n / 10) + "десят" + one(n % 10)
        4 -> " сорок${one(n % 10)}"
        else -> " девяносто${one(n % 10)}"
    }
}
fun hundred(n: Int): String {
    val m = n / 100
    val d = dex(n % 100)
    return when (m) {
        0 -> dex(n)
        1 -> "сто$d"
        2 -> "двести$d"
        4 -> one(m) + "сто" + d
        3 -> "триста$d"
        else -> one(m) + "сот" + d
    }
}
fun one2(n: Int): String {
    return when (n) {
        0 -> ""
        1 -> " одна"
        2 -> " две"
        3 -> " три"
        4 -> " четыре"
        5 -> " пять"
        6 -> " шесть"
        7 -> " семь"
        8 -> " восемь"
        else -> " девять"
    }
}
fun ten2(n: Int): String {
    return when (n % 10) {
        2 -> " двенадцать"
        0 -> " десять"
        1, 3 -> one2(n % 10) + "надцать"
        4 -> " четырнадцать"
        else -> one2(n % 10).replace("ь", "") + "надцать"
    }
}
fun dex2(n: Int): String {
    return when (n / 10) {
        0 -> one2(n)
        1 -> ten2(n)
        2 -> " двадцать${one2(n % 10)}"
        3 -> one2(n / 10) + "дцать" + one2(n % 10)
        in 5..8 -> one2(n / 10) + "десят" + one2(n % 10)
        4 -> " сорок${one2(n % 10)}"
        else -> " девяносто${one2(n % 10)}"
    }
}
fun hundred2(n: Int): String {
    val m = n / 100
    val end1 = "сот"
    val d = dex2(n % 100)
    return when (m) {
        0 -> dex2(n)
        1 -> "сто$d"
        2 -> "двести$d"
        4 -> one2(m) + "ста" + d
        3 -> "триста$d"
        else -> one2(m) + "сот" + d
    }
}
