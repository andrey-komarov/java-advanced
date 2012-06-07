package ru.ifmo.ctddev.komarov.implementor;

import java.lang.reflect.Method;
import java.util.Arrays;

public class MyMethod {
    public Method m;

    public MyMethod(Method m) {
        this.m = m;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Method m2 = ((MyMethod) o).m;

        if (!m.getName().equals(m2.getName())) {
            return false;
        }
        
        if (!Arrays.equals(m.getParameterTypes(), m2.getParameterTypes())) {
            return false;
        }

        if (m.getReturnType().equals(m2.getReturnType())) {
            return true;
        }
        
        if (m.getReturnType().isInstance(m2.getReturnType())) {
            ((MyMethod)o).m = m;
        } else if (m2.getReturnType().isInstance(m.getReturnType())){
            m = m2;
        } else {
//            throw new AssertionError();
        }

        return true;
    }

    @Override
    public int hashCode() {
        return m != null ? Arrays.hashCode(m.getParameterTypes()) + m.getName().hashCode() : 0;
    }
}
