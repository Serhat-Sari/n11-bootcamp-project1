package Services;

import User.User;
import java.util.Scanner;

public class UserService {
    private final Scanner input;

    public UserService(Scanner input) {
        this.input = input;
    }

    public User createUser() {
        System.out.print("Enter name of the user: ");
        String username = input.nextLine();
        System.out.print("Enter starting balance: ");
        int balance = input.nextInt();
        input.nextLine();
        User user = new User(username);
        user.addBalance(balance);
        return user;
    }
}