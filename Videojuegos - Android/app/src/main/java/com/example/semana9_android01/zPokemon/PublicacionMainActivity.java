package com.example.semana9_android01.zPokemon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.semana9_android01.R;

public class PublicacionMainActivity extends AppCompatActivity {

    Button btMisPokemones;
    Button btRegistrarPokemones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicacion_main);

        btMisPokemones = findViewById(R.id.btMisPublicacion);
        btRegistrarPokemones = findViewById(R.id.btRegistrarPublicacion);

        btMisPokemones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListaPublicacion.class);
                startActivity(intent);
            }
        });
        btRegistrarPokemones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PublicacionResgistrarActivity.class);
                startActivity(intent);
            }
        });
    }
}