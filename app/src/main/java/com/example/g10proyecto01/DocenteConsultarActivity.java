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

public class DocenteConsultarActivity extends Activity {
    ControlG10Proyecto01 helper;
    EditText editIdEmpleado;
    EditText editNip;
    EditText editCategoria;

    List<Integer> listIdDocente = new ArrayList<>();
    Spinner spinnerIdDocente;


    @SuppressLint("MissingInflatedId")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_consultar);

        helper = new ControlG10Proyecto01(this);

        editIdEmpleado = findViewById(R.id.editIdEmpleado);
        editNip = findViewById(R.id.editNipDocente);
        editCategoria = findViewById(R.id.editCategoria);
        spinnerIdDocente = findViewById(R.id.spinnerIdDocente);

        String sql = "SELECT id_docente FROM Docente";
        Cursor cursor = helper.llenarSpinner(sql);
        while (cursor.moveToNext()) {
            @SuppressLint("Range")
            int idDocent = cursor.getInt(cursor.getColumnIndex("id_docente"));
            listIdDocente.add(idDocent);
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listIdDocente);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdDocente.setAdapter(adapter);
    }
    public void consultarDocente(View v) {
        String idDocente = spinnerIdDocente.getSelectedItem().toString();
        helper.abrir();
        Docente docente = helper.consultarDocente(idDocente);
        helper.cerrar();
        if(docente == null)
            Toast.makeText(this, "Registro no encontrado", Toast.LENGTH_LONG).show();
        else{
            editIdEmpleado.setText(String.valueOf(docente.getId_empleado()));
            editNip.setText(String.valueOf(docente.getNip_docente()));
            editCategoria.setText(docente.getCategoria_docente());
        }
    }
    public void limpiarTexto(View v) {
        editIdEmpleado.setText("");
        editNip.setText("");
        editCategoria.setText("");
    }
}