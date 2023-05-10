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

public class LocalConsultarActivity extends Activity {
    ControlG10Proyecto01 helper;
    EditText editTextNombreLocal,editTextedificioLocal,editTextCupoLocal;
    List<Integer> idL = new ArrayList<>();
    Spinner spinnerlocal;
    final String ID_LOCAL = "id_localidad";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_consultar);

        helper = new ControlG10Proyecto01(this);

        spinnerlocal = findViewById(R.id.spinIdlocal);
        editTextedificioLocal = findViewById(R.id.editEdifiLoc);
        editTextNombreLocal = findViewById(R.id.editNomLoc);
        editTextCupoLocal = findViewById(R.id.editCupoLo);

        String query = "SELECT id_localidad FROM Localidad";
        Cursor cursor = helper.llenarSpinner(query);
        while(cursor.moveToNext()){
            @SuppressLint("Range")
            int id = cursor.getInt(cursor.getColumnIndex(ID_LOCAL));
            idL.add(id);
        }
        ArrayAdapter<Integer> adapterLocal = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,idL);
        adapterLocal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerlocal.setAdapter(adapterLocal);
    }

    public void consultarLocal(View v) {
        String id_Lo = spinnerlocal.getSelectedItem().toString();
        helper.abrir();
        Localidad localidad = helper.consultarlocalidad(id_Lo);
        helper.cerrar();
        if(localidad == null) {
            Toast.makeText(this, "Registro no encontrado", Toast.LENGTH_LONG).show();
        }
        editTextedificioLocal.setText(localidad.getEdificio_localidad());
        editTextNombreLocal.setText(localidad.getNombre_localidad());
        editTextCupoLocal.setText(String.valueOf(localidad.getCapacidad_localidad()));
    }
    public void limpiarTextoLoc(View v){
        editTextedificioLocal.setText("");
        editTextNombreLocal.setText("");
        editTextCupoLocal.setText("");
    }
}