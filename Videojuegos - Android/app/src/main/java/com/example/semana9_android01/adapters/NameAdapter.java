package com.example.semana9_android01.adapters;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.semana9_android01.R;
import com.example.semana9_android01.entities.Persona;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NameAdapter extends RecyclerView.Adapter {

     private List<Persona> items;
     private static final int REQUEST_PHONE_CALL = 1;
     private static final int REQUEST_SEND_SMS = 1;

     public NameAdapter(List<Persona> items){
         this.items = items;
     }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.items_persona, parent, false);
        NameViewHolder viewHolder = new NameViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Persona item = items.get(position);
        View view = holder.itemView;

        TextView nombre = view.findViewById(R.id.tvNombreAnime);
        TextView telefono = view.findViewById(R.id.tvDescripcionAnime);
        ImageView imagenPersona = view.findViewById(R.id.ImagenAnime);

        ImageButton botonLlamar = view.findViewById(R.id.bottonEstrella);


        nombre.setText(item.getNombre());
        telefono.setText(item.getTelefono());

      //Picasso.get().load("https://i.imgur.com/DvpvklR.png").into(imagenPersona);
        Picasso.get().load(item.getLinkImagen()).into(imagenPersona);

        botonLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String telephone =item.getTelefono();
               String message = item.getLinkImagen();
               //dialPhoneNumber(telephone, v.getContext());
                sendTextMessage(telephone,message,v.getContext());

            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class NameViewHolder extends RecyclerView.ViewHolder {

        public NameViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private void dialPhoneNumber(String phoneNumber, Context context) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        Log.d("APP_MAIN","LLAMAR");
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
        }
        context.startActivity(intent);
    }
    private void sendTextMessage(String phoneNumber, String message,Context context){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + phoneNumber));
        intent.putExtra("sms_body", message);
        Log.d("APP_MAIN","MENSAJE");

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.SEND_SMS}, REQUEST_SEND_SMS);
        }
        context.startActivity(intent);



    }


}
