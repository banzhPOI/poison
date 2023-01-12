package org.poison.methodidempotent;

import lombok.Data;

import java.util.Arrays;
import java.util.Objects;

@Data
public class MethodObject {

    private String methodName;

    private Object[] args;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MethodObject methodObject = (MethodObject) o;
        return Objects.equals(methodName, methodObject.methodName) && Arrays.equals(args, methodObject.args);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(methodName);
        result = 31 * result + Arrays.hashCode(args);
        return result;
    }
}
