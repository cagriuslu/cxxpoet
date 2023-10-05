package com.adyen.ipp.cxxpoet.emittables

import com.adyen.ipp.cxxpoet.CodeStream

interface Definable {
    fun emitDefinition(out: CodeStream)

    fun emitDefinition(): String {
        val out = CodeStream()
        this.emitDefinition(out)
        return out.toString()
    }
}
