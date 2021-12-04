package menu;

import java.util.Scanner;

public class SubMenuOptions {
    boolean exit = false;
    private MenuCustomer menuCustomer;
    private MenuProperty menuProperty;
    private MenuOwner menuOwner;
    private MenuBroker menuBroker;

    public SubMenuOptions() {
        this.menuCustomer = new MenuCustomer();
        this.menuProperty = new MenuProperty();
        this.menuOwner = new MenuOwner();
        this.menuBroker = new MenuBroker();
    }

    private int menuOptions(Scanner input) {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("Main menu ");
        System.out.println("-------------------------------------------------------");
        System.out.println();
        System.out.println("1: Sub Menu - Property");
        System.out.println("2: Sub Menu - Broker");
        System.out.println("3: Sub Menu - Owner");
        System.out.println("4: Sub Menu - Customer");
        System.out.println("100 - Quit");
        System.out.println("***************************************************");

        System.out.println("Type the sub menu option: ");
        return input.nextInt();
    }

    public void menuChoice(Scanner input) {
        while(!exit) {
            int userChoice = menuOptions(input);
            switch (userChoice) {
                case 1:
                    this.menuProperty.menuChoice(input);
                    break;
                case 2:
                    this.menuBroker.menuChoice(input);
                    break;
                case 3:
                    this.menuOwner.menuChoice(input);
                    break;
                case 4:
                    this.menuCustomer.menuChoice(input);
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:

                    break;
                case 100:
                    exit = true;
                    System.out.println("System closed");
                    break;
//                default:
//                    System.out.println("\nSorry, please enter valid Option");
//                    menuChoice(input);
            }
        }
    }
}
