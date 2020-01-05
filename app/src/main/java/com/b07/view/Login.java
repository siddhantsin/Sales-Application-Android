package com.b07.view;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.example.view.R;

public class Login extends AppCompatActivity {

    private ImageButton emplb, adminb, custb;
    private TextView loginTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginTitle = findViewById(R.id.chooseLoginTitle);

        //connecting empl_view to login

        emplb = findViewById(R.id.Empl1);
        emplb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                openEmplView();
            }
        });

        //connecting to admin

        adminb = findViewById(R.id.Admin1);
        adminb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                openAdminView();
            }
        });

        //connecting to customer

        custb = findViewById(R.id.Cust1);
        custb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v3) {
                openCustView();
            }
        });
    }

    public void openEmplView(){
        Intent openEmpl = new Intent (this, EmplLogin.class);
        startActivity(openEmpl);
    }

    public void openAdminView(){
        Intent openAdmin = new Intent (this, AdminLogin.class);
        startActivity(openAdmin);
    }

    public void openCustView(){
        Intent openCust = new Intent (this, CustomerLogin.class);
        startActivity(openCust);
    }
}
