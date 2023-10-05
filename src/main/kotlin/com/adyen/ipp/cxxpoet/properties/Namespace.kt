package com.adyen.ipp.cxxpoet.properties

class Namespace(val spaces: List<String>) {
    override fun toString(): String {
        return spaces.joinToString("::")
    }

    fun toGlobalPrefix(): String {
        return spaces.joinToString("::", prefix = "::", postfix = "::")
    }

    companion object {
        const val keyword = "namespace"
    }
}