package com.adyen.ipp.cxxpoet.emittables

import com.adyen.ipp.cxxpoet.CodeStream

class CompilationUnit private constructor(
    private val builder: Builder
) : Definable {

    enum class Type {
        HEADER,
        SOURCE
    }

    override fun emitDefinition(out: CodeStream) {
        // Header guard
        if (builder.type == Type.HEADER) {
            out.println("#pragma once")
            out.println()
        }

        // Include statements
        builder.includeStatements.forEach {
            it.emitDefinition(out)
        }
        if (0 < builder.includeStatements.size) {
            out.println()
        }

        // Forward Declarations
        builder.forwardDeclarations.forEach {
            it.emitForwardDeclaration(out)
            out.println()
        }

        // Declarations
        builder.declarations.forEach {
            it.emitDeclaration(out)
            out.println()
        }

        // Definitions
        builder.definitions.forEach {
            it.emitDefinition(out)
            out.println()
        }
    }

    class Builder(val type: Type) {
        internal val includeStatements: MutableList<Include> = mutableListOf()
        internal val forwardDeclarations: MutableList<ForwardDeclarable> = mutableListOf()
        internal val declarations: MutableList<Declarable> = mutableListOf()
        internal val definitions: MutableList<Definable> = mutableListOf()

        fun build(): CompilationUnit {
            return CompilationUnit(this)
        }

        fun addIncludeStatement(inc: Include) {
            includeStatements.add(inc)
        }

        fun addForwardDeclaration(fdecl: ForwardDeclarable) {
            forwardDeclarations.add(fdecl)
        }

        fun addDeclaration(decl: Declarable) {
            declarations.add(decl)
        }

        fun addDefinition(def: Definable) {
            definitions.add(def)
        }
    }
}