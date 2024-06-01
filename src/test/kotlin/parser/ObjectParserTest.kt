package parser

import gitp.StringReader
import gitp.parser.ObjectParser
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class ObjectParserTest {
    @Test
    fun parse_just_string() {
        val json = """
            {
                "name1" : "value1" ,
                "name2" :   "value2"
            }
        """
        val reader = StringReader(json)
        reader.readSkipWhitespace()
        assertThat(ObjectParser.parse(reader))
            .containsOnly(
                entry("name1", "value1"),
                entry("name2", "value2"),
            )
    }

    @Test
    fun parse_just_number() {
        val json = """
            {
                "name1" : 123e-3,
                "name2" : 30.32,
                "name3" : 456
            }
        """
        val reader = StringReader(json)
        reader.readSkipWhitespace()
        assertThat(ObjectParser.parse(reader))
            .containsOnly(
                entry("name1", 0.123),
                entry("name2", 30.32),
                entry("name3", 456),
            )
    }

    @Test
    fun parse_error_test() {
        val json = """
            {
                "name1" : "value1" 
                "name2" :   "value2 "
            }
        """
        val reader = StringReader(json)
        reader.readSkipWhitespace()

        assertThatThrownBy { ObjectParser.parse(reader) }
    }

    @Test
    fun parse_nested_object() {
        val json = """
            {
                "object1" : {
                    "e1": "1"
                },
                "name2" :  "value2"
            }
        """
        val reader = StringReader(json)
        reader.readSkipWhitespace()
        assertThat(ObjectParser.parse(reader))
            .contains(
                entry("object1", mapOf("e1" to "1")),
                entry("name2", "value2"),
            )
    }

}