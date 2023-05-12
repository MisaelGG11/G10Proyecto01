package com.example.g10proyecto01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EscuelaConsultarActivity extends AppCompatActivity {

    EditText editIdEscuela, editAcronimo, editNombre;
    Button btnActualizar;
    Escuela escuela;
    ControlG10Proyecto01 helper;
    int edicion = 0;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escuela_consultar);

        editIdEscuela = findViewById(R.id.txtIdEscuela);
        editAcronimo = findViewById(R.id.txtAcronimo);
        editNombre = findViewById(R.id.txtNombre);

        btnActualizar = findViewById(R.id.btnActualizar);
        //btnAgregar.setVisibility(View.INVISIBLE);

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

        helper = new ControlG10Proyecto01(this);

        helper.abrir();

        Escuela escuela = helper.consultar(String.valueOf(id));

        helper.cerrar();

        if (escuela == null) {
            Toast.makeText(this, "Escuela no encontrada en la DB", Toast.LENGTH_LONG).show();
        } else {
            editIdEscuela.setEnabled(false);
            editIdEscuela.setTextColor(Color.BLACK);
            editAcronimo.setEnabled(false);
            editAcronimo.setTextColor(Color.BLACK);
            editNombre.setEnabled(false);
            editNombre.setTextColor(Color.BLACK);

            editIdEscuela.setText(String.valueOf(escuela.getId_escuela()));
            editAcronimo.setText(escuela.getAcronimo());
            editNombre.setText(escuela.getNombre());
        }
    }

    public void habilitarEdicion(View v) {
        if (edicion == 0) {
            editAcronimo.setEnabled(true);
            editNombre.setEnabled(true);
            btnActualizar.setText(getResources().getString(R.string.guardar));

            edicion = 1;

        } else {
            if (editIdEscuela.getText().toString().isEmpty() ||
                    editAcronimo.getText().toString().isEmpty() ||
                    editNombre.getText().toString().isEmpty()) {

                Toast.makeText(this, getResources().getString(R.string.vacio), Toast.LENGTH_SHORT).show();
            } else {
                Escuela escuela = new Escuela();

                escuela.setId_escuela(Integer.parseInt(editIdEscuela.getText().toString()));
                escuela.setAcronimo(editAcronimo.getText().toString());
                escuela.setNombre(editNombre.getText().toString());

                helper.abrir();
                String estado = helper.actualizar(escuela);
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

    public void eliminarEscuela(View v) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setCancelable(false);

        alerta.setMessage(getResources().getString(R.string.mensajeAlerta));

        alerta.setPositiveButton(getResources().getString(R.string.si), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //ACCIONES SI RESPONDE QUE SI A LA ALERTA
                String regEliminadas;

                Escuela escuela = new Escuela();
                escuela.setId_escuela(Integer.parseInt(editIdEscuela.getText().toString()));

                helper.abrir();
                regEliminadas = helper.eliminar(escuela);
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