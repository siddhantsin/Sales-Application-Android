package com.b07.controller.inventory;

import java.math.BigDecimal;
import java.sql.SQLException;

import com.b07.model.database.helper.DatabaseUpdateHelper;

public class ItemImpl implements Item {
  private static final long serialVersionUID = -3953494497289424090L;
  private String name;
  private int id;
  private BigDecimal price;

//  public ItemImpl(String name, BigDecimal price) {
//    this.name = name;
//    this.price = price;
//  }

  public ItemImpl(int id, String name, BigDecimal price) {
    this.id = id;
    this.name = name;
    this.price = price;
  }

  public static Item hockeyStick = new ItemImpl(1, "HOCKEY_STICK", new BigDecimal("50"));
  public static Item fishingRod = new ItemImpl(2, "FISHING_ROD", new BigDecimal("50"));
  public static Item skates = new ItemImpl(3, "SKATES", new BigDecimal("50"));
  public static Item runningShoes = new ItemImpl(4, "RUNNING_SHOES", new BigDecimal("50"));
  public static Item proteinBar = new ItemImpl(5, "PROTEIN_BAR", new BigDecimal("50"));

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
  public String getName() {
    // TODO Auto-generated method stub
    return this.name;
  }

  @Override
  public void setName(String name) {
    // TODO Auto-generated method stub
    this.name = name;
    try {
      DatabaseUpdateHelper.updateItemName(name, this.id);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public BigDecimal getPrice() {
    // TODO Auto-generated method stub
    return this.price;
  }

  @Override
  public void setPrice(BigDecimal price) {
    // TODO Auto-generated method stub
    this.price = price;
    try {
      DatabaseUpdateHelper.updateItemPrice(price, this.id);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  public boolean isSameItem(Item item) {
    return this.id == item.getId() && this.name.equals(item.getName()) && (this.price.compareTo(item.getPrice()) == 0);
  }

}
