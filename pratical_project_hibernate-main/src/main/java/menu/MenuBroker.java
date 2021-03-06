package menu;

import model.Broker;
import persistence.RepositoryBroker;

import java.util.List;
import java.util.Scanner;

public class MenuBroker {

    RepositoryBroker repo = new RepositoryBroker();

    private int menuOption(Scanner input) {
        System.out.println("\n/***************************************************/");
        System.out.println("Select the submenu option: ");
        System.out.println("-------------------------\n");
        System.out.println();
        System.out.println("1: Save new broker");
        System.out.println("2: List of all brokers");
        System.out.println("3: Delete broker");
        System.out.println("100 - Return to Main Menu");
        System.out.println("\n/***************************************************/");
        return input.nextInt();
    }

    protected void menuChoice(Scanner input) {
        int userChoice;
        do {
            userChoice = menuOption(input);
            switch (userChoice) {
                case 1:
                    menuSaveNewBroker(input, repo);
                    break;
                case 2:
                    menuAllBrokers(repo);
                    break;
                case 3:
                    menuDeleteBroker(input, repo);
                    break;
                default:
                    System.out.println("\nSorry, please enter valid Option");
                    menuOption(input);
                    break;
            }
        } while (userChoice != 100);
    }

    public boolean validateHasNotSpecialCharacters(String name) {

        String specialCharacters = " !#$%&'()*+,-./:;<=>?@[]^_`{|}~0123456789";

        String[] splitName = name.split("");

        for (String s : splitName) {
            if (specialCharacters.contains(s)) {
                return false;
            }
        }
        return true;
    }

    public boolean validateNameSize(String name) {
        return name.length() >= 3 && name.length() <= 20;
    }

    public void menuSaveNewBroker(Scanner scanner, RepositoryBroker repo) {

        String name = null;
        boolean validateName = false;

        while (!validateName) {
            System.out.println("Name cannot contain special character and should be 3 to 20 letters. ");
            System.out.print("Enter name: ");
            name = scanner.next();
            validateName = validateHasNotSpecialCharacters(name) && validateNameSize(name); // = true
        }

        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.next();
        while (!phoneNumber.matches("[0-9]+")) {
            System.out.println("Please don't use letters or special characters!");
            System.out.print("Enter phone number: ");
            phoneNumber = scanner.next();
        }

        System.out.print("Enter email: ");
        String email = scanner.next();
        while (!email.matches("^([\\w-.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$")) {
            System.out.println("Email address is not correct");
            System.out.print("Enter email");
            email = scanner.next();
        }

        Broker broker = new Broker();
        broker.setName(name);
        broker.setEmail(email);
        broker.setPhone(phoneNumber);
        repo.saveBroker(broker);
    }

    public void menuAllBrokers(RepositoryBroker repo) {
        List<Broker> allBrokersList = repo.brokerList();

        if (allBrokersList.isEmpty()) {
            System.out.println("Brokers list is empty");
        } else {
            System.out.println("Brokers list: ");
            for (Broker list : allBrokersList) {
                System.out.println(list.toString());
            }
        }
    }

    public void menuDeleteBroker(Scanner scanner, RepositoryBroker repo) {
        System.out.print("Enter broker id: ");
        int brokerId = scanner.nextInt();
        Broker broker = new Broker();
        broker.setBrokerId(brokerId);
        repo.deleteBroker(broker);
        System.out.println("Owner with id" + brokerId + " deleted");
    }

}
