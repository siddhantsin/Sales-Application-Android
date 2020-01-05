package com.b07.view;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.b07.controller.users.User;
import com.b07.model.database.helper.DatabaseDriverAndroidHelper;
import com.example.view.R;

public class EmplLogin extends AppCompatActivity {

    Context context = this;
    EditText userNameInp, passwordInp;
    int userId;
    String password;
    User potentialEmpl;
    private Button loginB;
    DatabaseDriverAndroidHelper myDb;
    private String dbPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empl_login);

        myDb = new DatabaseDriverAndroidHelper(this);

        loginB = findViewById(R.id.loginButtonE);
        userNameInp = (EditText) findViewById(R.id.usernameTbE);
        passwordInp = (EditText) findViewById(R.id.passwordTbE);

        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v3) {
                userId = Integer.valueOf(userNameInp.getText().toString());
                password = passwordInp.getText().toString();

                try {
                    potentialEmpl = myDb.getUserDetailsHelper(userId);
                    dbPassword = myDb.getPassword(userId);
                    if (myDb.getUserRoleHelper(userId) != 2){
                        ToastFactory.showNotAuthenticatedToast(context);
                    } else if (potentialEmpl.authenticate(password, dbPassword)) {
                        openEmplView();
                    } else {
                        ToastFactory.showNotAuthenticatedToast(context);
                    }
                } catch (Exception e) {
                    ToastFactory.showNotAuthenticatedToast(context);
                }
            }
        });
    }

    public void openEmplView() {
        Intent openEmpl = new Intent(this, EmplView.class);
        startActivity(openEmpl);
    }

}
