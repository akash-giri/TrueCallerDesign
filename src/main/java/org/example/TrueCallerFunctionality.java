package org.example;

import java.util.*;

public class TrueCallerFunctionality {
    private final static Map<String, String> userDatabase = new HashMap<>();
    private final static Map<String, HashMap<Object, Object>> userContacts = new HashMap<>();
    private final static Map<String, ArrayList<Object>> userBlockedContacts = new HashMap<>();
    private final static Map<String, ArrayList<Object>> userSpamReports = new HashMap<>();
    private final static Map<String, Boolean> userPremiumStatus = new HashMap<>(); // Indicates premium plan status
    private final static Map<String, Map<String, String>> userBusinesses = new HashMap<>(); // Stores user-added businesses
    private final static Map<String, List<String>> globalDirectory = new HashMap<>(); // Stores registered users and their contacts

    static void registerUser(Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.next();

        System.out.print("Enter your phone number (e.g., +1XXXXXXXXXX): ");
        String phoneNumber = scanner.next();

        // Check if the phone number is already registered.
        if (userDatabase.containsKey(phoneNumber)) {
            System.out.println("This phone number is already registered.");
            return;
        }

        userDatabase.put(phoneNumber, name);
        userContacts.put(phoneNumber, new HashMap<>());
        System.out.println("Registration successful!");
    }
    static void addContact(Scanner scanner) {
        System.out.print("Enter your phone number (e.g., +1XXXXXXXXXX): ");
        String userPhoneNumber = scanner.next();

        if (!userDatabase.containsKey(userPhoneNumber)) {
            System.out.println("Phone number not registered. Please register first.");
            return;
        }

        System.out.print("Enter the contact's name: ");
        String contactName = scanner.next();

        System.out.print("Enter the contact's phone number (e.g., +1XXXXXXXXXX): ");
        String contactPhoneNumber = scanner.next();

        // Add the contact to the user's contacts.
        userContacts.get(userPhoneNumber).put(contactPhoneNumber, contactName);
        System.out.println("Contact added successfully!");
    }

    static void importContacts(Scanner scanner) {
        System.out.print("Enter your phone number (e.g., +1XXXXXXXXXX): ");
        String userPhoneNumber = scanner.next();

        if (!userDatabase.containsKey(userPhoneNumber)) {
            System.out.println("Phone number not registered. Please register first.");
            return;
        }

        List<String> hardcodedContacts = new ArrayList<>();
        hardcodedContacts.add("John Doe, +1234567890");
        hardcodedContacts.add("Jane Smith, +9876543210");
        hardcodedContacts.add("Alice Johnson, +5555555555");

        for (String contactInfo : hardcodedContacts) {
            String[] parts = contactInfo.split(",");
            if (parts.length == 2) {
                String contactName = parts[0].trim();
                String contactPhoneNumber = parts[1].trim();

                // Add the imported contact to the user's contacts.
                userContacts.get(userPhoneNumber).put(contactPhoneNumber, contactName);
                System.out.println("Contact '" + contactName + "' added successfully.");
            }
        }
    }
    static void blockContact(Scanner scanner) {
        System.out.print("Enter your phone number (e.g., +1XXXXXXXXXX): ");
        String userPhoneNumber = scanner.next();

        if (!userDatabase.containsKey(userPhoneNumber)) {
            System.out.println("Phone number not registered. Please register first.");
            return;
        }

        System.out.print("Enter the phone number of the contact you want to block (e.g., +1XXXXXXXXXX): ");
        String contactToBlock = scanner.next();

        // Block the contact by adding them to the user's blocked contacts list.
        if (!userBlockedContacts.containsKey(userPhoneNumber)) {
            userBlockedContacts.put(userPhoneNumber, new ArrayList<>());
        }

        userBlockedContacts.get(userPhoneNumber).add(contactToBlock);
        System.out.println("Contact '" + contactToBlock + "' has been blocked.");
    }


