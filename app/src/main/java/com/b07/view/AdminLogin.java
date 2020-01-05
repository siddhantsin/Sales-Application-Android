package com.b07.view;

import android.content.Context;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.b07.controller.users.User;
import com.b07.model.database.helper.DatabaseDriverAndroidHelper;
import com.example.view.R;

public class AdminLogin extends AppCompatActivity {

    Context context = this;
    EditText userNameInp, passwordInp;
    int userId;
    String password;
    User potentialAdmin;
    private Button loginB;
    DatabaseDriverAndroidHelper myDb;
    private String dbPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        myDb = new DatabaseDriverAndroidHelper(this);

        loginB = findViewById(R.id.loginButtonA);
        userNameInp = (EditText) findViewById(R.id.usernameTbA);
        passwordInp = (EditText) findViewById(R.id.passwordTbA);

        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v3) {
                userId = Integer.valueOf(userNameInp.getText().toString());
                password = passwordInp.getText().toString();

                try {
                    potentialAdmin = myDb.getUserDetailsHelper(userId);
                    dbPassword = myDb.getPassword(userId);
                    if (myDb.getUserRoleHelper(userId) != 1){
                        ToastFactory.showNotAuthenticatedToast(context);
                    } else if (potentialAdmin.authenticate(password, dbPassword)) {
                        openAdmView();
                    } else {
                        ToastFactory.showNotAuthenticatedToast(context);
                    }
                } catch (Exception e) {
                    ToastFactory.showNotAuthenticatedToast(context);
                }
            }
        });
    }

    public void openAdmView() {
        Intent openAdm = new Intent(this, AdminView.class);
        startActivity(openAdm);
    }

}
