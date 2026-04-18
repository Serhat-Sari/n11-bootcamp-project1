import Interfaces.IPayment;
import PaymentMethods.ApplePay;
import PaymentMethods.CreditCard;
import PaymentMethods.Paypal;
import PaymentSystem.Payment;
import User.User;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.println("Enter name of the user: ");
        String username = input.nextLine();

        User user = new User(username);
        Payment payment = new Payment(user);

        System.out.println("Enter starting balance: ");
        int balance = input.nextInt();
        user.addBalance(balance);

        ArrayList<IPayment> availableMethods = new ArrayList<>();
        availableMethods.add(new ApplePay());
        availableMethods.add(new CreditCard());
        availableMethods.add(new Paypal());

        while(true){
            System.out.println("\n--- Menu ---");
            System.out.println("1. Add payment method");
            System.out.println("2. Withdraw");
            System.out.println("3. Add balance");
            System.out.println("4. View balance");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int option = input.nextInt();
            input.nextLine();

            switch(option){
                case 1:
                    boolean validMethod = false;
                    while(!validMethod){
                        System.out.println("Choose payment method to add to Users account:");
                        for(int i = 0; i<availableMethods.size();i++){
                            System.out.println((i+1) + "." + availableMethods.get(i).getPaymentType());
                        }
                        System.out.println(availableMethods.size() + 1 + ". Back");
                        int methodChoice = input.nextInt();
                        input.nextLine();
                        if(methodChoice == availableMethods.size() + 1){
                            validMethod = true;
                        } else if(methodChoice>=1 && methodChoice <= availableMethods.size()) {
                            user.addPaymentMethod(availableMethods.get(methodChoice - 1));
                            validMethod = true;
                        } else {
                            System.out.println("Invalid choice, try again!");
                        }
                    }
                    break;
                case 2:
                    if(user.getPaymentMethods().isEmpty()){
                        System.out.println("No payment methods available, add one first!");
                        break;
                    }
                    System.out.println("Choose payment method to use: ");
                    ArrayList<IPayment> methods = user.getPaymentMethods();
                    for(int i = 0; i<methods.size();i++){
                        System.out.println((i+1) + ". " + methods.get(i).getPaymentType());
                    }
                    int payChoice = input.nextInt();
                    input.nextLine();
                    if(payChoice < 1 || payChoice > methods.size()){
                        System.out.println("Invalid choice!");
                        break;
                    }
                    IPayment selectedPayment = methods.get(payChoice-1);
                    System.out.println("Enter amount to withdraw: ");
                    int amount = input.nextInt();
                    input.nextLine();
                    payment.withdrawFromUser(amount,selectedPayment);
                    break;
                case 3:
                    System.out.print("Enter amount to add: ");
                    int addAmount = input.nextInt();
                    input.nextLine();
                    payment.addBalanceToUser(addAmount);
                    break;
                case 4:
                    System.out.println("Username: " + user.getUsername());
                    System.out.println("Balance: " + user.getBalance());
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option, Try again!");
            }
        }


    }
}