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
import com.example.g10proyecto01.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaCicloAdapter extends RecyclerView.Adapter<ListaCicloAdapter.CicloViewHolder> {
    ArrayList<Ciclo> listaCiclo;
    ArrayList<Ciclo> listaOriginal;

    public ListaCicloAdapter(ArrayList<Ciclo> listaCiclos) {
        this.listaCiclo = listaCiclos;

        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaCiclos);
    }

    @NonNull
    @Override
    public CicloViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_ciclo, null, false);
        return new CicloViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CicloViewHolder holder, int position) {
        holder.viewAño.setText(listaCiclo.get(position).getAño());
        holder.viewIdCiclo.setText("Id: " + listaCiclo.get(position).getId_ciclo());
        holder.viewCiclo.setText("Ciclo: " + listaCiclo.get(position).getCiclo());
    }

    public void filtrado(final String txtBuscar) {
        int longitud = txtBuscar.length();

        if (longitud == 0) {
            listaCiclo.clear();
            listaCiclo.addAll(listaOriginal);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                List<Ciclo> collecion = listaCiclo.stream()
                        .filter(i -> i.getAño().toLowerCase().contains(txtBuscar.toLowerCase())
                                || i.getCiclo().toLowerCase().contains(txtBuscar.toLowerCase())
                                || String.valueOf(i.getId_ciclo()).contains(txtBuscar))
                        .collect(Collectors.toList());

                listaCiclo.clear();
                listaCiclo.addAll(collecion);
            } else {
                for (Ciclo c : listaOriginal) {
                    if (c.getCiclo().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        listaCiclo.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaCiclo.size();
    }

    public class CicloViewHolder extends RecyclerView.ViewHolder {

        TextView viewAño, viewIdCiclo, viewCiclo;

        public CicloViewHolder(@NonNull View itemView) {
            super(itemView);

            viewAño = itemView.findViewById(R.id.viewAño);
            viewIdCiclo = itemView.findViewById(R.id.viewIdCiclo);
            viewCiclo = itemView.findViewById(R.id.viewCiclo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, CicloConsultarActivity.class);
                    intent.putExtra("ID", listaCiclo.get(getAdapterPosition()).getId_ciclo());
                    ((Activity) context).startActivityForResult(intent,1);
                }
            });
        }
    }
}
