package com.example.g10proyecto01;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TipoEmpleadoActualizarActivity extends Activity {
    ControlG10Proyecto01 helper;
    EditText editOcupacion;
    Spinner spinIdTipoEmpleado;
    List<Integer> ids = new ArrayList<>();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_empleado_actualizar);

        helper = new ControlG10Proyecto01(this);
        editOcupacion = (EditText) findViewById(R.id.editOcupacion);

        spinIdTipoEmpleado = findViewById(R.id.spinnerIdTipoEmpleado);

        String sql = "SELECT id_tipo_empleado FROM Tipo_de_Empleado";
        Cursor cursor = helper.llenarSpinner(sql);
        while (cursor.moveToNext()) {
            @SuppressLint("Range")
            int id = cursor.getInt(cursor.getColumnIndex("id_tipo_empleado"));
            ids.add(id);
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ids);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinIdTipoEmpleado.setAdapter(adapter);
    }
    public void actualizarTipoDeEmpleado(View v) {
        TipoEmpleado tipoEmpleado = new TipoEmpleado();
        int id_TE;
        id_TE =ids.get(spinIdTipoEmpleado.getSelectedItemPosition());
        tipoEmpleado.setId_tipo_empleado(id_TE);
        tipoEmpleado.setOcupacion(editOcupacion.getText().toString());
        helper.abrir();
        String estado = helper.actualizar(tipoEmpleado);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editOcupacion.setText("");
    }
}