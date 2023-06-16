package com.example.semana9_android01.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.semana9_android01.R;
import com.example.semana9_android01.entities.Comentario;

import java.util.List;

public class ComentarioAdapter extends RecyclerView.Adapter {

    private List<Comentario> items;


    public ComentarioAdapter(List<Comentario> items){
        this.items = items;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_comentarios, parent, false);
        NameViewHolder viewHolder = new NameViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Comentario item = items.get(position);
        View view = holder.itemView;


        TextView tvComentario = view.findViewById(R.id.tvComentario);

        //ImageView imagenPokemon= view.findViewById(R.id.ImagenPokemon);

        //ImageView estrella = view.findViewById(R.id.Estrella);

        tvComentario.setText(item.comentarios);
        //tvDescripcionPubli.setText(item.descripcion);



        //Picasso.get().load(item.urlimagen).into(imagenPokemon);

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
