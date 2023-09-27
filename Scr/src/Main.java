import java.util.Scanner;

/**
 * The class Main with user interface (UI), allows the person working
 * in the warehouse to access information about the items in stock.
 *
 * @author 10016
 * @version 24.11.22
 */

public class Main {
  private static Scanner scanner = new Scanner(System.in);
  private static Register itemRegister = new Register();

  /**
   * User interface as a menu, integer between 1 and 7 allowed as input.
   * 1 = print all registered items
   * 2 = search for a specific item
   * 3 = register a new item
   * 4 = change the stock of an item
   * 5 = change the price of an item
   * 6 = change the description of an item
   * 7 = quit the program
   *
   */
  public static void main(String[] args) {
    storage();
    while (true) {
      int choice1 = menu();
      switch (choice1) {
        case 1 -> printRegister();
        case 2 -> searchItem();
        case 3 -> registerNewItem();
        case 4 -> changeStock();
        case 5 -> changePrice();
        case 6 -> changeDescription();
        case 7 -> quit();
        default -> System.out.println("Type an integer between 1 and 6!");
      }
    }
  }

  /**
   * The default storage of items in stock.
   */
  public static void storage() {
    itemRegister.registerNewItem("AS4001",
        "Pergo Perstorp natural laminate flooring of high quality that withstands a lot of tear.",
        209, "Pergo", 1.2, 1.2, 0.19, "Morning Oak", 57, 1);
    itemRegister.registerNewItem("AF2154",
        "Harmonie inner door massive is a minimalistic door with simple and clean lines.", 1295,
        "Harmonie", 15, 0.72, 2.04, "White", 17, 3);
    itemRegister.registerNewItem("ST1101",
        "Nature window Top swing 2-layer Glass. Designed for Norwegian conditions.", 209, "Nature",
        23.15, 1.188, 1.088, "black", 20, 2);
    itemRegister.registerNewItem("AS4000",
        "BerryAlloc Grand Avenue high pressure laminate flooring."
            + " A strong flooring in a long and wide format. Lifetime guarantee.",
        439.9, "BerryAlloc", 2.4, 2.41, 0.241, "Via Monte", 5, 1);
    itemRegister.registerNewItem("DB5678",
        "12x58x4400 painted skirting board in pine. Clean with a damp cloth.", 169, "Rindalslist",
        1.12, 0.44, 0.012, "White", 33, 4);
  }

  /**
   * The interface menu as a String.
   * Takes the users input as an integer and returns it the main()-method,
   * deciding the what the application should execute.
   *
   * @return (int)
   */
  public static int menu() {
    try {
      System.out.println("""
          
          ***** Smarthus AS warehouse *****
          1. List all items
          2. Search for an item
          3. Register an item
          4. Change the stock
          5. Change the price of an item
          6. Change the description of an item
          7. Quit""");
      return Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException n) {
      System.out.println("Type an integer, not text!");
      return -1;
    }
  }

  /**
   * Printing all the registered items, sorted by their category.
   */
  public static void printRegister() {
    if (itemRegister.getItems().size() > 0) {
      System.out.println("Warehouse register sorted by category:"
          + itemRegister.getAllItems(itemRegister.sortByCategory()));
    } else {
      System.out.println("The warehouse is empty.");
    }
  }

  /**
   * The menu for searching for an item, 1 and 2 allowed as an input.
   * 1 = by item number
   * 2 = by description
   */
  public static void searchItem() {
    int choice2 = searchMenu();
    switch (choice2) {
      case 1 -> findByItemNumber();
      case 2 -> findByDescription();
      default -> System.out.println("Type 1 or 2!");
    }
  }

  /**
   * The search menu as a String.
   * Returns the users choice as an integer to the searchItem()-method.
   *
   * @return (int)
   */
  public static int searchMenu() {
    try {
      System.out.println("""
        Choose how you want to search for the item:
        1. By  the item number
        2. By the description of the item""");
      return Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException n) {
      System.out.println("Type an integer, not text!");
    }
    return 0;
  }

