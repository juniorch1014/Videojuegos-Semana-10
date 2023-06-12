package com.example.semana9_android01.zPokemon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.semana9_android01.R;
import com.example.semana9_android01.adapters.PublicacionAdapter;
import com.example.semana9_android01.entities.Publicacion;
import com.example.semana9_android01.services.PublicacionService;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaPublicacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_publicacion);

        Log.d("APP_MAIN", "ListasaaaasssAnimeeeeee");

        Log.d("APP_MAIN", "Useeeeeeeeeeeeeeeeer");


        RecyclerView rvListaU = findViewById(R.id.rvListaPokemon);
        rvListaU.setLayoutManager(new LinearLayoutManager(this));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://64787960362560649a2dda13.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PublicacionService service = retrofit.create(PublicacionService.class);
        Call<List<Publicacion>> call = service.getAllUser();


        call.enqueue(new Callback<List<Publicacion>>() {
            @Override
            public void onResponse(Call<List<Publicacion>> call, Response<List<Publicacion>> response) {

                Log.i("MAIN_APP",String.valueOf(response.code()));

                if (response.isSuccessful()) {
                    List<Publicacion> data = response.body();

                    Log.i("MAIN_APP",String.valueOf(data.size()));
                    Log.i("MAIN_APP", new Gson().toJson(data));

                    PublicacionAdapter adapter = new PublicacionAdapter(data);
                    rvListaU.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Publicacion>> call, Throwable t) {

            }
        });
    }
}