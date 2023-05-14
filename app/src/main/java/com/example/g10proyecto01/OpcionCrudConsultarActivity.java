package com.example.g10proyecto01;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class OpcionCrudConsultarActivity extends Activity {

    ControlG10Proyecto01 helper;
    EditText editDescripcion;
    List<String> opcionesSpinner = new ArrayList<>();
    Spinner spinnerIdOpcionCrud;
    final String ID_OPCION_CRUD = "id_opcion_crud";
    //Button btnIdOpcionCrud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcion_crud_consultar);
        helper = new ControlG10Proyecto01(this);
        spinnerIdOpcionCrud = findViewById(R.id.spinIdOpcionCrud);
        editDescripcion = findViewById(R.id.editConsultarOpcionCrud);
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
    public void consultarOpcionCrud(View v){

        String idOpcionSeleccionada = spinnerIdOpcionCrud.getSelectedItem().toString().substring(0,1);
        helper.abrir();
        OpcionCrud opcionCrud = helper.consultarOpcionCrud(idOpcionSeleccionada);
        helper.cerrar();
        if(opcionCrud == null){
            Toast.makeText(this, R.string.registro_no_encontrado, Toast.LENGTH_LONG).show();
        }
        editDescripcion.setText(opcionCrud.getDes_opcion());
    }

    public void limpiarTexto(View v){
        editDescripcion.setText("");

    }
}