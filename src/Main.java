import PaymentSystem.Payment;
import Services.ActionHandler;
import Services.MenuService;
import Services.UserService;
import User.User;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
       Scanner input = new Scanner(System.in);
       User user = new UserService(input).createUser();
       Payment payment = new Payment(user);
       MenuService menu = new MenuService(input);
       ActionHandler handler = new ActionHandler(user, payment, menu);

       // Endless loop that works until user decides to quit.
       while(handler.handle(menu.showMainMenu()));
    }
}