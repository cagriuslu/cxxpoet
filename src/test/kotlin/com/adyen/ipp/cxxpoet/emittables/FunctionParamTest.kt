package com.adyen.ipp.cxxpoet.emittables

import com.adyen.ipp.cxxpoet.emittables.expressions.IntegerValue
import kotlin.test.Test
import kotlin.test.assertEquals

class FunctionParamTest {
    @Test
    fun functionParam() {
        assertEquals("int a = 5", FunctionParam("int", "a", IntegerValue(5)).emitDeclaration())
        assertEquals("int a", FunctionParam("int", "a", IntegerValue(5)).emitDefinition())
    }
}