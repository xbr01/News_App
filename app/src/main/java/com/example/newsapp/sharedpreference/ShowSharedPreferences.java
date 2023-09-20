package com.example.newsapp.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.newsapp.R;

public class ShowSharedPreferences extends AppCompatActivity {

    TextView textView1,textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_shared_preferences);

        textView1 = findViewById(R.id.nameTV);
        textView2 = findViewById(R.id.emailTV);

        SharedPreferences sp = getApplicationContext().getSharedPreferences("User", Context.MODE_PRIVATE);
        String name = sp.getString("name","");
        String email = sp.getString("email","");

        textView1.setText(name);
        textView2.setText(email);

    }
}