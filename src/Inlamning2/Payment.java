package Inlamning2;


import java.util.Scanner;

public class Payment {

    public Payment() {}

    public void customerPay(int total) {
        Scanner input = new Scanner(System.in);
        String pay;

        System.out.println("\nTotal: " + total + " SEK");
        System.out.println("Do you want to pay or exit? p/q");
        pay = input.nextLine();

        if (pay.equals("p")) {
            System.out.println("Thank you!..");
            System.exit(0);
        } else if (pay.equals("q")) {
            System.out.println("Bye!..");
            System.exit(0);
        }
    }
}
