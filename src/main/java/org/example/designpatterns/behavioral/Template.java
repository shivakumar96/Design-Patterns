package org.example.designpatterns.behavioral;

// This pattern is used when many objects have to follow a set of steps.
// also you need to provide flexibility to object to have custom implementation of those steps
// This is similar to strategy but has one predefined method which is common to all implementations

abstract class PaymentFlow {
    abstract public void ValidateRequest(double amount, String account);
    abstract public double calculateProcessingFee(double amount);
    abstract public void credit(double amount, String account);

    abstract public void debit(double amount, String account);

    public void Pay(double amount, String fromAccount, String toAccount){
        ValidateRequest(amount,fromAccount);
        double total = amount + calculateProcessingFee(amount);
        debit(total,fromAccount);
        credit(amount,toAccount);
    }

}

class DomesticPayment extends PaymentFlow {

    @Override
    public void ValidateRequest(double amount, String account) {
        // check if the amount is available in the account
    }

    @Override
    public double calculateProcessingFee(double amount) {
        if(amount < 5000)
            return 0;
        return amount*0.01;
    }

    @Override
    public void credit(double amount, String account) {
        // add money to account and update
    }

    @Override
    public void debit(double amount, String account) {
        // debit money to account and update
    }
}

class INternationalPayment extends PaymentFlow {

    @Override
    public void ValidateRequest(double amount, String account) {
        // check if this transaction is supported
        // check if the amount is available in the account
    }

    @Override
    public double calculateProcessingFee(double amount) {
        return amount*0.05;
    }

    @Override
    public void credit(double amount, String account) {
        // add money to account and update
    }

    @Override
    public void debit(double amount, String account) {
        // debit money to account and update
    }
}


public class Template {
    public static void main(String[] args) {
        PaymentFlow payment = new DomesticPayment();
        payment.Pay(10,"124","567");
        PaymentFlow internationalPayment = new INternationalPayment();
        internationalPayment.Pay(10,"124","567");
    }
}
