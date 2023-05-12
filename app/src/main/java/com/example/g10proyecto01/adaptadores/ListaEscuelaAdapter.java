package com.example.g10proyecto01.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.g10proyecto01.Escuela;
import com.example.g10proyecto01.EscuelaConsultarActivity;
import com.example.g10proyecto01.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaEscuelaAdapter extends RecyclerView.Adapter<ListaEscuelaAdapter.EscuelaViewHolder> {
    ArrayList<Escuela> listaEscuela;
    ArrayList<Escuela> listaOriginal;

    public ListaEscuelaAdapter(ArrayList<Escuela> listaEscuelas) {
        this.listaEscuela = listaEscuelas;

        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaEscuelas);
    }

    @NonNull
    @Override
    public EscuelaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_escuela, null, false);
        return new EscuelaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EscuelaViewHolder holder, int position) {
        holder.viewAcronimo.setText(listaEscuela.get(position).getAcronimo());
        holder.viewIdEscuela.setText(String.valueOf(listaEscuela.get(position).getId_escuela()));
        holder.viewNombre.setText(listaEscuela.get(position).getNombre());
    }

    public void filtrado(final String txtBuscar) {
        int longitud = txtBuscar.length();

        if (longitud == 0) {
            listaEscuela.clear();
            listaEscuela.addAll(listaOriginal);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                List<Escuela> collecion = listaEscuela.stream()
                        .filter(i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())
                        || i.getAcronimo().toLowerCase().contains(txtBuscar.toLowerCase())
                        || String.valueOf(i.getId_escuela()).contains(txtBuscar))
                        .collect(Collectors.toList());

                listaEscuela.clear();
                listaEscuela.addAll(collecion);
            } else {
                for (Escuela c : listaOriginal) {
                    if (c.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        listaEscuela.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaEscuela.size();
    }

    public class EscuelaViewHolder extends RecyclerView.ViewHolder {

        TextView viewAcronimo, viewIdEscuela, viewNombre;

        public EscuelaViewHolder(@NonNull View itemView) {
            super(itemView);

            viewAcronimo = itemView.findViewById(R.id.viewAcronimo);
            viewIdEscuela = itemView.findViewById(R.id.viewIdEscuela);
            viewNombre = itemView.findViewById(R.id.viewNombre);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, EscuelaConsultarActivity.class);
                    intent.putExtra("ID", listaEscuela.get(getAdapterPosition()).getId_escuela());
                    context.startActivity(intent);
                }
            });
        }
    }
}
