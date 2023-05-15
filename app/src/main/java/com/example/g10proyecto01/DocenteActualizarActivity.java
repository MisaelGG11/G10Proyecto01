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

public class DocenteActualizarActivity extends Activity {
    ControlG10Proyecto01 helper;
    EditText editIdEmpleado;
    EditText editNip;
    EditText editCategoria;

    List<Integer> listIdDocente = new ArrayList<>();
    Spinner spinnerIdDocente;
    List<Integer> listIdEmp = new ArrayList<>();

    List<String> nombresEmp = new ArrayList<>();
    List <String> spinnerList = new ArrayList<>();
    Spinner spinnerIdEmpleado;


    @SuppressLint("MissingInflatedId")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_actualizar);

        helper = new ControlG10Proyecto01(this);

        editNip = findViewById(R.id.editNipDocente);
        editCategoria = findViewById(R.id.editCategoria);
        spinnerIdDocente = findViewById(R.id.spinnerIdDocente);
        spinnerIdEmpleado = findViewById(R.id.spinnerIdEmpleado);

        String sql1 = "SELECT id_empleado, nombre_empleado, apellido_empleado FROM Empleado_UES";
        Cursor cursor1 = helper.llenarSpinner(sql1);
        while (cursor1.moveToNext()) {
            @SuppressLint("Range")
            int idEmp = cursor1.getInt(cursor1.getColumnIndex("id_empleado"));
            listIdEmp.add(idEmp);

            @SuppressLint("Range")
            String nom = cursor1.getString(cursor1.getColumnIndex("nombre_empleado")) + " " + cursor1.getString(cursor1.getColumnIndex("apellido_empleado"));
            nombresEmp.add(nom);

            String itemSpinner = idEmp + ": " + nom;
            spinnerList.add(itemSpinner);
        }
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerList);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdEmpleado.setAdapter(adapter1);

        String sql2 = "SELECT id_docente FROM Docente";
        Cursor cursor2 = helper.llenarSpinner(sql2);
        while (cursor2.moveToNext()) {
            @SuppressLint("Range")
            int idDocente = cursor2.getInt(cursor2.getColumnIndex("id_docente"));
            listIdDocente.add(idDocente);
        }
        ArrayAdapter<Integer> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listIdDocente);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdDocente.setAdapter(adapter2);
    }
    public void actualizarDocente(View v) {
        if(editNip.getText().toString().isEmpty() ||
            editCategoria.getText().toString().isEmpty() ||
            listIdEmp.size() == 0 || listIdDocente.size() == 0){
            Toast.makeText(this, getResources().getString(R.string.vacio), Toast.LENGTH_SHORT).show();
        }
        else {
            Docente docente = new Docente();

            int idEmpleado, idDocente;
            idEmpleado =listIdEmp.get(spinnerIdEmpleado.getSelectedItemPosition());
            idDocente = listIdDocente.get(spinnerIdDocente.getSelectedItemPosition());

            int nip = Integer.valueOf(editNip.getText().toString());
            String categoria = editCategoria.getText().toString();

            docente.setId_docente(idDocente);
            docente.setId_empleado(idEmpleado);
            docente.setNip_docente(nip);
            docente.setCategoria_docente(categoria);

            helper.abrir();
            String estado = helper.actualizar(docente);
            helper.cerrar();
            Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
        }

    }
    public void limpiarTexto(View v) {
        editNip.setText("");
        editCategoria.setText("");;
    }
}