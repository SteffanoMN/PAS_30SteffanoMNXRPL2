package com.example.pas_30steffanoxrpl2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String name, phone, email, bio;
    private ArrayList<ContactModel> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("contacts");

        TextInputEditText txt_bio = findViewById(R.id.txt_bio);
        TextInputEditText txt_email = findViewById(R.id.txt_email);
        TextInputEditText txt_name = findViewById(R.id.txt_name);
        TextInputEditText txt_phone = findViewById(R.id.txt_phone);
        Button btn_add = findViewById(R.id.btn_add);
        Button btn_intent = findViewById(R.id.btn_intent);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = txt_name.getText().toString();
                phone = txt_phone.getText().toString();

                if (name.trim().isEmpty() || phone.trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill all the fields!", Toast.LENGTH_SHORT).show();
                } else {

                    String key = myRef.push().getKey();
                    ContactModel profile = new ContactModel(name, phone);
                    myRef.child(key).setValue(profile);

                    txt_name.setText("");
                    txt_phone.setText("");

                    Toast.makeText(getApplicationContext(), "Data has been successfully added!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_intent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ContactActivity.class);
                startActivity(intent);
            }
        });
    }
}