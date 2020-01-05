package com.b07.controller.store;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.b07.model.database.helper.*;
import com.b07.controller.exceptions.*;
import com.b07.controller.inventory.*;
import com.b07.controller.users.*;

import java.io.BufferedReader;
import java.io.IOException;
// import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;


public class SalesApplication {
  /**
   * 
   * 
   * @param argv unused.
   * @throws SQLException
   */
  @Deprecated
  public static void main(String[] argv) throws SQLException {

    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    String input = "";
    Inventory inventory = null;

    Connection connection = DatabaseDriverExtender.connectOrCreateDataBase();
    if (connection == null) {
      System.out.print("NOOO");
    }
    try {
      while (true) {
        System.out.println("1. Enter -1 to initialize system.(If not initialized)\n"
            + "2. Enter 1 to go into admin mode.\n" + "3. Enter anything else to open the menu.\n"
            + "4. Enter 0 to exit the program.\n" + "What is your input?");
        try {
          input = bufferedReader.readLine();
        } catch (IOException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
        if (input.contentEquals("0")) {
          break;
        }
        if (input.contentEquals("-1")) {

          inventory = initializeDatabase(inventory, connection);

          System.out.println("Creating new admin");
          System.out.println("Enter your name:");
          input = bufferedReader.readLine();
          String name = input;

          System.out.println("Enter your age:");
          input = bufferedReader.readLine();
          int age = (int) Integer.valueOf(input);

          System.out.println("Enter your address:");
          input = bufferedReader.readLine();
          String address = input;

          System.out.println("Enter your password:");
          input = bufferedReader.readLine();
          String password = input;

          int userId = DatabaseInsertHelper.insertNewUser(name, age, address, password);

          DatabaseInsertHelper.insertUserRole(userId, 1);

          System.out.println("Your id is " + userId);

          System.out.println("Creating new employee");
          System.out.println("Enter your name:");
          input = bufferedReader.readLine();
          name = input;

          System.out.println("Enter your age:");
          input = bufferedReader.readLine();
          age = (int) Integer.valueOf(input);

          System.out.println("Enter your address:");
          input = bufferedReader.readLine();
          address = input;

          System.out.println("Enter your password:");
          input = bufferedReader.readLine();
          password = input;

          userId = DatabaseInsertHelper.insertNewUser(name, age, address, password);

          DatabaseInsertHelper.insertUserRole(userId, 2);

          System.out.println("Your id is " + userId);

        } else if (input.contentEquals("1")) {
          System.out.println("You are in admin mode. What is your id?");
          String password = "";
          Admin potentialAdmin = null;
          try {
            input = bufferedReader.readLine();
            int id = (int) Integer.valueOf(input);
            System.out.println("You are in admin mode. What is your password?");
            input = bufferedReader.readLine();
            password = input;
            potentialAdmin = new Admin(id, "Name", 21, "Address", false);
          } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
          if (potentialAdmin.authenticate(password, password)) {
            showAdminInterface(bufferedReader, potentialAdmin);

          } else {
            System.out.println("You are not an admin!");
            continue;
          }
        } else {
          while (true) {
            System.out.println("1 - Employee Login \n" + "2 - Customer Login\n" + "0 - Back to main screen.\n"
                + " Enter Selection:");
            try {
              input = bufferedReader.readLine();
            } catch (IOException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
            if (input.contentEquals("1")) {
  
              Employee potentialEmployee = authenticateEmployee(bufferedReader);
              if (authenticatePassword(bufferedReader, potentialEmployee)) {
                showEmployeeInterface(bufferedReader, potentialEmployee, inventory);
              } else {
                System.out.println("The session has been ended due to failure of authentication.");
                continue;
              }
            } else if (input.contentEquals("2")) {
  
              Customer potentialCustomer;
              try {
                potentialCustomer = authenticateCustomer(bufferedReader);
              } catch (ClassCastException e1) {
                // TODO Auto-generated catch block
                System.out.println("That was not a valid id!");
                continue;
              }
              while (!authenticatePassword(bufferedReader, potentialCustomer)) {
                System.out.println("That was not a valid password!");
                continue;
              }
              try {
                showCustomerInterface(bufferedReader, potentialCustomer);
              } catch (ConnectionFailedException | ItemNotFoundException
                  | DatabaseInsertException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
            } else if (input.contentEquals("0")) {
              break;
            }
          }
        }
      }

      System.out.println("Thank you for using my program!.");

      // TODO Check what is in argv
      // If it is -1
      /*
       * TODO This is for the first run only! Add this code:
       * DatabaseDriverExtender.initialize(connection); Then add code to create your first account,
       * an administrator with a password Once this is done, create an employee account as well.
       * 
       */
      // If it is 1
      /*
       * TODO In admin mode, the user must first login with a valid admin account This will allow
       * the user to promote employees to admins. Currently, this is all an admin can do.
       */
      // If anything else - including nothing
      /*
       * TODO Create a context menu, where the user is prompted with: 1 - Employee Login 2 -
       * Customer Login 0 - Exit Enter Selection:
       */
      // If the user entered 1
      /*
       * TODO Create a context menu for the Employee interface Prompt the employee for their id and
       * password Attempt to authenticate them. If the Id is not that of an employee or password is
       * incorrect, end the session If the Id is an employee, and the password is correct, create an
       * EmployeeInterface object then give them the following options: 1. authenticate new employee
       * 2. Make new User 3. Make new account 4. Make new Employee 5. Restock Inventory 6. Exit
       * 
       * Continue to loop through as appropriate, ending once you get an exit code (9)
       */
      // If the user entered 2
      /*
       * TODO create a context menu for the customer Shopping cart Prompt the customer for their id
       * and password Attempt to authenticate them If the authentication fails or they are not a
       * customer, repeat If they get authenticated and are a customer, give them this menu: 1. List
       * current items in cart 2. Add a quantity of an item to the cart 3. Check total price of
       * items in the cart 4. Remove a quantity of an item from the cart 5. check out 6. Exit
       * 
       * When checking out, be sure to display the customers total, and ask them if they wish to
       * continue shopping for a new order
       * 
       * For each of these, loop through and continue prompting for the information needed Continue
       * showing the context menu, until the user gives a 6 as input.
       */
      // If the user entered 0
      /*
       * TODO Exit condition
       */
      // If the user entered anything else:
      /*
       * TODO Re-prompt the user
       */

    } catch (OutOfFormatException e) {
      // TODO Improve this!
      e.printStackTrace();
      System.out.println("Something went wrong :(");
    } catch (NotInDatabaseException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (Exception e) {
        System.out.println("Looks like it was closed already :)");
      }
    }

  }

  private static Inventory initializeDatabase(Inventory inventory, Connection connection)
      throws OutOfFormatException {
    try {
      DatabaseDriverExtender.initialize(connection);
    } catch (ConnectionFailedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    // Adding all roleIds
    DatabaseInsertHelper.insertRole("ADMIN");
    DatabaseInsertHelper.insertRole("EMPLOYEE");
    DatabaseInsertHelper.insertRole("CUSTOMER");

    // Set the items table
    Item HOCKEY_STICK = new ItemImpl(1,"HOCKEY_STICK", new BigDecimal("100"));
    Item FISHING_ROD = new ItemImpl(2, "FISHING_ROD", new BigDecimal("100"));
    Item SKATES = new ItemImpl(3, "SKATES", new BigDecimal("100"));
    Item RUNNING_SHOES = new ItemImpl(4,"RUNNING_SHOES", new BigDecimal("100"));
    Item PROTEIN_BAR = new ItemImpl(5, "PROTEIN_BAR", new BigDecimal("100"));
    DatabaseInsertHelper.insertItem("HOCKEY_STICK", new BigDecimal("100"));
    DatabaseInsertHelper.insertItem("FISHING_ROD", new BigDecimal("100"));
    DatabaseInsertHelper.insertItem("SKATES", new BigDecimal("100"));
    DatabaseInsertHelper.insertItem("RUNNING_SHOES", new BigDecimal("100"));
    DatabaseInsertHelper.insertItem("PROTEIN_BAR", new BigDecimal("100"));


    // Set the inventory
    HashMap<Item, Integer> itemMap = new HashMap<Item, Integer>();
    itemMap.put(HOCKEY_STICK, 10);
    itemMap.put(PROTEIN_BAR, 10);
    itemMap.put(RUNNING_SHOES, 10);
    itemMap.put(SKATES, 10);
    itemMap.put(FISHING_ROD, 10);
    inventory = new InventoryImpl(itemMap, 5);
    DatabaseInsertHelper.insertInventory(1, 100);
    DatabaseInsertHelper.insertInventory(2, 100);
    DatabaseInsertHelper.insertInventory(3, 100);
    DatabaseInsertHelper.insertInventory(4, 100);
    DatabaseInsertHelper.insertInventory(5, 100);

    return inventory;
  }


  private static Employee authenticateEmployee(BufferedReader bufferedReader) {
    System.out.println("Type in your id");
    int id = -1;
    Employee potentialEmployee;
    try {
      id = Integer.valueOf(bufferedReader.readLine());
    } catch (NumberFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }


    potentialEmployee = (Employee) DatabaseSelectHelper.getUserDetails(id);


    return potentialEmployee;
  }

  private static Customer authenticateCustomer(BufferedReader bufferedReader)
      throws NotInDatabaseException {
    System.out.println("Type in your id");
    int id = -1;
    Customer potentialCustomer;
    try {
      String input = bufferedReader.readLine();
      id = Integer.valueOf(input);
      potentialCustomer = (Customer) DatabaseSelectHelper.getUserDetails(id);
      return potentialCustomer;

    } catch (NumberFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    throw new NotInDatabaseException();
  }

  private static boolean authenticatePassword(BufferedReader bufferedReader, User potentialUser) {
    System.out.println("Type in your password");
    String password = "";
    try {
      password = bufferedReader.readLine();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }


    try {
      return potentialUser.authenticate(password, password);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return false;
    }
  }

  private static void showAdminInterface(BufferedReader bufferedReader, Admin potentialAdmin)
      throws IOException {
    String input = "";
    System.out.println("You are an admin!\n" + "1. Enter 1 to promote an employee\n"
        + "2. Enter 2 to view all sales book.\n"
        + "3. Enter 3 to view all active accounts of a user.\n"
        + "4. Enter 4 to view all inactive accounts of a user.\n"
        + "5. Enter 5 to exit admin mode.");
    input = bufferedReader.readLine();
    if (input.equals("1")) {
      System.out.println("Enter the Employee's ID who needs to be promoted.");
      input = bufferedReader.readLine();

      int employeeId = Integer.valueOf(input);
      potentialAdmin.promoteEmployee(employeeId);

      System.out.println("The user with id " + employeeId + " is now an admin.");
    } else if (input.equals("2")) {
      DatabaseSelectHelper.viewBooks();
      showAdminInterface(bufferedReader, potentialAdmin);
    } else if (input.equals("3")) {
      System.out.println("Enter the Customer's ID whose active accounts you need to see.");
      input = bufferedReader.readLine();

      int accId = DatabaseSelectHelper.getAccountId(Integer.valueOf(input));

      if (accId > 0) {
        accId = DatabaseSelectHelper.getUserActiveAccounts(Integer.valueOf(input));
        if (accId > 0) {
          System.out.println("ACCOUNTID : " + accId + "\n");
        } else {
          System.out.println("Customer has no active accounts.");
        }
      } else {
        System.out.println("Enter a valid ID instead!");
      }
      showAdminInterface(bufferedReader, potentialAdmin);
    } else if (input.equals("4")) {
      System.out.println("Enter the Customer's ID whose inactive accounts you need to see.");
      input = bufferedReader.readLine();

      int accId = DatabaseSelectHelper.getAccountId(Integer.valueOf(input));

      if (accId > 0) {
        List<Integer> inactiveAccIds =
            DatabaseSelectHelper.getUserInactiveAccounts(Integer.valueOf(input));

        for (Integer id : inactiveAccIds) {
          if (id > 0) {
            System.out.println("ACCOUNTID : " + id + "\n");
          }
        }
      } else {
        System.out.println("Enter a valid ID instead!");
      }
      showAdminInterface(bufferedReader, potentialAdmin);
    }else if (input.equals("5")) {
      return;
    }
  }

  private static void showEmployeeInterface(BufferedReader bufferedReader, Employee employee,
      Inventory inventory) {
    EmployeeInterface employeeInt = new EmployeeInterfaceImpl(employee, inventory);
    String input = "";

    while (true) {
      System.out.println(
          "1. authenticate new employee \n" + "2. Make new customer\n" + "3. Make new account\n"
              + "4. Make new Employee\n" + "5. Restock Inventory \n" + "6. Exit");
      try {
        input = bufferedReader.readLine();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      if (input.equals("1")) {
        Employee potentialEmployee = authenticateEmployee(bufferedReader);
        if (authenticatePassword(bufferedReader, potentialEmployee)) {
          continue;
        } else {
          System.out.println("The session has been ended due to failure of authentication.");
          continue;
        }
      } else if (input.equals("2")) {
        String name;
        int age;
        String address;
        String password;

        System.out.println("Enter your name:");
        int id = -1;
        try {
          name = bufferedReader.readLine();

          System.out.println("Enter your age:");
          age = Integer.valueOf(bufferedReader.readLine());

          System.out.println("Enter your address:");
          address = bufferedReader.readLine();

          System.out.println("Enter your password:");
          password = bufferedReader.readLine();

          id = DatabaseInsertHelper.insertNewUser(name, age, address, password);
          DatabaseInsertHelper.insertUserRole(id, 3);

        } catch (NumberFormatException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (OutOfFormatException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }

        System.out.println("Your id is " + id);
      } else if (input.equals("3")) {

        int userId = -1;

        System.out.println("Enter the customer's user ID that you want to create an account for:");
        try {
          userId = Integer.valueOf(bufferedReader.readLine());

          int accId = DatabaseInsertHelper.insertAccountHelper(userId, true);
          DatabaseInsertHelper.insertAccountLineHelper(accId, 1, 0);
          DatabaseInsertHelper.insertAccountLineHelper(accId, 2, 0);
          DatabaseInsertHelper.insertAccountLineHelper(accId, 3, 0);
          DatabaseInsertHelper.insertAccountLineHelper(accId, 4, 0);
          DatabaseInsertHelper.insertAccountLineHelper(accId, 5, 0);


        } catch (NumberFormatException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (OutOfFormatException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (DatabaseInsertException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        System.out.println("Account created successfully");
      } else if (input.equals("4")) {
        String name;
        int age;
        String address;
        String password;

        System.out.println("Enter your name:");
        try {
          name = bufferedReader.readLine();

          System.out.println("Enter your age:");
          age = Integer.valueOf(bufferedReader.readLine());

          System.out.println("Enter your address:");
          address = bufferedReader.readLine();

          System.out.println("Enter your password:");
          password = bufferedReader.readLine();

          int id = DatabaseInsertHelper.insertNewUser(name, age, address, password);
          DatabaseInsertHelper.insertUserRole(id, 2);

          System.out.println("Your id is " + id);
        } catch (NumberFormatException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (OutOfFormatException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      } else if (input.equals("5")) {
        System.out.println("Enter the id of item to be restocked:");
        String itemId = "";
        String quantity = "";
        try {
          itemId = bufferedReader.readLine();

          System.out.println("By what quantity:");
          quantity = bufferedReader.readLine();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }

        Item item = DatabaseSelectHelper.getItem(Integer.valueOf(itemId));
        try {
          employeeInt.restockInventory(item, Integer.valueOf(quantity));
        } catch (NumberFormatException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (OutOfFormatException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      } else if (input.equals("6")) {
        break;
      }
    }
  }

  private static void showCustomerInterface(BufferedReader bufferedReader, Customer customer)
      throws ConnectionFailedException, ItemNotFoundException, SQLException, OutOfFormatException,
      DatabaseInsertException {
    ShoppingCart shoppingCart = new ShoppingCartImpl(customer);
    int customerId = customer.getId();
    String input = "";

    while (true) {
      System.out
          .println("1. List current items in cart\n" + "2. Add a quantity of an item to the cart\n"
              + "3. Check total price of items in the cart \n"
              + "4. Remove a quantity of an item from the cart \n" + "5. check out \n"
              + "6. restore previous shopping cart\n"
              + "7. Save current items of the shopping cart.\n" + "8. Exit");
      try {
        input = bufferedReader.readLine();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      if (input.equals("1")) {
        List<Item> items = shoppingCart.getItemsInCart();
        for (Item i : items) {
          System.out.println(i.getName() + " X " + shoppingCart.getItemQuantity(i));
        }
      } else if (input.equals("2")) {
        int itemId;
        int quantity = -1;
        System.out.println("Enter the id of the item to be added.");
        try {
          itemId = Integer.valueOf(bufferedReader.readLine());

          System.out.println("How much quantity?");
          quantity = Integer.valueOf(bufferedReader.readLine());

          while (quantity < 0) {
            System.out.println("That was invalid. Type in a positive integer.");
            quantity = Integer.valueOf(bufferedReader.readLine());
          }

          Item item = DatabaseSelectHelper.getItem(itemId);
          shoppingCart.addItem(item, quantity);
        } catch (NumberFormatException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (ItemNotFoundException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }

      } else if (input.equals("3")) {
        System.out.println(shoppingCart.getTotal());
      } else if (input.equals("4")) {
        input = "";
        int itemId = -1;
        int quantity;
        System.out.println("Enter the id of the item to be removed.");
        Item item = null;
        try {
          input = bufferedReader.readLine();
          itemId = Integer.valueOf(input);
          item = DatabaseSelectHelper.getItem(itemId);

          while (!shoppingCart.containsItem(item)) {
            System.out.println("Enter a valid id of the item to be removed.");

            input = bufferedReader.readLine();
            itemId = Integer.valueOf(input);
            item = DatabaseSelectHelper.getItem(itemId);
          }

          System.out.println("How much quantity?");
          input = bufferedReader.readLine();
        } catch (NumberFormatException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        } catch (IOException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
        quantity = Integer.valueOf(input);
        item = DatabaseSelectHelper.getItem(itemId);

        try {
          shoppingCart.removeItem(item, quantity);
        } catch (ItemNotFoundException | OutOfFormatException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      } else if (input.equals("5")) {
        if (true) {
          System.out.println("Checkout failed!");
          continue;
        } else {
          try {
            shoppingCart.clearCart();
            System.out.println("Checkout was successful!");

          } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }

      } else if (input.equals("6")) {
        int accountId = DatabaseSelectHelper.getAccountId(customerId);
        if (accountId > 0) {
          if (DatabaseSelectHelper.getUserActiveAccounts(customerId) > 0) {
            accountId = DatabaseSelectHelper.getUserActiveAccounts(customerId);
            shoppingCart.restorePrevious(accountId);
            System.out.println(
                "Previous cart restored! Note that if you never saved anything, your cart stays the same.");
          } else {
            System.out.println("No active accounts for the given user were found!");
          }
        } else {
          System.out.println("You need to make a customer account to save/restore items!");
        }
      } else if (input.equals("7")) {
        int accountId = DatabaseSelectHelper.getAccountId(customerId);
        if (accountId > 0) {
          if (DatabaseSelectHelper.getUserActiveAccounts(customerId) > 0) {
            accountId = DatabaseSelectHelper.getUserActiveAccounts(customerId);
            DatabaseUpdateHelper.updateAccountStatus(accountId, false);
          }
          shoppingCart.saveShoppingCart(customerId);
        } else {
          System.out.println("You need to make a customer account to save/restore items!");
        }
      } else if (input.equals("8")) {
        shoppingCart.clearCart();
        // try {
        // int accountId = DatabaseSelectHelper.getAccountId(customerId);
        // if (accountId > 0) {
        // if (DatabaseSelectHelper.getUserActiveAccounts(customerId) > 0) {
        // accountId = DatabaseSelectHelper.getUserActiveAccounts(customerId);
        // DatabaseUpdateHelper.updateAccountStatus(accountId, false);
        // }
        // shoppingCart.saveShoppingCart(customerId);
        // } else {
        // System.out.println("You need to make a customer account to save/restore items!");
        // }
        // } catch (Exception e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        break;
      }
    }
  }
}
