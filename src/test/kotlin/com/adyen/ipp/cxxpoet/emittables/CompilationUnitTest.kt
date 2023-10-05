package com.adyen.ipp.cxxpoet.emittables

import com.adyen.ipp.cxxpoet.properties.Namespace
import kotlin.test.Test
import kotlin.test.assertEquals

class CompilationUnitTest {

    @Test
    fun `empty header file`() {
        val unit = CompilationUnit.Builder(CompilationUnit.Type.HEADER).build()
        assertEquals("""#pragma once

""", unit.emitDefinition())
    }

    @Test
    fun `empty source file`() {
        val unit = CompilationUnit.Builder(CompilationUnit.Type.SOURCE).build()
        assertEquals("", unit.emitDefinition())
    }

    @Test
    fun `header file with includes`() {
        val builder = CompilationUnit.Builder(CompilationUnit.Type.HEADER)
        builder.addIncludeStatement(Include(Include.Type.SYSTEM, "cstdlib"))
        builder.addIncludeStatement(Include(Include.Type.SYSTEM, "cstdio"))

        assertEquals("""#pragma once

#include <cstdlib>
#include <cstdio>

""", builder.build().emitDefinition())
    }

    @Test
    fun `source file with includes`() {
        val builder = CompilationUnit.Builder(CompilationUnit.Type.SOURCE)
        builder.addIncludeStatement(Include(Include.Type.SYSTEM, "cstdlib"))
        builder.addIncludeStatement(Include(Include.Type.SYSTEM, "cstdio"))

        assertEquals("""#include <cstdlib>
#include <cstdio>

""", builder.build().emitDefinition())
    }

    @Test
    fun `header file with forward declaration`() {
        val builder = CompilationUnit.Builder(CompilationUnit.Type.HEADER)
        val `class` = Class.Builder("MyClass", Namespace(listOf("adyen", "test"))).build()
        builder.addForwardDeclaration(`class`)

        assertEquals("""#pragma once

namespace adyen::test {
    class MyClass;
}

""", builder.build().emitDefinition())
    }
}