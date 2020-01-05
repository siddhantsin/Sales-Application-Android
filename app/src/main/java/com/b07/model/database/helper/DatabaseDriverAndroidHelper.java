package com.b07.model.database.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.b07.controller.exceptions.OutOfFormatException;
import com.b07.controller.users.Admin;
import com.b07.controller.users.Customer;
import com.b07.controller.users.Employee;
import com.b07.controller.users.User;
import com.b07.model.database.DatabaseDriverAndroid;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DatabaseDriverAndroidHelper extends DatabaseDriverAndroid {
    //Context context;
    public DatabaseDriverAndroidHelper(Context context) {
        super(context);
    //    this.context = context;
        initializeDatabase();
    }

    public void initializeDatabase(){
        if (!isAlreadyInitializedDatabase()) {
            super.insertRole("ADMIN");
            super.insertRole("EMPLOYEE");
            super.insertRole("CUSTOMER");
        }
    }

    //DATABASE INSERTS

    public long insertNewUserHelper(String name, int age, String address, String password, int roleId) {
        long userId = super.insertNewUser(name, age, address, password);
        long id = super.insertUserRole((int) userId, roleId);
        return userId;
    }

    public long insertSaleHelper(int userId, BigDecimal totalPrice) {
        long saleId = super.insertSale(userId, totalPrice);
        return saleId;
    }

    public long insertItemizedSaleHelper(int saleId, int itemId, int quantity) {
        return super.insertItemizedSale(saleId, itemId, quantity);
    }

    //DATABASE GETTERS

    public String getPassword(int id){
        String pass = "";
        pass = super.getPassword(id);
        return pass;
    }

    public User getUserDetailsHelper(int id){
        Cursor cursor = super.getUserDetails(id);
        cursor.moveToFirst();

        int roleId = super.getUserRole(id);

        String name = cursor.getString(cursor.getColumnIndex("NAME"));
        int age = cursor.getInt(cursor.getColumnIndex("AGE"));
        String address = cursor.getString(cursor.getColumnIndex("ADDRESS"));

        if (roleId == 1){
            return new Admin(id, name, age, address);
        } else if (roleId == 2){
            return new Employee(id, name, age, address);
        } else if (roleId == 3){
            return new Customer(id, name, age, address);
        }

        return null;
    }

    public int getUserRoleHelper(int userId){
        return super.getUserRole(userId);
    }

    public int getInventoryQuantityHelper(int itemId) {
        return super.getInventoryQuantity(itemId);
    }

    //DATABASE UPDATERS

    public boolean updateInventoryQuantityHelper (int quantity, int id) {
        return super.updateInventoryQuantity(quantity, id);
    }

    public boolean promoteEmployee(int employeeId) {
        return super.updateUserRole(1, employeeId);
    }


    // ALL HELPER FUNCTIONS ARE BELOW:
    public boolean isAlreadyInitializedDatabase(){
        int roleId = 0;
        try {
            roleId = super.getUserRole(1);
        } catch (RuntimeException e){
            return false;
        }
        if (roleId == 1) {
            return true;
        } else {
            return false;
        }
    }


}
