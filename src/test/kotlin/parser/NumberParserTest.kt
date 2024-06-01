package parser

import gitp.StringReader
import gitp.parser.NumberParser
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class NumberParserTest {
    @Test
    fun scientific_notation_test() {
        val num1 = "123.456e+2"
        val num2 = "-123.456e-2"
        val num3 = "-3.33e-2"

        assertThat(
            NumberParser.parse(StringReader(num1).also { it.readNext() })
        ).isEqualTo(12345.6)

        assertThat(
            NumberParser.parse(StringReader(num2).also { it.readNext() })
        ).isEqualTo(-1.23456)

        assertThat(
            NumberParser.parse(StringReader(num3).also { it.readNext() })
        ).isEqualTo(-0.0333)
    }

    @Test
    fun test_decimal_fraction() {
        val num1 = "123.33"
        val num2 = "123.30"
        val num3 = "-120.30"
        assertThat(
            NumberParser.parse(StringReader(num1).also { it.readNext() })
        ).isEqualTo(123.33)

        assertThat(
            NumberParser.parse(StringReader(num2).also { it.readNext() })
        ).isEqualTo(123.3)
        assertThat(
            NumberParser.parse(StringReader(num3).also { it.readNext() })
        ).isEqualTo(-120.3)
    }
}