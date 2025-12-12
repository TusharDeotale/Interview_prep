package com.interviewprep.CustomLinkedHashMap;

public class Main {
    public static void main(String[] args) {
        CustomLinkedHashMap<String, Integer> map = new CustomLinkedHashMap<>();

        map.put("key1", 11);
        map.put("key2", 22);
        map.put("key3", 33);
        map.put("key4", 44);

        System.out.println("Get call for key1 : " + map.get("key1"));
        System.out.println("\n Insertion Order");

        map.display();
    }
}
