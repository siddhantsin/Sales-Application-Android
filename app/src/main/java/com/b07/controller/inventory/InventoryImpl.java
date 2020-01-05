package com.b07.controller.inventory;

import java.util.HashMap;

public class InventoryImpl implements Inventory {
  private HashMap<Item, Integer> itemMap;
  private int totalItems;

  public InventoryImpl(HashMap<Item, Integer> itemMap, int totalItems) {
    this.totalItems = totalItems;
    this.itemMap = itemMap;
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
    // this.itemMap = itemMap;
    // this.totalItems = 0;
    //
    // for (int value : itemMap.values()) {
    // this.totalItems += value;
    // }

  }

  @Override
  public void updateMap(Item item, Integer value) {
    // TODO Auto-generated method stub
    itemMap.put(item, value);
    // int prevValue;
    //
    // if (itemMap.get(item) != null) {
    // prevValue = itemMap.get(item);
    // } else {
    // prevValue = 0;
    // }
    //
    // itemMap.put(item, value);
    // this.totalItems += value - prevValue;

  }

  @Override
  public int getTotalItems() {
    // TODO Auto-generated method stub
    return this.totalItems;
  }

  @Override
  public void setTotalITems(int total) {
    // TODO Auto-generated method stub
    this.totalItems = total;
  }

}
