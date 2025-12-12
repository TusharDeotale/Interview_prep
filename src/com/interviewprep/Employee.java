package com.interviewprep;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Employee {
    public static void main(String[] args) {

        String fileName = "C:\\Users\\Wissen\\Documents\\Employee.csv";

        String line;
        String splitter = "\t";

        int max_salary = Integer.MIN_VALUE;
        int min_salary = Integer.MAX_VALUE;

        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            br.readLine();

            while((line = br.readLine()) != null){
                String[] split = line.split(splitter);
                int salary = Integer.parseInt(split[2]);

                min_salary = Math.min(min_salary, salary);
                max_salary = Math.max(max_salary, salary);
            }
            System.out.println("min_salary " + min_salary);
            System.out.println("max_salary " + max_salary);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
