package com.adyen.ipp.cxxpoet.emittables

import com.adyen.ipp.cxxpoet.emittables.expressions.IntegerValue
import kotlin.test.Test
import kotlin.test.assertEquals

class ConstructorTest {
    @Test
    fun defaultConstructor() {
        val classBuilder = Class.Builder("MyClass")

        assertEquals("MyClass();",
            Constructor.Builder(
                classBuilder,
                Constructor.Type.DEFAULT_CONSTRUCTOR,
                Constructor.Builder.Implementation.CUSTOM)
                .build().emitDeclaration())
        assertEquals("MyClass() = default;",
            Constructor.Builder(
                classBuilder,
                Constructor.Type.DEFAULT_CONSTRUCTOR,
                Constructor.Builder.Implementation.DEFAULT)
                .build().emitDeclaration())
        assertEquals("MyClass() = delete;",
            Constructor.Builder(
                classBuilder,
                Constructor.Type.DEFAULT_CONSTRUCTOR,
                Constructor.Builder.Implementation.DELETED)
                .build().emitDeclaration())
    }

    @Test
    fun copyConstructor() {
        val classBuilder = Class.Builder("MyClass")

        assertEquals("MyClass(const MyClass&);",
            Constructor.Builder(
                classBuilder,
                Constructor.Type.COPY_CONSTRUCTOR,
                Constructor.Builder.Implementation.CUSTOM)
                .build().emitDeclaration())
        assertEquals("MyClass(const MyClass&) = default;",
            Constructor.Builder(
                classBuilder,
                Constructor.Type.COPY_CONSTRUCTOR,
                Constructor.Builder.Implementation.DEFAULT)
                .build().emitDeclaration())
        assertEquals("MyClass(const MyClass&) = delete;",
            Constructor.Builder(
                classBuilder,
                Constructor.Type.COPY_CONSTRUCTOR,
                Constructor.Builder.Implementation.DELETED)
                .build().emitDeclaration())
    }

    @Test
    fun copyAssignmentOperator() {
        val classBuilder = Class.Builder("MyClass")

        assertEquals("MyClass& operator=(const MyClass&);",
            Constructor.Builder(
                classBuilder,
                Constructor.Type.COPY_ASSIGNMENT_OPERATOR,
                Constructor.Builder.Implementation.CUSTOM)
                .build().emitDeclaration())
        assertEquals("MyClass& operator=(const MyClass&) = default;",
            Constructor.Builder(
                classBuilder,
                Constructor.Type.COPY_ASSIGNMENT_OPERATOR,
                Constructor.Builder.Implementation.DEFAULT)
                .build().emitDeclaration())
        assertEquals("MyClass& operator=(const MyClass&) = delete;",
            Constructor.Builder(
                classBuilder,
                Constructor.Type.COPY_ASSIGNMENT_OPERATOR,
                Constructor.Builder.Implementation.DELETED)
                .build().emitDeclaration())
    }

    @Test
    fun moveConstructor() {
        val classBuilder = Class.Builder("MyClass")

        assertEquals("MyClass(MyClass&&);",
            Constructor.Builder(
                classBuilder,
                Constructor.Type.MOVE_CONSTRUCTOR,
                Constructor.Builder.Implementation.CUSTOM)
                .build().emitDeclaration())
        assertEquals("MyClass(MyClass&&) = default;",
            Constructor.Builder(
                classBuilder,
                Constructor.Type.MOVE_CONSTRUCTOR,
                Constructor.Builder.Implementation.DEFAULT)
                .build().emitDeclaration())
        assertEquals("MyClass(MyClass&&) = delete;",
            Constructor.Builder(
                classBuilder,
                Constructor.Type.MOVE_CONSTRUCTOR,
                Constructor.Builder.Implementation.DELETED)
                .build().emitDeclaration())
    }

    @Test
    fun moveAssignmentOperator() {
        val classBuilder = Class.Builder("MyClass")

        assertEquals("MyClass& operator=(MyClass&&);",
            Constructor.Builder(
                classBuilder,
                Constructor.Type.MOVE_ASSIGNMENT_OPERATOR,
                Constructor.Builder.Implementation.CUSTOM)
                .build().emitDeclaration())
        assertEquals("MyClass& operator=(MyClass&&) = default;",
            Constructor.Builder(
                classBuilder,
                Constructor.Type.MOVE_ASSIGNMENT_OPERATOR,
                Constructor.Builder.Implementation.DEFAULT)
                .build().emitDeclaration())
        assertEquals("MyClass& operator=(MyClass&&) = delete;",
            Constructor.Builder(
                classBuilder,
                Constructor.Type.MOVE_ASSIGNMENT_OPERATOR,
                Constructor.Builder.Implementation.DELETED)
                .build().emitDeclaration())
    }

    @Test
    fun destructor() {
        val classBuilder = Class.Builder("MyClass")

        assertEquals("~MyClass();",
            Constructor.Builder(
                classBuilder,
                Constructor.Type.DESTRUCTOR,
                Constructor.Builder.Implementation.CUSTOM)
                .build().emitDeclaration())
        assertEquals("~MyClass() = default;",
            Constructor.Builder(
                classBuilder,
                Constructor.Type.DESTRUCTOR,
                Constructor.Builder.Implementation.DEFAULT)
                .build().emitDeclaration())
        assertEquals("~MyClass() = delete;",
            Constructor.Builder(
                classBuilder,
                Constructor.Type.DESTRUCTOR,
                Constructor.Builder.Implementation.DELETED)
                .build().emitDeclaration())
        assertEquals("virtual ~MyClass();",
            Constructor.Builder(
                classBuilder,
                Constructor.Type.DESTRUCTOR,
                Constructor.Builder.Implementation.CUSTOM,
                true)
                .build().emitDeclaration())
        assertEquals("virtual ~MyClass() = default;",
            Constructor.Builder(
                classBuilder,
                Constructor.Type.DESTRUCTOR,
                Constructor.Builder.Implementation.DEFAULT,
                true)
                .build().emitDeclaration())
        assertEquals("virtual ~MyClass() = delete;",
            Constructor.Builder(
                classBuilder,
                Constructor.Type.DESTRUCTOR,
                Constructor.Builder.Implementation.DELETED,
                true)
                .build().emitDeclaration())
    }

    @Test
    fun customConstructor() {
        val classBuilder = Class.Builder("MyClass")

        assertEquals("MyClass();",
            Constructor.Builder(
                classBuilder,
                Constructor.Type.CUSTOM_CONSTRUCTOR,
                Constructor.Builder.Implementation.CUSTOM)
                .build().emitDeclaration())
        assertEquals("MyClass(int a = 5);",
            Constructor.Builder(
                classBuilder,
                Constructor.Type.CUSTOM_CONSTRUCTOR,
                Constructor.Builder.Implementation.CUSTOM)
                .addParam(FunctionParam("int", "a", IntegerValue(5)))
                .build().emitDeclaration())
        assertEquals("MyClass(float b, int a = 5);",
            Constructor.Builder(
                classBuilder,
                Constructor.Type.CUSTOM_CONSTRUCTOR,
                Constructor.Builder.Implementation.CUSTOM)
                .addParam(FunctionParam("float", "b"))
                .addParam(FunctionParam("int", "a", IntegerValue(5)))
                .build().emitDeclaration())
    }
}