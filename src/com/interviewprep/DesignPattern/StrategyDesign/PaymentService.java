package com.interviewprep.DesignPattern.StrategyDesign;

public class PaymentService {

    private Payable payment;

    public PaymentService(Payable payment) {
        this.payment = payment;
    }

    public void sendMoney(int amount){
        payment.pay(amount);
    }
}
