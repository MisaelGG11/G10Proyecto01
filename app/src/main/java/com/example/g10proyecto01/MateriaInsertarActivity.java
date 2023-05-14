package com.example.g10proyecto01;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.g10proyecto01.adaptadores.ListaCicloAdapter;

import java.util.ArrayList;
import java.util.List;

public class MateriaInsertarActivity extends AppCompatActivity {
    ControlG10Proyecto01 helper;
    EditText editIdMateria, editCodMateria, editNomMateria;
    Spinner spinnerMateria;
    List<Escuela> listaEscuelas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_insertar);

        helper = new ControlG10Proyecto01(this);

        editIdMateria = (EditText) findViewById(R.id.editIdMateria);
        editCodMateria = (EditText) findViewById(R.id.editCodMateria);
        editNomMateria = (EditText) findViewById(R.id.editNombreMateria);
        spinnerMateria = findViewById(R.id.spinnerMateria);

        helper.abrir();
        listaEscuelas = helper.mostrarEscuelas();
        helper.cerrar();

        //establece valores al spinner
        ArrayAdapter<Escuela> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaEscuelas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMateria.setAdapter(adapter);
    }

    public void insertarCiclo(View v) {
        if (editIdMateria.getText().toString().isEmpty() ||
                editCodMateria.getText().toString().isEmpty() ||
                editNomMateria.getText().toString().isEmpty()) {

            Toast.makeText(this, getResources().getString(R.string.vacio), Toast.LENGTH_SHORT).show();
        } else {
            int id_materia = Integer.valueOf(editIdMateria.getText().toString());
            String cod_materia = editCodMateria.getText().toString();
            String nombre_materia = editNomMateria.getText().toString();

            int seleccion = spinnerMateria.getSelectedItemPosition();
            int id_escuela = listaEscuelas.get(seleccion).getId_escuela();

            String regInsertados;

            Materia materia = new Materia();
            materia.setId_materia(id_materia);
            materia.setCod_materia(cod_materia);
            materia.setNom_materia(nombre_materia);
            materia.setId_escuela(id_escuela);

            helper.abrir();
            regInsertados = helper.insertar(materia);
            helper.cerrar();

            if (regInsertados.contains("Err")) {
                Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }

    public void limpiarTexto(View v) {
        editIdMateria.setText("");
        editCodMateria.setText("");
        editNomMateria.setText("");
    }
}