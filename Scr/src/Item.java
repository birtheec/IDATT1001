/**
 * Software for Smarthus AS (Warehouse management system).
 * The item class holds the information about a single item.
 *
 * @author 10016
 * @version 17.11.22
 */
public class Item {
  private final String itemNumber;
  private String description;
  private double price;
  private final String brand;
  private final double weight;
  private final double length;
  private final double height;
  private final String color;
  private int inStock;
  private final int category;

  /**
   * Constructor of the class Item.
   *
   * @param itemNumber both letters and numbers (String)
   * @param description describes the item (String)
   * @param price in norwegian kroner, must be greater than zero (double)
   * @param brand describes the producer of the item (String)
   * @param weight in kilograms, must be greater than zero (double)
   * @param length in meters, must be greater than zero (double)
   * @param height in meters, must be greater than zero (double)
   * @param color describes the item by its color (String)
   * @param inStock describes the stock of the item in the warehouse,
   *      can't be less than zero (int)
   * @param category describes the category of the item: 1. laminate floor,
   *                2. window, 3. door or 4. lumber (int)
   * @throws IllegalArgumentException "You typed something invalid. The price, weight, length,"
   *               + " height and items in stock must be greater than zero."
   * @throws  IllegalArgumentException "You typed something invalid. "
   *           + "The items in stock must be greater than or equal to zero!"
   * @throws IllegalArgumentException "The category must be an integer between 1 and 4"
   */
  public Item(String itemNumber, String description, double price, String brand, double weight,
              double length, double height, String color, int inStock, int category) {
    if (price <= 0 || weight <= 0 || length <= 0 || height <= 0) {
      throw new IllegalArgumentException("You typed something invalid. The price, weight, length"
              + "  and height must be greater than zero.");
    }
    if (inStock < 0) {
      throw new IllegalArgumentException("You typed something invalid. "
          + "The items in stock must be greater than or equal to zero!");
    }
    if (category < 1 || category > 4) {
      throw new IllegalArgumentException("The category must be an integer between 1 and 4");
    }
    this.itemNumber = itemNumber;
    this.description = description;
    this.price = price;
    this.brand = brand;
    this.weight = weight;
    this.length = length;
    this.height = height;
    this.color = color;
    this.inStock = inStock;
    this.category = category;
  }

  /**
   * Copy constructor of the item class.
   *
   * @param item (Item)
   */
  public Item(Item item) {
    this.itemNumber = item.getItemNumber();
    this.description = item.getDescription();
    this.price = item.getPrice();
    this.brand = item.getBrand();
    this.weight = item.getWeight();
    this.length = item.getLength();
    this.height = item.getHeight();
    this.color = item.getColor();
    this.inStock = item.getInStock();
    this.category = item.getCategoryInt();
  }

  /**
   * Accessing the item number.
   *
   * @return itemNumber (String)
   */
  public String getItemNumber() {
    return itemNumber;
  }

  /**
   * Accessing the description of the item.
   *
   * @return description (String)
   */
  public String getDescription() {
    return description;
  }

  /**
   * Accessing the price of the item in norwegian kroner(kr).
   *
   * @return price (double)
   */
  public double getPrice() {
    return price;
  }

  /**
   * Accessing the brand of the item.
   *
   * @return brand (String)
   */
  public String getBrand() {
    return brand;
  }

  /**
   * Accessing the weight of the item in kilograms(kg).
   *
   * @return weight (double)
   */
  public double getWeight() {
    return weight;
  }

  /**
   * Accessing the length of the item in meters(m).
   *
   * @return length (double)
   */
  public double getLength() {
    return length;
  }

  /**
   * Accessing the height of the item in meters(m).
   *
   * @return height (double)
   */
  public double getHeight() {
    return height;
  }

  /**
   * Accessing the color of the item.
   *
   * @return color (String)
   */
  public String getColor() {
    return color;
  }

  /**
   * Accessing the number of an item in stock.
   *
   * @return inStock (int)
   */
  public int getInStock() {
    return inStock;
  }

  /**
   * Accessing the integer of the category of the item.
   *
   * @return category (int)
   */
  private int getCategoryInt() {
    return category;
  }

  /**
   * Accessing the category of the item.
   * Converting the category as an int to a String, by using the CategoryEnum method:
   *  1 = Floor
   *  2 = Window
   *  3 = Door
   *  4 = Lumber
   *
   * @return  category (String)
   */
  public Enum<Category> getCategory() {
    return switch (category) {
      case 1 -> Category.FLOOR;
      case 2 -> Category.WINDOW;
      case 3 -> Category.DOOR;
      case 4 -> Category.LUMBER;
      default -> null;
    };
  }

  /**
   * Changes the description of the item, by setting a new one.
   *
   * @param newDescription (String)
   */

  public void setDescription(String newDescription) {
    this.description = newDescription.trim();
  }

  /**
   * Changes the price of the item.
   *  An illegal argument exception is thrown if new price
   *  is set to be equal to or below zero.
   *
   * @param newPrice (double)
   * @throws IllegalArgumentException "You typed something invalid."
   *           + " The price must be greater than zero!"
   * @retrun new price (double)
   */
  public void setPrice(double newPrice) {
    if (newPrice <= 0) {
      throw new IllegalArgumentException("You typed something invalid."
          + " The price must be greater than zero!");
    }
    this.price = newPrice;
  }

  /**
   * Changes the number of items in stock.
   *  An illegal argument exception is thrown if new number of items in stock
   *  is set to be below zero.
   *
   * @param newInStock (int)
   * @throws IllegalArgumentException "You typed something invalid."
   *           + " The number of items in stock must be greater than or equal to zero!"
   */
  public void setInStock(int newInStock) {
    if (newInStock < 0) {
      throw new IllegalArgumentException("You typed something invalid."
          + " The number of items in stock must be greater than or equal to zero!");
    }
    this.inStock = newInStock;
  }

  /**
   * A prebuilt String, containing the information of the item.
   *
   * @return (String)
   */
  @Override
  public String toString() {
    return "\nItem number: " + itemNumber
            + ", Description: " + description
            + ", price: " + price + " kr"
            + ", brand: " + brand
            + ", weight: " + weight + " kg"
            + ", length: " + length + " m"
            + ", height: " + height + " m"
            + ", color: " + color
            + ", number of items: " + inStock
            + ", category: " + getCategory();
  }
}


