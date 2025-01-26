import java.util.Scanner;

public class OrderBillingSystem {

    public static void main(String[] args) {
        
        String[] pizzaItems = {"Margherita", "Pepperoni", "Veggie"};
        double[] pizzaPrices = {8.99, 9.99, 7.99};

        String[] sidesItems = {"Garlic Bread", "Chicken Wings"};
        double[] sidesPrices = {4.99, 6.99};

        String[] dessertsItems = {"Chocolate Cake", "Ice Cream"};
        double[] dessertsPrices = {3.99, 2.99};

        String[] drinksItems = {"Soda", "Water"};
        double[] drinksPrices = {1.99, 0.99};

        final double TAX_RATE = getRealTimeTaxRate();

      
        String[] orderedItems = new String[100];
        int[] orderedQuantities = new int[100];
        int orderIndex = 0;

        Scanner scanner = new Scanner(System.in);
        String choice = ""; 

        System.out.println("Welcome to Pizza Hut!");
        System.out.println("Hello Customer!");
        System.out.println("What do you want to order?\n");

        do {
            System.out.println("The menu is:");
            System.out.println("Pizza\nSides\nDesserts\nDrinks");

            System.out.print("Choose a category (e.g., Pizza, Sides, Desserts, Drinks): ");
            String categoryChoice = scanner.nextLine().toLowerCase();

            int itemCount = 0;
            String[] categoryItems = null;
            double[] categoryPrices = null;

            switch (categoryChoice) {
                case "pizza":
                    categoryItems = pizzaItems;
                    categoryPrices = pizzaPrices;
                    itemCount = pizzaItems.length;
                    break;
                case "sides":
                    categoryItems = sidesItems;
                    categoryPrices = sidesPrices;
                    itemCount = sidesItems.length;
                    break;
                case "desserts":
                    categoryItems = dessertsItems;
                    categoryPrices = dessertsPrices;
                    itemCount = dessertsItems.length;
                    break;
                case "drinks":
                    categoryItems = drinksItems;
                    categoryPrices = drinksPrices;
                    itemCount = drinksItems.length;
                    break;
                default:
                    System.out.println("Category not found. Please try again.");
                    continue;
            }

            System.out.println("\nItems in " + categoryChoice + ":");
            for (int i = 0; i < itemCount; i++) {
                System.out.printf("  %s - $%.2f\n", categoryItems[i], categoryPrices[i]);
            }

            System.out.print("Enter the name of the item to order: ");
            String item = scanner.nextLine();

            int itemIndex = -1;
            for (int i = 0; i < itemCount; i++) {
                if (categoryItems[i].equalsIgnoreCase(item)) {
                    itemIndex = i;
                    break;
                }
            }

            if (itemIndex == -1) {
                System.out.println("Item not found in the selected category. Please try again.");
                continue;
            }

            System.out.print("Enter the quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); 

            orderedItems[orderIndex] = item;
            orderedQuantities[orderIndex] = quantity;
            orderIndex++;

            System.out.print("Would you like to order more? (yes/no): ");
            choice = scanner.nextLine().toLowerCase();
        } while (choice.equals("yes"));

       
        double subtotal = 0.0;
        System.out.println("\nYour Order:");
        for (int i = 0; i < orderIndex; i++) {
            String item = orderedItems[i];
            int quantity = orderedQuantities[i];
            double price = 0.0;

            
            for (int j = 0; j < pizzaItems.length; j++) {
                if (item.equalsIgnoreCase(pizzaItems[j])) {
                    price = pizzaPrices[j];
                    break;
                }
            }
            for (int j = 0; j < sidesItems.length; j++) {
                if (item.equalsIgnoreCase(sidesItems[j])) {
                    price = sidesPrices[j];
                    break;
                }
            }
            for (int j = 0; j < dessertsItems.length; j++) {
                if (item.equalsIgnoreCase(dessertsItems[j])) {
                    price = dessertsPrices[j];
                    break;
                }
            }
            for (int j = 0; j < drinksItems.length; j++) {
                if (item.equalsIgnoreCase(drinksItems[j])) {
                    price = drinksPrices[j];
                    break;
                }
            }

            double cost = price * quantity;
            subtotal += cost;
            System.out.printf("%s x%d - $%.2f\n", item, quantity, cost);
        }

        double tax = subtotal * TAX_RATE / 100;
        double total = subtotal + tax;

        System.out.printf("\nSubtotal: $%.2f\n", subtotal);
        System.out.printf("Tax (%.2f%%): $%.2f\n", TAX_RATE, tax);
        System.out.printf("Total: $%.2f\n", total);

        System.out.println("\nThank you for your order!");
        scanner.close();
    }

    
    private static double getRealTimeTaxRate() {
        
        return 8.25; 
    }
}
