package com.example.semana9_android01.adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.semana9_android01.R;
import com.example.semana9_android01.entities.Publicacion;
import com.example.semana9_android01.zPokemon.PublicacionDetallesActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PublicacionAdapter extends RecyclerView.Adapter {
    private List<Publicacion> items;


    public PublicacionAdapter(List<Publicacion> items){
        this.items = items;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_publicacion, parent, false);
        NameViewHolder viewHolder = new NameViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Publicacion item = items.get(position);
        View view = holder.itemView;


        TextView tvDescripcionPubli = view.findViewById(R.id.tvDescripcionPublicacion);

        ImageView imagenPokemon= view.findViewById(R.id.ImagenPokemon);

        //ImageView estrella = view.findViewById(R.id.Estrella);


        tvDescripcionPubli.setText(item.descripcion);



        Picasso.get().load(item.urlimagen).into(imagenPokemon);

        Button btComentarios = view.findViewById(R.id.btDetalles);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PublicacionDetallesActivity.class);
                intent.putExtra("id", item.id);
                v.getContext().startActivity(intent);
                Log.d("APP_MAIN1", String.valueOf(item.id));
            }
        });

        btComentarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PublicacionDetallesActivity.class);
                intent.putExtra("id", item.id);
                v.getContext().startActivity(intent);
                Log.d("APP_MAIN2", String.valueOf(item.id));
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
}
