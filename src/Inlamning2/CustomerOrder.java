package Inlamning2;

import java.sql.*;

public class CustomerOrder {

    private int customerOrderId;

    public CustomerOrder() {
    }

    public void setCustomerOrderId(int customer) throws InterruptedException {
        Repository repo = new Repository();

        try (Connection con = DriverManager.getConnection(
                repo.getProperties().getProperty("connectionString"),
                repo.getProperties().getProperty("name"),
                repo.getProperties().getProperty("password"));

             PreparedStatement ps = con.prepareStatement("SELECT id FROM customerOrder where customerId = ?")

        ){
            ps.setInt(1, customer);
            ResultSet rs = ps.executeQuery();
            rs.next();
            this.customerOrderId = rs.getInt(1);



        }catch (SQLException e){
            System.out.println("\nYou do not have an active order..");
            System.out.println("Creating a new order now..\n");
            Thread.sleep(700);

        }
    }

    public int getCustomerOrderId() {
        return customerOrderId;
    }
}
