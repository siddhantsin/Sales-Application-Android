package com.b07.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.view.R;

public class AdminView extends AppCompatActivity {

    private Button logOutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view);

    //logout button code
        logOutButton = findViewById(R.id.adminLogout);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                openLoginPage();
            }
        });
    }

    public void openLoginPage(){
        Intent log = new Intent(this, Login.class);
        log.putExtra("First letter", "a");
        startActivity(log);
    }
}
