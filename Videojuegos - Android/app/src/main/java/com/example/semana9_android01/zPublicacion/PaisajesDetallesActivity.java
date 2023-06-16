package com.example.semana9_android01.zPublicacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.example.semana9_android01.entities.LocationData;
import com.example.semana9_android01.entities.Paisajes;
import com.example.semana9_android01.mapasController.MapsMostrarActivity;
import com.example.semana9_android01.services.ComentarioService;
import com.example.semana9_android01.services.PaisajeService;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PaisajesDetallesActivity extends AppCompatActivity {

    ImageView imagenP;
    TextView tvPaisajeDescrip;
    TextView tvNombre;
    TextView tvTipo;
    TextView tvLatitudDet;
    TextView tvLongitudDet;
    RecyclerView rvComentarios;
    Button btEliminarP;
    Button btAddComent;
    Button btMapaDet;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicacion_detalles);

        imagenP  = findViewById(R.id.imagenP);
        tvPaisajeDescrip = findViewById(R.id.tvPaisajeDescrip);
        tvLatitudDet     = findViewById(R.id.tvLatitudDetalle);
        tvLongitudDet    = findViewById(R.id.tvLongitudDetalle);

        btEliminarP = findViewById(R.id.btEliminar);
        btAddComent = findViewById(R.id.btAddComentario);
        btMapaDet   = findViewById(R.id.btMapaDet);
        rvComentarios = findViewById(R.id.rvComentarios);
        rvComentarios.setLayoutManager(new LinearLayoutManager(this));



        int idObtener;
        idObtener = getIntent().getIntExtra("id",0);
        Log.d("APP_MAIN3", String.valueOf(idObtener));

//****************************************************************************************
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://64787960362560649a2dda13.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PaisajeService service = retrofit.create(PaisajeService.class);

        Call<Paisajes> call = service.findUser(idObtener);
        call.enqueue(new Callback<Paisajes>() {
            @Override
            public void onResponse(Call<Paisajes> call, Response<Paisajes> response) {
                if (response.isSuccessful()) {
                    Paisajes data = response.body();

                    Picasso.get().load(data.urlimagen).into(imagenP);
                    tvPaisajeDescrip.setText(data.nombrePasaje);
                    tvLatitudDet.setText(data.latitud);
                    tvLongitudDet.setText(data.longitud);

                    Log.i("MAIN_APP", new Gson().toJson(data));

                }
            }

            @Override
            public void onFailure(Call<Paisajes> call, Throwable t) {

            }
        });
        ComentarioService serviceC = retrofit.create(ComentarioService.class);
        Call<List<Comentario>> callC = serviceC.getAllUser();
        callC.enqueue(new Callback<List<Comentario>>() {
            @Override
            public void onResponse(Call<List<Comentario>> call, Response<List<Comentario>> response) {
                if (response.isSuccessful()) {
                    List<Comentario> dataC = response.body();

                    Log.i("MAIN_APP1",String.valueOf(dataC.size()));
                    Log.i("MAIN_APP1", new Gson().toJson(dataC));

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
//**************************************************************************************
        btAddComent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getApplicationContext(), ComentarioAgregarActivity.class);
                intent.putExtra("id1", idObtener);
                Log.i("APP_MAIN8", String.valueOf(idObtener));
               startActivity(intent);


            }
        });
//*************************************************************************************
        btMapaDet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomPaisaje = tvPaisajeDescrip.getText().toString();
                Double latitude = Double.valueOf(tvLatitudDet.getText().toString());
                Double longitude = Double.valueOf(tvLongitudDet.getText().toString());

                Log.i("MAIN_APPDet: Location - ",  "Latitude: " + latitude);
                Log.i("MAIN_APPDet: Location - ",  "Longitude: " + longitude);

                LocationData.getInstance().setCoordinates(latitude,longitude);
                Intent intent = new Intent(getApplicationContext(), MapsMostrarActivity.class);
                intent.putExtra("paisaje",nomPaisaje);
                startActivity(intent);
            }
        });
    }
}