package com.interviewprep.CoustomHashMap;

public class Main {
    public static void main(String[] args) {
        CustomHashMap<Integer, String> map = new CustomHashMap<>();

        map.put(1, "abc");
        map.put(2, "xyz");
        String s = map.get(1);

        System.out.println(s);
        System.out.println("Map Size before remove() >> " + map.getSize());

        System.out.println("Map Size after remove() for key "+2+" with value " + map.remove(2) + map.getSize() + " >> ");
    }
}
