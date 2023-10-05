package com.adyen.ipp.cxxpoet.emittables

import com.adyen.ipp.cxxpoet.properties.Namespace
import kotlin.test.Test
import kotlin.test.assertEquals

class EnumTest {
    @Test
    fun enumClass() {
        val enumClassBuilder = EnumClass.Builder("MyEnum", Namespace(listOf("adyen", "test")))
            .addOption("A", 0)
            .addOption("B")
            .addOption("C", -1)

        assertEquals("""
            namespace adyen::test {
                enum class MyEnum;
            }
            
        """.trimIndent(), enumClassBuilder.build().emitForwardDeclaration())

        assertEquals("""
            namespace adyen::test {
                enum class MyEnum {
                    A = 0,
                    B,
                    C = -1,
                };
            }
            
        """.trimIndent(), enumClassBuilder.build().emitDeclaration())
    }
}