package com.example.g10proyecto01;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TipoEmpleadoConsultarActivity extends Activity {
    ControlG10Proyecto01 helper;
    EditText editOcupacion;
    List<Integer> ids = new ArrayList<>();
    Spinner spinnerIntento;

    /** Called when the activity is first created. */
    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_empleado_consultar);

        helper = new ControlG10Proyecto01(this);

        spinnerIntento = findViewById(R.id.spinnerIdTipoEmpleado);
        editOcupacion = findViewById(R.id.editOcupacion);

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
    public void consultarTipoDeEmpleado(View v) {
        String id_TE = spinnerIntento.getSelectedItem().toString();
        helper.abrir();
        TipoEmpleado tipoEmpleado = helper.consultar(id_TE);
        helper.cerrar();
        if(tipoEmpleado == null)
            Toast.makeText(this, "Registro no encontrado", Toast.LENGTH_LONG).show();
        else{
            editOcupacion.setText(tipoEmpleado.getOcupacion());
        }
        editOcupacion.setText(tipoEmpleado.getOcupacion());
    }
    public void limpiarTexto(View v){
        editOcupacion.setText("");

    }
}
