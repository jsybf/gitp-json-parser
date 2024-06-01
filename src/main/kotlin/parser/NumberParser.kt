package gitp.parser

import gitp.StringReader
import java.math.BigDecimal

object NumberParser {
    /**
     * current of reader should point start of number value
     * current of reader will point end of number value
     */
    fun parse(reader: StringReader): Number {
        if (reader.current()!!.code !in 48..57 && reader.current()!! != '-')
            throw IllegalStateException(
                "expected current pointing digit or minus but pointing (${reader.current()})"
            )


        var integer: String = extractDigits(reader, allowMinus = true)
        var decimalFraction: String? = null
        var exponent: String? = null

        // case 2: float type
        if (reader.glanceNext() == '.') {
            reader.readNext()
            reader.readNext()
            decimalFraction = extractDigits(reader)
        }

        if (reader.glanceNext() != 'e' && reader.glanceNext() != 'E') {
            return if (decimalFraction == null) integer.toInt()
            else ("$integer.$decimalFraction").toDouble()
        }

        // case 3: exponential
        reader.readNext()
        reader.readNext()
        exponent = extractDigits(reader, allowMinus = true, allowPlus = true)
        println("$integer.${decimalFraction ?: "0"}$exponent")
        return BigDecimal("$integer.${decimalFraction ?: "0"}e$exponent").toDouble()
    }

    /**
     * reader should point start point of digits
     * it will move current of reader to end of digits
     */
    fun extractDigits(
        reader: StringReader,
        allowMinus: Boolean = false,
        allowPlus: Boolean = false
    ): String {
        if (reader.current()!!.code !in 48..57
            && !(allowMinus && reader.current()!! == '-')
            && !(allowPlus && reader.current()!! == '+')
        )
            throw IllegalStateException(
                "expected current pointing digit but pointing (${reader.current()})"
            )

        val num: MutableList<Char> = mutableListOf()
        num.add(reader.current()!!)

        while ((reader.glanceNext()?.code ?: return num.joinToString("")) in 48..57) {
            num.add(reader.readNext()!!)
        }

        return num.joinToString("")

    }
}