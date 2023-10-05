package com.adyen.ipp.cxxpoet.emittables.expressions

import com.adyen.ipp.cxxpoet.CodeStream

class Reference(val name: String) : Expression() {
    override fun emitDefinition(out: CodeStream) {
        out.print(name)
    }
}