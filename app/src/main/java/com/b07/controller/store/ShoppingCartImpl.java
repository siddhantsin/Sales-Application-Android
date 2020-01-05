package com.b07.controller.store;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.b07.model.database.helper.DatabaseDriverAndroidHelper;
import com.b07.model.database.helper.DatabaseInsertHelper;
import com.b07.model.database.helper.DatabaseSelectHelper;
import com.b07.model.database.helper.DatabaseUpdateHelper;
import com.b07.controller.exceptions.*;
import com.b07.controller.inventory.Item;
import com.b07.controller.inventory.ItemImpl;
import com.b07.controller.users.Customer;

public class ShoppingCartImpl implements ShoppingCart {
  private static final long serialVersionUID = -3953494497289424091L;
  private HashMap<Item, Integer> items;
  private Customer customer = null;
  private BigDecimal total;
  private final BigDecimal TAXRATE = new BigDecimal("1.13");

  public ShoppingCartImpl(Customer customer) throws ConnectionFailedException {
    this.customer = customer;

    // Check if the customer if authenticated. If not, raise exception.
    if (!customer.getAuthenticated()) {
      throw new ConnectionFailedException();
    }
    this.items = new HashMap<Item, Integer>();
    items.put(ItemImpl.hockeyStick, 0);
    items.put(ItemImpl.fishingRod, 0);
    items.put(ItemImpl.skates, 0);
    items.put(ItemImpl.runningShoes, 0);
    items.put(ItemImpl.proteinBar, 0);
    total = new BigDecimal("0");
  }

  @Override
  public void addItem(Item item, int quantity) throws ItemNotFoundException {
    // TODO Auto-generated method stub
    Item equalItem = returnEqualItem(item);

    if (this.containsItem(equalItem)) {
      // Adding to items table.
      int prevQuantity = this.items.get(equalItem);
      this.items.put(equalItem, prevQuantity + quantity);

      // Updating the total
      BigDecimal quantityInBD = new BigDecimal(String.valueOf(quantity));
      BigDecimal addToTotal = equalItem.getPrice().multiply(quantityInBD);
      this.total = total.add(addToTotal);

    } else {
      throw new ItemNotFoundException();
    }
  }

  @Override
  public void removeItem(Item item, int quantity)
      throws ItemNotFoundException, OutOfFormatException {
    // TODO Auto-generated method stub
    Item equalItem = returnEqualItem(item);
    if (this.items.containsKey(equalItem)) {
      // change value of total.
      BigDecimal quantityInBD = new BigDecimal(String.valueOf(quantity));
      BigDecimal removeFromTotal = equalItem.getPrice().multiply(quantityInBD);
      total = total.subtract(removeFromTotal);

      // Remove from items map.
      if (items.get(equalItem) - quantity >= 0) {
        this.items.put(equalItem, items.get(equalItem) - quantity);
      } else {
        throw new OutOfFormatException();
      }
    } else {
      throw new ItemNotFoundException();
    }
  }

  @Override
  public List<Item> getItemsInCart() {
    // TODO Auto-generated method stub
    List<Item> r = new ArrayList<>();
    if (items.isEmpty()) {
      return r;
    }
    Set<Item> s = items.keySet();
    for (Item i : s) {
      if (items.get(i) != 0) {
        r.add(i);
      }
    }
    return r;
  }

  @Override
  public List<Item> getItems() {
    // TODO Auto-generated method stub
    List<Item> r = new ArrayList<>();
    if (items.isEmpty()) {
      return r;
    }
    Set<Item> s = items.keySet();
    for (Item i : s) {
      r.add(i);
    }
    return r;
  }

  @Override
  public int getItemQuantity(Item item) throws ItemNotFoundException {
    // TODO Auto-generated method stub
    Item equalItem = returnEqualItem(item);
    return items.get(equalItem);
  }

  @Override
  public Customer getCustomer() {
    // TODO Auto-generated method stub
    return this.customer;
  }

  @Override
  public BigDecimal getTotal() {
    // TODO Auto-generated method stub
    return this.total;
  }

  @Override
  public BigDecimal getTaxRate() {
    return this.TAXRATE;
  }

  @Override
  public boolean checkOut(DatabaseDriverAndroidHelper myDb) {
    if (this.customer == null) {
      return false;
    }

    BigDecimal totalAfterTax = (this.TAXRATE).multiply(this.total);
    int prevQuantity;
    int newQuantity;
    long saleId = -1;

    if (!isCartInStock(myDb)){
      return false;
    }

    saleId = myDb.insertSaleHelper(customer.getId(), totalAfterTax);
    for (Item i : this.getItemsInCart()) {
      prevQuantity = myDb.getInventoryQuantityHelper(i.getId());
      newQuantity = prevQuantity - items.get(i);
      myDb.updateInventoryQuantityHelper(newQuantity, i.getId());
      myDb.insertItemizedSaleHelper((int)saleId, i.getId(), items.get(i));
    }

    return true;

  }

  @Override
  public void clearCart() {
    for (Item i : items.keySet()) {
      items.put(i, 0);
    };
    this.total = new BigDecimal("0");
  }
  
  @Override
  public void saveShoppingCart(int userId) throws OutOfFormatException, DatabaseInsertException, SQLException, ItemNotFoundException {
    int accountId = DatabaseInsertHelper.insertAccountHelper(userId, true);
    if (accountId == -1) {
      return;
    }
    
    for (Item i : this.getItems()) {
      DatabaseInsertHelper.insertAccountLineHelper(accountId, i.getId(),
          this.getItemQuantity(i));
    }
  }

  public boolean containsItem(Item item) {
    for (Item i : this.getItems()) {
      if (i.isSameItem(item)) {
        return true;
      }
    }
    return false;
  }

  private Item returnEqualItem(Item item) throws ItemNotFoundException {

    for (Item i : this.getItems()) {
      if (i.isSameItem(item)) {
        return i;
      }
    }

    throw new ItemNotFoundException();
  }
  
  @Override
  public void restorePrevious(int accountId) throws ItemNotFoundException, SQLException {
    HashMap<Item, Integer> map = DatabaseSelectHelper.getAccountDetailsHelper(accountId);
    
    for (Item i : map.keySet()) {
      if (this.containsItem(i)) {
        Item item = returnEqualItem(i);
        int prevValue = this.items.get(item);
        this.items.put(item, prevValue + map.get(i));
      }
    }
   //PUT CODE TO SET CURRENT ACCOUNT INACTIVE.
    DatabaseUpdateHelper.updateAccountStatus(accountId, false);
  }

  private boolean isCartInStock(DatabaseDriverAndroidHelper myDb) {
    for (Item i : this.getItemsInCart()) {
      if (items.get(i) > myDb.getInventoryQuantityHelper(i.getId())) {
        return false;
      }
    }
    return true;
  }
}

