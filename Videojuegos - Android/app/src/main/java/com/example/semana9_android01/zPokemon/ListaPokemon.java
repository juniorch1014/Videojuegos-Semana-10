package com.example.semana9_android01.zPokemon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.semana9_android01.R;
import com.example.semana9_android01.adapters.AnimeAdapter;
import com.example.semana9_android01.adapters.PokemonAdapter;
import com.example.semana9_android01.entities.Persona;
import com.example.semana9_android01.entities.Pokemon;
import com.example.semana9_android01.services.PokemonService;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaPokemon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pokemon);

        Log.d("APP_MAIN", "ListasaaaasssAnimeeeeee");

        Log.d("APP_MAIN", "Useeeeeeeeeeeeeeeeer");


        RecyclerView rvListaU = findViewById(R.id.rvListaPokemon);
        rvListaU.setLayoutManager(new LinearLayoutManager(this));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://64787960362560649a2dda13.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PokemonService service = retrofit.create(PokemonService.class);
        Call<List<Pokemon>> call = service.getAllUser();


        call.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {

                Log.i("MAIN_APP",String.valueOf(response.code()));

                if (response.isSuccessful()) {
                    List<Pokemon> data = response.body();

                    Log.i("MAIN_APP",String.valueOf(data.size()));
                    Log.i("MAIN_APP", new Gson().toJson(data));

                    PokemonAdapter adapter = new PokemonAdapter(data);
                    rvListaU.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {

            }
        });
    }
}