package PaymentMethods;

import Interfaces.IPayment;

public class ApplePay implements IPayment {

    public boolean equals(Object obj) {
        return obj instanceof ApplePay;
    }

    @Override
    public void printPayment(int value) {
        System.out.println("Apple Pay payment of " + value + " was successful.");
    }

    @Override
    public String getPaymentType(){
        return "Apple Pay";
    }
}
