package com.example.semana9_android01.zPokemon;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.Manifest;
import android.widget.Toast;

import com.example.semana9_android01.R;
import com.example.semana9_android01.entities.Publicacion;

import com.example.semana9_android01.services.PublicacionService;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PublicacionResgistrarActivity extends AppCompatActivity {
    ImageView ivPhoto;
    EditText etPublicacion;
    EditText etTIpo;
    Button btCamara;
    Button btGaleria;
    Button btRegistar;

    String urlImage = "";
    private static final int OPEN_CAMERA_REQUEST = 1001;
    private static final int OPEN_GALLERY_REQUEST = 1002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicacion_resgistrar);

         ivPhoto = findViewById(R.id.ivPhoto);
         etPublicacion = findViewById(R.id.etPublicacionR);

         btCamara = findViewById(R.id.btCamara);
         btGaleria = findViewById(R.id.btGaleria);
         btRegistar = findViewById(R.id.btRegistrarP);

         Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://64787960362560649a2dda13.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PublicacionService service = retrofit.create(PublicacionService.class);

        btRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // int num = Integer.parseInt(etNumero.getText().toString());
                Publicacion publicacion = new Publicacion();
                // pokemon.numero = etNumero.getText().toString();
                publicacion.descripcion = etPublicacion.getText().toString();
                publicacion.urlimagen = String.valueOf("https://demo-upn.bit2bittest.com/"+urlImage);


                //String urlimagen = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/"+ etNumero.getText().toString() + ".png";
                //pokemon.urlimagen = urlimagen;

                Call<Publicacion> call = service.create(publicacion);
                call.enqueue(new Callback<Publicacion>() {
                    @Override
                    public void onResponse(Call<Publicacion> call, Response<Publicacion> response) {
                        Log.i("MAIN_APP",String.valueOf(response.code()));
                        if (response.isSuccessful()) {
                            Publicacion data = response.body();
                            Log.i("MAIN_APP", new Gson().toJson(data));

                        }
                    }

                    @Override
                    public void onFailure(Call<Publicacion> call, Throwable t) {

                    }
                });
                Intent intent = new Intent(getApplicationContext(), PublicacionMainActivity.class);
                startActivity(intent);
            }
        });

        btCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOpenCamera();
            }
        });
        btGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    openGallery();
                }
                else {
                    String[] permissions = new String[] {Manifest.permission.READ_EXTERNAL_STORAGE};
                    requestPermissions(permissions, 2000);
                }
            }
        });






    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == OPEN_CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ivPhoto.setImageBitmap(photo);

            String base64 = BitmaptoBase64(photo);

            base64toLink(base64);

        }
        if(requestCode == OPEN_GALLERY_REQUEST && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close(); // close cursor

            Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
            ivPhoto.setImageBitmap(bitmap);

            String base64 = BitmaptoBase64(bitmap);

            base64toLink(base64);
        }
    }



    private void handleOpenCamera() {
        if(checkSelfPermission(Manifest.permission.CAMERA)  == PackageManager.PERMISSION_GRANTED)
        {
            // abrir camara
            Log.i("MAIN_APP", "Tiene permisos para abrir la camara");
            openCamara();
        } else {
            // solicitar el permiso
            Log.i("MAIN_APP", "No tiene permisos para abrir la camara, solicitando");
            String[] permissions = new String[] {Manifest.permission.CAMERA};
            requestPermissions(permissions, 1001);
        }
    }

    private void openCamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, OPEN_CAMERA_REQUEST);
    }
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, OPEN_GALLERY_REQUEST);
    }


    private String BitmaptoBase64 (Bitmap imagenBitmap){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        imagenBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();

        String Resulbase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
        Log.i("APP_MAIN", Resulbase64);
        return Resulbase64;
    }

    private void base64toLink(String base64) {
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl("https://demo-upn.bit2bittest.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PublicacionService service = retrofit1.create(PublicacionService.class);
        Call<PublicacionService.ImagenResponse> call = service.guardarImage(new PublicacionService.ImagenToSave(base64));
        call.enqueue(new Callback<PublicacionService.ImagenResponse>() {
            @Override
            public void onResponse(Call<PublicacionService.ImagenResponse> call, Response<PublicacionService.ImagenResponse> response) {
                if (response.isSuccessful()) {
                    PublicacionService.ImagenResponse imageResponse = response.body();
                    Log.i("Respues", response.toString());
                    urlImage = imageResponse.getUrl();
                    Toast.makeText(getBaseContext(), "Link GENERADO", Toast.LENGTH_SHORT).show();
                    Log.i("Imagen url:", urlImage);

                } else {

                    Log.e("Error cargar imagen",response.toString());
                }
            }

            @Override
            public void onFailure(Call<PublicacionService.ImagenResponse> call, Throwable t) {

            }
        });

    }

//    private static final int REQUEST_CAMERA = 1;
//    private static final int REQUEST_IMAGE_CAPTURE = 2;
//    private void takePhoto(Context context, ImageView photoTomada) {
//        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
//        } else {
//            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            if (intent.resolveActivity(context.getPackageManager()) != null) {
//                ((Activity) context).startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
//            } else {
//                Toast.makeText(context, "No se pudo abrir la cámara", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
}