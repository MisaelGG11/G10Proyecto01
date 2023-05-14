package com.example.g10proyecto01;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PropuestaGeneralActualizarActivity extends Activity {

    // initialize variables
    ControlG10Proyecto01 helper;
    Spinner spinIdPropuestaGeneral;
    List<Integer> opcSpiIdPropuestaGeneral = new ArrayList<>();
    Spinner spinEstadoPropuesta;
    List<String> opcSpinEstado = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propuesta_general_actualizar);
        helper = new ControlG10Proyecto01(this);
        spinIdPropuestaGeneral = findViewById(R.id.spinIdPropuestaGeneral);
        spinEstadoPropuesta = findViewById(R.id.spinActualizarEstadoPropuestaGeneral);
        llenarSpinnerIdPropuestaGeneral();
        llenarSpinnerEstado();
    }
    public void llenarSpinnerIdPropuestaGeneral(){
        String query = "SELECT id_propuesta FROM Propuesta_General";
        Cursor cursor = helper.llenarSpinner(query);
        while(cursor.moveToNext()){
            @SuppressLint("Range")
            int idPropuesta = cursor.getInt(cursor.getColumnIndex("id_propuesta"));
            opcSpiIdPropuestaGeneral.add(idPropuesta);
        }
        ArrayAdapter<Integer> adapterOpcionesSpinner = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, opcSpiIdPropuestaGeneral);
        adapterOpcionesSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinIdPropuestaGeneral.setAdapter(adapterOpcionesSpinner);
    }

    public void llenarSpinnerEstado(){
        String[] opcionesEstado = {"Aprobada", "Denegada","Parcialmente Aprobada"};
        for (int i = 0; i < opcionesEstado.length; i++) {
            opcSpinEstado.add(opcionesEstado[i]);
        }
        ArrayAdapter<String> adapterOpcionesSpinner = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, opcSpinEstado);
        adapterOpcionesSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinEstadoPropuesta.setAdapter(adapterOpcionesSpinner);
    }

    public void ActualizarPropuestaGeneral(View v){
        int id = Integer.parseInt(spinIdPropuestaGeneral.getSelectedItem().toString());
        String estadoPropuesta = spinEstadoPropuesta.getSelectedItem().toString().substring(0,1);
        PropuestaGeneral propuestaGeneral = new PropuestaGeneral(id,estadoPropuesta);
        helper.abrir();
        String estado = helper.actualizar(propuestaGeneral);
        helper.cerrar();
        Toast.makeText(this,estado,Toast.LENGTH_LONG).show();
    }

}