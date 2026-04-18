package User;

import Interfaces.IPayment;

import java.util.ArrayList;

public class User {
    private String username;
    private int balance = 0;
    private final ArrayList<IPayment> payments = new ArrayList<>();

    public User(String username){
        this.username = username;
    }

    public void setUsername(String name){
        this.username = name;
    }
    public String getUsername() {
        return this.username;
    }
    public int getBalance(){
        return balance;
    }
    public void addBalance(int value){
        this.balance += value;
    }
    public void setBalance(int value) {
        this.balance = value;
    }

    public void addPaymentMethod(IPayment payment){
        if(!payments.contains(payment)){
            payments.add(payment);
        }
        else System.out.println("User already has the payment method.");
    }

    public ArrayList<IPayment> getPaymentMethods() {
        return payments;
    }
}

