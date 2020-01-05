package com.b07.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.b07.model.database.helper.DatabaseDriverAndroidHelper;
import com.example.view.R;

public class PromoteEmployee extends AppCompatActivity {

    EditText employeeId;
    Button promoteBtn;
    DatabaseDriverAndroidHelper myDb;
    Context context;

    @Override
    public void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
        setContentView(R.layout.activity_promote_employee);
        context = this;
        myDb = new DatabaseDriverAndroidHelper(this);

        employeeId = findViewById(R.id.promote_employee_id);

        promoteBtn = findViewById(R.id.promote_employee_btn);
        promoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.valueOf(employeeId.getText().toString());

                if(myDb.promoteEmployee(id)) {
                    ToastFactory.showEmployeePromotedToast(context, String.valueOf(id));
                } else {
                    ToastFactory.showErrorToast(context);
                }
            }
        });
    }

}
