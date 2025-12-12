package com.interviewprep.DesignPattern.Registry;

import com.interviewprep.DesignPattern.ClassLoader.CoustomClassLoader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;

public class SingletonRegistryTest {
    public static void main(String[] args) throws Exception {
        Path classDir = Path.of("out/production/Collections/com/interviewprep/DesignPattern/Singleton");
        CoustomClassLoader loader1 = new CoustomClassLoader(classDir);
        CoustomClassLoader loader2 = new CoustomClassLoader(classDir);

        Class<?> class1 = loader1.loadClass("SingletonDoubleChecked");
        Class<?> class2 = loader2.loadClass("SingletonDoubleChecked");

        Object instance1 = SingletonRegistry.getSingleton("SingletonDoubleChecked", () -> {
            try {
                Constructor<?> constructor = class1.getDeclaredConstructor();
                constructor.setAccessible(true);

                return constructor.newInstance();
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });

        Object instance2 = SingletonRegistry.getSingleton("SingletonDoubleChecked", () -> {
            try {
                Constructor<?> constructor = class2.getDeclaredConstructor();
                constructor.setAccessible(true);

                return constructor.newInstance();
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });


        System.out.println(instance1 == instance2);
    }
}
