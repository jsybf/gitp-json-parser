package gitp

import gitp.parser.ArrayParser
import gitp.parser.ObjectParser
import gitp.parser.StringParser

object ValueParser {
    /**
     * determined parser base on form of text and return parsed value
     * kind of dispatcher
     * @param reader: current of reader should point out starting point of json value
     */
    fun parse(reader: StringReader): Any? {
        return when (reader.current()) {
            '{' -> ObjectParser.parse(reader)
            '"' -> StringParser.parse(reader)
            '[' -> ArrayParser.parse(reader)
            else -> throw IllegalStateException("unexpected char:${reader.current()}")
        }
    }
}