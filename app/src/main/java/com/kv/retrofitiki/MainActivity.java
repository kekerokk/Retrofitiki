package com.kv.retrofitiki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.kv.retrofitiki.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding __binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        __binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(__binding.getRoot());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PostService service = retrofit.create(PostService.class);
        service.getById(1).enqueue(new Callback<User>(){
            @Override
            public void onResponse(Call<User> call, Response<User> responce){
                __binding.getRoot().post(() ->
                    __binding.textView.setText((CharSequence) responce.body().toString()));
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                __binding.getRoot().post(()->
                        Toast.makeText(getApplicationContext(),"Server Dosnt dostypen", Toast.LENGTH_SHORT).show());
            }
        });
    }
}