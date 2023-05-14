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

public class OpcionCrudEliminarActivity extends Activity {

    ControlG10Proyecto01 helper;
    List<String> opcionesSpinner = new ArrayList<>();
    Spinner spinnerIdOpcionCrud;
    final String ID_OPCION_CRUD = "id_opcion_crud";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcion_crud_eliminar);
        helper = new ControlG10Proyecto01(this);
        spinnerIdOpcionCrud = findViewById(R.id.spinIdOpcionCrud);
        String query = "SELECT id_opcion_crud, des_opcion FROM OpcionCrud";
        Cursor cursor = helper.llenarSpinner(query);
        while(cursor.moveToNext()){
            @SuppressLint("Range")
            String id = cursor.getString(cursor.getColumnIndex(ID_OPCION_CRUD));
            String descripcion = cursor.getString(1);
            opcionesSpinner.add(id + " " + descripcion);
        }
        ArrayAdapter<String> adapterOpcionesSpinner = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,opcionesSpinner);
        adapterOpcionesSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdOpcionCrud.setAdapter(adapterOpcionesSpinner);
    }

    public void eliminarOpcionCrud(View v){
        AlertDialog.Builder alerta = new AlertDialog.Builder(OpcionCrudEliminarActivity.this);
        alerta.setCancelable(false);
        alerta.setMessage(R.string.mensajeAlerta);
        alerta.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //ACCIONES SI RESPONDE QUE SI A LA ALERTA
                String regEliminadas;

                int idOpcionCrud =Integer.parseInt(spinnerIdOpcionCrud.getSelectedItem().toString().substring(0,1));
                OpcionCrud opcionCrud = new OpcionCrud(idOpcionCrud);
                helper.abrir();
                regEliminadas=helper.eliminar(opcionCrud);
                helper.cerrar();
                Toast.makeText(OpcionCrudEliminarActivity.this, regEliminadas, Toast.LENGTH_SHORT).show();
            }
        });
        alerta.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //ACCIONES SI PONE NO
                dialog.cancel();
            }
        });
        //MUESTRA ALERTA PARA EVENTO ONCLICK DEL BOTON
        alerta.show();
    }
}