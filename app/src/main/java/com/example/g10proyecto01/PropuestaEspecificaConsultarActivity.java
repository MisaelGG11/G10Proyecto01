package com.example.g10proyecto01;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PropuestaEspecificaConsultarActivity extends Activity {
    ControlG10Proyecto01 helper;
    EditText editPropuestaGeneral;
    EditText editEstadoPropuesta;
    EditText editGrupoHorario;
    EditText editLocalidad;
    Spinner spinIdPropuesta;
    List<Integer> opcionesSpinner = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propuesta_especifica_consultar);
        helper = new ControlG10Proyecto01(this);
        spinIdPropuesta = findViewById(R.id.spinCunsultaIdPropuestaEspecifica);
        editPropuestaGeneral = findViewById(R.id.editConsultaGeneralPropuestaEspecifica);
        editEstadoPropuesta = findViewById(R.id.editConsultarEstadoPropuestaEspecifica);
        editGrupoHorario = findViewById(R.id.editConsultarHorarioPropuestaEspecifica);
        editLocalidad = findViewById(R.id.editConsultarLocalidadPropuestaEspecifica);
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
    public void ConsultarPropuestaEspecifica(View v){
        String idPropuestaEspecifica = spinIdPropuesta.getSelectedItem().toString();
        helper.abrir();
        PropuestaEspecifica propuestaEspecifica = helper.consultarPropuestaEspecifica(idPropuestaEspecifica);
        Localidad localidad = helper.consultarlocalidad(String.valueOf(propuestaEspecifica.getId_localidad()));
        String horario = helper.consularHorarioPropuestaEspcifica(String.valueOf(propuestaEspecifica.getId_grupo_horario()));
        helper.cerrar();
        if(propuestaEspecifica == null){
            Toast.makeText(this, R.string.registro_no_encontrado, Toast.LENGTH_LONG).show();
        }
        editPropuestaGeneral.setText(String.valueOf(propuestaEspecifica.getId_propuesta_general()));
        editGrupoHorario.setText(horario);
        editLocalidad.setText(String.valueOf(localidad.getNombre_localidad()));

        String estadoPropuesta = propuestaEspecifica.getEstado_especifica().substring(0,1);
        Log.wtf("Test",estadoPropuesta);
        if(estadoPropuesta.equals("D")){
            editEstadoPropuesta.setText("Denegada");
        } else if (estadoPropuesta.equals("P")) {
            editEstadoPropuesta.setText("Pendiente");
        }else {
            editEstadoPropuesta.setText("Aprobada");
        }
    }

    public void LimpiarTexto(View v){
        editLocalidad.setText("");
        editEstadoPropuesta.setText("");
        editGrupoHorario.setText("");
        editPropuestaGeneral.setText("");
    }
}