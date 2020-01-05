package com.b07.view;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.b07.controller.exceptions.ConnectionFailedException;
import com.b07.controller.store.ShoppingCart;
import com.b07.controller.store.ShoppingCartImpl;
import com.b07.controller.users.Customer;
import com.b07.controller.users.User;
import com.b07.model.database.helper.DatabaseDriverAndroidHelper;
import com.example.view.R;

public class CustomerView extends AppCompatActivity {

    private ShoppingCart shoppingCart;
    private Button checkOutBtn, logOut, addItemsBtn, viewItemsBtn, rmvItemsBtn;
    DatabaseDriverAndroidHelper myDb;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view);
        myDb = new DatabaseDriverAndroidHelper(this);
        context = this;

        shoppingCart = (ShoppingCart) getIntent().getSerializableExtra("ShoppingCart");

        //check out button
        checkOutBtn = findViewById(R.id.checkout);
        checkOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                if (shoppingCart.checkOut(myDb)) {
                    openSuccCO();
                } else {
                    ToastFactory.showErrorToast(context);
                }
            }
        });

        //Adding items
        addItemsBtn = findViewById(R.id.addToCart);
        addItemsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                openAddItemsWin();
            }
        });

        //Removing items
        rmvItemsBtn = findViewById(R.id.removeFromCart);
        rmvItemsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRmvItemsWin();
            }
        });

        //log out button
        logOut = findViewById(R.id.custLogout);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                openLoginPage();
            }
        });

        viewItemsBtn = findViewById(R.id.viewCart);
        viewItemsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openViewCart();
            }
        });
    }

    public void openLoginPage(){
        Intent openLogin = new Intent(this, Login.class);
        startActivity(openLogin);
    }

    public void openSuccCO(){
        Intent checkoutSuc = new Intent(this, SuccessfulCheckout.class);
        startActivity(checkoutSuc);
    }

    public void openAddItemsWin(){
        Intent addItemsWin = new Intent(this, AddItemsPage.class);
        addItemsWin.putExtra("ShoppingCart", shoppingCart);
        startActivity(addItemsWin);
    }

    public void openViewCart(){
        Intent openViewCart = new Intent(this, ViewCart.class);
        openViewCart.putExtra("ShoppingCart", shoppingCart);
        startActivity(openViewCart);
    }

    public void openRmvItemsWin(){
        Intent openRmvItems = new Intent(this, RemoveItemsPage.class);
        openRmvItems.putExtra("ShoppingCart", shoppingCart);
        startActivity(openRmvItems);
    }


}
