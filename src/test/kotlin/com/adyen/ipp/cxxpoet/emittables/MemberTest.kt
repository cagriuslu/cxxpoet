package com.adyen.ipp.cxxpoet.emittables

import com.adyen.ipp.cxxpoet.emittables.expressions.IntegerValue
import com.adyen.ipp.cxxpoet.properties.Namespace
import kotlin.test.Test
import kotlin.test.assertEquals

class MemberTest {
    @Test
    fun staticMember() {
        val classBuilder = Class.Builder("MyClass", Namespace(listOf("adyen")))

        val member = Member(classBuilder, "int", "a", IntegerValue(5), isStatic = true)
        assertEquals("static int a;", member.emitDeclaration())
        assertEquals("int ::adyen::MyClass::a = 5;", member.emitDefinition())
    }

    @Test
    fun member() {
        val classBuilder = Class.Builder("MyClass")

        val member = Member(classBuilder, "int", "a", IntegerValue(5))
        assertEquals("int a = 5;", member.emitDeclaration())
    }
}