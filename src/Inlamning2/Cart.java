package Inlamning2;

import java.sql.*;


public class Cart {
    private Repository repo;

    public Cart() {}

    public void addToCart(int customerId,int customerOrderId,int shoeId){
        repo = new Repository();

        try (Connection con = DriverManager.getConnection(
                repo.getProperties().getProperty("connectionString"),
                repo.getProperties().getProperty("name"),
                repo.getProperties().getProperty("password"));

             CallableStatement stmt = con.prepareCall("{call addToCart(?,?,?)}")

        ){
            stmt.setInt(1, customerId);
            stmt.setInt(2, customerOrderId);
            stmt.setInt(3, shoeId);
            stmt.execute();


        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public int calculateTotal(int shoeId) {
        repo = new Repository();
        int sum = 0;

        try (Connection con = DriverManager.getConnection(
                repo.getProperties().getProperty("connectionString"),
                repo.getProperties().getProperty("name"),
                repo.getProperties().getProperty("password"));

             PreparedStatement ps = con.prepareStatement("SELECT shoe.price FROM cart" +
                                                            " INNER JOIN shoe ON shoe.id = cart.shoeId " +
                                                            "WHERE shoeId = ? GROUP BY shoe.id ")
        ){
            ps.setInt(1, shoeId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                sum = rs.getInt("price");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return sum;
    }

}
