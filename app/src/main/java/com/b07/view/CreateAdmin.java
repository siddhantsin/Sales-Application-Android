package com.b07.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.view.R;

public class CreateAdmin extends AppCompatActivity {
    private Button createAdm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_admin);


        createAdm = findViewById(R.id.createAdminButton);
        createAdm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                openAdmPage();
            }
        });
    }

    public void openAdmPage(){
        Intent openAdmin = new Intent(this, AdminView.class);
        startActivity(openAdmin);
    }
}
