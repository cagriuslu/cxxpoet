package com.adyen.ipp.cxxpoet.emittables

import com.adyen.ipp.cxxpoet.emittables.expressions.*
import com.adyen.ipp.cxxpoet.emittables.statements.*
import kotlin.test.Test
import kotlin.test.assertEquals

class StatementTest {
    @Test
    fun assignment() {
        assertEquals("*(this) = other;\n", Assignment(deref("this"), Reference("other")).emitDefinition())
    }

    @Test
    fun compoundStatement() {
        assertEquals(
            """
            {
                SomeFunc();
                int a = 5;
            }
            
        """.trimIndent(), CompoundStatement(
                listOf(
                    ExpressionStatement(FunctionCall("SomeFunc")),
                    VariableDeclaration("int", "a", IntegerValue(5))
                )
            ).emitDefinition()
        )
    }

    @Test
    fun expressionStatement() {
        assertEquals("someFuncCall();\n", ExpressionStatement(FunctionCall("someFuncCall")).emitDefinition())
    }

    @Test
    fun ifBlock() {
        val ifBlockBuilder = IfBlock.Builder(BooleanValue(true))
        ifBlockBuilder.addStatement(VariableDeclaration("std::string", "name", StringValue("John Smith")))
        ifBlockBuilder.addStatement(Assignment(ref("name"), StringValue("Mary Elizabeth")))
        ifBlockBuilder.addElseIfBlock(IntegerValue(1), listOf(
                VariableDeclaration("std::string", "name", StringValue("Tom Hanks")),
                Assignment(ref("name"), StringValue("Tom Holland"))
        ))
        ifBlockBuilder.addElseStatement(VariableDeclaration("std::string", "name", StringValue("Jack Sparrow")))
        ifBlockBuilder.addElseStatement(Assignment(ref("name"), StringValue("CAPTAIN Jack Sparrow")))

        assertEquals("""
            if (true) {
                std::string name = "John Smith";
                name = "Mary Elizabeth";
            } else if (1) {
                std::string name = "Tom Hanks";
                name = "Tom Holland";
            } else {
                std::string name = "Jack Sparrow";
                name = "CAPTAIN Jack Sparrow";
            }
            
        """.trimIndent(), ifBlockBuilder.build().emitDefinition())
    }

    @Test
    fun `return`() {
        assertEquals("return \"Hello\";\n", Return(StringValue("Hello")).emitDefinition())
    }

    @Test
    fun variableDeclaration() {
        assertEquals("int a;\n", VariableDeclaration("int", "a").emitDefinition())
        assertEquals("int a = 5;\n", VariableDeclaration("int", "a", IntegerValue(5)).emitDefinition())
    }
}
