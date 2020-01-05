package com.b07.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.view.R;

public class SuccessfulCheckout extends AppCompatActivity {

    private Button continuess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_checkout);

        continuess = findViewById(R.id.continues);
        continuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustViewPg();
            }
        });
    }

    public void openCustViewPg(){
        Intent custView = new Intent (this, CustomerView.class);
        startActivity(custView);
    }
}
