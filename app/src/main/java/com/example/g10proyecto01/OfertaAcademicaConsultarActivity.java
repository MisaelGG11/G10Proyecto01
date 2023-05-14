package com.example.g10proyecto01;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class OfertaAcademicaConsultarActivity extends AppCompatActivity {
    ControlG10Proyecto01 helper;
    EditText editIdOferta;
    Spinner spinnerCiclo, spinnerDocente, spinnerMateria;
    List<Ciclo> listaCiclos = new ArrayList<>();
    List<Docente> listaDocentes = new ArrayList<>();
    List<Materia> listaMaterias = new ArrayList<>();
    Button btnActualizar, btnEliminar;

    int edicion = 0;
    int id = 0;

    String userPermisos;
    List<Integer> permisos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oferta_academica_consultar);

        helper = new ControlG10Proyecto01(this);

        editIdOferta = findViewById(R.id.editIdOferta);
        spinnerCiclo = findViewById(R.id.spinnerOfertaCiclo);
        spinnerDocente = findViewById(R.id.spinnerOfertaDocente);
        spinnerMateria = findViewById(R.id.spinnerOfertaMateria);

        btnActualizar = findViewById(R.id.btnActualizarOferta);
        btnEliminar = findViewById(R.id.btnEliminarOferta);

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

        //Metodo para obtener el id
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        //Pregunta por los permisos
        AppGlobal global = (AppGlobal) getApplicationContext();
        userPermisos = global.getUserPermisos();

        helper = new ControlG10Proyecto01(this);

        String sql = "SELECT id_opcion_crud FROM AccesoUsuario WHERE id_usuario = '" + userPermisos + "'";

        Cursor cursor = helper.llenarSpinner(sql);

        while (cursor.moveToNext()) {
            @SuppressLint("Range")
            int permiso = cursor.getInt(cursor.getColumnIndex("id_opcion_crud"));
            permisos.add(permiso);
        }

        if (!permisos.contains(2)) {
            btnActualizar.setEnabled(false);
        }

        if (!permisos.contains(3)) {
            btnEliminar.setEnabled(false);
        }

        helper = new ControlG10Proyecto01(this);

        helper.abrir();
        OfertaAcademica ofertaAcademica = helper.consultarOfertaAcdemica(String.valueOf(id));
        helper.cerrar();

        if (ofertaAcademica == null) {
            Toast.makeText(this, "Oferta no encontrada en la DB", Toast.LENGTH_LONG).show();
        } else {
            editIdOferta.setEnabled(false);
            spinnerCiclo.setEnabled(false);
            spinnerDocente.setEnabled(false);
            spinnerMateria.setEnabled(false);


            int posCiclo = 0, posDocente = 0, posMateria = 0;

            for (Ciclo ciclo : listaCiclos) {
                if (ciclo.getId_ciclo() == ofertaAcademica.getId_ciclo()) {
                    posCiclo = listaCiclos.indexOf(ciclo);
                }
            }

            for (Docente docente : listaDocentes) {
                if (docente.getId_docente() == ofertaAcademica.getId_docente()) {
                    posDocente = listaDocentes.indexOf(docente);
                }
            }

            for (Materia materia : listaMaterias) {
                if (materia.getId_materia() == ofertaAcademica.getId_materia()) {
                    posMateria = listaMaterias.indexOf(materia);
                }
            }

            editIdOferta.setText(String.valueOf(ofertaAcademica.getId_oferta_a()));
            spinnerCiclo.setSelection(posCiclo);
            spinnerDocente.setSelection(posDocente);
            spinnerMateria.setSelection(posMateria);
        }
    }

    public void habilitarEdicion(View v) {
        if (edicion == 0) {
            spinnerCiclo.setEnabled(true);
            spinnerDocente.setEnabled(true);
            spinnerMateria.setEnabled(true);
            btnActualizar.setText(getResources().getString(R.string.guardar));

            edicion = 1;

        } else {
            int id_oferta_a = Integer.valueOf(editIdOferta.getText().toString());

            int seleccionCiclo = spinnerCiclo.getSelectedItemPosition();
            int id_ciclo = listaCiclos.get(seleccionCiclo).getId_ciclo();

            int seleccionDocente = spinnerDocente.getSelectedItemPosition();
            int id_docente = listaDocentes.get(seleccionDocente).getId_docente();

            int seleccionMateria = spinnerMateria.getSelectedItemPosition();
            int id_materia = listaMaterias.get(seleccionMateria).getId_materia();

            OfertaAcademica ofertaAcademica = new OfertaAcademica();
            ofertaAcademica.setId_oferta_a(id_oferta_a);
            ofertaAcademica.setId_ciclo(id_ciclo);
            ofertaAcademica.setId_docente(id_docente);
            ofertaAcademica.setId_materia(id_materia);

            helper.abrir();
            String estado = helper.actualizar(ofertaAcademica);
            helper.cerrar();

            Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();

            actulizarAlRegresar();
        }
    }

    public void eliminarOferta(View v) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setCancelable(false);

        alerta.setMessage(getResources().getString(R.string.mensajeAlerta));

        alerta.setPositiveButton(getResources().getString(R.string.si), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //ACCIONES SI RESPONDE QUE SI A LA ALERTA
                String regEliminadas;

                OfertaAcademica ofertaAcademica = new OfertaAcademica();
                ofertaAcademica.setId_oferta_a(Integer.parseInt(editIdOferta.getText().toString()));

                helper.abrir();
                regEliminadas = helper.eliminar(ofertaAcademica);
                helper.cerrar();

                Toast.makeText(v.getContext(), regEliminadas, Toast.LENGTH_SHORT).show();

                actulizarAlRegresar();
            }
        });

        alerta.setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //ACCIONES SI PONE NO
                dialog.cancel();
            }
        });
        //MUESTRA ALERTA PARA EVENTO ONCLICK DEL BOTON
        alerta.show();
    }

    public void actulizarAlRegresar() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}