package menu;

import model.Owner;
import persistence.RepositoryOwner;

import java.util.List;
import java.util.Scanner;

public class MenuOwner {

    RepositoryOwner repo = new RepositoryOwner();

    private int menuOption(Scanner input) {
        System.out.println("\n/***************************************************/");
        System.out.println("Select the submenu option: ");
        System.out.println("-------------------------\n");
        System.out.println();
        System.out.println("1: Save new owner");
        System.out.println("2: Update phone number");
        System.out.println("3: List of all owners");
        System.out.println("4: Delete owner");
        System.out.println("5: xxx");
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
                    saveNewOwner(input, repo);
                    break;
                case 2:
                    menuUpdateOwnerPhoneNumber(input);
                    break;
                case 3:
                    menuAllOwners(repo);
                    break;
                case 4:
                    menuDeleteOwner(input, repo);
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

        for (String s: splitName) {
            if (specialCharacters.contains(s)) {
                return false;
            }
        }
        return true;
    }

    public boolean validateNameSize(String name) {
        if (name.length() >= 3 && name.length() <= 20) {
            return true;
        }
        return false;
    }

    public void saveNewOwner(Scanner scanner, RepositoryOwner repo) {

        String name = null;
        boolean validateName = false;

        while (!validateName) {
                System.out.println("Name cannot contain special character and should be 3 to 20 letters. ");
                System.out.print("Enter name: ");
                name = scanner.next();
                boolean result = false;
                if(validateHasNotSpecialCharacters(name) && validateNameSize(name)) {
                    result = true;
                }
                validateName = result;
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

        Owner owner = new Owner();
        owner.setName(name);
        owner.setEmail(email);
        owner.setPhone(phoneNumber);
        repo.saveOwner(owner);
    }

    public void menuUpdateOwnerPhoneNumber(Scanner scanner) {
        System.out.print("Enter owner ID: ");
        int ownerId = scanner.nextInt();
        Owner owner = repo.findOwnerById(ownerId);

        while (owner == null) {
            System.out.println("Owner is not registered in the system!");
            System.out.print("Enter owner ID: ");
            ownerId = scanner.nextInt();
            owner = repo.findOwnerById(ownerId);
        }
            System.out.print("Enter owner new phone number: ");
            String newPhoneNumber = scanner.next();
            repo.updateOwnerPhoneNumber(ownerId, newPhoneNumber);
    }

    public void menuAllOwners(RepositoryOwner repo) {
        List<Owner> allOwnersList = repo.ownerList();

        if (allOwnersList.isEmpty()) {
            System.out.println("Owners list is empty");
        } else {
            System.out.println("Owners list: ");
            for (Owner list : allOwnersList) {
                System.out.println(list.toString());
            }
        }
    }

    public void menuDeleteOwner(Scanner scanner, RepositoryOwner repo) {
        System.out.print("Enter owner id: ");
        int ownerId = scanner.nextInt();
        Owner owner = new Owner();
        owner.setOwnerId(ownerId);
        repo.deleteOwner(owner);
        System.out.println("Owner with id " + ownerId + " deleted");
    }

}

