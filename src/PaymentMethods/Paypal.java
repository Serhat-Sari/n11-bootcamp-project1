package PaymentMethods;

import Interfaces.IPayment;

public class Paypal implements IPayment{

    public boolean equals(Object obj) {
        return obj instanceof Paypal;
    }

    @Override
    public void printPayment(int value) {
        System.out.println("Paypal payment of " + value + " was successful.");
    }

    @Override
    public String getPaymentType(){
        return "Paypal";
    }
}
