package com.example.g10proyecto01;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class PropuestaEspecificaActualizarActivity extends Activity {
    ControlG10Proyecto01 helper;
    Spinner spinIdPropuestaEspecifica;
    List<Integer> opcSpiIdPropuestaEspecifica = new ArrayList<>();
    Spinner spinIdPropuestaGeneral;
    List<Integer> opcSpiIdPropuestaGeneral = new ArrayList<>();
    Spinner spinEstadoPropuesta;
    Spinner spinHorarioSpinner;
    Spinner spinLocalidad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propuesta_especifica_actualizar);
        helper = new ControlG10Proyecto01(this);
        spinIdPropuestaEspecifica = findViewById(R.id.spinActualizarIdPropuestaEspecifica);
        spinIdPropuestaGeneral = findViewById(R.id.spinActualizarGeneralPropuestaEspecifica);
        spinEstadoPropuesta = findViewById(R.id.spinActualizarEstadoPropuestaEspecifica);
        spinHorarioSpinner = findViewById(R.id.spinActualizarHorarioPropuestaEspecifica);
        spinLocalidad = findViewById(R.id.spinActualizarLocalidadPropuestaEspecifica);
        llenarSpinnerIdPropuestaEspecifica();
        llenarSpinnerIdPropuestaGeneral();


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
}