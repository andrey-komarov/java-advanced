package ru.ifmo.ctddev.komarov.implementor;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.HashSet;


public class Implementor {
    private PrintWriter writer;
    private String className;
    private String fullName;
    Class<?> clazz;

    private Implementor(String fullName) throws ClassNotFoundException {
        this.fullName = fullName;
        String[] tokens = fullName.split("\\.");
        className = tokens[tokens.length - 1];
        clazz = Class.forName(fullName);
    }

    private void generate() throws FileNotFoundException {
        writer = new PrintWriter(className + "Impl.java");
        if (clazz.isInterface()) {
            generateInterface();
        } else {
            generateClass();
        }
        Type[] types = clazz.getGenericInterfaces();
        Type parent = clazz.getGenericSuperclass();

        writer.close();
    }

    private void writeMethodModifiers(Method m) {
        int mod = m.getModifiers();
        mod &= ~Modifier.ABSTRACT;
        mod &= ~Modifier.TRANSIENT;
        writer.print(Modifier.toString(mod) + " ");
    }

    private void writeArguments(Method m) {
        Class<?>[] args = m.getParameterTypes();
        int size = args.length;
        for (int i = 0; i < size; i++) {
            clazz.getGenericInterfaces();
            writer.print(args[i].getCanonicalName() + " arg" + i);
            if (i != size - 1)
                writer.print(", ");
        }
    }

    private void writeExceptions(Method m) {
        Class<?>[] exceptions = m.getExceptionTypes();
        int size = exceptions.length;
        if (exceptions.length == 0)
            return;
        writer.print("throws ");
        for (int i = 0; i < size; i++) {
            writer.print(exceptions[i].getCanonicalName());
            if (i != size - 1)
                writer.print(", ");
        }
        writer.print(" ");
    }

    private String getDefaultValue(Class<?> clazz) {
        if (clazz.equals(void.class)) {
            return "";
        } else if (clazz.isPrimitive()) {
            if (clazz.equals(boolean.class)) {
                return "false";
            } else {
                return "0";
            }
        } else {
            return "null";
        }
    }
    
    private void implementMethod(Method m, boolean isInterface) {
        if (!isInterface && !Modifier.isAbstract(m.getModifiers()))
            return;

        writer.print("\t");
        writeMethodModifiers(m);
        writer.print(m.getReturnType().getCanonicalName() + " ");
        writer.print(m.getName() + " ");
        writer.print("(");
        writeArguments(m);
        writer.print(") ");
        writeExceptions(m);
        writer.println("{");
        writer.println("\t\treturn " + getDefaultValue(m.getReturnType()) + ";");
        writer.println("\t}");
        writer.print("\n\n");
    }

    private void implementConstructor(Constructor c) {
        writer.print("\t");
        writer.print(className + "Impl");
        writer.print("(");
        Class[] args = c.getParameterTypes();
        for (int i = 0; i < args.length; i++) {
            writer.print(args[i].getName() + " arg" + i);
            if (i != args.length - 1) {
                writer.print(", ");
            }
        }
        writer.print(") throws Throwable {\n");
        writer.print("\t\tsuper(");
        for (int i = 0; i < args.length; i++) {
            writer.print("arg" + i);
            if (i != args.length - 1) {
                writer.print(", ");
            }
        }
        writer.print(");\n");
        writer.print("\t}\n");
    }

    private void generateInterface() {
        writer.println("import " + fullName + ";");
        writer.println();
        writer.println("class " + className + "Impl implements " + fullName + " {");

        HashSet<MyMethod> methods = new HashSet<>();
        for (Method m : clazz.getMethods()) {
            methods.add(new MyMethod(m));
        }
        for (MyMethod m : methods) {
            implementMethod(m.m, true);
        }
        writer.println("}");
    }

    private void generateClass() {
        writer.println("import " + fullName + ";");
        writer.println();
        writer.println("class " + className + "Impl extends " + fullName + " {");

        HashSet<MyMethod> methods = new HashSet<>();
        for (Method m : clazz.getMethods()) {
            methods.add(new MyMethod(m));
        }
        for (Method m : clazz.getDeclaredMethods()) {
            methods.add(new MyMethod(m));
        }
        for (MyMethod m : methods) {
            implementMethod(m.m, false);
        }

        for (Constructor c : clazz.getConstructors()) {
            implementConstructor(c);
        }

        writer.println("}");
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Implementor <Class>");
            args = new String[]{"ru.ifmo.ctddev.rakov.implementor.test.A"};
//            return;
        }
        try {
            new Implementor(args[0]).generate();
        } catch (FileNotFoundException e) {
            System.out.println("I/O Error");
        } catch (ClassNotFoundException e) {
            System.out.println("No such class: " + args[0]);
        }
    }
}