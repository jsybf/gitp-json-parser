package gitp

class StringReader(
    private val s: String,
) {
    private var idx: Int = -1
    private val length: Int = s.length

    fun ifEnd(): Boolean = (length - 1) == idx
    fun current(): Char? = if (idx == -1 || ifEnd()) null else s[idx]

    fun readNext(): Char? = if (ifEnd()) null else s[++idx]
    fun glanceNext(): Char? = if (ifEnd()) null else s[idx + 1]

    /**
     * if next character is not white space then just read next character
     * else skip white space util current is not whitespace
     * for convenience
     */
    fun readSkipWhitespace(): Char? {
        if (idx == -1)
            if (!ifWhiteSpace(glanceNext()!!)) return readNext()
            else readNext()

        if (!ifWhiteSpace(glanceNext() ?: return null)) return readNext()!!
        skipWhitespace()
        return current()
    }


    /**
     * just return if neither current nor next is whitespace
     * skip until current is not whitespace
     * should not invoke before readNext called
     */
    fun skipWhitespace() {
        if (idx == -1) throw IllegalStateException(
            "skipWhitespace should be invoked after once readNext called"
        )
        if (ifEnd()) return

        if (!(ifWhiteSpace(current()!!) || ifWhiteSpace(s[idx + 1]))) return

        do {
            val c: Char = readNext() ?: return
        } while (ifWhiteSpace(c) && !this.ifEnd())
    }

    private fun ifWhiteSpace(c: Char): Boolean = c == '\n' || c == '\t' || c == '\r' || c == ' '

}