package com.b07.view;

import android.content.Context;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.b07.model.database.helper.DatabaseDriverAndroidHelper;
import com.example.view.R;

public class InitializationAdmin extends AppCompatActivity {

    DatabaseDriverAndroidHelper myDb;
    Context context;
    EditText nameInput;
    EditText ageInput;
    EditText passInput;
    EditText addressInput;
    String name, pass, address;
    int age;
    private Button initialAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initialization_admin);

        context = this;
        myDb = new DatabaseDriverAndroidHelper(this);

        nameInput = (EditText) findViewById(R.id.unTbInitialA);
        ageInput = (EditText) findViewById(R.id.ageTbInitialA);
        addressInput = (EditText) findViewById(R.id.addTbInitialA);
        passInput = (EditText) findViewById(R.id.pwTbInitialA);

        initialAdmin = findViewById(R.id.createInitialAdminButton);
        initialAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                name = nameInput.getText().toString();
                age = Integer.valueOf(ageInput.getText().toString());
                pass = passInput.getText().toString();
                address = addressInput.getText().toString();
                long adminId = myDb.insertNewUserHelper(name, age, address, pass, 1);

                if (adminId > 0) {
                    ToastFactory.showAccountMadeToast(context, 1, adminId);
                    openEmplInitial();
                }else{
                    ToastFactory.showErrorToast(context);
                }

            }
        });
    }

    public void openEmplInitial(){

        Intent openEmpl = new Intent(this, CreateEmpl.class);
        //long userId = (int)driverObj.insertNewUser(name, age, address, pass);
        //need a way to pop up the admin id
        openEmpl.putExtra("Initialization", true);
        startActivity(openEmpl);
    }

}