package com.adyen.ipp.cxxpoet.emittables

import com.adyen.ipp.cxxpoet.CodeStream

class Include(val type: Type, val path: String) : Definable {
    enum class Type {
        SYSTEM,
        RELATIVE
    }

    override fun emitDefinition(out: CodeStream) {
        out.println(when (type) {
            Type.SYSTEM -> "#include <$path>"
            Type.RELATIVE -> "#include \"$path\""
        })
    }
}