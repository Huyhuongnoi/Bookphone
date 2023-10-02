import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Functions functions = new Functions();
        boolean run = true;
        while (run) {
            System.out.println("My Contacts!");
            System.out.print("Choose one of the following functions:\n1. Add Contact\n" +
                    "2. Edit Information\n3. Delete Contact\n4. View Contact\n5. Exit\nYour Choice: ");
            int choice = Main.scanner.nextInt();
            Main.scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String name = Main.scanner.nextLine();
                    System.out.print("Phone Number: ");
                    String phoneNumber = Main.scanner.next();
                    System.out.print("Note: ");
                    String note = Main.scanner.next();
                    functions.addNewContact(name, phoneNumber, note);
                    break;
                case 2:

                    System.out.print("What do you want to fix?\n1. Name\n2. Phone Number\n3. Note\nYour choice: ");
                    int choose = Main.scanner.nextInt();
                    Main.scanner.nextLine();
                    System.out.print("Enter the phone number to edit information: ");
                    String phone_Number = Main.scanner.next();
                    Main.scanner.nextLine();
                    System.out.print("Enter new information: ");
                    String newInformation = Main.scanner.nextLine();
                    functions.editInformation(choose, phone_Number, newInformation);
                    break;
                case 3:
                    System.out.print("Enter the phone number you want to delete: ");
                    String phoneNumberDelete = Main.scanner.next();
                    functions.deleteContact(phoneNumberDelete);
                    break;
                case 4:
                    System.out.println("\nMy Contacts!");
                    functions.viewContact();
                    break;
                case 5:
                    run = false;
                    break;
                default:
                    System.out.println("Function does not exist, please select again");
                    break;
            }

        }
    }
}
