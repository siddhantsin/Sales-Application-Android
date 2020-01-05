package com.b07.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import com.b07.controller.exceptions.ItemNotFoundException;
import com.b07.controller.inventory.ItemImpl;
import com.b07.controller.store.ShoppingCart;
import com.b07.controller.store.ShoppingCartImpl;
import com.example.view.R;

public class AddItemsPage extends AppCompatActivity {

    Button hockeySticks, skates, proteinBar, runningShoes, fishingRod;
    ImageButton backBtn;
    ShoppingCart shoppingCart;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);

        shoppingCart = (ShoppingCart) this.getIntent().getSerializableExtra("ShoppingCart");

        hockeySticks = findViewById(R.id.add_hockey_stick);
        hockeySticks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    shoppingCart.addItem(ItemImpl.hockeyStick, 1);
                } catch (ItemNotFoundException e) {
                    ToastFactory.showErrorToast(context);
                }
                ToastFactory.showAddedItemToast(context, "hockey stick");
            }
        });

        skates = findViewById(R.id.add_skates);
        skates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    shoppingCart.addItem(ItemImpl.skates, 1);
                } catch (ItemNotFoundException e) {
                    ToastFactory.showErrorToast(context);
                }
                ToastFactory.showAddedItemToast(context, "skates");
            }
        });

        proteinBar = findViewById(R.id.add_protein_bar);
        proteinBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    shoppingCart.addItem(ItemImpl.proteinBar, 1);
                } catch (ItemNotFoundException e) {
                    ToastFactory.showErrorToast(context);
                }
                ToastFactory.showAddedItemToast(context, "protein bar");
            }
        });

        runningShoes = findViewById(R.id.add_running_shoes);
        runningShoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    shoppingCart.addItem(ItemImpl.runningShoes, 1);
                } catch (ItemNotFoundException e) {
                    ToastFactory.showErrorToast(context);
                }
                ToastFactory.showAddedItemToast(context, "running shoes");
            }
        });

        fishingRod = findViewById(R.id.add_fishing_rod);
        fishingRod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    shoppingCart.addItem(ItemImpl.fishingRod, 1);
                } catch (ItemNotFoundException e) {
                    ToastFactory.showErrorToast(context);
                }
                ToastFactory.showAddedItemToast(context, "fishing rod");
            }
        });

        backBtn = findViewById(R.id.add_back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustView();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, CustomerView.class);
        intent.putExtra("ShoppingCart", shoppingCart);
        startActivity(intent);
    }

    public void openCustView(){
        Intent custView = new Intent(this, CustomerView.class);
        custView.putExtra("ShoppingCart", this.shoppingCart);
        startActivity(custView);
    }

}

