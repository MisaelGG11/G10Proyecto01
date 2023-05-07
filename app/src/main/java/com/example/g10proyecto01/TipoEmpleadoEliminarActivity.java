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

public class TipoEmpleadoEliminarActivity extends Activity {

    ControlG10Proyecto01 helper;
    List<Integer> ids = new ArrayList<>();
    Spinner spinnerIntento;
    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_empleado_eliminar);

        helper = new ControlG10Proyecto01(this);

        spinnerIntento = findViewById(R.id.spinIdEmpleado);
        String sql = "SELECT id_tipo_empleado FROM Tipo_de_Empleado";
        Cursor cursor = helper.llenarSpinner(sql);
        while (cursor.moveToNext()) {
            @SuppressLint("Range")
            int id = cursor.getInt(cursor.getColumnIndex("id_tipo_empleado"));
            ids.add(id);
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ids);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIntento.setAdapter(adapter);
    }

    public void eliminarTipoEmpleado(View v){
        AlertDialog.Builder alerta = new AlertDialog.Builder(TipoEmpleadoEliminarActivity.this);
        alerta.setCancelable(false);
        alerta.setMessage("¿Desea eliminar este registro?");
        alerta.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //ACCIONES SI RESPONDE QUE SI A LA ALERTA
                String regEliminadas;
                TipoEmpleado tipoEmpleado = new TipoEmpleado();
                int id_TE;
                id_TE =ids.get(spinnerIntento.getSelectedItemPosition());
                tipoEmpleado.setId_tipo_empleado(id_TE);
                helper.abrir();
                regEliminadas=helper.eliminar(tipoEmpleado);
                helper.cerrar();
                Toast.makeText(TipoEmpleadoEliminarActivity.this, regEliminadas, Toast.LENGTH_SHORT).show();
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
