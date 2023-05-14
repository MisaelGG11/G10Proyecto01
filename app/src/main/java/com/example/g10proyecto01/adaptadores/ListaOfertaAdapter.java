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
import com.example.g10proyecto01.ControlG10Proyecto01;
import com.example.g10proyecto01.Docente;
import com.example.g10proyecto01.Escuela;
import com.example.g10proyecto01.Materia;
import com.example.g10proyecto01.OfertaAcademica;
import com.example.g10proyecto01.OfertaAcademicaConsultarActivity;
import com.example.g10proyecto01.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaOfertaAdapter extends RecyclerView.Adapter<ListaOfertaAdapter.OfertaViewHolder> {
    ArrayList<OfertaAcademica> listaOferta;
    ArrayList<OfertaAcademica> listaOriginal;
    ControlG10Proyecto01 helper;
    List<Ciclo> listaCiclos = new ArrayList<>();
    List<Docente> listaDocentes = new ArrayList<>();
    List<Materia> listaMaterias = new ArrayList<>();

    public ListaOfertaAdapter(ArrayList<OfertaAcademica> listaOfertas) {
        this.listaOferta = listaOfertas;

        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaOfertas);
    }

    @NonNull
    @Override
    public OfertaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_oferta, null, false);
        return new OfertaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OfertaViewHolder holder, int position) {
        helper = new ControlG10Proyecto01(holder.itemView.getContext());

        helper.abrir();
        listaCiclos = helper.mostrarCiclos();
        listaDocentes = helper.mostrarDocentes();
        listaMaterias = helper.mostrarMaterias();
        helper.cerrar();

        OfertaAcademica oferta = listaOferta.get(position);

        int posCiclo = 0, posDocente = 0, posMateria = 0;

        for (Ciclo ciclo : listaCiclos) {
            if (ciclo.getId_ciclo() == oferta.getId_ciclo()) {
                posCiclo = listaCiclos.indexOf(ciclo);
            }
        }

        for (Docente docente : listaDocentes) {
            if (docente.getId_docente() == oferta.getId_docente()) {
                posDocente = listaDocentes.indexOf(docente);
            }
        }

        for (Materia materia : listaMaterias) {
            if (materia.getId_materia() == oferta.getId_materia()) {
                posMateria = listaMaterias.indexOf(materia);
            }
        }

        String ciclo = "Ciclo: " + listaCiclos.get(posCiclo).getCiclo() + "_" + listaCiclos.get(posCiclo).getAño();
        String docente = "NIP: " + String.valueOf(listaDocentes.get(posDocente).getNip_docente());
        String materia = listaMaterias.get(posMateria).getCod_materia();

        holder.viewIdOferta.setText(String.valueOf(oferta.getId_oferta_a()));
        holder.viewIdCiclo.setText(ciclo);
        holder.viewIdDocente.setText(docente);
        holder.viewIdMateria.setText(materia);
    }

    public void filtrado(final String txtBuscar) {
        int longitud = txtBuscar.length();

        if (longitud == 0) {
            listaOferta.clear();
            listaOferta.addAll(listaOriginal);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                List<OfertaAcademica> collecion = listaOferta.stream()
                        .filter(i -> String.valueOf(i.getId_materia()).contains(txtBuscar)
                                || String.valueOf(i.getId_docente()).contains(txtBuscar)
                                || String.valueOf(i.getId_ciclo()).contains(txtBuscar)
                                || String.valueOf(i.getId_oferta_a()).contains(txtBuscar))
                        .collect(Collectors.toList());

                listaOferta.clear();
                listaOferta.addAll(collecion);
            } else {
                for (OfertaAcademica m : listaOriginal) {
                    if (String.valueOf(m.getId_oferta_a()).toLowerCase().contains(txtBuscar.toLowerCase())) {
                        listaOferta.add(m);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaOferta.size();
    }

    public class OfertaViewHolder extends RecyclerView.ViewHolder {

        TextView viewIdOferta, viewIdCiclo, viewIdDocente, viewIdMateria;

        public OfertaViewHolder(@NonNull View itemView) {
            super(itemView);

            viewIdOferta = itemView.findViewById(R.id.viewIdOferta);
            viewIdCiclo = itemView.findViewById(R.id.viewIdCiclo);
            viewIdDocente = itemView.findViewById(R.id.viewIdDocente);
            viewIdMateria = itemView.findViewById(R.id.viewIdMateria);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, OfertaAcademicaConsultarActivity.class);
                    intent.putExtra("ID", listaOferta.get(getAdapterPosition()).getId_oferta_a());
                    ((Activity) context).startActivityForResult(intent,1);
                }
            });
        }
    }
}
