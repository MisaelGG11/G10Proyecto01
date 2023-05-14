package com.example.g10proyecto01;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UsuarioEliminarActivity extends Activity {
    ControlG10Proyecto01 helper;
    Spinner spinnerIdUsuario;
    List<String> opcionesSpinner = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_eliminar);
        helper = new ControlG10Proyecto01(this);
        spinnerIdUsuario = findViewById(R.id.spinEliminarIdUsuario);
        String query = "SELECT id_usuario FROM Usuario";
        Cursor cursor = helper.llenarSpinner(query);
        while(cursor.moveToNext()){
            @SuppressLint("Range")
            String idUsuario = cursor.getString(cursor.getColumnIndex("id_usuario"));
            opcionesSpinner.add(idUsuario);
        }
        ArrayAdapter<String> adapterOpcionesSpinner = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,opcionesSpinner);
        adapterOpcionesSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdUsuario.setAdapter(adapterOpcionesSpinner);
    }

    public void EliminarUsuario(View v){
        AlertDialog.Builder alerta = new AlertDialog.Builder(UsuarioEliminarActivity.this);
        alerta.setCancelable(false);
        alerta.setMessage(R.string.mensajeAlerta);
        alerta.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //ACCIONES SI RESPONDE QUE SI A LA ALERTA
                String regEliminadas;

                String idUsuario =spinnerIdUsuario.getSelectedItem().toString().substring(0,2);
                Usuario usuario = new Usuario(idUsuario);
                helper.abrir();
                regEliminadas=helper.eliminar(usuario);
                helper.cerrar();
                Toast.makeText(UsuarioEliminarActivity.this, regEliminadas, Toast.LENGTH_SHORT).show();
            }
        });
        alerta.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //ACCIONES SI PONE NO
                dialog.cancel();
            }
        });
        //MUESTRA ALERTA PARA EVENTO ONCLICK DEL BOTON
        alerta.show();
    }
}