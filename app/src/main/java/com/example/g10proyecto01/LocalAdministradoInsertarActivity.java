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
    List<Integer> listIdEmp = new ArrayList<>();
    List<String> nombresEmp = new ArrayList<>();
    List<Integer> idLA = new ArrayList<>();
    List<String> loc = new ArrayList<>();
    List <String> spinnerList = new ArrayList<>();
    List <String> spinnerList2 = new ArrayList<>();
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
        int idLocal = idLA.get(spinnerLocal.getSelectedItemPosition());
        int idEmpleado = listIdEmp.get(spinnerEmpleado.getSelectedItemPosition());
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

        String query = "SELECT l.id_local_admin, lo.nombre_localidad FROM Local_Administrado AS l JOIN Localidad AS lo ON l.id_localidad = lo.id_localidad";
        Cursor cursor = helper.llenarSpinner(query);
        while(cursor.moveToNext()){
            @SuppressLint("Range")
            int id = cursor.getInt(cursor.getColumnIndex("id_local_admin"));
            idLA.add(id);

            @SuppressLint("Range")
            String local = cursor.getString(cursor.getColumnIndex("nombre_localidad"));
            loc.add(local);

            String itemSpinner = id + ": " + local;
            spinnerList2.add(itemSpinner);
        }
        ArrayAdapter<String> adapterLocal = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,spinnerList2);
        adapterLocal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLocal.setAdapter(adapterLocal);
    }



    public void SpinnerEmpleado(){
        String sql = "SELECT id_empleado, nombre_empleado, apellido_empleado FROM Empleado_UES";
        Cursor cursor = helper.llenarSpinner(sql);
        while (cursor.moveToNext()) {
            @SuppressLint("Range")
            int idEmp = cursor.getInt(cursor.getColumnIndex("id_empleado"));
            listIdEmp.add(idEmp);

            @SuppressLint("Range")
            String nom = cursor.getString(cursor.getColumnIndex("nombre_empleado")) + " " + cursor.getString(cursor.getColumnIndex("apellido_empleado"));
            nombresEmp.add(nom);

            String itemSpinner = idEmp + ": " + nom;
            spinnerList.add(itemSpinner);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEmpleado.setAdapter(adapter);
    }

}