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

public class MateriaConsultarActivity extends AppCompatActivity {
    ControlG10Proyecto01 helper;
    EditText editIdMateria, editCodMateria, editNomMateria;
    Spinner spinnerMateria;
    List<Escuela> listaEscuelas = new ArrayList<>();
    Button btnActualizar, btnEliminar;

    int edicion = 0;
    int id = 0;

    String userPermisos;
    List<Integer> permisos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_consultar);

        helper = new ControlG10Proyecto01(this);

        editIdMateria = findViewById(R.id.editIdMateria);
        editCodMateria = findViewById(R.id.editCodMateria);
        editNomMateria = findViewById(R.id.editNombreMateria);

        spinnerMateria = findViewById(R.id.spinnerMateria);

        btnActualizar = findViewById(R.id.btnActualizarMateria);
        btnEliminar = findViewById(R.id.btnEliminarMateria);

        helper.abrir();
        listaEscuelas = helper.mostrarEscuelas();
        helper.cerrar();

        //establece valores al spinner
        ArrayAdapter<Escuela> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaEscuelas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMateria.setAdapter(adapter);

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

        String sql = "SELECT id_opcion_crud FROM AccesoUsuario WHERE id_usuario = '"+ userPermisos+"'";

        Cursor cursor = helper.llenarSpinner(sql);

        while (cursor.moveToNext()) {
            @SuppressLint("Range")
            int permiso = cursor.getInt(cursor.getColumnIndex("id_opcion_crud"));
            permisos.add(permiso);
        }

        if (!permisos.contains(2)){
            btnActualizar.setEnabled(false);
        }

        if (!permisos.contains(3)){
            btnEliminar.setEnabled(false);
        }

        helper = new ControlG10Proyecto01(this);

        helper.abrir();
        Materia materia = helper.consultarMateria(String.valueOf(id));
        helper.cerrar();

        if (materia == null) {
            Toast.makeText(this, "Materia no encontrada en la DB", Toast.LENGTH_LONG).show();
        } else {
            editIdMateria.setEnabled(false);
            editCodMateria.setEnabled(false);
            editNomMateria.setEnabled(false);
            spinnerMateria.setEnabled(false);

            int posicion = 0;

            for (Escuela escuela : listaEscuelas) {
                if (escuela.getId_escuela() == materia.getId_escuela()) {
                    posicion = listaEscuelas.indexOf(escuela);
                }
            }

            editIdMateria.setText(String.valueOf(materia.getId_materia()));
            editCodMateria.setText(materia.getCod_materia());
            editNomMateria.setText(materia.getNom_materia());
            spinnerMateria.setSelection(posicion);
        }
    }

    public void habilitarEdicion(View v) {
        if (edicion == 0) {
            editCodMateria.setEnabled(true);
            editNomMateria.setEnabled(true);
            spinnerMateria.setEnabled(true);
            btnActualizar.setText(getResources().getString(R.string.guardar));

            edicion = 1;

        } else {
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

                Materia materia = new Materia();
                materia.setId_materia(id_materia);
                materia.setCod_materia(cod_materia);
                materia.setNom_materia(nombre_materia);
                materia.setId_escuela(id_escuela);

                helper.abrir();
                String estado = helper.actualizar(materia);
                helper.cerrar();

                if (estado.contains("Correcto")) {
                    Toast.makeText(this, getResources().getString(R.string.regActualizado), Toast.LENGTH_SHORT).show();

                    actulizarAlRegresar();
                } else {
                    Toast.makeText(this, getResources().getString(R.string.regNoActualizado), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void eliminarMateria(View v) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setCancelable(false);

        alerta.setMessage(getResources().getString(R.string.mensajeAlerta));

        alerta.setPositiveButton(getResources().getString(R.string.si), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //ACCIONES SI RESPONDE QUE SI A LA ALERTA
                String regEliminadas;

                Materia materia = new Materia();
                materia.setId_materia(Integer.parseInt(editIdMateria.getText().toString()));

                helper.abrir();
                regEliminadas = helper.eliminar(materia);
                helper.cerrar();

                Toast.makeText(v.getContext(), getResources().getString(R.string.regEliminados) + regEliminadas, Toast.LENGTH_SHORT).show();

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