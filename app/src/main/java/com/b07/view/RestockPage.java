package com.b07.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.b07.model.database.helper.DatabaseDriverAndroidHelper;
import com.example.view.R;

public class RestockPage extends AppCompatActivity {

    EditText itemIdEt, quantityEt;
    Button restockBtn;
    DatabaseDriverAndroidHelper myDb;
    int quantity;
    int itemId;

    @Override
    public void onCreate(Bundle savedIntstances){
        super.onCreate(savedIntstances);
        setContentView(R.layout.activity_restock_page);
        myDb = new DatabaseDriverAndroidHelper(this);

        itemIdEt = findViewById(R.id.restock_item_id);
        quantityEt = findViewById(R.id.restock_quantity);
        restockBtn = findViewById(R.id.restock_btn);

        restockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = Integer.valueOf(quantityEt.getText().toString());
                itemId = Integer.valueOf(itemIdEt.getText().toString());

                myDb.updateInventoryQuantityHelper(quantity, itemId);
            }
        });

    }

}
