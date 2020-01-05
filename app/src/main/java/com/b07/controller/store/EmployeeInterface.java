package com.b07.controller.store;

import com.b07.controller.exceptions.OutOfFormatException;
import com.b07.controller.inventory.Item;
import com.b07.controller.users.Employee;

public interface EmployeeInterface {
  public void setCurrentEmployee(Employee employee);

  public boolean hasCurrentEmployee();

  public boolean restockInventory(Item item, int quantity) throws OutOfFormatException;

  public int createCustomer(String name, int age, String address, String password);

  public int createEmployee(String name, int age, String address, String password);
}
