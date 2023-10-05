package com.adyen.ipp.cxxpoet.emittables

import com.adyen.ipp.cxxpoet.emittables.expressions.BooleanValue
import com.adyen.ipp.cxxpoet.emittables.expressions.IntegerValue
import com.adyen.ipp.cxxpoet.emittables.expressions.Reference
import com.adyen.ipp.cxxpoet.emittables.expressions.StringValue
import com.adyen.ipp.cxxpoet.emittables.statements.Assignment
import com.adyen.ipp.cxxpoet.emittables.statements.IfBlock
import com.adyen.ipp.cxxpoet.emittables.statements.Return
import com.adyen.ipp.cxxpoet.emittables.statements.VariableDeclaration
import kotlin.test.Test
import kotlin.test.assertEquals

class StatementTest {
    @Test
    fun assignmentTest() {
        assertEquals("*this = other;", Assignment("*this", Reference("other")).emitDefinition())
    }

    @Test
    fun ifBlockTest() {
        val ifBlockBuilder = IfBlock.Builder(BooleanValue(true))
        ifBlockBuilder.addStatement(VariableDeclaration("std::string", "name", StringValue("John Smith")))
        ifBlockBuilder.addStatement(Assignment("name", StringValue("Mary Elizabeth")))
        ifBlockBuilder.addElseIfBlock(IntegerValue(1), listOf(
                VariableDeclaration("std::string", "name", StringValue("Tom Hanks")),
                Assignment("name", StringValue("Tom Holland"))
        ))
        ifBlockBuilder.addElseStatement(VariableDeclaration("std::string", "name", StringValue("Jack Sparrow")))
        ifBlockBuilder.addElseStatement(Assignment("name", StringValue("CAPTAIN Jack Sparrow")))

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
    fun returnTest() {
        assertEquals("return \"Hello\";", Return(StringValue("Hello")).emitDefinition())
    }
}
