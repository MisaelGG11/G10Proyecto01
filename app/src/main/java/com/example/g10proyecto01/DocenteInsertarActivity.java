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

public class DocenteInsertarActivity extends Activity {
    ControlG10Proyecto01 helper;
    EditText editIdDocente;
    EditText editNip;
    EditText editCategoria;

    List<Integer> listIdEmp = new ArrayList<>();
    List<String> nombresEmp = new ArrayList<>();
    List <String> spinnerList = new ArrayList<>();
    Spinner spinnerEmp;

    @SuppressLint("MissingInflatedId")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_insertar);

        helper = new ControlG10Proyecto01(this);

        editIdDocente = (EditText) findViewById(R.id.editIdDocente);
        editNip = (EditText) findViewById(R.id.editNipDocente);
        editCategoria= (EditText)  findViewById(R.id.editCategoria);

        spinnerEmp = findViewById(R.id.spinnerIdEmpleado);

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
        spinnerEmp.setAdapter(adapter);
    }
    public void insertarDocente(View v) {
        if (editIdDocente.getText().toString().isEmpty() ||
            editNip.getText().toString().isEmpty() ||
            editCategoria.getText().toString().isEmpty() ||
            listIdEmp.size() == 0){
            Toast.makeText(this, getResources().getString(R.string.vacio), Toast.LENGTH_SHORT).show();
        } else {
            String regInsertados;

            int idDocente = Integer.valueOf(editIdDocente.getText().toString());
            int nip = Integer.valueOf(editNip.getText().toString());
            String categoria = editCategoria.getText().toString();
            int idEmpleado = listIdEmp.get(spinnerEmp.getSelectedItemPosition());


            Docente docente = new Docente();
            docente.setId_docente(idDocente) ;
            docente.setId_empleado(idEmpleado);
            docente.setNip_docente(nip);
            docente.setCategoria_docente(categoria);

            helper.abrir();
            regInsertados=helper.insertar(docente);
            helper.cerrar();

            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        }

    }
    public void limpiarTexto(View v) {
        editIdDocente.setText("");
        editNip.setText("");
        editCategoria.setText("");
    }
}