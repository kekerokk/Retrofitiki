package com.kv.retrofitiki;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PostService {
    @GET("users")
    Call<List<User>> getAll();

    @GET("users/{id}")
    Call<User> getById(@Path("id") int id);


}
