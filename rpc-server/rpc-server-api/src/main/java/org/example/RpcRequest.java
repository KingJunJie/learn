package org.example;

import java.io.Serializable;
import java.util.Arrays;

public class RpcRequest implements Serializable {

    private String className;
    private String methodName;
    private Object[] parames;
    private Class[] types;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParames() {
        return parames;
    }

    public void setParames(Object[] parames) {
        this.parames = parames;
    }

    public Class[] getTypes() {
        return types;
    }

    public void setTypes(Class[] types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return "RpcRequest{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", parames=" + Arrays.toString(parames) +
                ", types=" + Arrays.toString(types) +
                '}';
    }
}
