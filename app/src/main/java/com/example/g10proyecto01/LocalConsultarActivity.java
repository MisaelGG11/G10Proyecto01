package com.example.g10proyecto01;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LocalConsultarActivity extends AppCompatActivity {
    ControlG10Proyecto01 helper;
    EditText editNomLocal,editEdificioLocal,editCupoLocal;
    List<Integer> idlocal = new ArrayList<>();
    Spinner spinnerLocal;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_consultar);

        helper = new ControlG10Proyecto01(this);

        spinnerLocal = findViewById(R.id.spinIdLocal);
        editNomLocal = findViewById(R.id.editNomLocal);
        editEdificioLocal = findViewById(R.id.editEdificioLocal);
        editCupoLocal = findViewById(R.id.editCupoLocal);

        String sql = "SELECT id_localidad FROM Localidad";
        Cursor cursor = helper.llenarSpinner(sql);
        while (cursor.moveToNext()) {
            @SuppressLint("Range")
            int id = cursor.getInt(cursor.getColumnIndex("id_localidad"));
            idlocal.add(id);
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, idlocal);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLocal.setAdapter(adapter);
    }
    public void consultarLocal(View v) {
        String id_L = spinnerLocal.getSelectedItem().toString();
        helper.abrir();
        Local local = helper.consultarlocal(id_L);
        helper.cerrar();
        if(local == null)
            Toast.makeText(this, "Registro no encontrado", Toast.LENGTH_LONG).show();
        else{
            editNomLocal.setText(local.getNombre_localidad());
            editEdificioLocal.setText(local.getEdificio_localidad());
            editCupoLocal.setText(local.getCapacidad_localidad());
        }
        editNomLocal.setText(local.getNombre_localidad());
        editEdificioLocal.setText(local.getEdificio_localidad());
        editCupoLocal.setText(local.getCapacidad_localidad());
    }
    public void limpiarTextoL(View v){
        editNomLocal.setText("");
        editEdificioLocal.setText("");
        editCupoLocal.setText("");
    }
}
