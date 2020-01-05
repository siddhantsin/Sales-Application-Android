package com.b07.model.database.helper;

import com.b07.model.database.DatabaseSelector;
import com.b07.controller.inventory.*;
import com.b07.model.database.DatabaseSelector;
import com.b07.controller.store.*;
import com.b07.controller.users.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * TODO: Complete the below methods to be able to get information out of the database. TODO: The
 * given code is there to aide you in building your methods. You don't have TODO: to keep the exact
 * code that is given (for example, DELETE the System.out.println()) TODO: and decide how to handle
 * the possible exceptions
 */
public class DatabaseSelectHelper extends DatabaseSelector {
  public static List<Integer> getRoleIds() {
    List<Integer> ids = new ArrayList<>();
    try {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      ResultSet results = getRoles(connection);
      while (results.next()) {
        ids.add(results.getInt("ID"));
      }
      results.close();
      connection.close();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return ids;

  }

  public static List<Integer> getSaleIds() {
    List<Integer> ids = new ArrayList<>();

    try {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      ResultSet results = getSales(connection);
      while (results.next()) {
        ids.add(results.getInt("ID"));
      }
      results.close();
      connection.close();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return ids;
  }

  public static List<Integer> getItemIds() {
    List<Integer> ids = new ArrayList<>();

    try {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      ResultSet results = getAllItems(connection);

      while (results.next()) {
        ids.add(results.getInt("ID"));
      }
      results.close();
      connection.close();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return ids;

  }

  public static List<Integer> getUserIds() {
    List<Integer> ids = new ArrayList<>();

    try {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      ResultSet results = getUsersDetails(connection);

      while (results.next()) {
        ids.add(results.getInt("ID"));
      }
      results.close();
      connection.close();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return ids;

  }


  public static String getRoleName(int roleId) {
    String role = "";
    try {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      role = getRole(roleId, connection);
      connection.close();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return role;

  }

  public static int getUserRoleId(int userId) {
    int roleId = -1;
    try {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      roleId = getUserRole(userId, connection);
      connection.close();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return roleId;
  }

  public static String getUserRole(int userId) {
    int roleId = getUserRoleId(userId);
    String roleName = getRoleName(roleId);
    return roleName;
  }

  public static List<Integer> getUsersByRole(int roleId) {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results;
    List<Integer> userIds = new ArrayList<>();
    try {
      results = getUsersByRole(roleId, connection);

      while (results.next()) {
        userIds.add(results.getInt("USERID"));
      }
      results.close();

    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return userIds;

  }

  public static List<User> getUsersDetails() {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    List<User> allUsers = new ArrayList<>();
    try {
      ResultSet results = getUsersDetails(connection);
      List<Integer> allUserIds = new ArrayList<>();
      allUsers = new ArrayList<>();

      allUserIds = getUserIds();
      for (int i = 0; i < allUserIds.size(); i++) {
        allUsers.add(getUserDetails(allUserIds.get(i)));
      }
      results.close();
      return allUsers;

    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return allUsers;
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  public static User getUserDetails(int userId) {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    User r = null;
    try {
      ResultSet results = getUserDetails(userId, connection);
      
      while (results.next()) {

        String userName = results.getString("NAME");
        int userAge = results.getInt("AGE");
        String userAddress = results.getString("ADDRESS");
        r = constructUser(userId, userName, userAge, userAddress);
      }
      results.close();
      connection.close();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return r;
  }

  private static User constructUser(int userId, String userName, int userAge, String userAddress) {

    String userRole = getUserRole(userId);

    if (userRole.equals("ADMIN")) {
      return new Admin(userId, userName, userAge, userAddress);
    } else if (userRole.equals("CUSTOMER")) {
      return new Customer(userId, userName, userAge, userAddress);
    } else {
      return new Employee(userId, userName, userAge, userAddress);
    }
  }

  public static String getPassword(int userId) {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    String password = "";
    try {
      password = getPassword(userId, connection);
      connection.close();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return password;
  }

  public static List<Item> getAllItems() {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    List<Item> items = null;
    try {
      ResultSet results = getAllItems(connection);
      items = new ArrayList<>();
      while (results.next()) {
        int id = results.getInt("ID");
        Item item = getItem(id);
        items.add(item);
      }
      results.close();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return items;
  }

  public static Item getItem(int itemId) {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    Item item = null;
    try {
      ResultSet results = getItem(itemId, connection);
      int id = results.getInt("ID");
      String name = results.getString("NAME");
      BigDecimal price = new BigDecimal(results.getString("PRICE"));
      item = constructItem(id, name, price);
      results.close();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return item;
  }

  private static Item constructItem(int id, String name, BigDecimal price) {
    Item item = new ItemImpl(id, name, price);
    return item;
  }

  public static Inventory getInventory() {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    Inventory inv = null;
    try {
      ResultSet results = getInventory(connection);
      HashMap<Item, Integer> itemMap = new HashMap<>();
      while (results.next()) {
        Item item = getItem(results.getInt("ITEMID"));
        int quantity = results.getInt("QUANTITY");
        itemMap.put(item, quantity);
      }
      inv = new InventoryImpl(itemMap, 100);
      results.close();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return inv;
  }

  public static int getInventoryQuantity(int itemId) {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int quantity = -1;
    try {
      quantity = getInventoryQuantity(itemId, connection);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return quantity;
  }

  public static SalesLog getSales() {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    SalesLog salesLog = null;
    User user = null;
    try {
      ResultSet results = getSales(connection);
      salesLog = new SalesLogImpl();
      while (results.next()) {
        int userId = results.getInt("USERID");
        BigDecimal totalPrice = new BigDecimal(results.getString("TOTALPRICE"));
        user = DatabaseSelectHelper.getUserDetails(userId);
        Sale sale = new SaleImpl(userId, user, totalPrice);
        salesLog.addSale(sale);
      }
      results.close();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return salesLog;
  }

  public static Sale getSaleById(int saleId) {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    Sale sale = null;
    try {
      ResultSet results = getSaleById(saleId, connection);
      int id = results.getInt("ID");
      int userId = results.getInt("USERID");
      BigDecimal totalPrice = new BigDecimal(results.getString("TOTALPRICE"));
      sale = new SaleImpl(id, getUserDetails(userId), totalPrice);
      results.close();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return sale;
  }

  public static List<Sale> getSalesToUser(int userId) {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    List<Sale> sales = null;
    try {
      ResultSet results = DatabaseSelectHelper.getSalesToUser(userId, connection);
      sales = new ArrayList<>();
      while (results.next()) {
        int id = results.getInt("ID");
        BigDecimal totalPrice = new BigDecimal(results.getString("TOTALPRICE"));
        Sale newSale = new SaleImpl(id, getUserDetails(userId), totalPrice);
        sales.add(newSale);
      }
      results.close();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return sales;
  }

  public static List<ItemizedSaleImpl> getItemizedSaleById(int saleId) {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ItemizedSaleImpl sale = null;
    List<ItemizedSaleImpl> salesList = new ArrayList<>();
    try {
      ResultSet results = getItemizedSaleById(saleId, connection);
      int itemId;
      int quantity;
      
      while (results.next()) {
        int thisSaleId = results.getInt("SALEID");
        
        if (thisSaleId == saleId) {
          itemId = results.getInt("ITEMID");
          quantity = results.getInt("QUANTITY");
    
          sale = new ItemizedSaleImpl(saleId, itemId, quantity);
          salesList.add(sale);
        }
      }
      results.close();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    }
    return salesList;
  }

  public static SalesLog getItemizedSales() {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    SalesLog log = null;
    try {
      ResultSet results = getItemizedSales(connection);

      log = new SalesLogImpl();
      while (results.next()) {
        Sale sale = null;
        int saleId;
        int itemId;
        int quantity;

        saleId = results.getInt("SALEID");
        itemId = results.getInt("ITEMID");
        quantity = results.getInt("QUANTITY");

        sale = new ItemizedSaleImpl(saleId, itemId, quantity);

        log.addSale(sale);
      }
      results.close();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return log;
  }
  
  public static int getAccountId(int userId)  {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int accountId = -1;
    
    try {
      ResultSet results = getUserAccounts(userId, connection);

      while (results.next()) {
          accountId = results.getInt("ID");
      }
      results.close();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return accountId;
  }
  
  
  public static HashMap<Item, Integer> getAccountDetailsHelper(int accountId) {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    HashMap<Item, Integer> items = new HashMap<Item, Integer>();
    
    try {
      ResultSet results = getAccountDetails(accountId, connection);

      while (results.next()) {
          Item item = getItem(results.getInt("ITEMID"));
          int quantity = results.getInt("QUANTITY");
          items.put(item, quantity);
      }
      results.close();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return items;

  }
  
  public static void viewBooks() {
    BigDecimal totalSales = new BigDecimal("0");
    for (int userId : getUserIds()) {
      if(getUserRole(userId).equals("CUSTOMER")) {
        String name = getUserDetails(userId).getName();
        for (Sale sale : getSalesToUser(userId)) {
            System.out.println("Customer: " + name);
            System.out.println("Purchase Number: " + sale.getId());
            System.out.println("Total Purchase Price: " + sale.getTotalPrice());
            totalSales = totalSales.add(sale.getTotalPrice());
            System.out.println("Itemized Breakdown: ");
            List<ItemizedSaleImpl> salesList = getItemizedSaleById(sale.getId());
            for (ItemizedSaleImpl s : salesList) {
              System.out.println(getItem(s.getItemId()).getName() + ": " + s.getQuantity());
            }
            System.out.println("----------------------------------------------------------------");
        }
      }  
    }
    
    System.out.println("TOTAL SALES: " + totalSales);
    
  }
  
  
  // PHASE 3 HELPER METHODS START HERE
  /*
   * returns the accountId for which the account is active.
   */
  public static int getUserActiveAccounts(int userId) {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int accountId = -1;
    
    
    try {
      ResultSet results = getUserActiveAccounts(userId, connection);
      while (results.next()) {
        accountId = results.getInt("ID");
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      System.out.println("There was a problem finding active accounts!");
      e.printStackTrace();
    }
    
    return accountId;
  }
  
  public static List<Integer> getUserInactiveAccounts(int userId) {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    List<Integer> accountIds = new ArrayList<>();
    
    
    try {
      ResultSet results = getUserInactiveAccounts(userId, connection);
      while (results.next()) {
        accountIds.add(results.getInt("ID"));
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      System.out.println("There was a problem finding active accounts!");
      e.printStackTrace();
    }
    
    return accountIds;
  }
  
  
}
