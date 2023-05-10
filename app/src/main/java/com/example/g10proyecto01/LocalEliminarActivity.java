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

public class LocalEliminarActivity extends Activity {

    ControlG10Proyecto01 helper;
    List<Integer> LocSpinner = new ArrayList<>();
    Spinner spinnerIdLoc;
    final String ID_LOCAL = "id_localidad";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_eliminar);
        helper = new ControlG10Proyecto01(this);
        spinnerIdLoc = findViewById(R.id.spinIdLocal);
        String query = "SELECT id_localidad FROM localidad";
        Cursor cursor = helper.llenarSpinner(query);
        while(cursor.moveToNext()){
            @SuppressLint("Range")
            int id = cursor.getInt(cursor.getColumnIndex(ID_LOCAL));
            LocSpinner.add(id);
        }
        ArrayAdapter<Integer> adapterLOCSpinner = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,LocSpinner);
        adapterLOCSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdLoc.setAdapter(adapterLOCSpinner);
    }

    public void eliminarLocal(View v){
        AlertDialog.Builder alerta = new AlertDialog.Builder(LocalEliminarActivity.this);
        alerta.setCancelable(false);
        alerta.setMessage("¿Desea eliminar este registro?");
        alerta.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //ACCIONES SI RESPONDE QUE SI A LA ALERTA
                String regEliminadas;
                Localidad localidad = new Localidad();
                int id_Loc;
                id_Loc =LocSpinner.get(spinnerIdLoc.getSelectedItemPosition());
                localidad.setId_localidad(id_Loc);
                helper.abrir();
                regEliminadas=helper.eliminar(localidad);
                helper.cerrar();
                Toast.makeText(LocalEliminarActivity.this, regEliminadas, Toast.LENGTH_SHORT).show();
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
