package Inlamning2;

import java.util.Scanner;

public class Dashboard {
    private UserLogin user;
    private Categories categories;
    private Repository repo;
    private Scanner input;
    private Cart cart;
    private Customer customer;
    private CustomerOrder order;
    private Shoes shoes;

    public Dashboard() throws InterruptedException {
        input = new Scanner(System.in);
        categories = new Categories();
        repo = new Repository();
        customer = new Customer();
        shoes = new Shoes();
        order = new CustomerOrder();
        repo.showWelcomeScreen();
        user = new UserLogin(repo.getUserNameInput(), repo.getPasswordInput());
        user.checkLoginCredentials();
        customer.setCustomerId(user.getUserName());
        int sum = 0;

        Thread.sleep(500);

        while(true) {

            while(true) {

                String shoename;
                String colour;
                int size;

                categories.chooseCategory();
                shoes.resetShoeId();

                System.out.println("Enter shoe name: ");
                shoename = input.next();
                System.out.println("Enter shoe colour: ");
                colour = input.next();
                System.out.println("Enter shoe size: ");
                size = input.nextInt();

                shoes.setShoeId(shoename, colour, size);
                if(shoes.getShoeId() == 0) {
                    System.out.println("Try again..");
                    break;
                }

                shoes.setShoeQuantity(shoes.getShoeId());
                if(shoes.getShoeQuantity() == 0){
                    System.out.println("This shoe is out of stock");
                    System.out.println("Select a new one by typing 'n'");
                    System.out.println("Exit store by typing 'q'");
                    String selection = input.next();

                    if(selection.equals("n")){
                        categories.showCategories();
                    } else if(selection.equals("q")) {
                        System.out.println("Exiting shop...");
                        Thread.sleep(500);
                        System.exit(0);
                    }
                }else{
                    cart = new Cart();
                    order.setCustomerOrderId(customer.getCustomerId());
                    cart.addToCart(customer.getCustomerId(), order.getCustomerOrderId(), shoes.getShoeId());
                    sum += cart.calculateTotal(shoes.getShoeId());
                    System.out.println("You have successfully added the shoe to your cart\n");
                    break;
                }
            }


            System.out.println("Do you want to add more y/n");
            String keepShopping = input.next();


                if(keepShopping.equals("n")) {
                    System.out.println("Redirecting to payment....");
                    Thread.sleep(300);
                    System.out.println("...");
                    Thread.sleep(300);
                    System.out.println("..");
                    Thread.sleep(300);
                    System.out.println(".");
                    Thread.sleep(300);
                    break;
                }else if(keepShopping.equals("y")) {
                    System.out.println("Press enter to continue or type pay to go to payment");
                }
            }

        Payment pay = new Payment();
        pay.customerPay(sum);
        }


    public static void main(String[] args) throws InterruptedException {
        new Dashboard();

    }
}
