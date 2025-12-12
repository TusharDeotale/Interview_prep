package com.interviewprep.DesignPattern.ClassLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;

public class CustomClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Path classDir = Path.of("out/production/Collections/com/interviewprep/DesignPattern/Singleton");

        CoustomClassLoader loader1 = new CoustomClassLoader(classDir);
        CoustomClassLoader loader2 = new CoustomClassLoader(classDir);

        Class<?> class1 = loader1.loadClass("SingletonDoubleChecked");
        Class<?> class2 = loader2.loadClass("SingletonDoubleChecked");

        Method getInstance1 = class1.getMethod("getInstance");
        Method getInstance2 = class2.getMethod("getInstance");

        Object instance1 = getInstance1.invoke(null);
        Object instance2 = getInstance2.invoke(null);


        System.out.println(instance1 == instance2);
    }
}
