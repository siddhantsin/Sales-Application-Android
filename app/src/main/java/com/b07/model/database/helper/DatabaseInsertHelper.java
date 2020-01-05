package com.b07.model.database.helper;

import com.b07.model.database.DatabaseInserter;
import com.b07.model.database.helper.DatabaseDriverHelper;
import com.b07.controller.exceptions.*;
import com.b07.model.database.DatabaseInserter;
import com.b07.controller.users.Roles;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseInsertHelper extends DatabaseInserter {


  /**
   * Use this to check if role is from enumerator
   * 
   * @param name the name of the role
   * @return true if role is in enumerator, false otherwise
   */
  public static int insertRole(String name) throws OutOfFormatException {
    // TODO Implement this method as stated on the assignment sheet
    // hint: You should be using these three lines in your final code
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int roleId = -1;
    if (name == null) {
      throw new NullPointerException();
    } else if (!isInRolesFormat(name)) {
      throw new OutOfFormatException();
    }

    try {
      roleId = insertRole(name, connection);
    } catch (DatabaseInsertException e) {
      // TODO Auto-generated catch block
      System.out.println("DatabaseInsert exception was caught");
      e.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return roleId;
  }

  public static int insertNewUser(String name, int age, String address, String password)
      throws OutOfFormatException {
    // TODO Implement this method as stated on the assignment sheet
    // hint: You should be using these three lines in your final code
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int userId = -1;
    if (name == null || password == null || address == null) {
      throw new NullPointerException();
    } else if (!isInUsersFormat(name, address)) {
      throw new OutOfFormatException();
    }

    try {
      userId = insertNewUser(name, age, address, password, connection);
    } catch (DatabaseInsertException e) {
      // TODO Auto-generated catch block
      System.out.println("DatabaseInsert exception was caught");
      e.printStackTrace();

    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return userId;
  }

  public static int insertUserRole(int userId, int roleId) throws OutOfFormatException {
    // TODO Implement this method as stated on the assignment sheet
    // hint: You should be using these three lines in your final code

    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int userRoleId = -1;
    if (!isInUserRoleFormat(userId, roleId)) {
      throw new OutOfFormatException();
    }

    try {
      userRoleId = insertUserRole(userId, roleId, connection);
    } catch (DatabaseInsertException e) {
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
    return userRoleId;
  }

  public static int insertItem(String name, BigDecimal price) throws OutOfFormatException {
    // TODO Implement this method as stated on the assignment sheet
    // hint: You should be using these three lines in your final code
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int itemId = -1;
    if (name == null || price == null) {
      throw new NullPointerException();
    } else if (!isInItemsFormat(name, price)) {
      throw new OutOfFormatException();
    }

    try {
      itemId = insertItem(name, price, connection);
    } catch (DatabaseInsertException e) {
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
    return itemId;
  }

  public static int insertInventory(int itemId, int quantity) throws OutOfFormatException {
    // TODO Implement this method as stated on the assignment sheet
    // hint: You should be using these three lines in your final code
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int inventoryId = -1;
    if (!isInInventoryFormat(itemId, quantity)) {
      throw new OutOfFormatException();
    }

    try {
      inventoryId = insertInventory(itemId, quantity, connection);
    } catch (DatabaseInsertException e) {
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
    return inventoryId;
  }

  public static int insertSale(int userId, BigDecimal totalPrice) throws OutOfFormatException {
    // TODO Implement this method as stated on the assignment sheet
    // hint: You should be using these three lines in your final code
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    if (totalPrice == null) {
      throw new NullPointerException();
    }
    if (!isInSalesFormat(userId, totalPrice)) {
      throw new OutOfFormatException();
    }

    int saleId = -1;
    try {
      saleId = insertSale(userId, totalPrice, connection);
    } catch (DatabaseInsertException e) {
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
    return saleId;
  }

  public static int insertItemizedSale(int saleId, int itemId, int quantity)
      throws OutOfFormatException {
    // TODO Implement this method as stated on the assignment sheet
    // hint: You should be using these three lines in your final code
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int itemizedId = -1;
    if (!isInItemizedSalesFormat(saleId, itemId, quantity)) {
      throw new OutOfFormatException();
    }

    try {
      itemizedId = insertItemizedSale(saleId, itemId, quantity, connection);
      connection.close();
    } catch (SQLException | DatabaseInsertException e) {
      e.printStackTrace();
    }
    return itemizedId;
  }

  public static int insertAccountHelper(int userId, boolean active)
      throws OutOfFormatException, DatabaseInsertException, SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();

    if (checkUserInUsers(userId)) {
      int accountId = insertAccount(userId, active, connection);
      connection.close();
      return accountId;
    }

    throw new OutOfFormatException();
  }

  private static boolean isInRolesFormat(String name) {
    try {
      Roles.valueOf(name);
      return true;
    } catch (IllegalArgumentException e) {
      return false;
    }
  }

  private static boolean isInUsersFormat(String name, String address) throws OutOfFormatException {
    if (address.length() > 100 || !isAllAlphabets(name)) {
      return false;
    } else {
      return true;
    }
  }

  private static boolean isAllAlphabets(String string) {
    if (string == null) {
      return false;
    } else {
      int length = string.length();
      for (int i = 0; i < length; i++) {
        if (!Character.isSpaceChar(string.charAt(i)) && !Character.isLetter(string.charAt(i))) {
          return false;
        }
      }
      return true;
    }
  }

  private static boolean isInUserRoleFormat(int userId, int roleId) {
    List<Integer> allRoleIds = new ArrayList<>();

    allRoleIds = DatabaseSelectHelper.getRoleIds();

    boolean validUserId = checkUserInUsers(userId);
    boolean validRoleId = false;

    for (int i = 0; i < allRoleIds.size(); i++) {
      if (allRoleIds.get(i) == roleId) {
        validRoleId = true;
      }
    }
    return validRoleId && validUserId;
  }

  private static boolean isInItemsFormat(String name, BigDecimal price) {
    if (name.length() < 64 && price.longValueExact() > 0) {
      return true;
    }
    return false;
  }

  private static boolean isInInventoryFormat(int itemId, int quantity) {
    List<Integer> allItemIds = new ArrayList<>();
    allItemIds = DatabaseSelectHelper.getItemIds();

    return quantity > 0 && allItemIds.contains(itemId);

  }

  private static boolean isInSalesFormat(int userId, BigDecimal totalPrice) {
    boolean validUserId = checkUserInUsers(userId);
    boolean validTotalPrice = false;

    if (totalPrice.longValueExact() >= 0) {
      validTotalPrice = true;
    }

    return validUserId && validTotalPrice;
  }

  // CHECK THIS FUNCTION FOR SURE LATER ON.
  private static boolean checkUserInUsers(int userId) {
    List<Integer> allUserIds = new ArrayList<>();
    allUserIds = DatabaseSelectHelper.getUserIds();

    return allUserIds.contains(userId);
  }

  private static boolean isInItemizedSalesFormat(int saleId, int itemId, int quantity) {
    List<Integer> allSaleIds = new ArrayList<>();
    List<Integer> allItemIds = new ArrayList<>();

    allSaleIds = DatabaseSelectHelper.getSaleIds();
    allItemIds = DatabaseSelectHelper.getItemIds();
    if (allSaleIds.contains(saleId) && allItemIds.contains(itemId) && quantity > 0) {
      return true;
    }
    return false;
  }

  public static int insertAccountLineHelper(int accountId, int itemId, int quantity)
      throws DatabaseInsertException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();

    return insertAccountLine(accountId, itemId, quantity, connection);
  }

}
