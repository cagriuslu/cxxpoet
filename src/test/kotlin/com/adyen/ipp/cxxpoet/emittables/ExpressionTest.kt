package com.adyen.ipp.cxxpoet.emittables

import com.adyen.ipp.cxxpoet.emittables.expressions.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ExpressionTest {
    @Test
    fun addition() {
        assertEquals("(myVar) + (5)", Addition(Reference("myVar"), IntegerValue(5)).emitDefinition())
    }

    @Test
    fun addressOf() {
        assertEquals("&(myVar)", AddressOf(Reference("myVar")).emitDefinition())
    }

    @Test
    fun and() {
        assertEquals("(myVar) && (5)", And(Reference("myVar"), IntegerValue(5)).emitDefinition())
    }

    @Test
    fun bitwiseAnd() {
        assertEquals("(myVar) & (5)", BitwiseAnd(Reference("myVar"), IntegerValue(5)).emitDefinition())
    }

    @Test
    fun bitwiseNot() {
        assertEquals("~(myVar)", BitwiseNot(Reference("myVar")).emitDefinition())
    }

    @Test
    fun bitwiseOr() {
        assertEquals("(myVar) | (5)", BitwiseOr(Reference("myVar"), IntegerValue(5)).emitDefinition())
    }

    @Test
    fun bitwiseXor() {
        assertEquals("(myVar) ^ (5)", BitwiseXor(Reference("myVar"), IntegerValue(5)).emitDefinition())
    }

    @Test
    fun dereference() {
        assertEquals("*(&(myVar))", Indirection(AddressOf(Reference("myVar"))).emitDefinition())
    }

    @Test
    fun conditional() {
        assertEquals("(myCondition) ? (5) : (10)", Conditional(Reference("myCondition"), IntegerValue(5), IntegerValue(10)).emitDefinition())
    }

    @Test
    fun division() {
        assertEquals("(myVar) / (5)", Division(Reference("myVar"), IntegerValue(5)).emitDefinition())
    }

    @Test
    fun equalTo() {
        assertEquals("(myVar) == (5)", EqualTo(Reference("myVar"), IntegerValue(5)).emitDefinition())
    }

    @Test
    fun functionCall() {
        assertEquals("myFunction(3, 5, \"Hello\")", FunctionCall("myFunction", listOf(IntegerValue(3), IntegerValue(5), StringValue("Hello"))).emitDefinition())
        assertEquals("myFunction()", FunctionCall("myFunction").emitDefinition())
    }

    @Test
    fun greaterThan() {
        assertEquals("(myVar) > (5)", GreaterThan(Reference("myVar"), IntegerValue(5)).emitDefinition())
    }

    @Test
    fun greaterOrEqualTo() {
        assertEquals("(myVar) >= (5)", GreaterOrEqualTo(Reference("myVar"), IntegerValue(5)).emitDefinition())
    }

    @Test
    fun leftShift() {
        assertEquals("(myVar) << (5)", LeftShift(Reference("myVar"), IntegerValue(5)).emitDefinition())
    }

    @Test
    fun lessThan() {
        assertEquals("(myVar) < (5)", LessThan(Reference("myVar"), IntegerValue(5)).emitDefinition())
    }

    @Test
    fun lessOrEqualTo() {
        assertEquals("(myVar) <= (5)", LessOrEqualTo(Reference("myVar"), IntegerValue(5)).emitDefinition())
    }

    @Test
    fun memberOfObject() {
        assertEquals("(myVar).myField", MemberOfObject(Reference("myVar"), "myField").emitDefinition())
    }

    @Test
    fun memberOfPointer() {
        assertEquals("(myVar)->myField", MemberOfPointer(Reference("myVar"), "myField").emitDefinition())
    }

    @Test
    fun multiplication() {
        assertEquals("(myVar) * (5)", Multiplication(Reference("myVar"), IntegerValue(5)).emitDefinition())
    }

    @Test
    fun negate() {
        assertEquals("-(myVar)", Negate(Reference("myVar")).emitDefinition())
    }

    @Test
    fun not() {
        assertEquals("!(myVar)", Not(Reference("myVar")).emitDefinition())
    }

    @Test
    fun notEqualTo() {
        assertEquals("(myVar) != (5)", NotEqualTo(Reference("myVar"), IntegerValue(5)).emitDefinition())
    }

    @Test
    fun or() {
        assertEquals("(myVar) || (5)", Or(Reference("myVar"), IntegerValue(5)).emitDefinition())
    }

    @Test
    fun reference() {
        assertEquals("myVar", Reference("myVar").emitDefinition())
    }

    @Test
    fun remainder() {
        assertEquals("(myVar) % (5)", Remainder(Reference("myVar"), IntegerValue(5)).emitDefinition())
    }

    @Test
    fun rightShift() {
        assertEquals("(myVar) >> (5)", RightShift(Reference("myVar"), IntegerValue(5)).emitDefinition())
    }

    @Test
    fun subscript() {
        assertEquals("(myArray)[15]", Subscript(Reference("myArray"), IntegerValue(15)).emitDefinition())
    }

    @Test
    fun subtraction() {
        assertEquals("(myVar) - (5)", Subtraction(Reference("myVar"), IntegerValue(5)).emitDefinition())
    }
}