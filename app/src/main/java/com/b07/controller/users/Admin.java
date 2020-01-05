package com.b07.controller.users;


import com.b07.model.database.helper.*;


public class Admin extends User {
  public Admin(int id, String name, int age, String address) {
    setId(id);
    setName(name);
    setAge(age);
    setAddress(address);
  }

  public Admin(int id, String name, int age, String address, boolean authenticated) {
    setId(id);
    setName(name);
    setAge(age);
    setAddress(address);
    setAuthenticated(authenticated);
  }

  public boolean promoteEmployee(int employeeId) {
    try {
      DatabaseUpdateHelper.updateUserRole(1, employeeId);
    } catch (Exception e) {
      return false;
    }

    return true;
  }


}
