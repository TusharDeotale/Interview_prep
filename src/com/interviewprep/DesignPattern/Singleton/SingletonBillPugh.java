package com.interviewprep.DesignPattern.Singleton;

import java.io.Serial;
import java.io.Serializable;

public class SingletonBillPugh implements Serializable {
    private SingletonBillPugh() {
    }

    private static class SingletonHelper {
        private static final SingletonBillPugh instance = new SingletonBillPugh();
    }

    public static SingletonBillPugh getInstance() {
        return SingletonHelper.instance;
    }

    @Serial
    protected Object readResolve() {
        return getInstance();
    }
}
