package com.b07.controller.inventory;

import java.io.Serializable;
import java.math.BigDecimal;

public interface Item extends Serializable {
  public int getId();

  public void setId(int id);

  public String getName();

  public void setName(String name);

  public BigDecimal getPrice();

  public void setPrice(BigDecimal price);
  
  public boolean isSameItem(Item item);

}
