package menu;

import model.Broker;
import model.Owner;
import model.Property;
import model.PropertyBrokerOwner;
import persistence.RepositoryProperty;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuProperty {

    RepositoryProperty repo = new RepositoryProperty();

    private int menuOptions(Scanner input) {
        System.out.println("\n/***************************************************/");
        System.out.println("Select the submenu option: ");
        System.out.println("-------------------------\n");
        System.out.println();
        System.out.println("1: Save new property");
        System.out.println("2: Delete property");
        System.out.println("3: Update property price");
        System.out.println("4: List of all available properties");
        System.out.println("5: List of properties for custom time period");
        System.out.println("6: List of properties for some year");
        System.out.println("7: List of properties for last month");
        System.out.println("8: List of properties for last week");
        System.out.println("9: Total properties");
        System.out.println("10: Print report: Property address, Broker name, Owner name");
        System.out.println("100 - Return to Main Menu");
        System.out.println("\n/***************************************************/");
        return input.nextInt();
    }

        protected void menuChoice(Scanner input) {

            int userChoice;
            do {
                userChoice = menuOptions(input);
                switch (userChoice) {
                    case 1:
                        menuSaveNewProperty(input, repo);
                        break;
                    case 2:
                        menuDeleteProperty(input, repo);
                        break;
                    case 3:
                        menuUpdatePropertyPrice(input);
                        break;
                    case 4:
                        menuListAllProperties(repo);
                        break;
                    case 5:
                        menuListOfPropertiesBetweenTwoDates(input, repo);
                        break;
                    case 6:
                        menuOneYearPropertyList(input, repo);
                        break;
                    case 7:
                        menuOneMonthPropertyList(repo);
                        break;
                    case 8:
                        menuOneWeekPropertyList(repo);
                        break;
                    case 9:
                        menuTotalProperties(repo);
                        break;
                    case 10:
                        menuPropertyListWithBrokerAndOwner(repo);
                    case 100:
                        MainMenu.getMainMenu();
                        break;
                    default:
                        System.out.println("\nSorry, please enter valid Option");
                        menuOptions(input);
                        break;
                }// End of switch statement
            } while (userChoice != 100);
        }

        public static void menuSaveNewProperty(Scanner scanner, RepositoryProperty repo) {
            System.out.print("Enter property type: ");
            String propertyType = scanner.next();
            System.out.print("Enter property address: ");
            String propertyAddress = scanner.next();
            System.out.print("Enter number of rooms: ");
            int numberOfRooms = scanner.nextInt();
            System.out.print("Enter floor number: ");
            int floorNumber = scanner.nextInt();
            System.out.print("Enter price: ");
            int price = scanner.nextInt();

            LocalDate dateOfRegister = LocalDate.now();
            Property property = new Property();
            property.setPropertyType(propertyType);
            property.setAddress(propertyAddress);
            property.setNumberOfRooms(numberOfRooms);
            property.setFloorNumber(floorNumber);
            property.setPrice(price);
            property.setDateOfRegister(dateOfRegister);

            Broker broker = new Broker();
            System.out.print("Enter broker id number: ");
            int brokerId = scanner.nextInt();
            broker.setBrokerId(brokerId);
            property.setBroker(broker);

            Owner owner = new Owner();
            System.out.print("Enter owner id number: ");
            int ownerId = scanner.nextInt();
            owner.setOwnerId(ownerId);
            property.setOwner(owner);

            //////////////////////////////////
            repo.saveProperty(property);
        }

        public static void menuDeleteProperty(Scanner scanner, RepositoryProperty repo) {
            System.out.println("Enter property id: ");
            int id = scanner.nextInt();
            Property property = new Property();
            property.setPropertyId(id);
            repo.deleteProperty(property);
        }

    public void menuUpdatePropertyPrice(Scanner scanner) {
        System.out.print("Enter property id: ");
        int propertyId = scanner.nextInt();
        Property property = repo.findPropertyById(propertyId);

        while (property == null) {
            System.out.println("Property id doesn't exist");
            System.out.print("Enter property id again please: ");
            propertyId = scanner.nextInt();
            property = repo.findPropertyById(propertyId);
        }
        System.out.print("Enter the property price: ");
        int newPrice = scanner.nextInt();
        repo.updatePropertyPrice(propertyId,newPrice);
        System.out.println("Price updated");
    }

    public void menuListAllProperties(RepositoryProperty repo) {
        List<Property> listProperty = repo.listAllProperties();

        if (listProperty.isEmpty()) {
            System.out.println("\n No available properties");
        } else {
            System.out.println("\nList of properties:");
            for (Property list : listProperty) {
                System.out.println(list.toString());
            }
        }
    }

    public void menuListOfPropertiesBetweenTwoDates(Scanner scanner, RepositoryProperty repo) {

        System.out.print("Enter start date (e.g. 2021-11-01): ");
        String date1 = scanner.next();
        LocalDate firstDate = LocalDate.parse(date1);

        System.out.print("Enter end date (e.g. 2021-11-01): ");
        String date2 = scanner.next();
        LocalDate secondDate = LocalDate.parse(date2);

        List<Property> listOfProperties = repo.betweenTwoDatesPropertyList(firstDate, secondDate);

        if (listOfProperties.isEmpty()) {
            System.out.println("\n No available properties");
        } else {
            System.out.println("\nList of properties:");
            for (Property list : listOfProperties) {
                System.out.println(list.toString());
            }
        }
    }

    public void menuOneYearPropertyList(Scanner scanner, RepositoryProperty repo) {

        System.out.print("Enter year (e.g. 2021): ");
        String year = scanner.next();
        int yearInt = Integer.parseInt(year);

        List<Property> listOfProperties = repo.someYearPropertyList(LocalDate.now().withYear(yearInt));

        if (listOfProperties.isEmpty()) {
            System.out.println("\n No available properties");
        } else {
            System.out.println("\nList of properties for" + " " + year + " : ");
        } for (Property list : listOfProperties) {
            System.out.println(list.toString());
        }
    }

    public void menuOneMonthPropertyList(RepositoryProperty repo) {

        List<Property> listOfProperties = repo.lastMonthPropertyList(LocalDate.now().withMonth(LocalDate.now().getMonthValue()-1));

        if (listOfProperties.isEmpty()) {
            System.out.println("\n No available properties");
        } else {
            System.out.println("\nList of properties for" + " " + /*month +*/ " : ");
        } for (Property list : listOfProperties) {
            System.out.println(list.toString());
        }
    }

    public void menuOneWeekPropertyList(RepositoryProperty repo) {

        LocalDate firstDate = LocalDate.now().minusDays(7);
        LocalDate secondDate = LocalDate.now();

        List<Property> listOfProperties = repo.betweenTwoDatesPropertyList(firstDate, secondDate);

        if (listOfProperties.isEmpty()) {
            System.out.println("\n No available properties");
        } else {
            System.out.println("\nList of properties for last week:");
            for (Property list : listOfProperties) {
                System.out.println(list.toString());
            }
        }
    }

    public void menuTotalProperties(RepositoryProperty repo) {

        repo.totalProperties();
    }

    public void menuPropertyListWithBrokerAndOwner(RepositoryProperty repo) {
        List<PropertyBrokerOwner> list = repo.listPropertyBrokerOwner();
        for(PropertyBrokerOwner property : list) {
            System.out.println(property.toString());
        }
    }


}


