package com.example.semana9_android01.zPokemon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.semana9_android01.R;
import com.example.semana9_android01.entities.Pokemon;
import com.example.semana9_android01.services.PokemonService;
import com.example.semana9_android01.zPokemon.PokemonMainActivity;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonResgistrarActivity extends AppCompatActivity {
    EditText etNumero;
    EditText etNombre;
    EditText etTIpo;
    Button btRegistar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_resgistrar);
         etNumero = findViewById(R.id.etNumeroP);
         etNombre = findViewById(R.id.etNombreP);
         etTIpo = findViewById(R.id.etTipoP);
         btRegistar = findViewById(R.id.btRegistrarP);

         Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://64787960362560649a2dda13.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PokemonService service = retrofit.create(PokemonService.class);

        btRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // int num = Integer.parseInt(etNumero.getText().toString());
                Pokemon pokemon = new Pokemon();
                pokemon.numero = etNumero.getText().toString();
                pokemon.nombre = etNombre.getText().toString();
                pokemon.tipo   = etTIpo.getText().toString();

                String urlimagen = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/"+ etNumero.getText().toString() + ".png";
                pokemon.urlimagen = urlimagen;

                Call<Pokemon> call = service.create(pokemon);
                call.enqueue(new Callback<Pokemon>() {
                    @Override
                    public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                        Log.i("MAIN_APP",String.valueOf(response.code()));
                        if (response.isSuccessful()) {
                            Pokemon data = response.body();
                            Log.i("MAIN_APP", new Gson().toJson(data));

                        }
                    }

                    @Override
                    public void onFailure(Call<Pokemon> call, Throwable t) {

                    }
                });
                Intent intent = new Intent(getApplicationContext(), PokemonMainActivity.class);
                startActivity(intent);
            }
        });






    }
}