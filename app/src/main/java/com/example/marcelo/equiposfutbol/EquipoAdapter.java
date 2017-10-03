package com.example.marcelo.equiposfutbol;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcelo on 03/10/2017.
 */

public class EquipoAdapter extends RecyclerView.Adapter<EquipoAdapter.EquipoViewholder> {

    List<Equipo> dataset;
    Context context;
    onEquipoSelectedListener onEquipoSelectedListener;

    public interface onEquipoSelectedListener{
        void onEquipoSelected(Equipo equipo);
    }

    public EquipoAdapter(Context context, onEquipoSelectedListener onEquipoSelectedListener) {
        this.context = context;
        this.dataset = new ArrayList<>();
        this.onEquipoSelectedListener = onEquipoSelectedListener;
    }

    @Override
    public EquipoViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_equipo,parent,false);
        return new EquipoViewholder(view);
    }

    @Override
    public void onBindViewHolder(EquipoViewholder holder, int position) {
        Equipo equipo = dataset.get(position);
        holder.nombre.setText(equipo.getNombre());
        holder.imagen.setImageResource(equipo.getImagen());

        holder.setOnEquipoSelectedListener(equipo, onEquipoSelectedListener);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class EquipoViewholder extends RecyclerView.ViewHolder {

        View cardView;
        ImageView imagen;
        TextView nombre;

        public EquipoViewholder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_main);
            imagen = itemView.findViewById(R.id.imagen_item);
            nombre = itemView.findViewById(R.id.texto_item);
        }

        public void setOnEquipoSelectedListener(final Equipo equipo, final onEquipoSelectedListener onEquipoSelectedListener) {
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onEquipoSelectedListener.onEquipoSelected(equipo);
                }
            });
        }
    }

    public void setDataset(List<Equipo> equipos) {
        if (equipos == null){
            dataset = new ArrayList<>();
        }
        else{
            dataset = equipos;
        }
        notifyDataSetChanged();
    }
}
