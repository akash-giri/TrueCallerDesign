package org.example;

import java.util.Scanner;

import static org.example.TrueCallerFunctionality.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        App trueCallerApp = new App();
        trueCallerApp.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to TrueCaller!");
            System.out.println("1. Register");
            System.out.println("2. Add Contact");
            System.out.println("3. Import Contacts");
            System.out.println("4. Block Contact");
            System.out.println("5. Unblock Contact");
            System.out.println("6. Report Spam");
            System.out.println("7. Make a Call");
            System.out.println("8. Receive a Call");
            System.out.println("9. Upgrade to Premium");
            System.out.println("10. Search Contacts by Name");
            System.out.println("11. Search Contacts by Number");
            System.out.println("12. Add Business");
            System.out.println("13. Register with Global Directory");
            System.out.println("14. Search from Global Directory");
            System.out.println("15. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character.

            switch (choice) {
                case 1:
                    registerUser(scanner);
                    break;
                case 2:
                    addContact(scanner);
                    break;
                case 3:
                    importContacts(scanner);
                    break;
                case 4:
                    blockContact(scanner);
                    break;
                case 5:
                    unblockContact(scanner);
                    break;
                case 6:
                    reportSpam(scanner);
                    break;
                case 7:
                    makeCall(scanner);
                    break;
                case 8:
                    receiveCall(scanner);
                    break;
                case 9:
                    upgradeToPremium(scanner);
                    break;
                case 10:
                    searchContactsByName(scanner);
                    break;
                case 11:
                    searchContactsByNumber(scanner);
                    break;
                case 12:
                    addBusiness(scanner);
                    break;
                case 13:
                    registerWithGlobalDirectory(scanner);
                    break;
                case 14:
                    searchInGlobalDirectory(scanner);
                    break;
                case 15:
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
