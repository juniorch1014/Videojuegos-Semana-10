package com.example.semana9_android01.zPokemon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.semana9_android01.R;
import com.example.semana9_android01.adapters.PokemonAdapter;
import com.example.semana9_android01.entities.Idguarda;
import com.example.semana9_android01.entities.Pokemon;
import com.example.semana9_android01.services.PokemonService;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonDetallesActivity extends AppCompatActivity {

    ImageView imagenP;
    TextView tvNumero;
    TextView tvNombre;
    TextView tvTipo;

    Button btEliminarP;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detalles);

        imagenP  = findViewById(R.id.imagenP);
        tvNumero = findViewById(R.id.nroPokemon);
        tvNombre = findViewById(R.id.nombrePokemon);
        tvTipo   = findViewById(R.id.tipoPokemon);
        btEliminarP = findViewById(R.id.btEliminar);



        int idObtener;
        idObtener = getIntent().getIntExtra("id",0);
        Log.d("APP_MAIN3", String.valueOf(idObtener));

//****************************************************************************************
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://64787960362560649a2dda13.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PokemonService service = retrofit.create(PokemonService.class);

        Call<Pokemon> call = service.findUser(idObtener);
        call.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (response.isSuccessful()) {
                    Pokemon data = response.body();

                    Picasso.get().load(data.urlimagen).into(imagenP);
                    tvNumero.setText(data.numero);
                    tvNombre.setText(data.nombre);
                    tvTipo.setText(data.tipo);

                    Log.i("MAIN_APP", new Gson().toJson(data));

                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {

            }
        });
//**************************************************************************************
        btEliminarP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Void> call = service.delete(idObtener);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.isSuccessful()) {
                            Intent intent = new Intent(getApplicationContext(), ListaPokemon.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });
    }
}