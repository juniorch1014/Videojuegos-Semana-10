package com.example.semana9_android01.zPokemon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.semana9_android01.R;
import com.example.semana9_android01.adapters.ComentarioAdapter;
import com.example.semana9_android01.entities.Comentario;
import com.example.semana9_android01.entities.Publicacion;
import com.example.semana9_android01.services.ComentarioService;
import com.example.semana9_android01.services.PublicacionService;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PublicacionDetallesActivity extends AppCompatActivity {

    ImageView imagenP;
    TextView nroPublicDescrip;
    TextView tvNombre;
    TextView tvTipo;
    RecyclerView rvComentarios;
    Button btEliminarP;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicacion_detalles);

        imagenP  = findViewById(R.id.imagenP);
        nroPublicDescrip = findViewById(R.id.nroPublicDescrip);
        btEliminarP = findViewById(R.id.btEliminar);
        rvComentarios = findViewById(R.id.rvComentarios);



        int idObtener;
        idObtener = getIntent().getIntExtra("id",0);
        Log.d("APP_MAIN3", String.valueOf(idObtener));

//****************************************************************************************
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://64787960362560649a2dda13.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PublicacionService service = retrofit.create(PublicacionService.class);

        Call<Publicacion> call = service.findUser(idObtener);
        call.enqueue(new Callback<Publicacion>() {
            @Override
            public void onResponse(Call<Publicacion> call, Response<Publicacion> response) {
                if (response.isSuccessful()) {
                    Publicacion data = response.body();

                    Picasso.get().load(data.urlimagen).into(imagenP);
                    nroPublicDescrip.setText(data.descripcion);
                    Log.i("MAIN_APP", new Gson().toJson(data));

                }
            }

            @Override
            public void onFailure(Call<Publicacion> call, Throwable t) {

            }
        });
        ComentarioService serviceC = retrofit.create(ComentarioService.class);
        Call<List<Comentario>> callC = serviceC.getAllUser();
        callC.enqueue(new Callback<List<Comentario>>() {
            @Override
            public void onResponse(Call<List<Comentario>> call, Response<List<Comentario>> response) {
                if (response.isSuccessful()) {
                    List<Comentario> dataC = response.body();

                    Log.i("MAIN_APP",String.valueOf(dataC.size()));
                    Log.i("MAIN_APP", new Gson().toJson(dataC));

                    ComentarioAdapter adapter = new ComentarioAdapter(dataC);
                    rvComentarios.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Comentario>> call, Throwable t) {

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
                            Intent intent = new Intent(getApplicationContext(), ListaPublicacion.class);
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