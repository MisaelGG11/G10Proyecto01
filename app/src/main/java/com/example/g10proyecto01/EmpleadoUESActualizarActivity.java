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

public class EmpleadoUESActualizarActivity extends Activity {
    ControlG10Proyecto01 helper;
    EditText editIdTipoEmpleado;
    EditText editNombre;
    EditText editApellido;
    EditText editCorreo;
    EditText editTelefono;
    List<Integer> listIdEmp = new ArrayList<>();
    List<Integer> listIdTipoEmp = new ArrayList<>();
    Spinner spinnerIdEmpleado;
    Spinner spinnerIdTipoEmpleado;

    @SuppressLint("MissingInflatedId")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleado_uesactualizar);

        helper = new ControlG10Proyecto01(this);

        editIdTipoEmpleado = (EditText) findViewById(R.id.editIdTipoEmpleado);
        editNombre = (EditText) findViewById(R.id.editNombreEmpleado);
        editApellido = (EditText) findViewById(R.id.editApellidoEmpleado);
        editCorreo = (EditText)  findViewById(R.id.editEmailEmpleado);
        editTelefono = (EditText) findViewById(R.id.editTelefonoEmpleado);
        spinnerIdEmpleado = findViewById(R.id.spinnerIdEmpleado);
        spinnerIdTipoEmpleado = findViewById(R.id.spinnerIdTipoEmpleado);

        String sql1 = "SELECT id_empleado FROM Empleado_UES";
        Cursor cursor1 = helper.llenarSpinner(sql1);
        while (cursor1.moveToNext()) {
            @SuppressLint("Range")
            int idEmp = cursor1.getInt(cursor1.getColumnIndex("id_empleado"));
            listIdEmp.add(idEmp);
        }
        ArrayAdapter<Integer> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listIdEmp);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdEmpleado.setAdapter(adapter1);

        String sql2 = "SELECT id_tipo_empleado FROM Tipo_de_Empleado";
        Cursor cursor2 = helper.llenarSpinner(sql2);
        while (cursor2.moveToNext()) {
            @SuppressLint("Range")
            int idTipodeEmp = cursor2.getInt(cursor2.getColumnIndex("id_tipo_empleado"));
            listIdTipoEmp.add(idTipodeEmp);
        }
        ArrayAdapter<Integer> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listIdTipoEmp);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdTipoEmpleado.setAdapter(adapter2);
    }
    public void actualizarEmpleadoUES(View v) {
        EmpleadoUES empleado = new EmpleadoUES();

        int idEmpleado, idTipoEmpleado;
        idEmpleado =listIdEmp.get(spinnerIdEmpleado.getSelectedItemPosition());
        idTipoEmpleado = listIdTipoEmp.get(spinnerIdTipoEmpleado.getSelectedItemPosition());

        String nombre = editNombre.getText().toString();
        String apellido = editApellido.getText().toString();
        String correo = editCorreo.getText().toString();
        int telefono = Integer.valueOf(editTelefono.getText().toString());

        empleado.setId_empleado(idEmpleado);
        empleado.setId_tipo_empleado(idTipoEmpleado);
        empleado.setNombre_empleado(nombre);
        empleado.setApellido_empleado(apellido);
        empleado.setEmail_empleado(correo);
        empleado.setTelefono_empleado(telefono);

        helper.abrir();
        String estado = helper.actualizar(empleado);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editIdTipoEmpleado.setText("");
        editNombre.setText("");
        editApellido.setText("");
        editCorreo.setText("");
        editTelefono.setText("");
    }
}