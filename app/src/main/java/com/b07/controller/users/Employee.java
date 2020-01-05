package com.b07.controller.users;

public class Employee extends User {
  public Employee(int id, String name, int age, String address) {
    setId(id);
    setName(name);
    setAge(age);
    setAddress(address);
  }

  public Employee(int id, String name, int age, String address, boolean authenticated) {
    setId(id);
    setName(name);
    setAge(age);
    setAddress(address);
    setAuthenticated(authenticated);
  }

}
