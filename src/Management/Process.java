/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import static Library.PasswordHelper.getSHA;
import static Library.PasswordHelper.toHexString;
import Model.User;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author NHT
 */
public class Process {

    private ArrayList<User> users;

    public Process() {
        this.users = new ArrayList<>();
        this.loadFile();
    }

    public User getUser(String userName) {
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }
        return null;
    }

    public boolean checkContinue() {
        String chose = "";
        Scanner sc = new Scanner(System.in);
        System.out.print("Do you want continue(y/n)");
        chose = sc.nextLine();
        if ("y".equals(chose)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkDelete() {
        String chose = "";
        Scanner sc = new Scanner(System.in);
        System.out.print("Do you want delete (y/n)");
        chose = sc.nextLine();
        if ("y".equals(chose)) {
            return true;
        } else {
            return false;
        }
    }

    public void addUser() {
        Scanner sc = new Scanner(System.in);
        do {
            String userName;
            User user = null;
            do {
                user = null;
                System.out.print("Enter Username(-1 to Exit):");
                userName = sc.nextLine();
                if ("-1".equals(userName)) {
                    return;
                }
                user = getUser(userName);
                if (user != null) {
                    System.out.println("User does not exist");
                }
            } while (user != null);

            User newUser = new User();
            newUser.input(userName);
            users.add(newUser);
        } while (this.checkContinue());
    }

    public void checkExistUser() {
        Scanner sc = new Scanner(System.in);
        do {
            String userName;
            User user = null;

            user = null;
            System.out.print("Enter Username(-1 to Exit):");
            userName = sc.nextLine();
            if ("-1".equals(userName)) {
                return;
            }
            user = getUser(userName);
            if (user == null) {
                System.out.println("“No User Found!");
            } else {
                System.out.println("Exist User");
            }
        } while (this.checkContinue());
    }

    public void searchUser() {
        Scanner sc = new Scanner(System.in);
        String name;
        do {
            System.out.print("Enter name:");
            name = sc.nextLine();
            boolean kt = false;
            for (User user : users) {
                if (user.getFirstName().equals(name) || user.getLastName().equals(name)) {
                    kt = true;
                    user.output();
                }
            }

            if (!kt) {
                System.out.println("Have no any user");
            }
        } while (this.checkContinue());

    }

    public User login() {
        Scanner sc = new Scanner(System.in);
        String userName;
        String password;
        System.out.print("Enter username:");
        userName = sc.nextLine();
        System.out.print("Enter password:");
        password = sc.nextLine();
        password=toHexString(getSHA(password));
        
        for (User user : users) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {

                return user;
            }
        }

        return null;
    }

    public void updateUser() {
        do {
            User user = this.login();
            if (user == null) {
                System.out.println("Username does not exist");
            } else {
                user.update();
            }
        } while (this.checkContinue());
    }

    public void deleteUser() {
        do {
            User userLogin = this.login();
            if (userLogin == null) {
                System.out.println("Username does not exist");
            } else {
                for (User user : this.users) {
                    if (user.getUserName().equals(userLogin.getUserName())) {
                        if (this.checkDelete()) {
                            this.users.remove(user);
                            System.out.println("Succes");
                        }
                        return;
                    }
                }

            }
        } while (this.checkContinue());
    }

    public User getData(String str) {
        String[] words = str.split("\\s");
        return new User(words[0], words[1], words[2], words[3], words[4], words[5]);
    }

    public void loadFile() {
        try {
            File myObj = new File("D:\\Code\\Java\\NetBeansProjects\\J1.L.P0002.-User_Management_400LOC\\src\\DB\\user.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                //System.out.println(data);
                users.add(getData(data));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void saveFile() {
        try {
            try (FileWriter myWriter = new FileWriter("D:\\Code\\Java\\NetBeansProjects\\J1.L.P0002.-User_Management_400LOC\\src\\DB\\user.txt")) {
                for (User user : users) {
                    myWriter.write(user.getUserName() + " " + user.getFirstName() + " " + user.getLastName() + " "
                            + toHexString(getSHA(user.getPassword())) + " " + user.getEmail() + " " + user.getPhone() + "\n");
                }
            }
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void print() {
        if (users.isEmpty()) {
            System.out.println("List User Is Empty");
            return;
        }
        System.out.println("\t\t List User \n");
        System.out.printf("%-20s%-30s%-20s%-20s\n", "UserName", "Name", "Phone", "Email");
        for (User user : users) {
            user.output();
        }
        System.out.println("\n\t--------------------------------");
    }

}
