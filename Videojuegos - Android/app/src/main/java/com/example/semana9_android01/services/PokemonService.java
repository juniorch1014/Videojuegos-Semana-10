package com.example.semana9_android01.services;

import com.example.semana9_android01.entities.Pokemon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PokemonService {
    @GET("pokemon")
    Call<List<Pokemon>> getAllUser();

    // /users/1
    @GET("pokemon/{id}")
    Call<Pokemon> findUser(@Path("id") int id);

    // users
    @POST("pokemon")
    Call<Pokemon> create(@Body Pokemon user);

    @PUT("pokemon/{id}")
    Call<Pokemon> update(@Path("id") int id, @Body Pokemon user);

    @DELETE("pokemon/{id}")
    Call<Void> delete(@Path("id") int id);
}
