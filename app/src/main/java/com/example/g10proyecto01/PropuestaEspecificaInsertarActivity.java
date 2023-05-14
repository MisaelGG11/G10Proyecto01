package com.example.g10proyecto01;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PropuestaEspecificaInsertarActivity extends Activity {
    ControlG10Proyecto01 helper;
    Spinner spinnerIdPropuestaGeneral;
    Spinner spinnerIdGrupoHorario;
    Spinner spinnerIdLocalidad;
    List<Integer> opcionesSpinPropuesta = new ArrayList<>();
    List<Integer> opcionesSpinGrupoHorario = new ArrayList<>();
    List<Integer> opcionesSpinLocalidad = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propuesta_especifica_insertar);
        helper = new ControlG10Proyecto01(this);

        spinnerIdPropuestaGeneral = findViewById(R.id.spinPropuestaGeneralPropuestaEspecifica);
        String queryPropuestaGeneral = "SELECT id_propuesta FROM Propuesta_General";
        Cursor cursor = helper.llenarSpinner(queryPropuestaGeneral);
        while(cursor.moveToNext()){
            @SuppressLint("Range")
            int id = cursor.getInt(cursor.getColumnIndex("id_propuesta"));
            opcionesSpinPropuesta.add(id);
        }
        ArrayAdapter<Integer> adapterPropuestaGeneral = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, opcionesSpinPropuesta);
        adapterPropuestaGeneral.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdPropuestaGeneral.setAdapter(adapterPropuestaGeneral);

        spinnerIdGrupoHorario = findViewById(R.id.spinHorarioPropuestaEspecifica);
        String queryGrupoHorario = "SELECT id_gh FROM Grupo_Horario";
        Cursor cursorGrupoHorario = helper.llenarSpinner(queryGrupoHorario);
        while(cursorGrupoHorario.moveToNext()){
            @SuppressLint("Range")
            int id = cursorGrupoHorario.getInt(cursorGrupoHorario.getColumnIndex("id_gh"));
            opcionesSpinGrupoHorario.add(id);
        }
        ArrayAdapter<Integer> adapaterHorario = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, opcionesSpinGrupoHorario);
        adapaterHorario.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdGrupoHorario.setAdapter(adapaterHorario);

        spinnerIdLocalidad = findViewById(R.id.spinLocalidadPropuestaEspecifica);
        String queryLocalidad = "SELECT id_localidad FROM Localidad";
        Cursor cursorLocalidad = helper.llenarSpinner(queryLocalidad);
        while(cursorLocalidad.moveToNext()){
            @SuppressLint("Range")
            int id = cursorLocalidad.getInt(cursorLocalidad.getColumnIndex("id_localidad"));
            opcionesSpinLocalidad.add(id);
        }
        ArrayAdapter<Integer> adapaterLocalidad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, opcionesSpinLocalidad);
        adapaterLocalidad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdLocalidad.setAdapter(adapaterLocalidad);
    }

    public void InsertarPropuestaEspecifica(View V){
        int idPropuestaGeneral = Integer.parseInt(spinnerIdPropuestaGeneral.getSelectedItem().toString());
        int idGrupoHorario = Integer.parseInt(spinnerIdGrupoHorario.getSelectedItem().toString());
        int idLocalidad = Integer.parseInt(spinnerIdLocalidad.getSelectedItem().toString());
        String regInsertados;
        PropuestaEspecifica propuestaEspecifica = new PropuestaEspecifica(idPropuestaGeneral,idGrupoHorario,idLocalidad);

        helper.abrir();
        regInsertados=helper.insertar(propuestaEspecifica);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
}