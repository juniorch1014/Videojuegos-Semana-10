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
import com.example.semana9_android01.entities.Idguarda;
import com.example.semana9_android01.entities.Pokemon;
import com.example.semana9_android01.zPokemon.PokemonDetallesActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PokemonAdapter  extends RecyclerView.Adapter {
    private List<Pokemon> items;


    public PokemonAdapter(List<Pokemon> items){
        this.items = items;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_pokemon, parent, false);
        NameViewHolder viewHolder = new NameViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Pokemon item = items.get(position);
        View view = holder.itemView;

        TextView tvnumero = view.findViewById(R.id.tvNumeroPokemon);
        TextView tvnombre = view.findViewById(R.id.tvNombrePokemon);
        TextView tvetipo = view.findViewById(R.id.tvTipoPokemon);
        ImageView imagenPokemon= view.findViewById(R.id.ImagenPokemon);

        //ImageView estrella = view.findViewById(R.id.Estrella);

        tvnumero.setText(item.numero);
        tvnombre.setText(item.nombre);
        tvetipo.setText(item.tipo);


        Picasso.get().load(item.urlimagen).into(imagenPokemon);

        Button btDetalles = view.findViewById(R.id.btDetalles);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PokemonDetallesActivity.class);
                intent.putExtra("id", item.id);
                v.getContext().startActivity(intent);
                Log.d("APP_MAIN1", String.valueOf(item.id));
            }
        });

        btDetalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PokemonDetallesActivity.class);
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
