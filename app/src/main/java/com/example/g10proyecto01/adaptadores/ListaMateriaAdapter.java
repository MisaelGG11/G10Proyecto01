package com.example.g10proyecto01.adaptadores;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.g10proyecto01.Ciclo;
import com.example.g10proyecto01.CicloConsultarActivity;
import com.example.g10proyecto01.Materia;
import com.example.g10proyecto01.MateriaConsultarActivity;
import com.example.g10proyecto01.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaMateriaAdapter extends RecyclerView.Adapter<ListaMateriaAdapter.MateriaViewHolder> {
    ArrayList<Materia> listaMateria;
    ArrayList<Materia> listaOriginal;

    public ListaMateriaAdapter(ArrayList<Materia> listaMaterias) {
        this.listaMateria = listaMaterias;

        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaMaterias);
    }

    @NonNull
    @Override
    public MateriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_materia, null, false);
        return new MateriaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MateriaViewHolder holder, int position) {
        holder.viewIdMateria.setText(String.valueOf(listaMateria.get(position).getId_materia()));
        holder.viewCod.setText(listaMateria.get(position).getCod_materia());
        holder.viewNombre.setText(listaMateria.get(position).getNom_materia());
    }

    public void filtrado(final String txtBuscar) {
        int longitud = txtBuscar.length();

        if (longitud == 0) {
            listaMateria.clear();
            listaMateria.addAll(listaOriginal);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                List<Materia> collecion = listaMateria.stream()
                        .filter(i -> i.getNom_materia().toLowerCase().contains(txtBuscar.toLowerCase())
                                || i.getCod_materia().toLowerCase().contains(txtBuscar.toLowerCase())
                                || String.valueOf(i.getId_materia()).contains(txtBuscar))
                        .collect(Collectors.toList());

                listaMateria.clear();
                listaMateria.addAll(collecion);
            } else {
                for (Materia m : listaOriginal) {
                    if (m.getCod_materia().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        listaMateria.add(m);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaMateria.size();
    }

    public class MateriaViewHolder extends RecyclerView.ViewHolder {

        TextView viewIdMateria, viewNombre, viewCod;

        public MateriaViewHolder(@NonNull View itemView) {
            super(itemView);

            viewIdMateria = itemView.findViewById(R.id.viewIdMateria);
            viewCod = itemView.findViewById(R.id.viewCodMateria);
            viewNombre = itemView.findViewById(R.id.viewNombreMateria);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, MateriaConsultarActivity.class);
                    intent.putExtra("ID", listaMateria.get(getAdapterPosition()).getId_materia());
                    ((Activity) context).startActivityForResult(intent,1);
                }
            });
        }
    }
}
