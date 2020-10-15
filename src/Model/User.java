/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Library.PasswordHelper.getSHA;
import static Library.PasswordHelper.toHexString;
import java.util.Scanner;

/**
 *
 * @author NHT
 */
public class User {

    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String confirm;
    private String phone;
    private String email;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User() {
    }

    public User(String userName, String lastName , String firstName , String password, String phone, String email) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    public void input(String userName) {
        Scanner sc = new Scanner(System.in);
        String regexEmail = "^([\\w]*[\\w\\.]*(?!\\.)@gmail.com)";
        String regexPhone = "([0-9]{10})";

        this.userName = userName;
        do {
            System.out.print("Enter First Name:");
            this.firstName = sc.nextLine();
            if (this.firstName.isEmpty()) {
                System.out.println("Input invalid");
            }
        } while (this.firstName.isEmpty());

        do {
            System.out.print("Enter Last Name:");
            this.lastName = sc.nextLine();
            if (this.lastName.isEmpty()) {
                System.out.println("Input invalid");
            }
        } while (this.lastName.isEmpty());

        do {
            System.out.print("Enter Password:");
            this.password = sc.nextLine();
            if (this.password.length() < 6) {
                System.out.println("Input invalid");
            }
        } while (this.password.length() < 6);

        do {
            System.out.print("Enter Confirm Password:");
            this.confirm = sc.nextLine();
            if (!this.password.equals(this.confirm)) {
                System.out.print("Confirm password must equal password");
            }
        } while (!this.password.equals(this.confirm));

        do {
            System.out.print("Enter Phone:");
            this.phone = sc.nextLine();
            if (!this.phone.matches(regexPhone)) {
                System.out.println("Phone number must contain 10 numbers");
    
            }
        } while (!this.phone.matches(regexPhone));
        
        password=toHexString(getSHA(password));
         
        do {
            System.out.print("Enter Email:");
            this.email = sc.nextLine();
            if (!this.email.matches(regexEmail)) {
                System.out.println("Email not format");
   
            }
        } while (!this.email.matches(regexEmail));
    }

    public void update() {
        Scanner sc = new Scanner(System.in);
        String regexEmail = "^([\\w]*[\\w\\.]*(?!\\.)@gmail.com)";
        String regexPhone = "([0-9]{10})";
        String lastName, firstName, email, phone;

        System.out.print("Enter First Name:");
        firstName = sc.nextLine();
        if (!firstName.isEmpty()) {
            this.firstName = firstName;
        }

        System.out.print("Enter Last Name:");
        lastName = sc.nextLine();
        if (!lastName.isEmpty()) {
            this.lastName = lastName;
        }

        System.out.print("Enter Phone:");
        phone = sc.nextLine();
        if (!phone.isEmpty()) {
            this.phone = phone;
        }
        else if (phone.isEmpty()) {
          
        } else if (!phone.matches(regexPhone)) {
            System.out.println("Phone number must contain 10 numbers");
            return;
        }

        System.out.print("Enter Email:");
        email = sc.nextLine();
        if (!email.isEmpty()) {
            this.email = email;
        }
        else if (email.isEmpty()) {

        } else if (!email.matches(regexEmail)) {
            System.out.println("Email not format");
            return;
        }

        System.out.println("Succes");
    }

    public void output() {
        System.out.printf("%-20s%-30s%-20s%-20s\n", this.userName, this.lastName + " " + this.firstName,
                this.phone, this.email);
    }

}
