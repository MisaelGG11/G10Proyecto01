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

public class EmpleadoUESInsertarActivity extends Activity {
    ControlG10Proyecto01 helper;
    EditText editIdEmpleado;
    EditText editNombre;
    EditText editApellido;
    EditText editCorreo;
    EditText editTelefono;
    List<Integer> idTipoEmp = new ArrayList<>();
    Spinner spinnerTipoEmp;

    @SuppressLint("MissingInflatedId")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleado_uesinsertar);

        helper = new ControlG10Proyecto01(this);

        editIdEmpleado = (EditText) findViewById(R.id.editIdEmpleado);
        editNombre = (EditText) findViewById(R.id.editNombreEmpleado);
        editApellido = (EditText) findViewById(R.id.editApellidoEmpleado);
        editCorreo = (EditText)  findViewById(R.id.editEmailEmpleado);
        editTelefono = (EditText) findViewById(R.id.editTelefonoEmpleado);
        spinnerTipoEmp = findViewById(R.id.spinnerIdTipoEmpleado);

        String sql = "SELECT id_tipo_empleado FROM Tipo_de_Empleado";
        Cursor cursor = helper.llenarSpinner(sql);
        while (cursor.moveToNext()) {
            @SuppressLint("Range")
            int id = cursor.getInt(cursor.getColumnIndex("id_tipo_empleado"));
            idTipoEmp.add(id);
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, idTipoEmp);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoEmp.setAdapter(adapter);
    }
    public void insertarEmpleadoUES(View v) {
        String regInsertados;

        int idEmpleado = Integer.valueOf(editIdEmpleado.getText().toString());
        String nombre = editNombre.getText().toString();
        String apellido = editApellido.getText().toString();
        String correo = editCorreo.getText().toString();
        int telefono = Integer.valueOf(editTelefono.getText().toString());
        int id_TE = idTipoEmp.get(spinnerTipoEmp.getSelectedItemPosition());


        EmpleadoUES empleado = new EmpleadoUES();
        empleado.setId_empleado(idEmpleado);
        empleado.setId_tipo_empleado(id_TE);
        empleado.setNombre_empleado(nombre);
        empleado.setApellido_empleado(apellido);
        empleado.setEmail_empleado(correo);
        empleado.setTelefono_empleado(telefono);

        helper.abrir();
        regInsertados=helper.insertar(empleado);
        helper.cerrar();

        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editIdEmpleado.setText("");
        editNombre.setText("");
        editApellido.setText("");
        editCorreo.setText("");
        editTelefono.setText("");
    }
}