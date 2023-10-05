package com.adyen.ipp.cxxpoet.properties

import kotlin.test.Test
import kotlin.test.assertEquals

class NamespaceTest {
    @Test
    fun namespace() {
        assertEquals("", Namespace(listOf()).toString())
        assertEquals("std", Namespace(listOf("std")).toString())
        assertEquals("std::ranges", Namespace(listOf("std", "ranges")).toString())
        assertEquals("std::ranges::filters", Namespace(listOf("std", "ranges", "filters")).toString())
    }

    @Test
    fun toGlobalPrefix() {
        assertEquals("::std::ranges::filters::", Namespace(listOf("std", "ranges", "filters")).toGlobalPrefix())
    }
}