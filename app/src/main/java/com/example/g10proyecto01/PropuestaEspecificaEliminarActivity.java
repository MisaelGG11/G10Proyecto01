package com.example.g10proyecto01;

import androidx.appcompat.app.AppCompatActivity;

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

public class PropuestaEspecificaEliminarActivity extends Activity {

    ControlG10Proyecto01 helper;
    Spinner spinIdPropuesta;
    List<Integer> opcionesSpinner = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propuesta_especifica_eliminar);
        helper = new ControlG10Proyecto01(this);
        llenarSpinnerPropuestaGeneral();
    }
    public void llenarSpinnerPropuestaGeneral(){
        spinIdPropuesta = findViewById(R.id.spinEliminarPropuestaEspecifica);
        String query = "SELECT id_especifica FROM Propuesta_Especifica";
        Cursor cursor = helper.llenarSpinner(query);
        while(cursor.moveToNext()){
            @SuppressLint("Range")
            int idPropuesta = cursor.getInt(cursor.getColumnIndex("id_especifica"));
            opcionesSpinner.add(idPropuesta);
        }
        ArrayAdapter<Integer> adapterOpcionesSpinner = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,opcionesSpinner);
        adapterOpcionesSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinIdPropuesta.setAdapter(adapterOpcionesSpinner);
    }
    public void EliminarPropuestaGeneral(View v){
        AlertDialog.Builder alerta = new AlertDialog.Builder(PropuestaEspecificaEliminarActivity.this);
        alerta.setCancelable(false);
        alerta.setMessage(R.string.mensajeAlerta);
        alerta.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //ACCIONES SI RESPONDE QUE SI A LA ALERTA
                String regEliminadas;

                int idUsuario = Integer.parseInt(spinIdPropuesta.getSelectedItem().toString());
                PropuestaEspecifica propuestaEspecifica = new PropuestaEspecifica(idUsuario);
                helper.abrir();
                regEliminadas=helper.eliminar(propuestaEspecifica);
                helper.cerrar();
                Toast.makeText(PropuestaEspecificaEliminarActivity.this, regEliminadas, Toast.LENGTH_SHORT).show();
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