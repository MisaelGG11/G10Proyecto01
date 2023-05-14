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

public class PropuestaEspecificaInsertarActivity extends Activity {
    ControlG10Proyecto01 helper;
    Spinner spinnerIdPropuestaGeneral;
    Spinner spinnerIdGrupoHorario;
    Spinner spinnerIdLocalidad;
    List<Integer> opcionesSpinPropuesta = new ArrayList<>();
    List<String> opcionesSpinGrupoHorario = new ArrayList<>();
    List<String> opcionesSpinLocalidad = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propuesta_especifica_insertar);
        helper = new ControlG10Proyecto01(this);
        llenarSpinnerPropuestaGeneral();
        llenarSpinnerGrupoHorario();
        llenarSpinnerLocalidad();

    }

    public void InsertarPropuestaEspecifica(View V){
        int idPropuestaGeneral = Integer.parseInt(spinnerIdPropuestaGeneral.getSelectedItem().toString());
        int idGrupoHorario = Integer.parseInt(spinnerIdGrupoHorario.getSelectedItem().toString().substring(0,1));
        int idLocalidad = Integer.parseInt(spinnerIdLocalidad.getSelectedItem().toString().substring(0,1));
        String regInsertados = "";
        PropuestaEspecifica propuestaEspecifica = new PropuestaEspecifica(idPropuestaGeneral,idGrupoHorario,idLocalidad);

        helper.abrir();
        regInsertados=helper.insertar(propuestaEspecifica);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void llenarSpinnerGrupoHorario(){
        spinnerIdGrupoHorario = findViewById(R.id.spinHorarioPropuestaEspecifica);
        String queryGrupoHorario = "SELECT gh.id_gh, g.tipo_grupo, g.num_grupo, g.cupo, h.dia, h.hora_inicio, h.hora_finalizacion FROM Grupo_Horario gh INNER JOIN Horario h ON gh.id_horario = h.id_horario INNER JOIN  Grupo g  ON g.id_grupo = gh.id_grupo";
        Cursor cursorGrupoHorario = helper.opcioneSpinnerGrupoHorario(queryGrupoHorario);
        while(cursorGrupoHorario.moveToNext()){
            @SuppressLint("Range")
            String id = cursorGrupoHorario.getString(0);
            String tipoGrupo = cursorGrupoHorario.getString(1);
            String numeroGrupo = cursorGrupoHorario.getString(2);
            String cupoGrupo = cursorGrupoHorario.getString(3);
            String diaGrupo = cursorGrupoHorario.getString(4);
            String horaInicio = cursorGrupoHorario.getString(5).substring(10,19);
            String horaFinal = cursorGrupoHorario.getString(6).substring(10,19);
            String opcion = id + ": " + tipoGrupo + " "+numeroGrupo + " cupo: " + cupoGrupo + "\n" +diaGrupo+ " "+horaInicio+" -" + horaFinal;
            opcionesSpinGrupoHorario.add(opcion);
        }
        ArrayAdapter<String> adapaterHorario = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, opcionesSpinGrupoHorario);
        adapaterHorario.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdGrupoHorario.setAdapter(adapaterHorario);
    }

    public void llenarSpinnerPropuestaGeneral(){
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
    }

    public void llenarSpinnerLocalidad(){
        spinnerIdLocalidad = findViewById(R.id.spinLocalidadPropuestaEspecifica);
        String queryLocalidad = "SELECT id_localidad, nombre_localidad FROM Localidad";
        Cursor cursorLocalidad = helper.llenarSpinner(queryLocalidad);
        while(cursorLocalidad.moveToNext()){
            @SuppressLint("Range")
            String id = cursorLocalidad.getString(0);
            String nombre = cursorLocalidad.getString(1);
            opcionesSpinLocalidad.add(id+": "+nombre);
        }
        ArrayAdapter<String> adapaterLocalidad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, opcionesSpinLocalidad);
        adapaterLocalidad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdLocalidad.setAdapter(adapaterLocalidad);
    }
}