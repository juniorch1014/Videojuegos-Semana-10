package com.example.semana9_android01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.semana9_android01.adapters.NameAdapter;
import com.example.semana9_android01.entities.Persona;

import java.util.ArrayList;
import java.util.List;

public class ListaActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CALL_PHONE = 1;
    private List<Persona> listaPersona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        Log.d("APP_MAIN", "Listasaaaasss");

        NameAdapter adapter = new NameAdapter(data());

        RecyclerView rvLista = findViewById(R.id.rvListaSimple);
        rvLista.setLayoutManager(new LinearLayoutManager(this));
        rvLista.setAdapter(adapter);

    }

    private List<Persona> data(){
        listaPersona = new ArrayList<>();


        listaPersona.add(new Persona("Junior","946380744","https://i.imgur.com/DvpvklR.png"));
        listaPersona.add(new Persona("Alison","936064716","https://i.imgur.com/vEx9K1p.jpg"));
        //Persona persona = new Persona();

      //  persona.Nombre = "Junior";
      //  persona.Telefono = "946380744";
      //  persona.linkImagen = "https://i.imgur.com/DvpvklR.png";
      //  listaPersona.add(persona);

     //   persona.Nombre = "Alison";
     //   persona.Telefono = "936064716";
     //   persona.linkImagen = "https://i.imgur.com/vEx9K1p.jpg";
     //   listaPersona.add(persona);

        return listaPersona;
    }

}