package com.b07.controller.store;

import java.math.BigDecimal;
import java.util.HashMap;

import com.b07.controller.inventory.Item;
import com.b07.controller.users.User;

public class ItemizedSaleImpl implements Sale {

  private int saleId;
  private int itemId;
  private int quantity;

  public ItemizedSaleImpl(int saleId, int itemId, int quantity) {
    // TODO Auto-generated constructor stub
    this.itemId = itemId;
    this.saleId = saleId;
    this.quantity = quantity;
  }

  @Override
  public int getId() {
    // TODO Auto-generated method stub
    return this.saleId;
  }

  @Override
  public void setId(int id) {
    // TODO Auto-generated method stub
    this.itemId = id;
  }

  @Override
  public User getUser() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setUser(User user) {
    // TODO Auto-generated method stub

  }

  @Override
  public BigDecimal getTotalPrice() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setTotalPrice(BigDecimal price) {
    // TODO Auto-generated method stub

  }

  @Override
  public HashMap<Item, Integer> getItemMap() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setItemMap(HashMap<Item, Integer> itemMap) {
    // TODO Auto-generated method stub
  
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public int getItemId() {
    return itemId;
  }

  public void setItemId(int itemId) {
    this.itemId = itemId;
  }

  public int getSaleId() {
    return saleId;
  }

  public void setSaleId(int saleId) {
    this.saleId = saleId;
  }

}
