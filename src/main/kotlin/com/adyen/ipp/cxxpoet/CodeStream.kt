package com.adyen.ipp.cxxpoet

import java.io.ByteArrayOutputStream

// CodeStream is designed to write code into a ByteArrayOutputStream.
// It has awareness of the indentation.
class CodeStream : ByteArrayOutputStream() {
    private var indentation: Int = 0

    fun increaseIndentation() {
        ++indentation
    }

    fun decreaseIndentation() {
        --indentation
    }

    fun print(str: String) {
        write(str.toByteArray())
    }

    fun println() {
        printIndentation()
        write('\n'.code)
    }

    fun println(line: String) {
        printIndentation()
        write(line.toByteArray())
        write('\n'.code)
    }

    private fun printIndentation() {
        repeat(indentation) {
            repeat(4) {
                write(' '.code)
            }
        }
    }
}