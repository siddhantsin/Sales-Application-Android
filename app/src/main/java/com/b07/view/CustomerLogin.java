package com.b07.view;

import android.content.Context;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.b07.controller.exceptions.ConnectionFailedException;
import com.b07.controller.store.ShoppingCart;
import com.b07.controller.store.ShoppingCartImpl;
import com.b07.controller.users.Customer;
import com.b07.controller.users.User;
import com.b07.model.database.helper.DatabaseDriverAndroidHelper;
import com.example.view.R;

public class CustomerLogin extends AppCompatActivity {

    Context context = this;
    EditText userNameInp, passwordInp;
    int userId;
    String password;
    Customer potentialCust;
    private Button loginB;
    DatabaseDriverAndroidHelper myDb;
    private String dbPassword;
    ShoppingCart shoppingCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        myDb = new DatabaseDriverAndroidHelper(this);

        loginB = findViewById(R.id.loginButton);
        userNameInp = (EditText) findViewById(R.id.usernameTb);
        passwordInp = (EditText) findViewById(R.id.passwordTb);

        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v3) {
                userId = Integer.valueOf(userNameInp.getText().toString());
                password = passwordInp.getText().toString();

                try {
                    potentialCust = (Customer) myDb.getUserDetailsHelper(userId);
                    dbPassword = myDb.getPassword(userId);
                    if (myDb.getUserRoleHelper(userId) != 3){
                        ToastFactory.showNotAuthenticatedToast(context);
                    } else if (potentialCust.authenticate(password, dbPassword)) {
                        openCustView();
                    } else {
                        ToastFactory.showNotAuthenticatedToast(context);
                    }
                } catch (Exception e) {
                    ToastFactory.showNotAuthenticatedToast(context);
                }
            }
        });
    }

    public void openCustView() throws ConnectionFailedException {
        Intent openCust = new Intent(this, CustomerView.class);
        shoppingCart = new ShoppingCartImpl(potentialCust);
        //openCust.putExtra("ID", potentialCust.getId());
        openCust.putExtra("ShoppingCart", shoppingCart);
        startActivity(openCust);
    }

}