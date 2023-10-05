package com.adyen.ipp.cxxpoet.emittables

import com.adyen.ipp.cxxpoet.CodeStream
import com.adyen.ipp.cxxpoet.properties.Namespace
import com.adyen.ipp.cxxpoet.extensions.nonEmptyListOrNull

class Class private constructor(
        private val builder: Builder
) : ForwardDeclarable, Declarable, Definable {
    enum class AccessType(val keyword: String) {
        PUBLIC("public"),
        PRIVATE("private"),
        PROTECTED("protected")
    }

    val globalName = builder.globalName

    override fun emitForwardDeclaration(out: CodeStream) {
        // Namespace
        if (builder.namespace != null) {
            out.println("${Namespace.keyword} ${builder.namespace} {")
            out.increaseIndentation()
        }

        // Class name
        out.println("class ${builder.name};")

        // End namespace
        if (builder.namespace != null) {
            out.decreaseIndentation()
            out.println("}")
        }
    }

    override fun emitDeclaration(out: CodeStream) {
        // Namespace
        if (builder.namespace != null) {
            out.println("${Namespace.keyword} ${builder.namespace} {")
            out.increaseIndentation()
        }

        // Class name
        val baseClasses = builder.baseClasses.nonEmptyListOrNull()
                ?.joinToString(prefix = ": ", postfix = " ") { "${it.first.keyword} ${it.second.globalName}" }
                ?: ""
        out.println("class ${builder.name} ${baseClasses}{")

        // Members
        emitDeclarables(out, AccessType.PRIVATE, builder.members)
        emitDeclarables(out, AccessType.PROTECTED, builder.members)
        emitDeclarables(out, AccessType.PUBLIC, builder.members)

        // Constructors
        emitDeclarables(out, AccessType.PRIVATE, builder.constructors)
        emitDeclarables(out, AccessType.PROTECTED, builder.constructors)
        emitDeclarables(out, AccessType.PUBLIC, builder.constructors)

        // Methods
        emitDeclarables(out, AccessType.PRIVATE, builder.methods)
        emitDeclarables(out, AccessType.PROTECTED, builder.methods)
        emitDeclarables(out, AccessType.PUBLIC, builder.methods)

        // End class
        out.println("};")

        // End namespace
        if (builder.namespace != null) {
            out.decreaseIndentation()
            out.println("}")
        }
    }

    private fun emitDeclarables(out: CodeStream, accessType: AccessType, declarables: List<Pair<AccessType, Declarable>>) {
        val filtered = declarables.filter { it.first == accessType }
        if (filtered.isNotEmpty()) {
            out.println("${accessType.keyword}:")
            out.increaseIndentation()
            filtered.forEach { out.println(it.second.emitDeclaration()) }
            out.decreaseIndentation()
        }
    }

    override fun emitDefinition(out: CodeStream) {
        TODO("Not yet implemented")
    }

    class Builder(val name: String, val namespace: Namespace? = null) {
        internal val baseClasses: MutableList<Pair<AccessType, Class>> = mutableListOf()
        internal val members: MutableList<Pair<AccessType, Member>> = mutableListOf()
        internal val constructors: MutableList<Pair<AccessType, Constructor>> = mutableListOf()
        internal val methods: MutableList<Pair<AccessType, Method>> = mutableListOf()

        val globalName = "${namespace?.toGlobalPrefix() ?: ""}${name}"

        fun build(): Class {
            return Class(this)
        }

        fun addBaseClass(accessType: AccessType, `class`: Class): Builder {
            baseClasses.add(Pair(accessType, `class`))
            return this
        }

        fun addMember(accessType: AccessType, member: Member): Builder {
            members.add(Pair(accessType, member))
            return this
        }

        fun addConstructor(accessType: AccessType, constructor: Constructor): Builder {
            constructors.add(Pair(accessType, constructor))
            return this
        }

        fun addMethod(accessType: AccessType, method: Method): Builder {
            methods.add(Pair(accessType, method))
            return this
        }
    }
}