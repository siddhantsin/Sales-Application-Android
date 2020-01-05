package com.b07.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.view.R;

public class LoginAccountAlreadyExist extends AppCompatActivity {

    private Button cont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_account_already_exist);

        cont = findViewById(R.id.contButton);
        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                openLogin();
            }
        });
    }
    public void openLogin(){
        Intent openLogin = new Intent (this, Login.class);
        startActivity(openLogin);
    }
}
