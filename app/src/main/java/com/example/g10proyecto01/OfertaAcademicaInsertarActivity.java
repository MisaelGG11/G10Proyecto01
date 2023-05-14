package com.example.g10proyecto01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class OfertaAcademicaInsertarActivity extends AppCompatActivity {
    ControlG10Proyecto01 helper;
    EditText editIdOferta;
    Spinner spinnerCiclo, spinnerDocente, spinnerMateria;
    List<Ciclo> listaCiclos = new ArrayList<>();
    List<Docente> listaDocentes = new ArrayList<>();
    List<Materia> listaMaterias = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oferta_academica_insertar);

        helper = new ControlG10Proyecto01(this);

        editIdOferta = (EditText) findViewById(R.id.editIdOferta);
        spinnerCiclo = findViewById(R.id.spinnerOfertaCiclo);
        spinnerDocente = findViewById(R.id.spinnerOfertaDocente);
        spinnerMateria = findViewById(R.id.spinnerOfertaMateria);

        helper.abrir();
        listaCiclos = helper.mostrarCiclos();
        listaDocentes = helper.mostrarDocentes();
        listaMaterias = helper.mostrarMaterias();
        helper.cerrar();

        //establece valores al spinner
        ArrayAdapter<Ciclo> adapterCiclo = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaCiclos);
        adapterCiclo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCiclo.setAdapter(adapterCiclo);

        //establece valores al spinner
        ArrayAdapter<Docente> adapterDocente = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaDocentes);
        adapterDocente.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDocente.setAdapter(adapterDocente);

        //establece valores al spinner
        ArrayAdapter<Materia> adapterMateria = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaMaterias);
        adapterMateria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMateria.setAdapter(adapterMateria);
    }

    public void insertarOferta(View v) {
        if (editIdOferta.getText().toString().isEmpty()) {

            Toast.makeText(this, getResources().getString(R.string.vacio), Toast.LENGTH_SHORT).show();
        } else {
            int id_oferta_a = Integer.valueOf(editIdOferta.getText().toString());

            int seleccionCiclo = spinnerCiclo.getSelectedItemPosition();
            int id_ciclo = listaCiclos.get(seleccionCiclo).getId_ciclo();

            int seleccionDocente = spinnerDocente.getSelectedItemPosition();
            int id_docente = listaDocentes.get(seleccionDocente).getId_docente();

            int seleccionMateria = spinnerMateria.getSelectedItemPosition();
            int id_materia = listaMaterias.get(seleccionMateria).getId_materia();

            String regInsertados;

            OfertaAcademica ofertaAcademica = new OfertaAcademica();
            ofertaAcademica.setId_oferta_a(id_oferta_a);
            ofertaAcademica.setId_ciclo(id_ciclo);
            ofertaAcademica.setId_docente(id_docente);
            ofertaAcademica.setId_materia(id_materia);

            helper.abrir();
            regInsertados = helper.insertar(ofertaAcademica);
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
        editIdOferta.setText("");
    }
}