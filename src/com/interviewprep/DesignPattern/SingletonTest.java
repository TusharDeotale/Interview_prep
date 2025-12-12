package com.interviewprep.DesignPattern;


import com.interviewprep.DesignPattern.Singleton.SingletonBillPugh;
import com.interviewprep.DesignPattern.Singleton.SingletonEnum;

import java.io.*;

public class SingletonTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /*SingletonSynchronised s1 = SingletonSynchronised.getInstance();
        SingletonSynchronised s2 = SingletonSynchronised.getInstance();

        System.out.println(s1 == s2);*/
        SingletonBillPugh s1 = SingletonBillPugh.getInstance();

        SingletonEnum s_enum1 = SingletonEnum.INSTANCE;

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Serialize.ser"));
        oos.writeObject(s_enum1);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Serialize.ser"));
//        SingletonBillPugh s2 = (SingletonBillPugh) ois.readObject();
        SingletonEnum s_enum2 = (SingletonEnum) ois.readObject();
        ois.close();

        System.out.println(s_enum1 == s_enum2);
    }
}
