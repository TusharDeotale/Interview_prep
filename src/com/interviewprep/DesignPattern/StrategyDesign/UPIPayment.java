package com.interviewprep.DesignPattern.StrategyDesign;

public class UPIPayment implements Payable{
    private String upiId;

    public UPIPayment(String upiId){
        this.upiId = upiId;
    }
    @Override
    public void pay(int amount) {
        System.out.println("Paying " +amount + " with UPI Id : " + upiId);
    }
}
