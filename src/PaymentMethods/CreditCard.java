package PaymentMethods;

import Interfaces.IPayment;
public class CreditCard extends IPayment {

    public boolean equals(Object obj) {
        return obj instanceof CreditCard;
    }

    @Override
    public void printPayment(int value) {
        System.out.println("Credit Card payment of " + value + " was successful.");
    }

    @Override
    public String getPaymentType(){
        return "Credit Card";
    }
}
