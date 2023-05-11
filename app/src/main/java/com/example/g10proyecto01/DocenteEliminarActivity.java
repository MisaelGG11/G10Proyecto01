package com.example.g10proyecto01;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DocenteEliminarActivity extends Activity {

    ControlG10Proyecto01 helper;
    List<Integer> listIdDocente = new ArrayList<>();
    Spinner spinnerIdDocente;
    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_eliminar);

        helper = new ControlG10Proyecto01(this);
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

    public void eliminarDocente(View v){
        AlertDialog.Builder alerta = new AlertDialog.Builder(DocenteEliminarActivity.this);
        alerta.setCancelable(false);

        int idDocente;
        idDocente =listIdDocente.get(spinnerIdDocente.getSelectedItemPosition());

        alerta.setMessage("¿Desea todos los registros asociados a Id Docente:  " + idDocente +"?");
        alerta.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //ACCIONES SI RESPONDE QUE SI A LA ALERTA
                String regEliminadas;
                Docente docente = new Docente();
                docente.setId_docente(idDocente);
                helper.abrir();
                regEliminadas=helper.eliminar(docente);
                helper.cerrar();
                Toast.makeText(DocenteEliminarActivity.this, regEliminadas, Toast.LENGTH_SHORT).show();
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