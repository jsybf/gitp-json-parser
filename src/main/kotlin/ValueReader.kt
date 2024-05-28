package gitp

import gitp.parser.ObjectParser
import gitp.parser.StringParser

object ValueReader {
    /**
     * determined parser base on form of text and return parsed value
     * kind of dispatcher
     * @param reader: current of reader should point out starting point of json value
     */
    fun read(reader: StringReader): Any? {
        return when (reader.current()) {
            '{' -> ObjectParser.parse(reader)
            '"' -> StringParser.parse(reader)
            else -> throw IllegalStateException("unexpected char:${reader.current()}")
        }
    }
}