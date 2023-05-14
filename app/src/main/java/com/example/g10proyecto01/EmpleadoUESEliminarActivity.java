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

public class EmpleadoUESEliminarActivity extends Activity {

    ControlG10Proyecto01 helper;
    List<Integer> listIdEmpleado = new ArrayList<>();
    Spinner spinnerIdEmpleado;
    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleado_ueseliminar);

        helper = new ControlG10Proyecto01(this);

        spinnerIdEmpleado = findViewById(R.id.spinnerIdEmpleado);
        String sql = "SELECT id_empleado FROM Empleado_UES";
        Cursor cursor = helper.llenarSpinner(sql);
        while (cursor.moveToNext()) {
            @SuppressLint("Range")
            int idEmpleado  = cursor.getInt(cursor.getColumnIndex("id_empleado"));
            listIdEmpleado.add(idEmpleado);
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listIdEmpleado);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdEmpleado.setAdapter(adapter);
    }

    public void eliminarEmpleadoUES(View v){
        if (listIdEmpleado.size() == 0){
            Toast.makeText(this, getResources().getString(R.string.vacio), Toast.LENGTH_SHORT).show();
        }
        else {
            AlertDialog.Builder alerta = new AlertDialog.Builder(EmpleadoUESEliminarActivity.this);
            alerta.setCancelable(false);

            int idEmpleado;
            idEmpleado =listIdEmpleado.get(spinnerIdEmpleado.getSelectedItemPosition());

            alerta.setMessage(getResources().getString(R.string.mensajeAlerta));
            alerta.setPositiveButton(getResources().getString(R.string.si), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //ACCIONES SI RESPONDE QUE SI A LA ALERTA
                    String regEliminadas;
                    EmpleadoUES empleadoUES = new EmpleadoUES();
                    empleadoUES.setId_empleado(idEmpleado);
                    helper.abrir();
                    regEliminadas=helper.eliminar(empleadoUES);
                    helper.cerrar();
                    Toast.makeText(EmpleadoUESEliminarActivity.this, regEliminadas, Toast.LENGTH_SHORT).show();
                }
            });
            alerta.setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
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
}