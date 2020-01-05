package com.b07.view;

import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.b07.model.database.DatabaseDriverAndroid;
import com.b07.model.database.helper.DatabaseDriverAndroidHelper;
import com.example.view.R;

public class MainActivity extends AppCompatActivity {
    private Button getStarted;
    DatabaseDriverAndroidHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_installation_page);
//        if (myDb.isAlreadyInitializedDatabase()){
//            openLoginP();
//        }
        myDb = new DatabaseDriverAndroidHelper(this);

        if(myDb.isAlreadyInitializedDatabase()){
            openLoginP();
        }

        getStarted = findViewById(R.id.getStartedButton);
        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                openGetStarted();
            }
        });
    }

    public void openGetStarted(){
        Intent gettingStarted = new Intent(this, InitializationAdmin.class);
        startActivity(gettingStarted);
    }

    public void openLoginP(){
        Intent openLogin = new Intent(this, Login.class);
        //long userId = driverObj.insertNewUser(name, age, address, pass);
        //pop up the id at this point.
        startActivity(openLogin);
    }
}
