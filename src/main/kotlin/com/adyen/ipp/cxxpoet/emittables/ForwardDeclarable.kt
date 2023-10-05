package com.adyen.ipp.cxxpoet.emittables

import com.adyen.ipp.cxxpoet.CodeStream

interface ForwardDeclarable {
    fun emitForwardDeclaration(out: CodeStream)

    fun emitForwardDeclaration(): String {
        val out = CodeStream()
        this.emitForwardDeclaration(out)
        return out.toString()
    }
}