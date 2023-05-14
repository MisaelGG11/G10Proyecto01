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

public class LocalAdministradoInsertarActivity extends Activity {

    ControlG10Proyecto01 helper;
    EditText editIdLocalAdmin;

    List<Integer> listIdLocal = new ArrayList<>();
    List<Integer> listIdEncargado = new ArrayList<>();

    Spinner spinnerEmpleado;
    Spinner spinnerLocal;

    @SuppressLint("MissingInflatedId")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_administrado_insertar);

        helper = new ControlG10Proyecto01(this);

        editIdLocalAdmin = (EditText) findViewById(R.id.editidLocadmin);

        spinnerLocal = findViewById(R.id.SpinnerLocal);
        spinnerEmpleado = findViewById(R.id.SpinnerEncargado);

        SpinnerLoc();
        SpinnerEmpleado();
    }
    public void insertarLocalAdmin(View v) {
        String idLocalAdmin = editIdLocalAdmin.getText().toString();
        int idLocal = listIdLocal.get(spinnerLocal.getSelectedItemPosition());
        int idEmpleado = listIdEncargado.get(spinnerEmpleado.getSelectedItemPosition());
        String regInsertados;

        if (idLocalAdmin.isEmpty()) {
            Toast.makeText(LocalAdministradoInsertarActivity.this, "Ingresar datos obligatorios", Toast.LENGTH_SHORT).show();
        } else {
            LocalAdministrado locAd = new LocalAdministrado();
            locAd.setId_local_admin(Integer.valueOf(idLocalAdmin));
            locAd.setId_local(idLocal);
            locAd.setId_empleadoadministrador(idEmpleado);

            helper.abrir();
            regInsertados=helper.insertar(locAd);
            helper.cerrar();

            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        }

    }
    public void limpiarTextoLocA(View v) {
        editIdLocalAdmin.setText("");
    }

    public void SpinnerLoc(){
        String sql = "SELECT id_localidad FROM Localidad WHERE id_localidad NOT IN (SELECT id_localidad FROM Local_Administrado)";
        Cursor cursorL = helper.llenarSpinner(sql);
        while (cursorL.moveToNext()) {
            @SuppressLint("Range")
            int idLoc = cursorL.getInt(cursorL.getColumnIndex("id_localidad"));
            listIdLocal.add(idLoc);
        }
        ArrayAdapter<Integer> adapterL = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listIdLocal);
        adapterL.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapterL.notifyDataSetChanged();
        spinnerLocal.setAdapter(adapterL);
    }

    public void SpinnerEmpleado(){
        String sql = "SELECT id_empleado FROM Empleado_UES";
        Cursor cursorE = helper.llenarSpinner(sql);

        while (cursorE.moveToNext()) {
            @SuppressLint("Range")
            int idEn = cursorE.getInt(cursorE.getColumnIndex("id_empleado"));
            listIdEncargado.add(idEn);
        }
        ArrayAdapter<Integer> adapterE = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listIdEncargado);
        adapterE.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEmpleado.setAdapter(adapterE);
    }

}