package com.interviewprep.DesignPattern.StrategyDesign;

public class PaymentClient {
    public static void main(String[] args) {
        PaymentService card = new PaymentService(new CreditCardPayment("123-456-789"));
        card.sendMoney(500);

        PaymentService upi = new PaymentService(new UPIPayment("tushardeotale77@okicici.com"));
        upi.sendMoney(500);
    }
}
