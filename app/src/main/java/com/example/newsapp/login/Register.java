package com.example.newsapp.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsapp.MainActivity;
import com.example.newsapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    EditText editText1,editText2;
    Button btn;
    Button button;
    FirebaseAuth mAuth;
    ProgressBar progressBar; // object
    TextView textView;

    @Override  // for checking if the user already have registered
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        editText1 = findViewById(R.id.edttxt1);
        editText2 = findViewById(R.id.edttxt2);
        btn = findViewById(R.id.btn);
        progressBar = findViewById(R.id.progressBar); // initializing
        textView = findViewById(R.id.txtview2);
        // button = findViewById(R.id.nextButton);

        //setContentView(R.layout.activity_register);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class); // intent for opening login activity
                //getToLogin();
                startActivity(intent);
                finish();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE); // visibility for the progress bar
                String email,password; // to read the text from edittext
                email = String.valueOf(editText1.getText());
                password = String.valueOf(editText2.getText());

                // to check whether the user enters something in the editbox else toasts a message
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Register.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;

                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Register.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(Register.this, "Account Successfully Created", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Login.class); // intent for opening login activity
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Register.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }

                            private void updateUI(FirebaseUser user) {
                            }
                        });
            }
        });


    }

    //private void getToLogin() {
    //    Intent intent = new Intent(this,Login.class);
    //    startActivity(intent);
    //}
}