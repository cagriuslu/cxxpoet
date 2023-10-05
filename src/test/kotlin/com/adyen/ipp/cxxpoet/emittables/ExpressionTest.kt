package com.adyen.ipp.cxxpoet.emittables

import com.adyen.ipp.cxxpoet.emittables.expressions.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ExpressionTest {
    @Test
    fun referenceTest() {
        assertEquals("myVar", Reference("myVar").emitDefinition())
    }

    @Test
    fun functionCallTest() {
        assertEquals("myFunction(3, 5, \"Hello\")", FunctionCall("myFunction", listOf(IntegerValue(3), IntegerValue(5), StringValue("Hello"))).emitDefinition())
    }

    @Test
    fun doubleTest() {
        assertEquals("3.125", DoubleValue(3.125).emitDefinition())
    }
}