package com.example.semana9_android01.zPublicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.semana9_android01.R;
import com.example.semana9_android01.entities.Comentario;
import com.example.semana9_android01.services.ComentarioService;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ComentarioAgregarActivity extends AppCompatActivity {

    EditText edComentario;
    Button btComentar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario_agregar);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://64787960362560649a2dda13.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ComentarioService serviceC = retrofit.create(ComentarioService.class);



        int idPublicaion;
        idPublicaion = getIntent().getIntExtra("id1",0);
        Log.d("APP_MAIN9", String.valueOf(idPublicaion));


        edComentario = findViewById(R.id.edComentario);
        btComentar   = findViewById(R.id.btComentar);




        btComentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Comentario com = new Comentario();
                com.comentarios = edComentario.getText().toString();
                com.idPublicacion = idPublicaion;

                Call<Comentario> callC = serviceC.create(com);
                callC.enqueue(new Callback<Comentario>() {
                    @Override
                    public void onResponse(Call<Comentario> call, Response<Comentario> response) {
                        Log.i("MAIN_APPC",String.valueOf(response.code()));
                        if (response.isSuccessful()) {
                            Comentario data = response.body();
                            Log.i("MAIN_APP", new Gson().toJson(data));

                        }
                    }

                    @Override
                    public void onFailure(Call<Comentario> call, Throwable t) {

                    }
                });
                Intent intent = new Intent(getApplicationContext(), PaisajesDetallesActivity.class);

                startActivity(intent);
            }
        });


    }
}