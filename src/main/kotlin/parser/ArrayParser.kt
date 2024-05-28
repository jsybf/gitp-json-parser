package gitp.parser

import gitp.StringReader
import gitp.ValueParser
import gitp.exception.InvalidJsonException

object ArrayParser {
    fun parse(reader: StringReader): List<Any?> {
        if (reader.current() != '[')
            throw IllegalStateException("reader should bracket([) at first")

        reader.readSkipWhitespace()
        if (reader.current() == ']') return listOf()

        val jsonList: MutableList<Any?> = mutableListOf()

        while (true) {
            jsonList.add(ValueParser.parse(reader))
            if (reader.readSkipWhitespace()!! != ',') break
            reader.readSkipWhitespace()
        }

        if (reader.current() != ']')
            throw InvalidJsonException("] expected")

        return jsonList
    }
}