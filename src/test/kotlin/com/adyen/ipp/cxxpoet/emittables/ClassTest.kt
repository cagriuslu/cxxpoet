package com.adyen.ipp.cxxpoet.emittables

import com.adyen.ipp.cxxpoet.emittables.expressions.BooleanValue
import com.adyen.ipp.cxxpoet.emittables.expressions.FloatValue
import com.adyen.ipp.cxxpoet.emittables.expressions.IntegerValue
import com.adyen.ipp.cxxpoet.emittables.expressions.StringValue
import com.adyen.ipp.cxxpoet.properties.Namespace
import kotlin.test.Test
import kotlin.test.assertEquals

class ClassTest {
    @Test
    fun `class forward declaration`() {
        val classBuilder = Class.Builder("MyClass")
        assertEquals("class MyClass;\n", classBuilder.build().emitForwardDeclaration())
    }

    @Test
    fun `class forward declaration with namespace`() {
        val classBuilder = Class.Builder("MyClass", Namespace(listOf("adyen", "test")))
        assertEquals("""
            namespace adyen::test {
                class MyClass;
            }
            
            """.trimIndent(), classBuilder.build().emitForwardDeclaration())
    }

    @Test
    fun `class declaration with namespace`() {
        val classBuilder = Class.Builder("MyClass", Namespace(listOf("adyen", "cxx")))
        assertEquals("""
            namespace adyen::cxx {
                class MyClass {
                };
            }

            """.trimIndent(), classBuilder.build().emitDeclaration())
    }

    @Test
    fun `class declaration with namespace and base class`() {
        val baseClass1 = Class.Builder("MyBaseClass1", Namespace(listOf("adyen", "utils"))).build()
        val baseClass2 = Class.Builder("MyBaseClass2", Namespace(listOf("adyen", "cxx"))).build()

        val classBuilder = Class.Builder("MyClass", Namespace(listOf("adyen", "cxx")))
        classBuilder.addBaseClass(Class.AccessType.PUBLIC, baseClass1)
        classBuilder.addBaseClass(Class.AccessType.PROTECTED, baseClass2)
        assertEquals("""
            namespace adyen::cxx {
                class MyClass : public ::adyen::utils::MyBaseClass1, protected ::adyen::cxx::MyBaseClass2 {
                };
            }
            
            """.trimIndent(), classBuilder.build().emitDeclaration())
    }

    @Test
    fun `class declaration with members`() {
        val classBuilder = Class.Builder("MyClass")

        classBuilder.addMember(Class.AccessType.PRIVATE, Member(classBuilder, "int", "_age", IntegerValue(30)))
        classBuilder.addMember(Class.AccessType.PROTECTED, Member(classBuilder, "std::optional<float>", "_height", FloatValue(30.0f)))
        classBuilder.addMember(Class.AccessType.PUBLIC, Member(classBuilder, "std::string", "name", StringValue("John Mary")))

        assertEquals("""
            class MyClass {
            private:
                int _age = 30;
            protected:
                std::optional<float> _height = 30.0F;
            public:
                std::string name = "John Mary";
            };
            
            """.trimIndent(), classBuilder.build().emitDeclaration())
    }

    @Test
    fun `class declaration with default constructors`() {
        val classBuilder = Class.Builder("MyClass")

        val constructor = Constructor.Builder(classBuilder, Constructor.Type.DEFAULT_CONSTRUCTOR, Constructor.Builder.Implementation.DEFAULT).build()
        val copyConstructor = Constructor.Builder(classBuilder, Constructor.Type.COPY_CONSTRUCTOR, Constructor.Builder.Implementation.DELETED).build()
        val destructor = Constructor.Builder(classBuilder, Constructor.Type.DESTRUCTOR, Constructor.Builder.Implementation.DEFAULT, true).build()

        classBuilder.addConstructor(Class.AccessType.PRIVATE, constructor)
        classBuilder.addConstructor(Class.AccessType.PROTECTED, copyConstructor)
        classBuilder.addConstructor(Class.AccessType.PUBLIC, destructor)

        assertEquals("""
            class MyClass {
            private:
                MyClass() = default;
            protected:
                MyClass(const MyClass&) = delete;
            public:
                virtual ~MyClass() = default;
            };
            
            """.trimIndent(), classBuilder.build().emitDeclaration())
    }

