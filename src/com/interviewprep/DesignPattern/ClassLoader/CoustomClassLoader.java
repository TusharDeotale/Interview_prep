package com.interviewprep.DesignPattern.ClassLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CoustomClassLoader extends ClassLoader{

    private final Path classDir;

    public CoustomClassLoader(Path classDir) {
        super(CoustomClassLoader.getSystemClassLoader());
//        super(null);
        this.classDir = classDir;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try{
            Path classFile = classDir.resolve(name + ".class");
            byte[] bytes = Files.readAllBytes(classFile);
            return defineClass(null, bytes, 0, bytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name, e);
        }
    }
}
