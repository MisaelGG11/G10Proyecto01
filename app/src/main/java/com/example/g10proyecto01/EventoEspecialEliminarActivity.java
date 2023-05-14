package com.example.g10proyecto01;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EventoEspecialEliminarActivity extends Activity {
    ControlG10Proyecto01 helper;
    List<Integer> EvESpinner = new ArrayList<>();
    Spinner spinnerIdE;
    final String ID_EVENTO = "id_evento";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_especial_eliminar);
        helper = new ControlG10Proyecto01(this);
        spinnerIdE = findViewById(R.id.spinIdEvento);
        String query = "SELECT id_evento FROM Evento_Especial";
        Cursor cursor = helper.llenarSpinner(query);
        while(cursor.moveToNext()){
            @SuppressLint("Range")
            int id = cursor.getInt(cursor.getColumnIndex(ID_EVENTO));
            EvESpinner.add(id);
        }
        ArrayAdapter<Integer> adapterEVESpinner = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,EvESpinner);
        adapterEVESpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdE.setAdapter(adapterEVESpinner);
    }

    public void eliminarEventoEspecial(View v){
        AlertDialog.Builder alerta = new AlertDialog.Builder(EventoEspecialEliminarActivity.this);
        alerta.setCancelable(false);
        alerta.setMessage("¿Desea eliminar este registro?");
        alerta.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //ACCIONES SI RESPONDE QUE SI A LA ALERTA
                String regEliminadas;
                EventoEspecial eventoEspecial= new EventoEspecial();
                int id_Eve;
                id_Eve =EvESpinner.get(spinnerIdE.getSelectedItemPosition());
                eventoEspecial.setId_evento(id_Eve);
                helper.abrir();
                regEliminadas=helper.eliminar(eventoEspecial);
                helper.cerrar();
                Toast.makeText(EventoEspecialEliminarActivity.this, regEliminadas, Toast.LENGTH_SHORT).show();
            }
        });
        alerta.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alerta.show();
    }
}