    @Test
    fun `class declaration with methods`() {
        val classBuilder = Class.Builder("MyClass")

        val method1 = Method.Builder(classBuilder, "void", "sideEffectMethod", isVirtual = true, isConst = true).build()
        val method2 = Method.Builder(classBuilder, "int", "age", isStatic = true).build()
        val method3 = Method.Builder(classBuilder, "std::string", "name", isOverride = true).build()

        classBuilder.addMethod(Class.AccessType.PRIVATE, method1)
        classBuilder.addMethod(Class.AccessType.PROTECTED, method2)
        classBuilder.addMethod(Class.AccessType.PUBLIC, method3)

        assertEquals("""
            class MyClass {
            private:
                virtual void sideEffectMethod() const;
            protected:
                static int age();
            public:
                std::string name() override;
            };
            
        """.trimIndent(), classBuilder.build().emitDeclaration())
    }

    @Test
    fun `sample abort request`() {
        val namespace = Namespace(listOf("adyen", "nexo"))
        val baseClass = Class.Builder("Payload", namespace).build()

        val classBuilder = Class.Builder("AbortRequest", namespace)
        classBuilder.addBaseClass(Class.AccessType.PUBLIC, baseClass)
        classBuilder.addMember(Class.AccessType.PUBLIC, Member(classBuilder, "std::unique_ptr<MessageReference>", "message_reference"))
        classBuilder.addMember(Class.AccessType.PUBLIC, Member(classBuilder, "std::optional<std::string>", "abort_reason"))
        classBuilder.addMember(Class.AccessType.PUBLIC, Member(classBuilder, "std::unique_ptr<DisplayOutput>", "display_output"))

        // Constructors
        val defaultConstructorBuilder = Constructor.Builder(classBuilder, Constructor.Type.DEFAULT_CONSTRUCTOR, Constructor.Builder.Implementation.DEFAULT)
        classBuilder.addConstructor(Class.AccessType.PUBLIC, defaultConstructorBuilder.build())
        val moveConstructorBuilder = Constructor.Builder(classBuilder, Constructor.Type.MOVE_CONSTRUCTOR, Constructor.Builder.Implementation.DEFAULT)
        classBuilder.addConstructor(Class.AccessType.PUBLIC, moveConstructorBuilder.build())
        val moveAssignmentBuilder = Constructor.Builder(classBuilder, Constructor.Type.MOVE_ASSIGNMENT_OPERATOR, Constructor.Builder.Implementation.DEFAULT)
        classBuilder.addConstructor(Class.AccessType.PUBLIC, moveAssignmentBuilder.build())
        val copyConstructorBuilder = Constructor.Builder(classBuilder, Constructor.Type.COPY_CONSTRUCTOR, Constructor.Builder.Implementation.CUSTOM)
        classBuilder.addConstructor(Class.AccessType.PUBLIC, copyConstructorBuilder.build())
        val copyAssignmentBuilder = Constructor.Builder(classBuilder, Constructor.Type.COPY_ASSIGNMENT_OPERATOR, Constructor.Builder.Implementation.CUSTOM)
        classBuilder.addConstructor(Class.AccessType.PUBLIC, copyAssignmentBuilder.build())
        val deserializingConstructorBuilder = Constructor.Builder(classBuilder, Constructor.Type.CUSTOM_CONSTRUCTOR, Constructor.Builder.Implementation.CUSTOM)
        deserializingConstructorBuilder.addParam(FunctionParam("const adyen::Json&", "json"))
        deserializingConstructorBuilder.addParam(FunctionParam("IssueAccumulator&", "issues"))
        deserializingConstructorBuilder.addParam(FunctionParam("bool", "set_default", BooleanValue(true)))
        classBuilder.addConstructor(Class.AccessType.PUBLIC, deserializingConstructorBuilder.build())

        // Methods
        val typeMethod = Method.Builder(classBuilder, "nexo_payload_type", "type", isConst = true, isOverride = true);
        classBuilder.addMethod(Class.AccessType.PUBLIC, typeMethod.build())
        val toJsonMethod = Method.Builder(classBuilder, "adyen::Json", "to_json", isConst = true, isOverride = true);
        classBuilder.addMethod(Class.AccessType.PUBLIC, toJsonMethod.build())

        println(classBuilder.build().emitDeclaration())
    }

    @Test
    fun globalName() {
        val myClass = Class.Builder("MyClass", Namespace(listOf("adyen", "cxx"))).build()
        assertEquals("::adyen::cxx::MyClass", myClass.globalName)
    }
}