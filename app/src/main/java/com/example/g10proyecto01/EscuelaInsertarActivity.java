package com.example.g10proyecto01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EscuelaInsertarActivity extends AppCompatActivity {
    ControlG10Proyecto01 helper;
    EditText editId;
    EditText editAcronimo;
    EditText editNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escuela_insertar);
        helper = new ControlG10Proyecto01(this);
        editId = (EditText) findViewById(R.id.editId);
        editAcronimo = (EditText) findViewById(R.id.editAcronimo);
        editNombre = (EditText) findViewById(R.id.editNombre);
    }

    public void insertarEscuela(View v) {
        String id_escuela = editId.getText().toString();
        String acronimo = editAcronimo.getText().toString();
        String nombre = editNombre.getText().toString();

        String regInsertados;

        Escuela escuela = new Escuela();
        escuela.setId_escuela(Integer.valueOf(id_escuela));
        escuela.setAcronimo(acronimo);
        escuela.setNombre(nombre);

        helper.abrir();

        regInsertados = helper.insertar(escuela);

        helper.cerrar();

        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    public void limpiarTexto(View v) {
        editId.setText("");
        editAcronimo.setText("");
        editNombre.setText("");
    }
}