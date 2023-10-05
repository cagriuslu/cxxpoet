package com.adyen.ipp.cxxpoet.emittables

import com.adyen.ipp.cxxpoet.CodeStream

interface Declarable {
    fun emitDeclaration(out: CodeStream)

    fun emitDeclaration(): String {
        val out = CodeStream()
        this.emitDeclaration(out)
        return out.toString()
    }
}