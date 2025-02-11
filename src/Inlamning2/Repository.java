package Inlamning2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Repository {

    private Properties p = new Properties();
    private String userNameInput;
    private String passwordInput;

    public Repository() {

        try{
            p.load(new FileInputStream("src/Inlamning2/Settings.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Properties getProperties() {
        return p;
    }

    public void showWelcomeScreen() {
        Scanner input = new Scanner(System.in);
        System.out.println("----------------------------------");
        System.out.println("-----Welcome to the Shoe Shop-----");
        System.out.println("----------------------------------\n");
        System.out.println("Enter your username: ");
        setUserNameInput(input.nextLine());
        System.out.println("Enter your password: ");
        setPasswordInput(input.nextLine());

    }

    public String getUserNameInput() {
        return userNameInput;
    }
    public String getPasswordInput() {
        return passwordInput;
    }
    public void setUserNameInput(String userNameInput) {
        this.userNameInput = userNameInput;
    }
    public void setPasswordInput(String passwordInput) {
        this.passwordInput = passwordInput;
    }
}
