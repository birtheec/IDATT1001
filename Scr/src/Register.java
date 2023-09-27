import java.util.ArrayList;
import java.util.Comparator;

/**
 * The class Register creates the list of items,
 *  which holds the information about the individual items from the Item class.
 *
 * @author 10016
 * @version 10.12.22
 */
public class Register {
  private ArrayList<Item> items;


  /**
   * Constructor of the Register class.
   */
  public Register() {
    this.items = new ArrayList<>();
  }

  /**
   * Accessing the list of items, from the deep copy.
   *
   * @return (ArrayList<>)
   */
  public ArrayList<Item> getItems() {
    return deepCopyList(items);
  }

  /**
   * Printing all the registered items,
   * by iterating through the list,
   * adding the items and using the toString()-method for printing them as a list.
   *
   * @return (String)
   */
  public String getAllItems(ArrayList<Item> list) {
    String allItems = "";
    for (Item item : list) {
      allItems += item.toString();
    }
    return allItems;
  }

  /**
   * Sorting the items by their enum category.
   * Comparing the category of one item by another one,
   * for placing them in their respective place in the list.
   *
   * @return (ArrayList<>)
   */
  public ArrayList<Item> sortByCategory() {
    ArrayList<Item> itemCategory = items;
    Comparator<Item> sortByCategory = (Item item1, Item item2) -> item1.getCategory().compareTo(
        (Category) item2.getCategory());
    itemCategory.sort(sortByCategory);
    return deepCopyList(itemCategory);
  }

  /**
   * private help-method for making a deep copy of the list of items,
   * to be used in by the other methods.
   *
   * @param itemsToCopy (ArrayList<>)
   * @return (ArrayList<>)
   */
  private ArrayList<Item> deepCopyList(ArrayList<Item> itemsToCopy) {
    ArrayList<Item> copy = new ArrayList<>();
    for (Item item : itemsToCopy) {
      copy.add(new Item(item));
    }
    return copy;
  }

  /**
   * Finding an item by its item number.
   * Iterating through the items in the list of items,
   * and checking if a chosen item number is equal to one in the list,
   * when ignoring the letter casing.
   * Returning the item in question, if the item exist, else an exception is thrown.
   *
   * @param itemNumber (String)
   * @return (Item)
   * @throws IllegalArgumentException "Invalid input. You must type in an item number!"
   * @throws IllegalArgumentException "The item number does not exist in the warehouse!"
   */
  public Item findItemNumber(String itemNumber) {
    if (itemNumber.isBlank()) {
      throw new IllegalArgumentException("Invalid input. You must type in an item number!");
    }
    if (!itemExist(itemNumber)) {
      throw new IllegalArgumentException("The item number does not exist in the warehouse!");
    }
    for (Item item : items) {
      if (item.getItemNumber().equalsIgnoreCase(itemNumber)) {
        return new Item(item);
      }
    }
    return null;
  }

  /**
   * Finding an item by its description.
   * Creating an empty list for adding items that contains of words in their description,
   * that is equal to the description in question.
   * The items selected to fill the empty list by iteration.
   * If the description of the item is not found in the list, an exception is thrown.
   *
   * @param description (String)
   * @return (ArrayList<>)
   * @throws IllegalArgumentException "Invalid input. You must type in a description!"
   * @throws IllegalArgumentException "The description does not exist in the warehouse!"
   */
  public ArrayList<Item> findDescription(String description) {
    ArrayList<Item> descriptionList = new ArrayList<>();
    if (description.isBlank()) {
      throw new IllegalArgumentException("Invalid input. You must type a description!");
    }
    if (!descriptionExist(description)) {
      throw new IllegalArgumentException("The description does not exist in the warehouse!");
    }
    for (Item item : items) {
      if (item.getDescription().toLowerCase().contains(description.toLowerCase())) {
        descriptionList.add(item);
      }
    }
    return deepCopyList(descriptionList);
  }

