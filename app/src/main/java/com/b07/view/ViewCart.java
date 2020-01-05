package com.b07.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.b07.controller.exceptions.ItemNotFoundException;
import com.b07.controller.inventory.Item;
import com.b07.controller.inventory.ItemImpl;
import com.b07.controller.store.ShoppingCart;
import com.example.view.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewCart extends AppCompatActivity {

    TextView hockeySticks, skates, proteinBar, runningShoes, fishingRod;
    ShoppingCart shoppingCart;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);
        shoppingCart = (ShoppingCart) getIntent().getSerializableExtra("ShoppingCart");

        try {
            hockeySticks = findViewById(R.id.hockey_stick_no);
            hockeySticks.setText("Hockey Sticks :" + shoppingCart.getItemQuantity(ItemImpl.hockeyStick));

            skates = findViewById(R.id.skates_no);
            skates.setText("Skates :" + shoppingCart.getItemQuantity(ItemImpl.skates));

            proteinBar = findViewById(R.id.protein_bar_no);
            proteinBar.setText("Protein Bars :" + shoppingCart.getItemQuantity(ItemImpl.proteinBar));

            runningShoes = findViewById(R.id.running_shoes_no);
            runningShoes.setText("Running Shoes :" + shoppingCart.getItemQuantity(ItemImpl.runningShoes));

            fishingRod = findViewById(R.id.fishing_rod_no);
            fishingRod.setText("Fishing Rods :" + shoppingCart.getItemQuantity(ItemImpl.fishingRod));
        } catch (ItemNotFoundException e){
            ToastFactory.showErrorToast(this);
        }


    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, CustomerView.class);
        intent.putExtra("ShoppingCart", shoppingCart);
        startActivity(intent);
    }

}
