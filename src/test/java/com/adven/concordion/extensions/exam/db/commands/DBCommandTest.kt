package com.adven.concordion.extensions.exam.db.commands

import org.junit.Assert.assertEquals
import org.junit.Test

class DBCommandTest {

    @Test
    fun canParseColsDescription() {
        assertEquals(mapOf(
            "NOTHING" to (0 to ""), "MARKED" to (2 to ""), "HAS_VAL" to (0 to "''"), "MARKED_AND_VAL" to (1 to "42")),
            parse("NOTHING, **MARKED, HAS_VAL='', *MARKED_AND_VAL=42")
        )
    }

    @Test
    fun canParseSingleFieldColsDescription() {
        assertEquals(mapOf("some_column" to (0 to "")), parse("some_column"))
    }
}