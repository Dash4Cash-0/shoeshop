package Inlamning2;

import java.sql.*;
import java.util.Scanner;

public class Categories {
    Repository repo = new Repository();


    public void showCategories() {

        try (Connection con = DriverManager.getConnection(
                repo.getProperties().getProperty("connectionString"),
                repo.getProperties().getProperty("name"),
                repo.getProperties().getProperty("password"));

             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM category")
        ){
            int cNumber = 1;
            System.out.println("\n*********************");
            while (rs.next()) {
                System.out.println(cNumber + ": " + rs.getString("name"));
                cNumber++;
            }
            System.out.println("*********************\n");


        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public String showShoesInCategory(int categoryNumber){
        Scanner input = new Scanner(System.in);
        String goBack = "";
        try (Connection con = DriverManager.getConnection(
                repo.getProperties().getProperty("connectionString"),
                repo.getProperties().getProperty("name"),
                repo.getProperties().getProperty("password"));

             PreparedStatement stmt = con.prepareStatement(
                             "SELECT shoe.brand,shoe.colour,shoe.shoesize,shoe.price " +
                                 "FROM shoeCategory " +
                                 "INNER JOIN shoe " +
                                 "ON shoe.id = shoeCategory.shoeId WHERE categoryId = ? GROUP BY shoe.id");
        ){
            stmt.setInt(1, categoryNumber);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Name: " + rs.getString("brand") + " || " +
                        "Colour: " +rs.getString("colour") + " || " +
                        "Size: " + rs.getString("shoesize") + " || " +
                        "Price: " + rs.getString("price") + " SEK");
            }

            System.out.println("Press 'enter' to add shoes to your cart or type 'b' to go back to categories: ");
            goBack = input.nextLine();


        }catch (SQLException e){
            e.printStackTrace();
        }
        return goBack;
    }

    public int chooseCategory() throws InterruptedException {
        Scanner input = new Scanner(System.in);
        Categories categories = new Categories();

        categories.showCategories();
        System.out.println("Please choose a category: ");
        int category = input.nextInt();
        String goBack = categories.showShoesInCategory(category);


        if (goBack.equals("b")) {
            System.out.println("Going back to categories....");
            Thread.sleep(500);
            categories.showCategories();
            System.out.println("Please choose a category: ");
            category = input.nextInt();
            categories.showShoesInCategory(category);
        }
        return category;
    }
}
