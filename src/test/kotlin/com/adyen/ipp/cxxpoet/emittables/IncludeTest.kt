package com.adyen.ipp.cxxpoet.emittables

import com.adyen.ipp.cxxpoet.emittables.Include
import kotlin.test.Test
import kotlin.test.assertEquals

class IncludeTest {
    @Test
    fun `system header`() {
        assertEquals("#include <cstdlib>\n", Include(Include.Type.SYSTEM, "cstdlib").emitDefinition())
    }

    @Test
    fun `relative header`() {
        assertEquals("#include \"utils.h\"\n", Include(Include.Type.RELATIVE, "utils.h").emitDefinition())
    }
}