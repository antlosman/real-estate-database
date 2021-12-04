package menu;

import model.Property;
import model.PropertyBrokerOwner;
import persistence.RepositoryProperty;

import java.util.List;
import java.util.Scanner;

public class TestProject {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

//        Property property = new Property();
//        property.setPropertyType("Apartment");
//        property.setAddress("Niguliste 2, Tallinn");
//        property.setNumberOfRooms(3);
//        property.setFloorNumber(3);
//        property.setPrice(300000);

        RepositoryProperty repo = new RepositoryProperty();
//        repo.saveProperty(property);
//        repo.updatePropertyPrice(1,250000);
//        System.out.println(repo.findPropertyById(1).toString());

//        System.out.println(repo.betweenTwoDates(LocalDate.of(2021,11,1), LocalDate.of(2021,11,30)).toString());

//        System.out.println(repo.lastYearPropertiesList(LocalDate.now().withYear(2021)).toString());



        // last month property list test:

//        System.out.print("Enter month number (e.g. 11): ");
//        String month = scanner.next();
//        int lastMonth = Integer.parseInt(month);
//        LocalDate month = LocalDate.now().minusMonths(1);
//        int lastMonth = Integer.parseInt(month);

//        int lastMonth = LocalDate.now().getMonthValue()-1;

//        List<PropertyForSale> listOfProperties = repo.someYearPropertyList(LocalDate.now().withYear(yearInt));
//        List<PropertyForSale> listOfProperties = repo.lastMonthPropertyList(LocalDate.now().withMonth(lastMonth));
//        List<PropertyForSale> listOfProperties = repo.lastMonthPropertyList(LocalDate.now().withMonth(LocalDate.now().getMonthValue()-1));
//
//        if (listOfProperties.isEmpty()) {
//            System.out.println("\n No available properties");
//        } else {
//            System.out.println("\nList of properties for" + " " + /*month +*/ " : ");
//        } for (PropertyForSale list : listOfProperties) {
//            System.out.println(list.toString());
//        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // last month property list test:

//        List<PropertyForSale> listOfProperties = repo.lastMonthPropertyList(LocalDate.now().withMonth(LocalDate.now().getMonthValue()-1));
//
//        if (listOfProperties.isEmpty()) {
//            System.out.println("\n No available properties");
//        } else {
//            System.out.println("\nList of properties for" + " " + /*month +*/ " : ");
//        } for (PropertyForSale list : listOfProperties) {
//            System.out.println(list.toString());
//        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // last week property test:

//        LocalDate firstDate = LocalDate.now().minusDays(7);
//        LocalDate secondDate = LocalDate.now();
//
//        List<PropertyForSale> listOfProperties = repo.betweenTwoDatesPropertyList(firstDate, secondDate);
//
//        if (listOfProperties.isEmpty()) {
//            System.out.println("\n No available properties");
//        } else {
//            System.out.println("\nList of properties for last week:");
//            for (PropertyForSale list : listOfProperties) {
//                System.out.println(list.toString());
//            }
//        }

        // total properties test
//        System.out.println(repo.totalPropertiesOnSale().toString());
//        repo.totalPropertiesOnSale();

         List<PropertyBrokerOwner> list = repo.listPropertyBrokerOwner();
         for(PropertyBrokerOwner property : list) {
             System.out.println(property.toString());
         }

    }
}

// how to delete customer class
// add number of rooms