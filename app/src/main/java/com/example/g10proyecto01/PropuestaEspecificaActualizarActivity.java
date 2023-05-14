package com.example.g10proyecto01;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PropuestaEspecificaActualizarActivity extends Activity {
    ControlG10Proyecto01 helper;
    Spinner spinIdPropuestaEspecifica;
    List<Integer> opcSpiIdPropuestaEspecifica = new ArrayList<>();
    Spinner spinEstadoPropuesta;
    List<String> opcSpinEstado = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propuesta_especifica_actualizar);
        helper = new ControlG10Proyecto01(this);
        spinIdPropuestaEspecifica = findViewById(R.id.spinActualizarIdPropuestaEspecifica);
        spinEstadoPropuesta = findViewById(R.id.spinActualizarEstadoPropuestaEspecifica);
        llenarSpinnerIdPropuestaEspecifica();
        llenarSpinnerEstado();
    }
    public void llenarSpinnerIdPropuestaEspecifica(){
        String query = "SELECT id_especifica FROM Propuesta_Especifica";
        Cursor cursor = helper.llenarSpinner(query);
        while(cursor.moveToNext()){
            @SuppressLint("Range")
            int idPropuesta = cursor.getInt(cursor.getColumnIndex("id_especifica"));
            opcSpiIdPropuestaEspecifica.add(idPropuesta);
        }
        ArrayAdapter<Integer> adapterOpcionesSpinner = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, opcSpiIdPropuestaEspecifica);
        adapterOpcionesSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinIdPropuestaEspecifica.setAdapter(adapterOpcionesSpinner);
    }
    public void llenarSpinnerEstado(){
        String[] opcionesEstado = {"Aprobada", "Denegada","Pendiente"};
        for (int i = 0; i < opcionesEstado.length; i++) {
            opcSpinEstado.add(opcionesEstado[i]);
        }
        ArrayAdapter<String> adapterOpcionesSpinner = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, opcSpinEstado);
        adapterOpcionesSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinEstadoPropuesta.setAdapter(adapterOpcionesSpinner);
    }

    public void ActualizarPropuestaEspecifica(View v){
        int id = Integer.parseInt(spinIdPropuestaEspecifica.getSelectedItem().toString());
        String estadoPropuesta = spinEstadoPropuesta.getSelectedItem().toString().substring(0,1);
        PropuestaEspecifica propuestaEspecifica = new PropuestaEspecifica(id,estadoPropuesta);
        helper.abrir();
        String estado = helper.actualizar(propuestaEspecifica);
        helper.cerrar();
        Toast.makeText(this,estado,Toast.LENGTH_LONG).show();
    }
}