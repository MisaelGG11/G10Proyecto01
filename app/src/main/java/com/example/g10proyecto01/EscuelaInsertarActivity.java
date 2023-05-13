package com.example.g10proyecto01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EscuelaInsertarActivity extends AppCompatActivity {
    ControlG10Proyecto01 helper;
    EditText editIdEscuela;
    EditText editAcronimo;
    EditText editNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escuela_insertar);
        helper = new ControlG10Proyecto01(this);
        editIdEscuela = (EditText) findViewById(R.id.editIdEscuela);
        editAcronimo = (EditText) findViewById(R.id.editAcronimo);
        editNombre = (EditText) findViewById(R.id.editNombreEscuela);
    }

    public void insertarEscuela(View v) {
        if (editIdEscuela.getText().toString().isEmpty() ||
                editAcronimo.getText().toString().isEmpty() ||
                editNombre.getText().toString().isEmpty()) {

            Toast.makeText(this, getResources().getString(R.string.vacio), Toast.LENGTH_SHORT).show();
        } else {
            String id_escuela = editIdEscuela.getText().toString();
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

            if (regInsertados.contains("Error")) {
                Toast.makeText(this, getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getResources().getString(R.string.regInsertado) + regInsertados, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }

    public void limpiarTexto(View v) {
        editIdEscuela.setText("");
        editAcronimo.setText("");
        editNombre.setText("");
    }
}