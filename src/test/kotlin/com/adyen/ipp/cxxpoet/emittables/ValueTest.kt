package com.adyen.ipp.cxxpoet.emittables

import com.adyen.ipp.cxxpoet.emittables.expressions.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ValueTest {
    @Test
    fun variableValue() {
        assertEquals("myVar", Reference("myVar").emitDefinition())
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
    fun string() {
        assertEquals("\"Hello\"", StringValue("Hello").emitDefinition())
    }

    @Test
    fun unsigned() {
        assertEquals("12345U", UnsignedValue(12345).emitDefinition())
    }
}