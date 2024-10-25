package org.example.designpatterns.behavioral;

import java.util.ArrayList;
import java.util.List;

// example is payment class

interface PaymentStrategy {
    boolean pay(int amount);
}

class BankTransferPayment implements  PaymentStrategy{
    public boolean pay(int amount){
        // validate Bank and pay
        System.out.println("Payment through bank");
        return  true;
    }
}

class CardPayment implements  PaymentStrategy{
    public boolean pay(int amount){
        // validate card and pay
        System.out.println("Payment through card");
        return  true;
    }
}

// This class hold reference to different strategies and can be modified accordingly
class ProcessOrder {
    PaymentStrategy paymentStrategy;
    ProcessOrder(PaymentStrategy paymentStrategy){
        this.paymentStrategy = paymentStrategy;
    }

    public void updatePaymentMethod(PaymentStrategy paymentStrategy){
        this.paymentStrategy = paymentStrategy;
    }

   public void process(int amt){
        // make checks
        paymentStrategy.pay(amt);
        // print bill
    }
}



public class Strategy {
    public static void main(String[] args) {
        ProcessOrder order = new ProcessOrder(new BankTransferPayment());
        order.process(500);
        order.updatePaymentMethod(new CardPayment());
        order.process(200);
    }
}
