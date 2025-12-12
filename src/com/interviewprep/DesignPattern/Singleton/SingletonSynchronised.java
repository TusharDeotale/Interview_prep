package com.interviewprep.DesignPattern.Singleton;

public class SingletonSynchronised {

    private static SingletonSynchronised instance;

    private SingletonSynchronised() {
    }

    public static synchronized SingletonSynchronised getInstance() {
        if (instance == null) {
            instance = new SingletonSynchronised();
        }
        return instance;
    }
}