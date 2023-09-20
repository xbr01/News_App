package com.example.newsapp;

import static android.icu.util.ULocale.getDisplayCountry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsapp.Database.FavoriteActivity;
import com.example.newsapp.Database.FavoriteDB;
import com.example.newsapp.Model.Articles;
import com.example.newsapp.Model.FirstPage;
import com.example.newsapp.login.Login;
import com.example.newsapp.retrofit.Adapter;
import com.example.newsapp.retrofit.ApiClient;
import com.example.newsapp.sharedpreference.SharedPreferenceActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button button;
    TextView textView;
    FirebaseUser user;

    private Context context;
    final String API_KEY = "9f23d4fbcbc744e78f295c7cc09c5c0f";
    RecyclerView recyclerView;
    //SwipeRefreshLayout swipeRefreshLayout;
    Adapter adapter;
    List<Articles> articles = new ArrayList<>(); //org     List<Articles> articles = new ArrayList<>();
    public static FavoriteDB favoriteDB;
    Button btn,sharedBtn,logoutBtn;
    FavoriteList favoriteList;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance(); // for login redirection
        button = findViewById(R.id.btn);
        textView = findViewById(R.id.txtview);
        user = auth.getCurrentUser(); // initialize current user
        logoutBtn = findViewById(R.id.logoutButton);
        sharedBtn = findViewById(R.id.sharedButton);

        // to check if user is null or not
        if (user == null){
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish(); // will finish the current activity and open login activity
        }
        else {
            Toast.makeText(MainActivity.this,"Logged in",Toast.LENGTH_SHORT);  // new
        }
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish(); // will finish the current activity and open login activity
            }
        }); // till this comment
        //swipeRefreshLayout = findViewById(R.id.swipeRefresh);

        context = MainActivity.this;
        recyclerView = findViewById(R.id.recyclerView);

        //editTextSearch = findViewById(R.id.editTextSearch);
        //buttonSearch = findViewById(R.id.buttonSearch);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final String country = getCountry();
        retrieveJson(country,API_KEY);


        btn = findViewById(R.id.userButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FavoriteActivity.class));
            }
        });

        sharedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToActivity();
            }
        });


/*        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                retrieveJson("",country,API_KEY);
            }
        });
        retrieveJson("",country,API_KEY);
*/

    }

    public void retrieveJson(String country, String apiKey){

//        swipeRefreshLayout.setRefreshing(true);
        Call<FirstPage> call = ApiClient.getInstance().getApi().getFirstPage(country, apiKey); // changed to API_KEY from apiKey
        call.enqueue(new Callback<FirstPage>() {
            @Override
            public void onResponse(Call<FirstPage> call, Response<FirstPage> response) {
                // removed the if condition for checking respose code is 200
                if (response.body().getArticles() != null && response.isSuccessful()) {
                    //swipeRefreshLayout.setRefreshing(false);
                    articles.clear();
                    articles = response.body().getArticles();
                    adapter = new Adapter(MainActivity.this,articles);
                    recyclerView.setAdapter(adapter);
//                    Log.d("articles", "" + articles);
                }
            }
            @Override
            public void onFailure(Call<FirstPage> call, Throwable t) {
//                Log.d("error",t.getLocalizedMessage());
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String getCountry(){
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }

    private void goToActivity() {
        Intent intent = new Intent(this, SharedPreferenceActivity.class);
        startActivity(intent);

    }
}