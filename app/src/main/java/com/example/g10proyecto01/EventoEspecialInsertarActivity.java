package com.example.g10proyecto01;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import java.text.SimpleDateFormat;
import java.util.Locale;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EventoEspecialInsertarActivity extends Activity {
        ControlG10Proyecto01 helper;
        EditText editIdEventoEspecial,editNombre;
        //EditText editFecha;
        EditText editIdHorario;
        private EditText editFecha;
        private DatePickerDialog datePickerDialog;
        private SimpleDateFormat dateFormatter;
    List<Integer> listIdEmp = new ArrayList<>();
    List<String> nombresEmp = new ArrayList<>();
    List<Integer> idLA = new ArrayList<>();
    List<String> loc = new ArrayList<>();
    List <String> spinnerList = new ArrayList<>();
    List <String> spinnerList2 = new ArrayList<>();
    List <String> spinnerList3 = new ArrayList<>();


    List<Integer> listIdLocal = new ArrayList<>();
        List<Integer> listIdEncargado = new ArrayList<>();
        List<Integer> listIdTipo = new ArrayList<>();
        List<Integer> listIdHorario = new ArrayList<>();

        Spinner spinnerEmpleado;
        Spinner spinnerLocal;
        Spinner spinnerHorario;
        Spinner spinnerTipo;

        @SuppressLint("MissingInflatedId")
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_evento_especial_insertar);

            helper = new ControlG10Proyecto01(this);

            editIdEventoEspecial = (EditText) findViewById(R.id.edittidEventoE);

            editNombre = (EditText) findViewById(R.id.editTextNombreEvento);
            spinnerTipo = findViewById(R.id.spinidtipoevento);
            spinnerEmpleado = findViewById(R.id.spinorganizador);
            editFecha = (EditText) findViewById(R.id.editTextfecha);
            dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

            //editIdHorario= (EditText) findViewById(R.id.editTextHorario);
            spinnerHorario = findViewById(R.id.editTextHorario);
            spinnerLocal = findViewById(R.id.spinidlocal);

            SpinnerLoc();
            SpinnerEmpleado();
            SpinnerTipo();
            SpinnerHorario();
            // Configurar el DatePickerDialog para el campo de fecha
            editFecha.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDatePickerDialog();
                }
            });
        }
        public void insertarEventoEspecial(View v) {
            String idEve = editIdEventoEspecial.getText().toString();
            String NomEve = editNombre.getText().toString();
            int TipoEve = listIdTipo.get(spinnerTipo.getSelectedItemPosition());
            int OrganizadorEve = listIdEmp.get(spinnerEmpleado.getSelectedItemPosition());
            String FechaEve = editFecha.getText().toString();
            //String HorarioEve = editIdHorario.getText().toString();
            int HorarioEve = listIdHorario.get(spinnerHorario.getSelectedItemPosition());
            int LocalEve = idLA.get(spinnerLocal.getSelectedItemPosition());
            String regInsertados;

            if (idEve.isEmpty()||NomEve.isEmpty()||FechaEve.isEmpty()) {
                Toast.makeText(com.example.g10proyecto01.EventoEspecialInsertarActivity.this, "Ingresar datos obligatorios", Toast.LENGTH_SHORT).show();
            } else {
                EventoEspecial EvE = new EventoEspecial();
                EvE.setId_evento(Integer.valueOf(idEve));
                EvE.setNombre_evento(NomEve);
                EvE.setId_tipo_evento(Integer.valueOf(TipoEve));
                EvE.setId_organizador(Integer.valueOf(OrganizadorEve));
                EvE.setFecha_evento(FechaEve);
                EvE.setHorario(Integer.valueOf(HorarioEve));
                EvE.setId_localidad(Integer.valueOf(LocalEve));

                helper.abrir();
                regInsertados=helper.insertar(EvE);
                helper.cerrar();

                Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
            }

        }
        public void limpiarTextoEve(View v) {
            editIdEventoEspecial.setText("");
            editFecha.setText("");
            editNombre.setText("");

        }

    public void SpinnerLoc(){

        String query = "SELECT l.id_local_admin, lo.nombre_localidad FROM Local_Administrado AS l JOIN Localidad AS lo ON l.id_localidad = lo.id_localidad";
        Cursor cursor = helper.llenarSpinner(query);
        while(cursor.moveToNext()){
            @SuppressLint("Range")
            int id = cursor.getInt(cursor.getColumnIndex("id_local_admin"));
            idLA.add(id);

            @SuppressLint("Range")
            String local = cursor.getString(cursor.getColumnIndex("nombre_localidad"));
            loc.add(local);

            String itemSpinner = id + ": " + local;
            spinnerList2.add(itemSpinner);
        }
        ArrayAdapter<String> adapterLocal = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,spinnerList2);
        adapterLocal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLocal.setAdapter(adapterLocal);
    }



    public void SpinnerEmpleado(){
        String sql = "SELECT id_empleado, nombre_empleado, apellido_empleado FROM Empleado_UES";
        Cursor cursor = helper.llenarSpinner(sql);
        while (cursor.moveToNext()) {
            @SuppressLint("Range")
            int idEmp = cursor.getInt(cursor.getColumnIndex("id_empleado"));
            listIdEmp.add(idEmp);

            @SuppressLint("Range")
            String nom = cursor.getString(cursor.getColumnIndex("nombre_empleado")) + " " + cursor.getString(cursor.getColumnIndex("apellido_empleado"));
            nombresEmp.add(nom);

            String itemSpinner = idEmp + ": " + nom;
            spinnerList.add(itemSpinner);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEmpleado.setAdapter(adapter);
    }


    public void SpinnerTipo(){
        String sql = "SELECT id_tipo_evento, nombre_tipo_evento FROM Tipo_evento";
        Cursor cursorL = helper.llenarSpinner(sql);
        while (cursorL.moveToNext()) {
            @SuppressLint("Range")
            int idLoc = cursorL.getInt(cursorL.getColumnIndex("id_tipo_evento"));
            listIdTipo.add(idLoc);

            @SuppressLint("Range")
            String nom = cursorL.getString(cursorL.getColumnIndex("nombre_tipo_evento"));
            nombresEmp.add(nom);

            String itemSpinner = idLoc + ": " + nom;
            spinnerList3.add(itemSpinner);
        }
        ArrayAdapter<String> adapterT = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerList3);
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
   private void showDatePickerDialog() {
       Calendar newCalendar = Calendar.getInstance();
       datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
           @Override
           public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
               Calendar selectedDate = Calendar.getInstance();
               selectedDate.set(year, monthOfYear, dayOfMonth);
               String selectedDateString = dateFormatter.format(selectedDate.getTime());
               editFecha.setText(selectedDateString);
           }
       }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
       datePickerDialog.show();
   }
    }