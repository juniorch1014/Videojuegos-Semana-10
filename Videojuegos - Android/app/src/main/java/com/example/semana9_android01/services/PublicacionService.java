package com.example.semana9_android01.services;

import com.example.semana9_android01.entities.Publicacion;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PublicacionService {
    @GET("publicacion")
    Call<List<Publicacion>> getAllUser();

    @GET("publicacion/{id}")
    Call<Publicacion> findUser(@Path("id") int id);

    // users
    @POST("publicacion")
    Call<Publicacion> create(@Body Publicacion user);

    @PUT("publicacion/{id}")
    Call<Publicacion> update(@Path("id") int id, @Body Publicacion user);

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
