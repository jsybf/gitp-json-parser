package gitp

class StringReader(
    private val s: String,
    private var idx: Int = -1,
    private val length: Int,
) {
    fun read(): Char {
        if (idx == length) throw IllegalStateException(
            "it's end of string can't read more (idx:$idx)"
        )
        return s[++idx]
    }

    fun current(): Char = s[idx]

}