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

public class EmpleadoUESConsultarActivity extends Activity {
    ControlG10Proyecto01 helper;
    EditText editIdTipoEmpleado;
    EditText editNombre;
    EditText editApellido;
    EditText editCorreo;
    EditText editTelefono;
    List<Integer> idEmp = new ArrayList<>();
    Spinner spinnerEmpleado;

    @SuppressLint("MissingInflatedId")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleado_uesconsultar);

        helper = new ControlG10Proyecto01(this);

        editIdTipoEmpleado = (EditText) findViewById(R.id.editIdTipoEmpleado);
        editNombre = (EditText) findViewById(R.id.editNombreEmpleado);
        editApellido = (EditText) findViewById(R.id.editApellidoEmpleado);
        editCorreo = (EditText)  findViewById(R.id.editEmailEmpleado);
        editTelefono = (EditText) findViewById(R.id.editTelefonoEmpleado);
        spinnerEmpleado = findViewById(R.id.spinnerIdEmpleado);

        String sql = "SELECT id_empleado FROM Empleado_UES";
        Cursor cursor = helper.llenarSpinner(sql);
        while (cursor.moveToNext()) {
            @SuppressLint("Range")
            int id = cursor.getInt(cursor.getColumnIndex("id_empleado"));
            idEmp.add(id);
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, idEmp);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEmpleado.setAdapter(adapter);
    }
    public void consultarEmpleadoUES(View v) {
        String idEmpleado = spinnerEmpleado.getSelectedItem().toString();
        helper.abrir();
        EmpleadoUES empleado = helper.consultarEmpleadoUES(idEmpleado);
        helper.cerrar();
        if(empleado == null)
            Toast.makeText(this, "Registro no encontrado", Toast.LENGTH_LONG).show();
        else{
            editIdTipoEmpleado.setText(String.valueOf(empleado.getId_tipo_empleado()));
            editNombre.setText(empleado.getNombre_empleado());
            editApellido.setText(empleado.getApellido_empleado());
            editCorreo.setText(empleado.getEmail_empleado());
            editTelefono.setText(String.valueOf(empleado.getTelefono_empleado()));
        }
    }
    public void limpiarTexto(View v) {
        editIdTipoEmpleado.setText("");
        editNombre.setText("");
        editApellido.setText("");
        editCorreo.setText("");
        editTelefono.setText("");
    }
}