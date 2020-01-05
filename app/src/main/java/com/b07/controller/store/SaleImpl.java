package com.b07.controller.store;

import java.math.BigDecimal;
import java.util.HashMap;

import com.b07.controller.inventory.Item;
import com.b07.controller.users.User;

public class SaleImpl implements Sale {
  private int id;
  private User user;
  private BigDecimal totalPrice;
  private HashMap<Item, Integer> itemMap;


  public SaleImpl(int id, User user, BigDecimal totalPrice) {
    this.id = id;
    this.user = user;
    this.totalPrice = totalPrice;
  }

  @Override
  public int getId() {
    // TODO Auto-generated method stub
    return this.id;
  }

  @Override
  public void setId(int id) {
    // TODO Auto-generated method stub
    this.id = id;
  }

  @Override
  public User getUser() {
    // TODO Auto-generated method stub
    return this.user;
  }

  @Override
  public void setUser(User user) {
    // TODO Auto-generated method stub
    this.user = user;
  }

  @Override
  public BigDecimal getTotalPrice() {
    // TODO Auto-generated method stub
    return this.totalPrice;
  }

  @Override
  public void setTotalPrice(BigDecimal price) {
    // TODO Auto-generated method stub
    this.totalPrice = price;
  }

  @Override
  public HashMap<Item, Integer> getItemMap() {
    // TODO Auto-generated method stub
    return this.itemMap;
  }

  @Override
  public void setItemMap(HashMap<Item, Integer> itemMap) {
    // TODO Auto-generated method stub
    this.itemMap = itemMap;
  }

}
