package gitp

object StringParser {
    /**
     * @param reader: current(field) of it should point starting quotation
     */
    fun parse(reader: StringReader): String {
        // if current != " then exception
        if (reader.current() != '"') throw IllegalStateException(
            "reader should point quotation(\") at first"
        )


        val s: MutableList<Char> = mutableListOf()

        reader.read()
        while (reader.current() != '"') {
            if (reader.current() == ':' || reader.current() == ',') throw IllegalStateException(
                "quotation(\") might missing"
            )

            // TODO: test case like (" \") that has no quotation in end
            if (reader.current() != '\\') {
                s.add(reader.current())
            } else {
                s.add(escapeCharacter(reader.read()))
            }
            reader.read()
        }

        if (reader.current() == ',') throw IllegalStateException("quotation(\") might missing")

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
            else -> throw IllegalStateException("not expected escapeChar=($c)")
        }
    }


}