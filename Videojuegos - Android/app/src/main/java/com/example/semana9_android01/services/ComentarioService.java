package com.example.semana9_android01.services;

import com.example.semana9_android01.entities.Comentario;
import com.example.semana9_android01.entities.Publicacion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ComentarioService {


    @GET("comentarios")
    Call<List<Comentario>> getAllUser();

    @GET("comentarios/{id}")
    Call<Comentario> findUser(@Path("id") int id);

    // users
    @POST("comentarios")
    Call<Comentario> create(@Body Comentario user);

//    @PUT("publicacion/{id}")
//    Call<Publicacion> update(@Path("id") int id, @Body Publicacion user);
//
//    @DELETE("publicacion/{id}")
//    Call<Void> delete(@Path("id") int id);

    @POST ("image")
    Call<PublicacionService.ImagenResponse> guardarImage (@Body PublicacionService.ImagenToSave imagen);

}
