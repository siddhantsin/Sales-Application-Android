package com.b07.model.database.helper;

import com.b07.model.database.DatabaseUpdater;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import com.b07.controller.exceptions.*;
import com.b07.model.database.DatabaseUpdater;

public class DatabaseUpdateHelper extends DatabaseUpdater {

  public static boolean updateRoleName(String name, int id) throws SQLException {
    // TODO Implement this method as stated on the assignment sheet (Strawberry)
    // hint: You should be using these three lines in your final code
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    boolean complete = updateRoleName(name, id, connection);
    connection.close();
    return complete;
  }

  public static boolean updateUserName(String name, int userId) {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    boolean complete = updateUserName(name, userId, connection);
    try {
      connection.close();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return complete;
  }

  public static boolean updateUserAge(int age, int userId) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    boolean complete = updateUserAge(age, userId, connection);
    connection.close();
    return complete;
  }

  public static boolean updateUserAddress(String address, int userId) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    boolean complete = updateUserAddress(address, userId, connection);
    connection.close();
    return complete;

  }

  public static boolean updateUserRole(int roleId, int userId) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    boolean complete = updateUserRole(roleId, userId, connection);
    connection.close();
    return complete;

  }

  public static boolean updateItemName(String name, int itemId) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    boolean complete = updateItemName(name, itemId, connection);
    connection.close();
    return complete;

  }

  public static boolean updateItemPrice(BigDecimal price, int itemId) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    boolean complete = updateItemPrice(price, itemId, connection);
    connection.close();
    return complete;
  }

  public static boolean updateInventoryQuantity(int quantity, int itemId)
      throws SQLException, OutOfFormatException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    if (quantity < 0) {
      throw new OutOfFormatException();
    }
    boolean complete = updateInventoryQuantity(quantity, itemId, connection);
    connection.close();
    return complete;
  }

  // PHASE 3 HELPERS
  
  public static boolean updateAccountStatus(int accountId, boolean active) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();

    boolean complete = updateAccountStatus(accountId, active, connection);
    connection.close();
    return complete;
  }
}
