import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<String> itemList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        char choice;

        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = getRegExString("[AaDdPpQq]");

            switch (Character.toUpperCase(choice)) {
                case 'A':
                    addItem();
                    break;
                case 'D':
                    deleteItem();
                    break;
                case 'P':
                    printList();
                    break;
                case 'Q':
                    if (getYNConfirm("Are you sure you want to quit? (Y/N): ")) {
                        System.out.println("Exiting the program. Goodbye!");
                        System.exit(0);
                    }
                    break;
            }
        } while (true);
    }

    private static void displayMenu() {
        System.out.println("Menu:");
        System.out.println("A - Add an item to the list");
        System.out.println("D - Delete an item from the list");
        System.out.println("P - Print the list");
        System.out.println("Q - Quit the program");
        displayList();
    }

    private static void displayList() {
        if (itemList.isEmpty()) {
            System.out.println("The list is empty.");
        } else {
            System.out.println("Current list:");
            for (int i = 0; i < itemList.size(); i++) {
                System.out.println((i + 1) + ". " + itemList.get(i));
            }
        }
    }

    private static void addItem() {
        System.out.print("Enter the item to add: ");
        String item = scanner.nextLine();
        itemList.add(item);
        System.out.println("Item added successfully!");
    }

    private static void deleteItem() {
        if (itemList.isEmpty()) {
            System.out.println("The list is empty. Nothing to delete.");
            return;
        }

        displayList();
        int itemNumber = getRangedInt("Enter the item number to delete: ", 1, itemList.size());
        itemList.remove(itemNumber - 1);
        System.out.println("Item deleted successfully!");
    }

    private static void printList() {
        displayList();
    }

    private static int getRangedInt(String prompt, int min, int max) {
        int value;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // consume the invalid input
            }
            value = scanner.nextInt();
            scanner.nextLine(); // consume the newline character
        } while (value < min || value > max);
        return value;
    }

    private static boolean getYNConfirm(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim().toUpperCase();
        } while (!input.equals("Y") && !input.equals("N"));
        return input.equals("Y");
    }

    private static char getRegExString(String regex) {
        char choice;
        do {
            choice = scanner.nextLine().toUpperCase().charAt(0);
        } while (!String.valueOf(choice).matches(regex));
        return choice;
    }
}
