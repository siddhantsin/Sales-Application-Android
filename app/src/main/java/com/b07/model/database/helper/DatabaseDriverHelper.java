package com.b07.model.database.helper;

import com.b07.model.database.DatabaseDriver;
import com.b07.model.database.DatabaseDriver;

import java.sql.Connection;


public class DatabaseDriverHelper extends DatabaseDriver {

  protected static Connection connectOrCreateDataBase() {
    return DatabaseDriver.connectOrCreateDataBase();
  }

}
