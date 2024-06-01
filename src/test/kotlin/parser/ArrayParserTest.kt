package parser

import gitp.StringReader
import gitp.parser.ArrayParser
import gitp.parser.ObjectParser
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.entry
import org.junit.jupiter.api.Test

class ArrayParserTest {
    @Test
    fun just_string_element_test() {
        val json = """
            [
                "hello" , 
                "world"     ,
                "element3"
            ]
        """.trimIndent()

        val reader = StringReader(json)
        reader.readSkipWhitespace()
        val jsonList: List<Any?> = ArrayParser.parse(reader)
        assertThat(jsonList).containsOnly(
            "hello",
            "world",
            "element3"
        )
    }

    @Test
    fun object_nested_in_array_test() {
        val json = """
            [
                "hello" , 
                "world"     ,
                {
                    "k1": "val1",
                    "k2": "val2"
                }
            ]
        """.trimIndent()

        val reader = StringReader(json)
        reader.readSkipWhitespace()
        val jsonList: List<Any?> = ArrayParser.parse(reader)
        assertThat(jsonList).containsOnly(
            "hello",
            "world",
            mapOf("k1" to "val1", "k2" to "val2")
        )
    }

    @Test
    fun array_nested_in_object_test() {
        val json = """
            {
                "arr1" : [
                    "e11",
                    "e12",
                    "e13"
                ],
                "arr2" : [
                    "e21",
                    "e22",
                    "e23"
                ]
            }
        """.trimIndent()

        val reader = StringReader(json)
        reader.readSkipWhitespace()
        val jsonList = ObjectParser.parse(reader)
        assertThat(jsonList).containsOnly(
            entry("arr1", listOf("e11", "e12", "e13")),
            entry("arr2", listOf("e21", "e22", "e23")),
        )
    }
}