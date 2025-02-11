package Inlamning2;

import java.sql.*;

public class Shoes {
    Repository repo = new Repository();
    private int shoeId = 0;
    private int shoeQuantity;

    public Shoes(){
    }

    public void setShoeId(String shoeName,String shoeColour,int shoeSize) {

        try (Connection con = DriverManager.getConnection(
                repo.getProperties().getProperty("connectionString"),
                repo.getProperties().getProperty("name"),
                repo.getProperties().getProperty("password"));

             PreparedStatement stmt = con.prepareStatement("SELECT id  FROM SHOE " +
                     "WHERE brand = ? AND colour = ? AND shoeSize = ?");
        ){
            stmt.setString(1, shoeName);
            stmt.setString(2, shoeColour);
            stmt.setInt(3, shoeSize);
            ResultSet rs = stmt.executeQuery();

            rs.next();

            this.shoeId = rs.getInt("id");

        }catch (SQLException e){
            System.out.println("You have entered a shoe that doesnt exist");
        }
    }

    public void setShoeQuantity(int shoeId) {

        try (Connection con = DriverManager.getConnection(
                repo.getProperties().getProperty("connectionString"),
                repo.getProperties().getProperty("name"),
                repo.getProperties().getProperty("password"));

             PreparedStatement stmt = con.prepareStatement("SELECT quantity  FROM SHOE " +
                     "WHERE id = ?");
        ){
            stmt.setInt(1, shoeId);
            ResultSet rs = stmt.executeQuery();

            rs.next();
            this.shoeQuantity = rs.getInt("quantity");


        }catch (SQLException e){

        }
    }
    public void resetShoeId(){
        this.shoeId = 0;
    }
    public int getShoeQuantity() {
        return shoeQuantity;
    }
    public int getShoeId() {
        return shoeId;
    }
}