  /**
   * Checking if the item already exists,
   * by iterating through the list of items and checking if the item number
   * is equal to the item number given in the parameter of the method,
   * when ignoring the letter casing.
   * The result is given as a boolean, true or false.
   *
   * @param item (Item)
   * @return (boolean)
   */
  private boolean itemExist(Item item) {
    for (Item i : items) {
      if (i.getItemNumber().equalsIgnoreCase(item.getItemNumber())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checking if the item number already exists,
   * by iterating through the list of items and checking if the item number
   * is equal to the item number given in the parameter. The iteration is checking
   * for the item number and ignoring the letter casing.
   * Returning the result as true or false.
   *
   * @param itemNumber (String)
   * @return (boolean)
   */
  private boolean itemExist(String itemNumber) {
    for (Item i : items) {
      if (i.getItemNumber().equalsIgnoreCase(itemNumber)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checking if the description of the items already exists,
   * by iterating through the list of items and checking for an item description
   * containing the description (parameter). Returning the result as true or false.
   *
   * @param description (String)
   * @return (boolean)
   */
  private boolean descriptionExist(String description) {
    for (Item i : items) {
      if (i.getDescription().toLowerCase().contains(description.toLowerCase())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Registering a new Item.
   *  If the item already exist, an exception is thrown.
   *  Else the new item, with its information, is added to the list of items.
   *
   * @param itemNumber (String)
   * @param description (String)
   * @param price (double)
   * @param brand (String)
   * @param weight (double)
   * @param length (double)
   * @param height (double)
   * @param color (String)
   * @param inStock (int)
   * @param category (int)
   * @retrun new Item (Item)
   * @throws IllegalArgumentException "Invalid input. You left an input empty!"
   * @throws IllegalArgumentException "The item is already registered!"
   */
  public Item registerNewItem(String itemNumber, String description, double price, String brand,
      double weight, double length, double height, String color,
      int inStock, int category) {
    Item newItem = new Item(itemNumber, description, price,
        brand, weight, length, height, color, inStock, category);
    if (itemNumber.isBlank() || description.isBlank() || brand.isBlank() || color.isBlank()) {
      throw new IllegalArgumentException("Invalid input. You left an input empty!");
    }
    if (itemExist(newItem)) {
      throw new IllegalArgumentException("The item is already registered!");
    }
    items.add(newItem);
    return new Item(newItem);
  }

  /**
   * Accessing the number of items in stock.
   *  Searching the registered list for the item number by iteration
   *  and ignoring the case of the lettering.
   *  Throws an exception if the item number does not exist.
   *
   * @param itemNumber (String)
   * @return (int)
   * @throws IllegalArgumentException "Invalid input. You must type in an item number!"
   * @throws IllegalArgumentException "The item number does not exist in the warehouse!"
   */
  public int totalInStock(String itemNumber) {
    if (itemNumber.isBlank()) {
      throw new IllegalArgumentException("Invalid input. You must type an item number!");
    }
    if (!itemExist(itemNumber)) {
      throw new IllegalArgumentException("The item number does not exist in the warehouse!");
    }
    for (Item item : items) {
      if (item.getItemNumber().equalsIgnoreCase(itemNumber)) {
        return item.getInStock();
      }
    }
    return 0;
  }

  /**
   * Increasing the number of the items in stock for a chosen item number,
   * with iteration and ignoring the case lettering.
   * The integer increase is added to the existing number of items in stock.
   * Throws an exception if the item number does not exist or if the parameter
   * increase is less than zero, to maintain the logic of the method.
   *
   * @param itemNumber (String)
   * @param increase (int)
   * @throws IllegalArgumentException "Invalid input. You must type in an item number!"
   * @throws IllegalArgumentException "The item number does not exist in the warehouse!"
   * @throws IllegalArgumentException "The number of items added has to be positive!"
   */
  public void increaseStock(String itemNumber, int increase) {
    if (itemNumber.isBlank()) {
      throw new IllegalArgumentException("Invalid input. You must type in an item number!");
    }
    if (!itemExist(itemNumber)) {
      throw new IllegalArgumentException("The item number does not exist in the warehouse!");
    }
    if (increase < 0) {
      throw new IllegalArgumentException("The number of items added has to be positive!");
    }
    for (Item item : items) {
      if (item.getItemNumber().equalsIgnoreCase(itemNumber)) {
        item.setInStock(item.getInStock() + increase);
      }
    }
  }

  /**
   * Decreasing the number of items in stock for a chosen item number,
   * with iteration and ignoring the case lettering.
   * The integer decrease is subtracted from the existing number of items in stock.
   * Throws an exception if the item number does not exist or if the parameter
   * decrease is a negative integer, to maintain the logic of the method.
   *
   * @param itemNumber (String)
   * @param decrease (int)
   * @throws IllegalArgumentException "Invalid input. You must type in an item number!"
   * @throws IllegalArgumentException "The item number does not exist in the warehouse!"
   * @throws IllegalArgumentException "The number of items subtracted has to be positive!"
   */
  public void decreaseStock(String itemNumber, int decrease) {
    if (itemNumber.isBlank()) {
      throw new IllegalArgumentException("Invalid input. You must type an item number!");
    }
    if (!itemExist(itemNumber)) {
      throw new IllegalArgumentException("The item number does not exist in the warehouse!");
    }
    if (decrease < 0) {
      throw new IllegalArgumentException("The number of items subtracted has to be positive!");
    }
    for (Item item : items) {
      if (item.getItemNumber().equalsIgnoreCase(itemNumber)) {
        item.setInStock(item.getInStock() - decrease);
      }
    }
  }

  /**
   * Calculating the discounted price of an item by a percentage.
   * The percentage is dived by 100 to get the percentage factor,
   * and then multiplied by the price of the accessed from the Item class.
   * The percentage must be a double bounded by 0 and 100,
   * for setting a new price greater than zero.
   * Throws an exception if the item number does not exist.
   *
   * @param percentage (String, double)
   * @throws IllegalArgumentException "Invalid input. You must type in an item number!"
   * @throws IllegalArgumentException "The item number does not exist in the warehouse!"
   * @throws IllegalArgumentException "The discount percentage must be"
   *           + " a decimal greater than 0 and smaller than 100!"
   */
  public void discount(String itemNumber, double percentage) {
    if (itemNumber.isBlank()) {
      throw new IllegalArgumentException("Invalid input. You must type an item number!");
    }
    if (!itemExist(itemNumber)) {
      throw new IllegalArgumentException("The item number does not exist in the warehouse!");
    }
    if (percentage < 0 || percentage > 100) {
      throw new IllegalArgumentException("The discount percentage must be"
          + " a decimal greater than 0 and smaller than 100!");
    }
    for (Item item : items) {
      if (item.getItemNumber().equalsIgnoreCase(itemNumber)) {
        item.setPrice(item.getPrice() * (1 - percentage / 100));
      }
    }
  }

  /**
   * Changing the price of the item.
   *  Iterating through the list of registered items for the item number,
   *  while ignoring case lettering.
   *  Thereafter, setting a new positive price of the item in question.
   *  An exception is thrown if the item number does not exist.
   *
   * @param itemNumber (String, double)
   * @throws IllegalArgumentException "Invalid input. You must type in an item number!"
   * @throws IllegalArgumentException "The item number does not exist in the warehouse!"
   */
  public void setNewPrice(String itemNumber, double newPrice) {
    if (itemNumber.isBlank()) {
      throw new IllegalArgumentException("Invalid input. You must type an item number!");
    }
    if (!itemExist(itemNumber)) {
      throw new IllegalArgumentException("The item number does not exist in the warehouse!");
    }
    for (Item item : items) {
      if (item.getItemNumber().equalsIgnoreCase(itemNumber)) {
        item.setPrice(newPrice);
      }
    }
  }


  /**
   * Removing an item form the register by the item number.
   * The item is found by iteration of the list of registered items,
   * and ignoring the case lettering.
   * An exception is thrown if the item number is not found in the list.
   *
   * @param itemNumber (String)
   * @return (boolean)
   * @throws IllegalArgumentException "Invalid input. You must type in an item number!"
   * @throws IllegalArgumentException "The item number does not exist in the warehouse!"
   */
  public boolean removeItem(String itemNumber) {
    if (itemNumber.isBlank()) {
      throw new IllegalArgumentException("Invalid input. You must type an item number!");
    }
    if (!itemExist(itemNumber)) {
      throw new IllegalArgumentException("The item number does not exist in the warehouse!");
    }
    for (int i = 0; i < items.size(); i++) {
      if (items.get(i).getItemNumber().equalsIgnoreCase(itemNumber)) {
        items.remove(i);
        return true;
      }
    }
    return false;
  }

  /**
   * Changing the description of the item.
   * Iterating through the list of registered items for the item number,
   * while ignoring the case lettering.
   * Thereafter, setting the new description of the item.
   * If the item number does not exist, an exception is thrown.
   *
   * @param itemNumber (String, String)
   * @throws IllegalArgumentException "Invalid input. You must type in an item number!"
   * @throws IllegalArgumentException "The item number does not exist in the warehouse!"
   */
  public void setNewDescription(String itemNumber, String newDescription) {
    if (itemNumber.isBlank() || newDescription.isBlank()) {
      throw new IllegalArgumentException("Invalid input. You left an input empty!");
    }
    if (!itemExist(itemNumber)) {
      throw new IllegalArgumentException("The item number does not exist in the warehouse!");
    }
    for (Item item : items) {
      if (item.getItemNumber().equalsIgnoreCase(itemNumber)) {
        item.setDescription(newDescription);
      }
    }
  }
}


