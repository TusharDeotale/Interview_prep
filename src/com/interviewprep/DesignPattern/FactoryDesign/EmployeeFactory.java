package com.interviewprep.DesignPattern.FactoryDesign;

public class EmployeeFactory {
    private EmployeeFactory() {
    }

    public static Employee getEmployee(String name) {
        if(name.trim().equalsIgnoreCase("ANDROID")){
            return new AndroidDeveloper();
        } else if (name.trim().equalsIgnoreCase("WEB")){
            return new WebDeveloper();
        }
        return null;
    }

}
