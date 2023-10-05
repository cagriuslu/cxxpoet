package com.adyen.ipp.cxxpoet.emittables

import com.adyen.ipp.cxxpoet.emittables.expressions.IntegerValue
import kotlin.test.Test
import kotlin.test.assertEquals

class MethodTest {
    @Test
    fun virtualMethod() {
        val classBuilder = Class.Builder("MyClass")

        val methodBuilder = Method.Builder(classBuilder, "void", "myMethod", true, false, true, true)
        methodBuilder.addParam(FunctionParam("int", "a", IntegerValue(5)))
        assertEquals("virtual void myMethod(int a = 5) const override;", methodBuilder.build().emitDeclaration())
    }

    @Test
    fun staticMethod() {
        val classBuilder = Class.Builder("MyClass")

        val methodBuilder = Method.Builder(classBuilder, "void", "myMethod", isStatic = true)
        methodBuilder.addParam(FunctionParam("int", "a", IntegerValue(5)))
        assertEquals("static void myMethod(int a = 5);", methodBuilder.build().emitDeclaration())
    }
}