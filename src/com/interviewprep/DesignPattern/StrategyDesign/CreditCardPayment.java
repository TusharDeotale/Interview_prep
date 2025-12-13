package com.interviewprep.DesignPattern.StrategyDesign;

public class CreditCardPayment implements Payable{
    private String cardNo;

    public CreditCardPayment(String cardNo){
        this.cardNo = cardNo;
    }
    @Override
    public void pay(int amount) {
        System.out.println("Paying " +amount+ " with card no : " +cardNo);
    }
}
