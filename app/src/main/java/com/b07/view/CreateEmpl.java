package com.b07.view;

import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.b07.model.database.DatabaseDriver;
import com.b07.model.database.DatabaseDriverAndroid;
import com.b07.model.database.helper.DatabaseDriverAndroidHelper;
import com.example.view.R;
import com.b07.view.InitializationAdmin;

import static com.b07.model.database.helper.DatabaseInsertHelper.insertNewUser;

public class CreateEmpl extends AppCompatActivity {

    Context context;
    EditText nameInput;
    EditText ageInput;
    EditText passInput;
    EditText addressInput;
    String name, pass, address;
    int age;
    DatabaseDriverAndroidHelper myDb;
    private Button InitialEmpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initialization_employee);

        nameInput = (EditText) findViewById(R.id.unTbInitialE);
        ageInput = (EditText) findViewById(R.id.ageTbInitialE);
        addressInput = (EditText) findViewById(R.id.addTbInitialE);
        passInput = (EditText) findViewById(R.id.pwTbInitialE);

        myDb = new DatabaseDriverAndroidHelper(this);
        InitialEmpl = findViewById(R.id.createInitialEmplButton);
        InitialEmpl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                name = nameInput.getText().toString();
                age = Integer.valueOf(ageInput.getText().toString());
                pass = passInput.getText().toString();
                address = addressInput.getText().toString();

                long employeeId = myDb.insertNewUserHelper(name, age, address, pass, 2);

                if (employeeId > 0){
                    ToastFactory.showAccountMadeToast(context, 2, employeeId);
                    if (getIntent().getBooleanExtra("Initialization", false)) {
                        openLoginP();
                    } else {
                        openEmployeeView();
                    }
                } else {
                    ToastFactory.showErrorToast(context);
                }
            }
        });
    }

    public void openLoginP(){
        Intent openLogin = new Intent(this, Login.class);
        //long userId = driverObj.insertNewUser(name, age, address, pass);
        //pop up the id at this point.
        startActivity(openLogin);
    }

    public void openEmployeeView(){
        Intent openLogin = new Intent(this, EmplView.class);
        //long userId = driverObj.insertNewUser(name, age, address, pass);
        //pop up the id at this point.
        startActivity(openLogin);
    }


}
