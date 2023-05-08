package com.example.g10proyecto01;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class OpcionCrudActualizarActivity extends Activity {
    ControlG10Proyecto01 helper;
    EditText editDescripcion;
    List<Integer> opcionesSpinner = new ArrayList<>();
    Spinner spinnerIdOpcionCrud;
    final String ID_OPCION_CRUD = "id_opcion_crud";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcion_crud_actualizar);
        helper = new ControlG10Proyecto01(this);
        spinnerIdOpcionCrud = findViewById(R.id.spinIdOpcionCrud);
        editDescripcion = findViewById(R.id.editActualizarOpcionCrud);
        String query = "SELECT id_opcion_crud FROM OpcionCrud";
        Cursor cursor = helper.llenarSpinner(query);
        while(cursor.moveToNext()){
            @SuppressLint("Range")
            int id = cursor.getInt(cursor.getColumnIndex(ID_OPCION_CRUD));
            opcionesSpinner.add(id);
        }
        ArrayAdapter<Integer> adapterOpcionesSpinner = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,opcionesSpinner);
        adapterOpcionesSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdOpcionCrud.setAdapter(adapterOpcionesSpinner);
    }

    public void ActualizarOpcionCrud(View v){
        String idOpcionSeleccionada = spinnerIdOpcionCrud.getSelectedItem().toString();
        String descripcion = editDescripcion.getText().toString();
        OpcionCrud opcionCrud = new OpcionCrud(Integer.parseInt(idOpcionSeleccionada),descripcion);
        System.out.println(opcionCrud.toString());
        helper.abrir();
        String estado = helper.actualizar(opcionCrud);
        helper.cerrar();

        Toast.makeText(this,estado,Toast.LENGTH_LONG).show();
    }

    public void limpiarTexto(View v){
        editDescripcion.setText("");
    }
}