  /**
   * Finding the item by the unique item number as an input.
   */
  public static void findByItemNumber() {
    try {
      System.out.println("Type the item number: ");
      String itemNumber = scanner.nextLine();
      System.out.println("The item you searched for with the item number \"" + itemNumber
          + "\": " + itemRegister.findItemNumber(itemNumber));
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Finding the item by the description as input.
   * If several items contain words of the input String in their description,
   * a list of these items is created and printed.
   * Then it will be possible to select the item in question
   * by typing in the unique item number.
   */
  public static void findByDescription() {
    try {
      System.out.println("Type a keyword or the description of the item: ");
      String description = scanner.nextLine();
      System.out.println("Item(s) containing \"" + description + "\" in their description:"
          + itemRegister.findDescription(description));
      if (itemRegister.findDescription(description).size() > 1) {
        System.out.println("\nType the item number of the item in question: ");
        String itemNumber = scanner.nextLine();
        System.out.println("Item " + itemNumber + " containing \""
            + description + "\" in the description:" + itemRegister.findItemNumber(itemNumber));
      }
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Registering a new item to the warehouse,
   * by taking in input from the user as information about the item.
   * Adding the new item to the list of registered items.
   */
  public static void registerNewItem() {
    try {
      System.out.println("Type the item's item number:");
      final String itemNumber = scanner.nextLine();
      System.out.println("Type a description of the item:");
      final String description = scanner.nextLine();
      System.out.println("Type the price of the item (in kroner):");
      final double price = Double.parseDouble(scanner.nextLine());
      System.out.println("Type the brand of the item:");
      final String brand = scanner.nextLine();
      System.out.println("Type the weight of the item (in kilograms):");
      final double weight = Double.parseDouble(scanner.nextLine());
      System.out.println("Type the length of the item (in meters):");
      final double length = Double.parseDouble(scanner.nextLine());
      System.out.println("Type the height of the item (in meters):");
      final double height = Double.parseDouble(scanner.nextLine());
      System.out.println("Type the color of the item:");
      String color = scanner.nextLine();
      System.out.println("Type the number of item's in stock:");
      int inStock = Integer.parseInt(scanner.nextLine());
      System.out.println(""" 
          Type the category of the item:
          1. for floor
          2. for window
          3. for door
          4. for lumber""");
      int category = Integer.parseInt(scanner.nextLine());
      itemRegister.registerNewItem(itemNumber, description, price, brand, weight, length, height,
          color, inStock, category);
      System.out.println("UPDATE: Item \"" + itemNumber + "\" is added to the warehouse register.");
    } catch (NumberFormatException n) {
      System.out.println("You typed something invalid!");
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * The menu for changing the number of items in stock,
   * with 1, 2 and 3 as allowed input.
   * 1 = adding items
   * 2 = subtracting items
   * 3 = deleting an item from the warehouse
   */
  public static void changeStock() {
    int choice3 = stockMenu();
    switch (choice3) {
      case 1 -> addItems();
      case 2 -> subtractItems();
      case 3 -> eraseItem();
      default -> System.out.println("Type 1, 2 or 3!");
    }
  }

  /**
   * The change stock menu as a string.
   * Returns the choice as an integer to the changeStock()-method,
   * which decides if the stock of the item is getting increased (1), reduced (2) or removed (3).
   *
   * @return (int)
   */
  public static int stockMenu() {
    try {
      System.out.println("""
                  
                  What would you like to do?
                  1. Add items to the warehouse storage
                  2. Subtract items from the storage of the warehouse
                  3. Delete an item form the register""");
      return Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException n) {
      System.out.println("Invalid input. Type an integer, not text!");
    }
    return 0;
  }

  /**
   * Adding items to the stock,
   * by asking for the item number and the number of items to be added.
   */
  public static void addItems() {
    try {
      System.out.println("Type the item number:");
      String itemNumber = scanner.nextLine();
      System.out.println("\"" + itemNumber + "\" has: " + itemRegister.totalInStock(itemNumber)
          + " items in stock.\nType the number of items added to the storage:");
      int increase = Integer.parseInt(scanner.nextLine());
      itemRegister.increaseStock(itemNumber, increase);
      System.out.println("UPDATE: " + itemNumber + ": "
          + itemRegister.totalInStock(itemNumber) + " items in stock");
    }  catch (NumberFormatException n) {
      System.out.println("Invalid input. Type an integer, not text!");
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Subtracting items of the stock,
   * by asking for the item number and the number of items to be subtracted.
   */
  public static void subtractItems() {
    try {
      System.out.println("Type the item number:");
      String itemNumber = scanner.nextLine();
      System.out.println("\"" + itemNumber + "\" has: " + itemRegister.totalInStock(itemNumber)
          + " items in stock.\nType the number of items subtracted from the storage:");
      int decrease = Integer.parseInt(scanner.nextLine());
      itemRegister.decreaseStock(itemNumber, decrease);
      System.out.println("UPDATE: " + itemNumber + ": "
          + itemRegister.totalInStock(itemNumber) + " items in stock");
    }  catch (NumberFormatException n) {
      System.out.println("Invalid input. Type an integer, not text!");
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * removing an item from the warehouse storage,
   * by asking for its item number.
   */
  public static void eraseItem() {
    try {
      System.out.println("Type the item number:");
      String itemNumber = scanner.nextLine();
      itemRegister.removeItem(
          itemNumber);
      System.out.println("UPDATE: " + itemNumber + " is removed from the warehouse register.");
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * The menu for changing the price of an item, allowing 1 and 2 as input.
   * 1 = by a discount as percentage
   * 2 = by setting a new price
   */
  public static void changePrice() {
    int choice4 = priceMenu();
    switch (choice4) {
      case 1 -> discountPrice();
      case 2 -> decideNewPrice();
      default -> System.out.println("Type 1 or 2!");
    }
  }

  /**
   * The price change menu as a String.
   * Takes the users input as an integer and returns it to the changePrice()-method.
   *
   * @return (int)
   */
  public static int priceMenu() {
    try {
      System.out.println("""
                          
        How would you like to change the price of the item?
        1. By a discount
        2. Set new price""");
      return Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException n) {
      System.out.println("Invalid input. Type an integer, not text!");
    }
    return 0;
  }

  /**
   * Changing the price of an item,
   * by asking for the item number and the discount percentage as inputs.
   */
  public static void discountPrice() {
    try {
      System.out.println("Type item number: ");
      String itemNumber = scanner.nextLine();
      System.out.println("Current price of \"" + itemNumber + "\": "
          + itemRegister.findItemNumber(itemNumber).getPrice()
          + " kr.\nType the discount percentage: ");
      double percentage = Double.parseDouble(scanner.nextLine());
      itemRegister.discount(itemNumber, percentage);
      System.out.println("UPDATE: new price of \"" + itemNumber + "\": "
          + itemRegister.findItemNumber(itemNumber).getPrice()
          + " kr. (with " + percentage + "% discount)");
    } catch (NumberFormatException n) {
      System.out.println("Invalid input. Type a decimal number, not text!");
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Changing the price of an item,
   * by taking in the item number and the new price as inputs.
   */
  public static void decideNewPrice() {
    try {
      System.out.println("Type the item number of the item you"
          + " would like to change the price of: ");
      String itemNumber = scanner.nextLine();
      System.out.println("Current price of \"" + itemNumber + "\": "
          + itemRegister.findItemNumber(itemNumber).getPrice() + " kr.\n"
          + "Type the new price of \"" + itemNumber + "\" in norwegian kroner: ");
      double newPrice = Double.parseDouble(scanner.nextLine());
      itemRegister.setNewPrice(itemNumber, newPrice);
      System.out.println("UPDATE: new price of \"" + itemNumber + "\": "
          + itemRegister.findItemNumber(itemNumber).getPrice() + " kr.");
    } catch (NumberFormatException n) {
      System.out.println("Invalid input. Type a decimal number, not text!");
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Changing the description of an item,
   * with item number as an identity input,
   * and thereafter asking for the new description of the item.
   */
  public static void changeDescription() {
    try {
      System.out.println("Type item number: ");
      String itemNumber = scanner.nextLine();
      System.out.println("The registered description of " + itemNumber
          + " is: " + itemRegister.findItemNumber(itemNumber).getDescription());
      System.out.println("Type the new description: ");
      String newDescription = scanner.nextLine();
      itemRegister.setNewDescription(itemNumber, newDescription);
      System.out.println("UPDATE: new description of \"" + itemNumber
          + "\": " + itemRegister.findItemNumber(itemNumber).getDescription());
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Terminating the program, and exiting the menu.
   */
  public static void quit() {
    System.out.println("***** Thank you for using the software for Smarthus AS! Power off *****");
    System.exit(0);
  }
}
