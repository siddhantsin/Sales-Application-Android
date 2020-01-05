package com.b07.controller.store;

import java.sql.SQLException;

import com.b07.model.database.helper.*;
import com.b07.controller.exceptions.OutOfFormatException;
import com.b07.controller.inventory.*;
import com.b07.controller.users.Employee;

public class EmployeeInterfaceImpl implements EmployeeInterface {
  private Employee currentEmployee = null;
  private Inventory inventory;

  EmployeeInterfaceImpl(Employee currentEmployee, Inventory inventory) {
    this.currentEmployee = currentEmployee;
    this.setInventory(inventory);
  }

  @Override
  public void setCurrentEmployee(Employee employee) {
    // TODO Auto-generated method stub
    this.currentEmployee = employee;
  }

  @Override
  public boolean hasCurrentEmployee() {
    // TODO Auto-generated method stub
    return this.currentEmployee != null;
  }

  @Override
  public boolean restockInventory(Item item, int quantity) throws OutOfFormatException {
    // TODO Auto-generated method stub
    int id = item.getId();
    try {
      return DatabaseUpdateHelper.updateInventoryQuantity(quantity, id);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public int createCustomer(String name, int age, String address, String password) {
    // TODO Auto-generated method stub
    int id = -1;
    try {
      id = DatabaseInsertHelper.insertNewUser(name, age, address, password);
      DatabaseInsertHelper.insertUserRole(id, DatabaseSelectHelper.getUserRoleId(id));
    } catch (OutOfFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return id;
  }

  @Override
  public int createEmployee(String name, int age, String address, String password) {
    // TODO Auto-generated method stub
    int id = -1;
    try {
      id = DatabaseInsertHelper.insertNewUser(name, age, address, password);
      DatabaseInsertHelper.insertUserRole(id, DatabaseSelectHelper.getUserRoleId(id));
      return id;
    } catch (OutOfFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return id;
    }

  }

  public Inventory getInventory() {
    return inventory;
  }

  public void setInventory(Inventory inventory) {
    this.inventory = inventory;
  }

}
