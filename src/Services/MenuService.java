package Services;

import Interfaces.IPayment;
import PaymentMethods.ApplePay;
import PaymentMethods.CreditCard;
import PaymentMethods.Paypal;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuService {
    private Scanner input;
    private ArrayList<IPayment> availableMethods;

    public MenuService(Scanner input) {
        this.input = input;
        this.availableMethods = new ArrayList<>();
        availableMethods.add(new ApplePay());
        availableMethods.add(new CreditCard());
        availableMethods.add(new Paypal());
    }

    public int showMainMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Add payment method");
        System.out.println("2. Pay");
        System.out.println("3. Add balance");
        System.out.println("4. View balance");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
        int option = input.nextInt();
        input.nextLine();
        return option;
    }
    /*
        Shows the payment methods user has in their account.
    */
    public IPayment showPaymentMethodMenu(ArrayList<IPayment> userMethods) {
        for (int i = 0; i < userMethods.size(); i++) {
            System.out.println((i + 1) + ". " + userMethods.get(i).getPaymentType());
        }
        int choice = input.nextInt();
        input.nextLine();
        if (choice < 1 || choice > userMethods.size()) {
            System.out.println("Invalid choice!");
            return null;
        }
        return userMethods.get(choice - 1);
    }

    /*
        Adds a payment method to the users account.
    */
    public IPayment showAddMethodMenu() {
        System.out.println("Choose payment method to add:");
        for (int i = 0; i < availableMethods.size(); i++) {
            System.out.println((i + 1) + ". " + availableMethods.get(i).getPaymentType());
        }
        System.out.println((availableMethods.size() + 1) + ". Back");
        int choice = input.nextInt();
        input.nextLine();
        if (choice == availableMethods.size() + 1) return null;
        if (choice >= 1 && choice <= availableMethods.size()) {
            return availableMethods.get(choice - 1);
        }
        System.out.println("Invalid choice!");
        return null;
    }

    public int askAmount(String prompt) {
        System.out.print(prompt);
        int amount = input.nextInt();
        input.nextLine();
        return amount;
    }
}