package com.example.newsapp.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.newsapp.R;

public class SharedPreferenceActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    EditText name,email;
    Button saveButton,openButton;
    String nameStr,emailStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);

        name = findViewById(R.id.nameET);
        email = findViewById(R.id.emailET);
        saveButton = findViewById(R.id.saveButton);
        openButton = findViewById(R.id.nextButton);

        sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameStr = name.getText().toString();
                emailStr = email.getText().toString();

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("name",nameStr);
                editor.putString("email",emailStr);
                editor.apply();

                Toast.makeText(SharedPreferenceActivity.this, "Saved", Toast.LENGTH_SHORT).show();
            }
        });

        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SharedPreferenceActivity.this, ShowSharedPreferences.class);
                startActivity(intent);
            }
        });


    }
}