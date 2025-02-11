package Inlamning2;

import java.sql.*;

public class Customer {
    private int customerId;
    private Repository repo;


    public Customer() {
    }

    public void setCustomerId(String name) {
        repo = new Repository();

        try (Connection con = DriverManager.getConnection(
                repo.getProperties().getProperty("connectionString"),
                repo.getProperties().getProperty("name"),
                repo.getProperties().getProperty("password"));

             PreparedStatement ps = con.prepareStatement("Select id from customer where username = ?")

        ){
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                customerId = rs.getInt("id");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public int getCustomerId() {
        return customerId;
    }

}
