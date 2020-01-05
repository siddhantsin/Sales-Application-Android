package com.b07.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.view.R;

public class EmplView extends AppCompatActivity {

    private Button addCust, logout, addEmpl, restockInv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empl_view);

        logout = findViewById(R.id.emplLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                openLoginPage();
            }
        });

        addEmpl = findViewById(R.id.addEmpl);
        addEmpl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                openEmplAddPage();
            }
        });

        addCust = findViewById(R.id.addCust);
        addCust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                openCustAddPage();
            }
        });

        restockInv = findViewById(R.id.restockInventory);
        restockInv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRestockPage();
            }
        });
    }

    private void openLoginPage(){
        Intent loginPage = new Intent(this, Login.class);
        startActivity(loginPage);
    }

    private void openEmplAddPage(){
        Intent openEmpl = new Intent(this, CreateEmpl.class);
        openEmpl.putExtra("Initialization", false);
        startActivity(openEmpl);
    }

    private void openCustAddPage(){
        Intent openCust = new Intent(this, CreateCust.class);
        startActivity(openCust);
    }

    private void openRestockPage() {
        Intent openRestock = new Intent(this, RestockPage.class);
        startActivity(openRestock);
    }
}
