package com.adyen.ipp.cxxpoet.emittables

import com.adyen.ipp.cxxpoet.emittables.expressions.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ValueTest {
    @Test
    fun boolean() {
        assertEquals("false", BooleanValue(false).emitDefinition())
    }

    @Test
    fun double() {
        assertEquals("12.345", DoubleValue(12.345).emitDefinition())
    }

    @Test
    fun float() {
        assertEquals("12.345F", FloatValue(12.345F).emitDefinition())
    }

    @Test
    fun integer() {
        assertEquals("12345", IntegerValue(12345).emitDefinition())
    }

    @Test
    fun long() {
        assertEquals("12345L", LongValue(12345).emitDefinition())
    }

    @Test
    fun string() {
        assertEquals("\"Hello\"", StringValue("Hello").emitDefinition())
    }

    @Test
    fun unsigned() {
        assertEquals("12345U", UnsignedValue(12345U).emitDefinition())
    }

    @Test
    fun unsignedLong() {
        assertEquals("12345LU", UnsignedLongValue(12345UL).emitDefinition())
    }
}