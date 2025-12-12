package com.interviewprep.LRU;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LRUcache<String, Integer> cache = new LRUcache<>(3);

        Scanner sc = new Scanner(System.in);

        int option = 1;

        while(option != 0){
            System.out.println("1: Put\n2: Get\n0: Exit");
            option = sc.nextInt();

            String key;
            int value;

            switch (option){
                case 1:
                    System.out.println("Enter Key");
                    key = sc.next();
                    System.out.println("Enter Value");
                    value = sc.nextInt();
                    cache.put(key, value);
                    System.out.println("Inserted");
                    break;
                case 2:
                    System.out.println("Enter Key");
                    key = sc.next();
                    value = cache.get(key);
                    System.out.println("Value is : " + value);
                    break;

                default:
                    System.out.println("**");
            }
        }
    }
}
