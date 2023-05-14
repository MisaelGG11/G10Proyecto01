package com.example.g10proyecto01;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
public class  EventoEspecialActualizarActivity extends Activity {
    ControlG10Proyecto01 helper;
    List<Integer> EveSpinner = new ArrayList<>();

    EditText editIdEventoEspecial,editFecha,editNombre;
    EditText editIdHorario;
    List<Integer> listIdEvento = new ArrayList<>();
    List<Integer> listIdLocal = new ArrayList<>();
    List<Integer> listIdEncargado = new ArrayList<>();
    List<Integer> listIdTipo = new ArrayList<>();
    List<Integer> listIdHorario = new ArrayList<>();
    Spinner spinnerIdEvento;
    Spinner spinnerEmpleado;
    Spinner spinnerLocal;
    Spinner spinnerHorario;
    Spinner spinnerTipo;

    final String ID_EVENTO = "id_evento";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_especial_actualizar);
        helper = new ControlG10Proyecto01(this);

        spinnerIdEvento = findViewById(R.id.edittidEventoE);

        editNombre = (EditText) findViewById(R.id.editTextNombreEvento);
        spinnerTipo = findViewById(R.id.spinidtipoevento);
        spinnerEmpleado = findViewById(R.id.spinorganizador);
        editFecha = (EditText) findViewById(R.id.editTextfecha);
        //editIdHorario= (EditText) findViewById(R.id.editTextHorario);
        spinnerHorario = findViewById(R.id.editTextHorario);
        spinnerLocal = findViewById(R.id.spinidlocal);

        String query = "SELECT id_evento FROM Evento_Especial";
        Cursor cursor = helper.llenarSpinner(query);
        while(cursor.moveToNext()){
            @SuppressLint("Range")
            int id = cursor.getInt(cursor.getColumnIndex(ID_EVENTO));
            EveSpinner.add(id);
        }
        ArrayAdapter<Integer> adapterEveSpinner = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,EveSpinner);
        adapterEveSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdEvento.setAdapter(adapterEveSpinner);
        SpinnerLoc();
        SpinnerEmpleado();
        SpinnerTipo();
        SpinnerHorario();
    }

    public void ActualizarEventoE(View v){
        String idevento = spinnerIdEvento.getSelectedItem().toString();
        String NomEve = editNombre.getText().toString();
        String TipoEve = spinnerTipo.getSelectedItem().toString();
        String OrganizadorEve = spinnerEmpleado.getSelectedItem().toString();
        String FechaEve = editFecha.getText().toString();
        //String HorarioEve = editIdHorario.getText().toString();
        String HorarioEve = spinnerHorario.getSelectedItem().toString();
        String LocalEve = spinnerLocal.getSelectedItem().toString();


        if (NomEve.isEmpty()||FechaEve.isEmpty()) {
            Toast.makeText(EventoEspecialActualizarActivity.this, "Ingresar datos obligatorios", Toast.LENGTH_SHORT).show();
        } else {
            EventoEspecial eventoEspecial = new EventoEspecial(Integer.parseInt(idevento),NomEve, Integer.parseInt(TipoEve), Integer.parseInt(OrganizadorEve),FechaEve, Integer.parseInt(HorarioEve), Integer.parseInt(LocalEve));
            System.out.println(eventoEspecial.toString());
            helper.abrir();
            String estado = helper.actualizarEventoEspecial(eventoEspecial);
            helper.cerrar();

            Toast.makeText(this,estado,Toast.LENGTH_LONG).show();
        }

    }

    public void limpiarTextoEven(View v) {
        editFecha.setText("");
        editNombre.setText("");
    }
    public void SpinnerLoc(){
        String sql = "SELECT id_localidad FROM Localidad";
        Cursor cursorL = helper.llenarSpinner(sql);
        while (cursorL.moveToNext()) {
            @SuppressLint("Range")
            int idLoc = cursorL.getInt(cursorL.getColumnIndex("id_localidad"));
            listIdLocal.add(idLoc);
        }
        ArrayAdapter<Integer> adapterL = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listIdLocal);
        adapterL.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapterL.notifyDataSetChanged();
        spinnerLocal.setAdapter(adapterL);
    }

    public void SpinnerEmpleado(){
        String sql = "SELECT id_empleado FROM Empleado_UES";
        Cursor cursorE = helper.llenarSpinner(sql);

        while (cursorE.moveToNext()) {
            @SuppressLint("Range")
            int idEn = cursorE.getInt(cursorE.getColumnIndex("id_empleado"));
            listIdEncargado.add(idEn);
        }
        ArrayAdapter<Integer> adapterE = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listIdEncargado);
        adapterE.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEmpleado.setAdapter(adapterE);
    }
    public void SpinnerTipo(){
        String sql = "SELECT id_tipo_evento FROM Tipo_evento";
        Cursor cursorL = helper.llenarSpinner(sql);
        while (cursorL.moveToNext()) {
            @SuppressLint("Range")
            int idLoc = cursorL.getInt(cursorL.getColumnIndex("id_tipo_evento"));
            listIdTipo.add(idLoc);
        }
        ArrayAdapter<Integer> adapterT = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listIdTipo);
        adapterT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapterT.notifyDataSetChanged();
        spinnerTipo.setAdapter(adapterT);
    }


    public void SpinnerHorario(){
        String sql = "SELECT id_horario FROM Horario";
        Cursor cursorH = helper.llenarSpinner(sql);

        while (cursorH.moveToNext()) {
            @SuppressLint("Range")
            int idH = cursorH.getInt(cursorH.getColumnIndex("id_horario"));
            listIdHorario.add(idH);
        }
        ArrayAdapter<Integer> adapterH = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listIdHorario);
        adapterH.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHorario.setAdapter(adapterH);
    }
}

