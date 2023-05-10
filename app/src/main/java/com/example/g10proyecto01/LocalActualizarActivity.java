package com.example.g10proyecto01;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LocalActualizarActivity extends Activity {
    ControlG10Proyecto01 helper;
    EditText editedificio,editNomLoc,editCupo;
    List<Integer> LOCSpinner = new ArrayList<>();
    Spinner spinnerIdLOC;
    final String ID_LOCAL = "id_localidad";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_actualizar);
        helper = new ControlG10Proyecto01(this);
        spinnerIdLOC = findViewById(R.id.spinIdLoc);
        editedificio = findViewById(R.id.editedificio);
        editNomLoc = findViewById(R.id.editNomLoc);
        editCupo = findViewById(R.id.editCupoLoc);
        String query = "SELECT id_localidad FROM localidad";
        Cursor cursor = helper.llenarSpinner(query);
        while(cursor.moveToNext()){
            @SuppressLint("Range")
            int id = cursor.getInt(cursor.getColumnIndex(ID_LOCAL));
            LOCSpinner.add(id);
        }
        ArrayAdapter<Integer> adapterLOCSpinner = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,LOCSpinner);
        adapterLOCSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdLOC.setAdapter(adapterLOCSpinner);
    }

    public void ActualizarLOC(View v){
        String idLocal = spinnerIdLOC.getSelectedItem().toString();
        String EdiLocal = editedificio.getText().toString();
        String NomLocal = editNomLoc.getText().toString();
        String CupoLocal = editCupo.getText().toString();

        if (EdiLocal.isEmpty()||NomLocal.isEmpty()||CupoLocal.isEmpty()) {
            Toast.makeText(LocalActualizarActivity.this, "Ingresar datos obligatorios", Toast.LENGTH_SHORT).show();
        } else {
            Localidad localidad = new Localidad(Integer.parseInt(idLocal),EdiLocal,NomLocal,Integer.parseInt(CupoLocal));
            System.out.println(localidad.toString());
            helper.abrir();
            String estado = helper.actualizar(localidad);
            helper.cerrar();

            Toast.makeText(this,estado,Toast.LENGTH_LONG).show();
        }

    }

    public void limpiarTextoLOC(View v){
        editedificio.setText("");
        editNomLoc.setText("");
        editCupo.setText("");
    }
}
