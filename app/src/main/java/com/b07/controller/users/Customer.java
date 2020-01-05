package com.b07.controller.users;

public class Customer extends User {
  private static final long serialVersionUID = -3953494497289424092L;

  public Customer(int id, String name, int age, String address) {
    setId(id);
    setName(name);
    setAge(age);
    setAddress(address);
  }

  public Customer(int id, String name, int age, String address, boolean authenticated) {
    setId(id);
    setName(name);
    setAge(age);
    setAddress(address);
    setAuthenticated(authenticated);
  }

}
