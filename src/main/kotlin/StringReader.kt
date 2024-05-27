package gitp

class StringReader(
    private val s: String,
) {
    private var idx: Int = -1
    private val length: Int = s.length

    fun readNext(): Char? = if (ifEnd()) null else s[++idx]
    fun ifEnd(): Boolean = (length - 1) == idx
    fun current(): Char = s[idx]

    /**
     * just return if neither current nor next is whitespace
     * skip until current is not whitespace
     */
    fun skipWhitespace() {
        if (ifEnd()) return
        fun ifWhiteSpace(c: Char): Boolean = c == '\n' || c == '\t' || c == '\r' || c == ' '

        if (!(ifWhiteSpace(current()) || ifWhiteSpace(s[idx + 1]))) return

        do {
            val c: Char = readNext() ?: return
        } while (ifWhiteSpace(c) && !this.ifEnd())
    }


}