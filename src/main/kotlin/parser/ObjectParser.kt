package gitp.parser

import gitp.StringReader
import gitp.ValueReader

object ObjectParser {

    /**
     *
     */
    fun parse(reader: StringReader): Map<String, Any?> {
        // starting exception
        if (reader.current() != '{') throw IllegalStateException("expected {")

        val jsonMap: MutableMap<String, Any?> = mutableMapOf()

        reader.skipWhitespace()
        // if json object has no name-value pair
        if (reader.current() == '}') return jsonMap

        do {
            val name: String = StringParser.parse(reader)!!

            reader.readSkipWhitespace()
            if (reader.current() != ':') throw IllegalStateException("expected : ")

            reader.readSkipWhitespace()

            val value: Any? = ValueReader.read(reader)
            jsonMap[name] = value

            if (reader.readSkipWhitespace() != ',') break
            reader.readSkipWhitespace()
        } while (true)

        if (reader.current() != '}') throw IllegalStateException("expected }")
        return jsonMap
    }
}