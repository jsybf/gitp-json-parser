package gitp.parser

import gitp.StringReader
import gitp.exception.InvalidJsonException

object StringParser {
    // TODO: nullable return
    /**
     * @param reader: current(field) of it should point starting quotation
     */
    fun parse(reader: StringReader): String? {
        // if current != " then exception
        if (reader.current() != '"')
            throw IllegalStateException("reader should point quotation(\") at first")


        val s: MutableList<Char> = mutableListOf()

        reader.readNext()
        while (reader.current() != '"') {
            // TODO: test case like (" \") that has no quotation in end
            if (reader.current() == '\\') {
                val secondChar = reader.readNext()
                    ?: throw InvalidJsonException("""character must come after \""")
                s.add(escapeCharacter(secondChar))
            } else {
                s.add(reader.current()!!)
            }
            reader.readNext()
        }

        return s.joinToString("")
    }


    fun escapeCharacter(c: Char): Char {
        return when (c) {
            '"' -> '"'
            '\\' -> '\\'
            '/' -> '/'
            'b' -> '\b'
            'n' -> '\n'
            'r' -> '\r'
            't' -> '\t'
            // TODO: form feed character \f
            // TODO: hexadecimal digits \u
            else -> throw InvalidJsonException("not expected escapeChar=($c)")
        }
    }


}