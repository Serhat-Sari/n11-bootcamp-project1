package PaymentSystem;

import Interfaces.IPayment;
import User.User;

import java.util.ArrayList;

public class Payment {
    private final User user;

    public Payment(User user){
        this.user = user;
    }

    public void withdrawFromUser(int value, IPayment payment) {
        ArrayList<IPayment> userPayments = user.getPaymentMethods();

        if(userPayments.isEmpty()){
            System.out.println("User has no payment method in their account!");
            System.out.println("Add payment method!");
            return;
        }
        if(!userPayments.contains(payment)) {
            System.out.println("User does not have this payment method!");
            return;
        }
        if (user.getBalance() < value) {
            System.out.println("User doesn't have enough money in their account!");
            System.out.println("Please add more money!");
            return;
        }
        payment.printPayment(value);
        user.addBalance(-value);
    }

    public void addBalanceToUser(int value){
        user.addBalance(value);
        System.out.println("User has received payment of " + value);
    }
}
