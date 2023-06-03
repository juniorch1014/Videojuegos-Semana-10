package com.example.semana9_android01.zPokemon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.semana9_android01.R;

public class PokemonMainActivity extends AppCompatActivity {

    Button btMisPokemones;
    Button btRegistrarPokemones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_main);

        btMisPokemones = findViewById(R.id.btMisPokemones);
        btRegistrarPokemones = findViewById(R.id.btRegistrarPokemones);

        btMisPokemones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListaPokemon.class);
                startActivity(intent);
            }
        });
        btRegistrarPokemones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PokemonResgistrarActivity.class);
                startActivity(intent);
            }
        });
    }
}