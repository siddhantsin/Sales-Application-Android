package com.b07.controller.store;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import com.b07.controller.exceptions.DatabaseInsertException;
import com.b07.controller.exceptions.ItemNotFoundException;
import com.b07.controller.exceptions.OutOfFormatException;
import com.b07.controller.inventory.Item;
import com.b07.controller.users.Customer;
import com.b07.model.database.helper.DatabaseDriverAndroidHelper;

public interface ShoppingCart extends Serializable {
  public void addItem(Item item, int quantity) throws ItemNotFoundException;

  public void removeItem(Item item, int quantity)
      throws ItemNotFoundException, OutOfFormatException;

  public List<Item> getItems();
  
  public int getItemQuantity(Item item) throws ItemNotFoundException;
  
  public List<Item> getItemsInCart(); 

  public Customer getCustomer();

  public BigDecimal getTotal();

  public BigDecimal getTaxRate();

  public boolean checkOut(DatabaseDriverAndroidHelper myDb);

  public void clearCart();
  
  public boolean containsItem(Item item);
  
  public void saveShoppingCart(int userId) throws OutOfFormatException, DatabaseInsertException, SQLException, ItemNotFoundException;
  
  public void restorePrevious(int accountId) throws ItemNotFoundException, SQLException;
}
