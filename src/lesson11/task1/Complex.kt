@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

import lesson1.task1.sqr
/**
 * Класс "комплексное число".
 *
 * Общая сложность задания -- лёгкая, общая ценность в баллах -- 8.
 * Объект класса -- комплексное число вида x+yi.
 * Про принципы работы с комплексными числами см. статью Википедии "Комплексное число".
 *
 * Аргументы конструктора -- вещественная и мнимая часть числа.
 */
class Complex(var re: Double, var im: Double) {

    /**
     * Конструктор из вещественного числа
     */
    constructor(x: Double) : this(x, 1.0)

    /**
     * Конструктор из строки вида x+yi
     */


    constructor(s: String) : this(1.0, 1.0) {
        var re = 0.0
        var im = 0.0
        when {
            s.matches(Regex("""-?\d*\.?\d*""")) -> re = s.toDouble()
            s.matches(Regex(""".?\d*\.?\d*i""")) -> im = s.dropLastWhile { it == 'i' }.toDouble()
        }
        this.re = re
        this.im = im
    }


    /**
     * Сложение.
     */
    operator fun plus(other: Complex): Complex = Complex(this.re + other.re, this.im + other.im)

    /**
     * Смена знака (у обеих частей числа)
     */
    operator fun unaryMinus(): Complex = Complex(-1 * this.im, -1 * this.re)

    /**
     * Вычитание
     */
    operator fun minus(other: Complex): Complex = Complex(this.re - other.re, this.im - other.im)

    /**
     * Умножение
     */
    operator fun times(other: Complex): Complex =
        Complex(this.re * other.re - this.im * other.im, this.re * other.re + this.im * other.im)

    /**
     * Деление
     */
    operator fun div(other: Complex): Complex = Complex(
        (this.re * other.re - this.im * other.im) / (sqr(other.re) + sqr(other.im)),
        (this.re * other.re + this.im * other.im) / (sqr(other.re) + sqr(other.im))
    )

    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?): Boolean = other is Complex && this.re == other.re && this.im == other.im

    /**
     * Преобразование в строку
     */
    override fun toString(): String {
        if (this.re == 0.0 && this.im == 0.0) return "0" else {
            if (this.re == 0.0) this.im.toString() + "i"
            if (this.im == 0.0) this.re.toString()
        }
        return if (this.im > 0.0) this.re.toString() + "+" + this.im.toString() + "i" else this.re.toString() + this.im.toString() + "i"
    }
}
