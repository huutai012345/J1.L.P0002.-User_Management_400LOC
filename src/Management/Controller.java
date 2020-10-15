/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import java.util.Scanner;

/**
 *
 * @author NHT
 */
public class Controller {

    private Process process;

    public Controller(Process process) {
        this.process = process;
    }

    public void menu() {
        System.out.println("\t\t MENU \n");
        System.out.println("1.  Create User Account");
        System.out.println("2.  Check Exist User");
        System.out.println("3.  Search User Information By Name");
        System.out.println("4.  Update User");
        System.out.println("5.  Delete User");
        System.out.println("6.  Save File");
        System.out.println("7.  Print All");
        System.out.println("0.  Exit");
        System.out.print("Chose: ");
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        int luaChon = 0;
        while (true) {
            menu();
            luaChon = sc.nextInt();
            switch (luaChon) {
                case 1:
                    this.process.addUser();
                    break;
                case 2: {
                    this.process.checkExistUser();
                    break;
                }
                case 3:
                    this.process.searchUser();
                    break;
                case 4: {
                    this.process.updateUser();
                    break;
                }
                case 5: {
                    this.process.deleteUser();
                    break;
                }
                case 6: {
                    this.process.saveFile();
                    break;
                }
                case 7: {
                    this.process.print();
                    break;
                }
                case 0:
                    return;

                default:
                    System.out.println("Input invail");
                    break;
            }
        }
    }
}
