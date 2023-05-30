package com.example.semana9_android01.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.semana9_android01.R;
import com.example.semana9_android01.entities.Anime;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AnimeAdapter extends RecyclerView.Adapter {
    private List<Anime> items;

    public AnimeAdapter(List<Anime> items){
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.items__anime, parent, false);
        AnimeAdapter.NameViewHolder viewHolder = new AnimeAdapter.NameViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Anime item = items.get(position);
        View view = holder.itemView;

        TextView nombreAnime = view.findViewById(R.id.tvNombreAnime);
        TextView descripcionAnime = view.findViewById(R.id.tvDescripcionAnime);
        ImageView imagenAnime = view.findViewById(R.id.ImagenAnime);

        ImageView estrella = view.findViewById(R.id.Estrella);

        if(!item.getEstrella()){
           estrella.setImageResource(R.drawable.blanca);
        }else {
           estrella.setImageResource(R.drawable.negra);
        }

        nombreAnime.setText(item.getNombreAnime());
        descripcionAnime.setText(item.getDescripAnime());
        Picasso.get().load(item.getLinkImaAnime()).into(imagenAnime);

        estrella.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (item.getEstrella() == false) {
                    estrella.setImageResource(R.drawable.negra);
                    item.setEstrella(true);
                }else{
                    estrella.setImageResource(R.drawable.blanca);
                    item.setEstrella(false);
                }


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
