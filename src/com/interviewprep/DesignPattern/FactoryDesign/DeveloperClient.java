package com.interviewprep.DesignPattern.FactoryDesign;

public class DeveloperClient {
    public static void main(String[] args) {
        Employee android = EmployeeFactory.getEmployee("ANDROID");
        int salary = android.salary(500);
        System.out.println(salary);

        Employee web = EmployeeFactory.getEmployee("WEB");
        System.out.println(web.salary(800));
    }
}
