package com.b07.view;

import android.content.Context;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.b07.model.database.helper.DatabaseDriverAndroidHelper;
import com.example.view.R;

public class CreateCust extends AppCompatActivity {

    Context context = this;
    EditText nameInput;
    EditText ageInput;
    EditText passInput;
    EditText addressInput;
    String name, pass, address;
    int age;
    DatabaseDriverAndroidHelper myDb;
    private Button createCust;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_cust);

        nameInput = (EditText) findViewById(R.id.unTbC);
        ageInput = (EditText) findViewById(R.id.ageTbC);
        addressInput = (EditText) findViewById(R.id.addTbC);
        passInput = (EditText) findViewById(R.id.pwTbC);

        myDb = new DatabaseDriverAndroidHelper(this);
        createCust = findViewById(R.id.createCustButton);
        createCust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                name = nameInput.getText().toString();
                age = Integer.valueOf(ageInput.getText().toString());
                pass = passInput.getText().toString();
                address = addressInput.getText().toString();

                long customerId = myDb.insertNewUserHelper(name, age, address, pass, 3);

                if (customerId > 0){
                    ToastFactory.showAccountMadeToast(context, 3, customerId);
                    openEmployeeView();
                } else {
                    ToastFactory.showErrorToast(context);
                }
            }
        });
    }

    public void openEmployeeView(){
        Intent openEmpl = new Intent(this, EmplView.class);
        startActivity(openEmpl);
    }
}