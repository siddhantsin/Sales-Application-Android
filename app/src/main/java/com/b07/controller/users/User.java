package com.b07.controller.users;

import java.io.Serializable;
import java.sql.SQLException;

import com.b07.controller.security.PasswordHelpers;

public abstract class User implements Serializable {
  // TODO: Complete this class based on UML provided on the assignment sheet.

  private int id;
  private String name;
  private int age;
  private String address;
  private int roleId;
  private boolean authenticated;



  /**
   * 
   * Use this to get the userId of the user
   * 
   * @return the userId of the user
   */

  public int getId() {
    return this.id;
  }

  /**
   * 
   * Use this to set the userId of the user
   * 
   * @param id the userId of the user
   */

  public void setId(int id) {
    this.id = id;
  }

  /**
   * 
   * Use this to get the name of the user
   * 
   * @return the name of the user
   */

  public String getName() {
    return this.name;
  }

  /**
   * 
   * Use this to set the name of the user
   * 
   * @param name the name of the user
   */

  public void setName(String name) {
    this.name = name;
  }

  /**
   * 
   * Use this to get the age of the user
   * 
   * @return the age of the user
   */

  public int getAge() {
    return this.age;
  }

  /**
   * 
   * Use this to set the age of the user
   * 
   * @param age the age of the user
   */

  public void setAge(int age) {
    this.age = age;
  }

  /**
   * 
   * Use this to get the address of the user
   * 
   * @return the address of the user
   */

  public String getAddress() {
    return this.address;
  }

  /**
   * 
   * Use this to set the address of the user
   * 
   * @param address the address of the user
   */

  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * 
   * Use this to get the roleId of the user
   * 
   * @return the roleId of the user
   */

  public int getRoleId() {
    return this.roleId;
  }

  public boolean getAuthenticated() {
    return this.authenticated;
  }

  public void setAuthenticated(boolean authenticated) {
    this.authenticated = authenticated;
  }

  public final boolean authenticate(String password, String dbPassword) throws SQLException {
    // Change the value of dbPassword
    if (PasswordHelpers.comparePassword(dbPassword, password)) {
      this.authenticated = true;
    } else {
      this.authenticated = false;
    }
    return this.authenticated;
  }
}
