package com.example.sqlitenote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;

    AppCompatEditText userNameE, userLocationE, phoneNumberE;
    AppCompatTextView orderNoT, userNameT, userLocationT, phoneNumberT, viewDataT;
    AppCompatButton saveDataB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);

        userNameE = findViewById(R.id.userNameE);
        userLocationE = findViewById(R.id.userLocationE);
        phoneNumberE = findViewById(R.id.phoneNumberE);

        orderNoT = findViewById(R.id.orderNoT);
        userNameT = findViewById(R.id.userNameT);
        userLocationT = findViewById(R.id.userLocationT);
        phoneNumberT = findViewById(R.id.phoneNumberT);

        saveDataB = findViewById(R.id.saveDataB);
        viewDataT = findViewById(R.id.viewDataT);

        saveDataB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name, location, phone;

                name = String.valueOf(userNameE.getText());
                location = String.valueOf(userLocationE.getText());
                phone = String.valueOf(phoneNumberE.getText());

                if (!name.isEmpty() && !location.isEmpty() && !phone.isEmpty()) {

                    Model model = new Model(0, userNameE.getText().toString(), userLocationE.getText().toString(), phoneNumberE.getText().toString());
                    dbHelper.addData(model);

                    userNameE.setText("");
                    userLocationE.setText("");
                    phoneNumberE.setText("");
                    viewDataT.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), "Save your data successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Enter yor full details", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewDataT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ViewDataActivity.class);
                startActivity(intent);
            }
        });
    }
}