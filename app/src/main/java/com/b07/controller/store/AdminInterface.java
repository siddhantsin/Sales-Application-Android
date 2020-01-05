package com.b07.controller.store;

import com.b07.controller.exceptions.OutOfFormatException;
import com.b07.controller.inventory.Item;
import com.b07.controller.users.Admin;

public interface AdminInterface {
  public void setCurrentAdmin(Admin admin);

  public boolean hasCurrentAdmin();

  public boolean restockInventory(Item item, int quantity) throws OutOfFormatException;

  public int createCustomer(String name, int age, String address, String password);

  public int createAdmin(String name, int age, String address, String password);
}
