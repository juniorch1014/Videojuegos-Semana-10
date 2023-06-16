package com.example.semana9_android01.zPublicacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.semana9_android01.R;
import com.example.semana9_android01.adapters.PaisajeAdapter;
import com.example.semana9_android01.entities.Paisajes;
import com.example.semana9_android01.services.PaisajeService;
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
        PaisajeService service = retrofit.create(PaisajeService.class);
        Call<List<Paisajes>> call = service.getAllUser();


        call.enqueue(new Callback<List<Paisajes>>() {
            @Override
            public void onResponse(Call<List<Paisajes>> call, Response<List<Paisajes>> response) {

                Log.i("MAIN_APP",String.valueOf(response.code()));

                if (response.isSuccessful()) {
                    List<Paisajes> data = response.body();

                    Log.i("MAIN_APP",String.valueOf(data.size()));
                    Log.i("MAIN_APP", new Gson().toJson(data));

                    PaisajeAdapter adapter = new PaisajeAdapter(data);
                    rvListaU.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Paisajes>> call, Throwable t) {

            }
        });
    }
}