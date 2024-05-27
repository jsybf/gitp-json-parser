package gitp

class StringReader(
    private val s: String,
) {
    private var idx: Int = -1
    private val length: Int = s.length
    fun read(): Char {
        if (idx == length) throw IllegalStateException(
            "it's end of string can't read more (idx:$idx)"
        )
        return s[++idx]
    }

    /**
     * TODO: change this function to property with public getter and private setter
     */
    fun current(): Char = s[idx]

    // TODO: add skipWhiteSpace ( space, tab, newline, Carriage return??)


}