package com.b07.controller.store;

import java.util.List;

public class SalesLogImpl implements SalesLog {

  private List<Sale> sales;

  @Override
  public List<Sale> getSales() {
    // TODO Auto-generated method stub
    return this.sales;
  }

  @Override
  public void setSales(List<Sale> sales) {
    // TODO Auto-generated method stub
    this.sales = sales;
  }

  public void addSale(Sale sale) {
    this.sales.add(sale);
  }

}
