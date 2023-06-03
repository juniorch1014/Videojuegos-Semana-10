package com.example.semana9_android01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.semana9_android01.zPokemon.PokemonMainActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    Button BottonJugador;
    Button BReset;
    Button BList;
    Button BAnime;
    Button BPokemon;

    TextView NroJugador1;
    TextView NroJugador2;
    TextView Resultado;


    int a;
    int b;

    int aux = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    BottonJugador = findViewById(R.id.BottonJugador);
    BReset = findViewById(R.id.BReset);
    BList = findViewById(R.id.btLista);
    BAnime = findViewById(R.id.btAnime);
    BPokemon = findViewById(R.id.btPokemon);

    NroJugador1 = findViewById(R.id.NroJugador1);
    NroJugador2 = findViewById(R.id.NroJugador2);
    Resultado = findViewById(R.id.Resultado);


    BottonJugador.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (aux == 0){
                BottonJugador.setText("Jugador 2");
                a = generarAleatorio(1,10);
                NroJugador1.setText(String.valueOf(a));
            }
            if (aux == 1){
                //BottonJugador.setText("Jugador 2");
                b = generarAleatorio(1,10);
                NroJugador2.setText(String.valueOf(b));
            }
            aux++;
            if (aux > 1) {
                if(a<b){
                    Resultado.setText("JUGADOR 2");
                }
                if (a>b) {
                    Resultado.setText("JUGADOR 1");
                }
                if (a==b){
                    Resultado.setText("EMPATE");
                }
            }

        }
    });

    BReset.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            BottonJugador.setText("Jugador 1");
            aux = 0;
            NroJugador1.setText("");
            NroJugador2.setText("");
            Resultado.setText("");
        }
    });

    BList.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), ListaActivity.class);
            startActivity(intent);
        }
    });
    BAnime.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), ListaAnimeActivity.class);
            startActivity(intent);
        }
    });
    BPokemon.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), PokemonMainActivity.class);
            startActivity(intent);
        }
    });



    }


    private int generarAleatorio(int min, int max) {
        // Crear una instancia de la clase Random
        Random random = new Random();

        // Generar el número aleatorio dentro del rango especificado
        return random.nextInt(max - min + 1) + min;
    }
}