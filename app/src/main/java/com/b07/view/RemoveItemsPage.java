package com.b07.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import com.b07.controller.exceptions.ItemNotFoundException;
import com.b07.controller.exceptions.OutOfFormatException;
import com.b07.controller.inventory.ItemImpl;
import com.b07.controller.store.ShoppingCart;
import com.example.view.R;

public class RemoveItemsPage extends AppCompatActivity {

    Button hockeySticks, skates, proteinBar, runningShoes, fishingRod;
    ImageButton backBtn;
    ShoppingCart shoppingCart;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_items);
        shoppingCart = (ShoppingCart)getIntent().getSerializableExtra("ShoppingCart");
        context = this;

        hockeySticks = findViewById(R.id.remove_hockey_stick);
        hockeySticks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    shoppingCart.removeItem(ItemImpl.hockeyStick, 1);
                    ToastFactory.showRemovedItemToast(context, "hockey stick");
                } catch (ItemNotFoundException e) {
                    ToastFactory.showErrorToast(context);
                } catch (OutOfFormatException e) {
                    ToastFactory.showItemNotFound(context);
                }
            }
        });

        skates = findViewById(R.id.remove_skates);
        skates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    shoppingCart.removeItem(ItemImpl.skates, 1);
                    ToastFactory.showRemovedItemToast(context, "skates");
                } catch (ItemNotFoundException e) {
                    ToastFactory.showErrorToast(context);
                } catch (OutOfFormatException e) {
                    ToastFactory.showItemNotFound(context);
                }
            }
        });

        proteinBar = findViewById(R.id.remove_protein_bar);
        proteinBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    shoppingCart.removeItem(ItemImpl.proteinBar, 1);
                    ToastFactory.showRemovedItemToast(context, "protein bar");
                } catch (ItemNotFoundException e) {
                    ToastFactory.showErrorToast(context);
                } catch (OutOfFormatException e) {
                    ToastFactory.showItemNotFound(context);
                }
            }
        });

        runningShoes = findViewById(R.id.remove_running_shoes);
        runningShoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    shoppingCart.removeItem(ItemImpl.runningShoes, 1);
                    ToastFactory.showRemovedItemToast(context, "running shoes");
                } catch (ItemNotFoundException e) {
                    ToastFactory.showErrorToast(context);
                } catch (OutOfFormatException e) {
                    ToastFactory.showItemNotFound(context);
                }
            }
        });

        fishingRod = findViewById(R.id.remove_fishing_rod);
        fishingRod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    shoppingCart.removeItem(ItemImpl.fishingRod, 1);
                    ToastFactory.showRemovedItemToast(context, "fishing rod");
                } catch (ItemNotFoundException e) {
                    ToastFactory.showErrorToast(context);
                } catch (OutOfFormatException e) {
                    ToastFactory.showItemNotFound(context);
                }
            }
        });

        backBtn = findViewById(R.id.rmv_back_btn);
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
        custView.putExtra("ShoppingCart", shoppingCart);
        startActivity(custView);
    }

}
