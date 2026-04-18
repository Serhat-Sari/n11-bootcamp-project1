package Services;

import Interfaces.IPayment;
import PaymentSystem.Payment;
import User.User;

import java.util.ArrayList;

public class ActionHandler {
    private User user;
    private Payment payment;
    private MenuService menu;

    public ActionHandler(User user, Payment payment, MenuService menu) {
        this.user = user;
        this.payment = payment;
        this.menu = menu;
    }

    public boolean handle(int option) {
        switch (option) {
            case 1: addPaymentMethod(); break;
            case 2: pay(); break;
            case 3: addBalance(); break;
            case 4: viewBalance(); break;
            case 5: System.out.println("Goodbye!"); return false;
            default: System.out.println("Invalid option, try again!");
        }
        return true;
    }

    private void addPaymentMethod() {
        IPayment chosen = menu.showAddMethodMenu();
        if (chosen != null) {
            user.addPaymentMethod(chosen);
        }
    }

    private void pay() {
        ArrayList<IPayment> methods = user.getPaymentMethods();
        if (methods.isEmpty()) {
            System.out.println("No payment methods available, add one first!");
            return;
        }
        System.out.println("Choose payment method to use:");
        IPayment selected = menu.showPaymentMethodMenu(methods);
        if (selected == null) return;
        int amount = menu.askAmount("Enter amount to pay: ");
        payment.withdrawFromUser(amount, selected);
    }

    private void addBalance() {
        int amount = menu.askAmount("Enter amount to add: ");
        payment.addBalanceToUser(amount);
    }

    private void viewBalance() {
        System.out.println("Username: " + user.getUsername());
        System.out.println("Balance: " + user.getBalance());
    }
}