    static void reportSpam(Scanner scanner) {
        System.out.print("Enter your phone number (e.g., +1XXXXXXXXXX): ");
        String userPhoneNumber = scanner.next();

        if (!userDatabase.containsKey(userPhoneNumber)) {
            System.out.println("Phone number not registered. Please register first.");
            return;
        }

        System.out.print("Enter the phone number of the contact you want to report as spam (e.g., +1XXXXXXXXXX): ");
        String contactToReport = scanner.next();

        // Report the contact as spam by adding them to the user's spam reports list.
        if (!userSpamReports.containsKey(userPhoneNumber)) {
            userSpamReports.put(userPhoneNumber, new ArrayList<>());
        }

        userSpamReports.get(userPhoneNumber).add(contactToReport);
        System.out.println("Contact '" + contactToReport + "' has been reported as spam.");
    }

    static void unblockContact(Scanner scanner) {
        System.out.print("Enter your phone number (e.g., +1XXXXXXXXXX): ");
        String userPhoneNumber = scanner.next();

        if (!userDatabase.containsKey(userPhoneNumber)) {
            System.out.println("Phone number not registered. Please register first.");
            return;
        }

        if (!userBlockedContacts.containsKey(userPhoneNumber) || userBlockedContacts.get(userPhoneNumber).isEmpty()) {
            System.out.println("No contacts are currently blocked.");
            return;
        }

        System.out.println("Blocked Contacts:");
        for (int i = 0; i < userBlockedContacts.get(userPhoneNumber).size(); i++) {
            System.out.println((i + 1) + ". " + userBlockedContacts.get(userPhoneNumber).get(i));
        }

        System.out.print("Enter the number of the contact you want to unblock: ");
        int unblockChoice = scanner.nextInt();
        unblockChoice--; // Adjust for 0-based indexing.

        if (unblockChoice >= 0 && unblockChoice < userBlockedContacts.get(userPhoneNumber).size()) {
            String contactToUnblock = (String) userBlockedContacts.get(userPhoneNumber).get(unblockChoice);
            userBlockedContacts.get(userPhoneNumber).remove(unblockChoice);
            System.out.println("Contact '" + contactToUnblock + "' has been unblocked.");
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    static void makeCall(Scanner scanner) {
        System.out.print("Enter your phone number (e.g., +1XXXXXXXXXX): ");
        String userPhoneNumber = scanner.next();

        if (!userDatabase.containsKey(userPhoneNumber)) {
            System.out.println("Phone number not registered. Please register first.");
            return;
        }

        System.out.print("Enter the phone number you want to call (e.g., +1XXXXXXXXXX): ");
        String callNumber = scanner.next();

        // Check if the call is from a blocked or reported spam contact.
        boolean isBlocked = userBlockedContacts.containsKey(userPhoneNumber) &&
                userBlockedContacts.get(userPhoneNumber).contains(callNumber);
        boolean isSpam = userSpamReports.containsKey(userPhoneNumber) &&
                userSpamReports.get(userPhoneNumber).contains(callNumber);

        if (isBlocked) {
            System.out.println("This call is blocked.");
        } else if (isSpam) {
            System.out.println("This call is suspected to be spam.");
        } else {
            System.out.println("Call connected.");
        }
    }

    static void receiveCall(Scanner scanner) {
        System.out.print("Enter your phone number (e.g., +1XXXXXXXXXX): ");
        String userPhoneNumber = scanner.next();

        if (!userDatabase.containsKey(userPhoneNumber)) {
            System.out.println("Phone number not registered. Please register first.");
            return;
        }

        System.out.print("Enter the caller's phone number (e.g., +1XXXXXXXXXX): ");
        String callerNumber = scanner.next();

        if (userContacts.containsKey(userPhoneNumber) && userContacts.get(userPhoneNumber).containsKey(callerNumber)) {
            String callerName = (String) userContacts.get(userPhoneNumber).get(callerNumber);
            System.out.println("Call from: " + callerName + " (" + callerNumber + ")");
        } else {
            System.out.println("Call from: " + callerNumber);
        }
    }
    static void upgradeToPremium(Scanner scanner) {
        System.out.print("Enter your phone number (e.g., +1XXXXXXXXXX): ");
        String userPhoneNumber = scanner.next();

        if (!userDatabase.containsKey(userPhoneNumber)) {
            System.out.println("Phone number not registered. Please register first.");
            return;
        }

        if (userPremiumStatus.containsKey(userPhoneNumber) && userPremiumStatus.get(userPhoneNumber)) {
            System.out.println("You are already a premium user.");
        } else {
            // In a real application, this is where you would handle billing and subscription management.
            // For the sake of simplicity, we'll just mark the user as a premium user here.
            userPremiumStatus.put(userPhoneNumber, true);
            System.out.println("Congratulations! You are now a premium user.");
        }
    }
    static void searchContactsByName(Scanner scanner) {
        System.out.print("Enter your phone number (e.g., +1XXXXXXXXXX): ");
        String userPhoneNumber = scanner.next();

        if (!userDatabase.containsKey(userPhoneNumber)) {
            System.out.println("Phone number not registered. Please register first.");
            return;
        }

        System.out.print("Enter the name to search for: ");
        String searchName = scanner.next();

        if (userContacts.containsKey(userPhoneNumber)) {
            System.out.println("Matching Contacts for '" + searchName + "':");

            for (Map.Entry<Object, Object> entry : userContacts.get(userPhoneNumber).entrySet()) {
                if (entry.getValue().toString().toLowerCase().contains(searchName.toLowerCase())) {
                    System.out.println("Name: " + entry.getValue() + ", Phone: " + entry.getKey());
                }
            }
        }
    }
    static void searchContactsByNumber(Scanner scanner) {
        System.out.print("Enter your phone number (e.g., +1XXXXXXXXXX): ");
        String userPhoneNumber = scanner.next();

        if (!userDatabase.containsKey(userPhoneNumber)) {
            System.out.println("Phone number not registered. Please register first.");
            return;
        }

        System.out.print("Enter the phone number to search for: ");
        String searchNumber = scanner.next();

        if (userContacts.containsKey(userPhoneNumber)) {
            System.out.println("Matching Contacts for Number '" + searchNumber + "':");

            for (Map.Entry<Object, Object> entry : userContacts.get(userPhoneNumber).entrySet()) {
                if (entry.getKey().toString().contains(searchNumber)) {
                    System.out.println("Name: " + entry.getValue() + ", Phone: " + entry.getKey());
                }
            }
        }
    }
    static void addBusiness(Scanner scanner) {
        System.out.print("Enter your phone number (e.g., +1XXXXXXXXXX): ");
        String userPhoneNumber = scanner.next();

        if (!userDatabase.containsKey(userPhoneNumber)) {
            System.out.println("Phone number not registered. Please register first.");
            return;
        }

        System.out.print("Enter the business name: ");
        String businessName = scanner.next();

        System.out.print("Enter the business phone number (e.g., +1XXXXXXXXXX): ");
        String businessPhoneNumber = scanner.next();

        Map<String, String> businessMap = userBusinesses.getOrDefault(userPhoneNumber, new HashMap<>());
        businessMap.put(businessPhoneNumber, businessName);
        userBusinesses.put(userPhoneNumber, businessMap);

        System.out.println("Business added successfully.");
    }

    static void registerWithGlobalDirectory(Scanner scanner) {
        System.out.print("Enter your phone number (e.g., +1XXXXXXXXXX): ");
        String userPhoneNumber = scanner.next();

        if (!userDatabase.containsKey(userPhoneNumber)) {
            System.out.println("Phone number not registered. Please register first.");
            return;
        }

        List<String> userContactsList = new ArrayList<>();

        if (userContacts.containsKey(userPhoneNumber)) {
            for (Object contactPhoneNumber : userContacts.get(userPhoneNumber).keySet()) {
                userContactsList.add((String) contactPhoneNumber);
            }
        }

        userContactsList.add(userPhoneNumber); // Include the user's own number in the global directory.

        globalDirectory.put(userPhoneNumber, userContactsList);
        System.out.println("Registered with the Global Directory successfully.");
    }
    static void searchInGlobalDirectory(Scanner scanner) {
        System.out.print("Enter the phone number to search in the Global Directory: ");
        String searchNumber = scanner.next();

        if (globalDirectory.containsKey(searchNumber)) {
            System.out.println("Contacts registered with this number in the Global Directory:");

            for (String phoneNumber : globalDirectory.get(searchNumber)) {
                System.out.println(phoneNumber);
            }
        } else {
            System.out.println("Number not found in the Global Directory.");
        }
    }

}

