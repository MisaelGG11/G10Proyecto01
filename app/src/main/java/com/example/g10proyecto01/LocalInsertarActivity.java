package com.example.g10proyecto01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LocalInsertarActivity extends AppCompatActivity {
    ControlG10Proyecto01 helper;
    EditText editidlocal, editedificio, editnombrelocal, editcupolocal;

    //Spinner editedificioo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_insertar);
        //SpinnerEdificios();
        helper = new ControlG10Proyecto01(this);
        editidlocal = (EditText) findViewById(R.id.edittidLocal);
        editedificio = (EditText) findViewById(R.id.editTextedificioLocal);
        //editedificioo = (Spinner) findViewById(R.id.spinnerEdificioLocal);
        editnombrelocal = (EditText) findViewById(R.id.editTextNombreLocal);
        editcupolocal = (EditText) findViewById(R.id.editTextCupoLocal);
    }
 /*public void SpinnerEdificios(){
        Spinner spinnerEdificios = (Spinner) findViewById(R.id.spinnerEdificioLocal);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.spinnerEdificioLocal, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEdificios.setAdapter(adapter);
    }*/
    public void insertar(View v) {
        String id_localidad = editidlocal.getText().toString();
        //String edificio_localidad = editedificioo.getSelectedItem().toString();
        String edificio_localidad = editedificio.getText().toString();
        String nombre_localidad = editnombrelocal.getText().toString();
        String capacidad_localidad = editcupolocal.getText().toString();
        String regInsertados;

        if (id_localidad.isEmpty()||edificio_localidad.isEmpty()||nombre_localidad.isEmpty()||capacidad_localidad.isEmpty()) {
            Toast.makeText(LocalInsertarActivity.this, "Ingresar datos obligatorios", Toast.LENGTH_SHORT).show();
        } else {
            if (TextUtils.isDigitsOnly(id_localidad)){
                Localidad localidad = new Localidad();
                localidad.setId_localidad(Integer.valueOf(id_localidad));
                localidad.setEdificio_localidad(edificio_localidad);
                localidad.setNombre_localidad(nombre_localidad);
                localidad.setCapacidad_localidad(Integer.valueOf(capacidad_localidad));

                helper.abrir();

                regInsertados = helper.insertar(localidad);

                helper.cerrar();

                Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(LocalInsertarActivity.this, "ID y Capacidad son valores numericos", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void limpiarTextoLocal(View v) {
        editidlocal.setText("");
        editedificio.setText("");
        editnombrelocal.setText("");
        editcupolocal.setText("");
    }
}




