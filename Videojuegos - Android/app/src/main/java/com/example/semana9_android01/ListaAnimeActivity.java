package com.example.semana9_android01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.semana9_android01.adapters.AnimeAdapter;
import com.example.semana9_android01.adapters.NameAdapter;
import com.example.semana9_android01.entities.Anime;
import com.example.semana9_android01.entities.Persona;

import java.util.ArrayList;
import java.util.List;

public class ListaAnimeActivity extends AppCompatActivity {

    private List<Anime> listaAnime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_anime);

        Log.d("APP_MAIN", "ListasaaaasssAnimeeeeee");

        AnimeAdapter adapter = new AnimeAdapter(dataAnime());

        RecyclerView rvLista = findViewById(R.id.rvListaAnime);
        rvLista.setLayoutManager(new LinearLayoutManager(this));
        rvLista.setAdapter(adapter);
    }
    private List<Anime> dataAnime(){
        listaAnime = new ArrayList<>();


        listaAnime.add(new Anime("Kimetsu no Yaiba","también conocida bajo su nombre en inglés Demon Slayer, o en español Guardianes de la Noche es una serie de manga escrita e ilustrada por Koyoharu Gotōge","https://i.imgur.com/8h0iqqC.png",true));
        listaAnime.add(new Anime("Dragon Slayer","Los Dragon Slayer se han visto clasificados por los Dragón Slayer del Viejo Estilo y los Dragon Slayer de la Segunda Generación y por último los más recientes los","https://i.imgur.com/iPhQNlT.png",false));
        listaAnime.add(new Anime("Black Clover","Black Clover: Sword of the Wizard King es una próxima película animada japonesa dirigida por Ayataka Tanemura y producida por Pierrot.","https://i.imgur.com/AQlJnRN.png",false));
        listaAnime.add(new Anime("Attack on Titan","también conocida en países de habla hispana como Ataque a los titanes y Ataque de los titanes, es una serie de manga japonesa escrita","https://i.imgur.com/McY5s0b.jpg",false));
        listaAnime.add(new Anime("Dragon Ball Z","Un valiente joven con poderes increíbles se aventura hacia un viaje místico en tierras exóticas llenas de guerreros ","https://i.imgur.com/cPQw9N6.png",true));






        return listaAnime;
    }
}