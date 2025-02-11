package Inlamning2;


import java.sql.*;
import java.util.Scanner;

public class UserLogin {

    Repository repo = new Repository();
    private String tempUserName;
    private String tempPassword;
    private String userName;
    private String password;


    public UserLogin(String userName, String password) {
        this.tempUserName = userName;
        this.tempPassword = password;
    }


    public void checkLoginCredentials(){
        Scanner input = new Scanner(System.in);
        boolean existingUser = false;

        while(!existingUser) {

            try (Connection con = DriverManager.getConnection(
                    repo.getProperties().getProperty("connectionString"),
                    repo.getProperties().getProperty("name"),
                    repo.getProperties().getProperty("password"));

                 PreparedStatement ps = con.prepareStatement("SELECT * FROM customer WHERE username = ? AND password = ?");

            ) {

                ps.setString(1, getTempUserName());
                ps.setString(2, getTempPassword());
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    setUserName(rs.getString("userName"));
                    setPassword(rs.getString("password"));
                }

                if (tempUserName.equals(getUserName()) && tempPassword.equals(getPassword())) {
                    existingUser = true;
                    System.out.println("You've been successfully logged in!");
                } else {
                    System.out.println("There is no user with that username or password!");
                    System.out.println("Try again 'enter' or exit by typing 'q' ");
                    String exit = input.nextLine();

                    if (exit.equals("q")) {
                        System.out.println("Exiting shop...");
                        System.exit(0);
                    } else {
                        System.out.println("Enter your username: ");
                        setTempUserName(input.nextLine());
                        System.out.println("Enter your password: ");
                        setTempPassword(input.nextLine());
                    }

                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getTempUserName() {
        return tempUserName;
    }
    public void setTempUserName(String tempUserName) {
        this.tempUserName = tempUserName;
    }
    public String getTempPassword() {
        return tempPassword;
    }
    public void setTempPassword(String tempPassword) {
        this.tempPassword = tempPassword;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }



}
