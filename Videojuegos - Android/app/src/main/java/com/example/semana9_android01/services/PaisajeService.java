package com.example.semana9_android01.services;

import com.example.semana9_android01.entities.Paisajes;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PaisajeService {
    @GET("publicacion")
    Call<List<Paisajes>> getAllUser();

    @GET("publicacion/{id}")
    Call<Paisajes> findUser(@Path("id") int id);

    // users
    @POST("publicacion")
    Call<Paisajes> create(@Body Paisajes user);

    @PUT("publicacion/{id}")
    Call<Paisajes> update(@Path("id") int id, @Body Paisajes user);

    @DELETE("publicacion/{id}")
    Call<Void> delete(@Path("id") int id);

    @POST ("image")
    Call<ImagenResponse> guardarImage (@Body ImagenToSave imagen);


    class  ImagenResponse{
        @SerializedName("url")
        private String url;

        public String getUrl() {
            return url;
        }
    }
    class ImagenToSave{
        String base64Image;

        public ImagenToSave(String base64Image){
            this.base64Image = base64Image;
        }
    }
}
