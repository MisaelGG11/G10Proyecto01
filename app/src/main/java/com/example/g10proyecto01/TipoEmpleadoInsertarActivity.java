package com.example.g10proyecto01;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
public class TipoEmpleadoInsertarActivity extends Activity {
    ControlG10Proyecto01 helper;
    EditText editIdEmpleado;
    EditText editOcupacion;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_empleado_insertar);
        helper = new ControlG10Proyecto01(this);
        editIdEmpleado = (EditText) findViewById(R.id.editIdEmpleado);
        editOcupacion = (EditText) findViewById(R.id.editOcupacion);
    }
    public void insertarTipoEmpleado(View v) {
        String idEmpleado = editIdEmpleado.getText().toString();
        String ocupacion = editOcupacion.getText().toString();
        String regInsertados;
        TipoEmpleado tipoEmp = new TipoEmpleado();
        tipoEmp.setId_tipo_empleado(Integer.valueOf(idEmpleado));
        tipoEmp.setOcupacion(ocupacion);

        helper.abrir();
        regInsertados=helper.insertar(tipoEmp);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editIdEmpleado.setText("");
        editOcupacion.setText("");

    }
}
