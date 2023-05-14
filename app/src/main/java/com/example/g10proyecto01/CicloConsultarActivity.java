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
import java.util.Arrays;
import java.util.List;

public class CicloConsultarActivity extends AppCompatActivity {

    EditText editIdCiclo, editAño;
    Spinner spinnerCiclo;
    Button btnActualizar, btnEliminar;

    ControlG10Proyecto01 helper;
    int edicion = 0;
    int id = 0;
    String userPermisos;
    List<Integer> permisos = new ArrayList<>();
    List<Integer> valores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo_consultar);

        editIdCiclo = findViewById(R.id.editIdCiclo);
        spinnerCiclo = findViewById(R.id.spinnerCiclo);
        editAño = findViewById(R.id.editAño);

        btnActualizar = findViewById(R.id.btnActualizarCiclo);
        btnEliminar = findViewById(R.id.btnEliminarCiclo);

        //llenamos la lista
        valores.add(1);
        valores.add(2);

        //establece valores al spinner
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, valores);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCiclo.setAdapter(adapter);

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

        Ciclo ciclo = helper.consultarCiclo(String.valueOf(id));

        helper.cerrar();

        if (ciclo == null) {
            Toast.makeText(this, "Ciclo no encontrada en la DB", Toast.LENGTH_LONG).show();
        } else {
            editIdCiclo.setEnabled(false);
            spinnerCiclo.setEnabled(false);
            editAño.setEnabled(false);

            int posicion = valores.indexOf(Integer.valueOf(ciclo.getCiclo()));

            editIdCiclo.setText(String.valueOf(ciclo.getId_ciclo()));
            spinnerCiclo.setSelection(posicion);
            editAño.setText(ciclo.getAño());
        }
    }

    public void habilitarEdicion(View v) {
        if (edicion == 0) {
            spinnerCiclo.setEnabled(true);
            editAño.setEnabled(true);
            btnActualizar.setText(getResources().getString(R.string.guardar));

            edicion = 1;

        } else {
            String regex = "^(2000|2[0-9]{3}|3000)$";

            if (editIdCiclo.getText().toString().isEmpty() ||
                    editAño.getText().toString().isEmpty()) {

                Toast.makeText(this, getResources().getString(R.string.vacio), Toast.LENGTH_SHORT).show();
            } else if (editAño.getText().toString().matches(regex)){
                Ciclo ciclo = new Ciclo();

                ciclo.setId_ciclo(Integer.valueOf(editIdCiclo.getText().toString()));
                ciclo.setCiclo(String.valueOf(valores.get(spinnerCiclo.getSelectedItemPosition())));
                ciclo.setAño(editAño.getText().toString());

                helper.abrir();
                String estado = helper.actualizar(ciclo);
                helper.cerrar();

                Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();

                actulizarAlRegresar();
            } else {
                Toast.makeText(this, getResources().getString(R.string.anioValido), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void eliminarCiclo(View v) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setCancelable(false);

        alerta.setMessage(getResources().getString(R.string.mensajeAlerta));

        alerta.setPositiveButton(getResources().getString(R.string.si), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //ACCIONES SI RESPONDE QUE SI A LA ALERTA
                String regEliminadas;

                Ciclo ciclo = new Ciclo();
                ciclo.setId_ciclo(Integer.parseInt(editIdCiclo.getText().toString()));

                helper.abrir();
                regEliminadas = helper.eliminar(ciclo);
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