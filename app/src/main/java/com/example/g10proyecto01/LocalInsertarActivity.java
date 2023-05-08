package com.example.g10proyecto01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class LocalInsertarActivity extends AppCompatActivity {
    ControlG10Proyecto01 helper;
    EditText editidlocal, editnombrelocal, editcupolocal, editedificio;

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
    public void insertarLocalidad(View v) {
        String id_localidad = editidlocal.getText().toString();
        //String edificio_localidad = editedificioo.getSelectedItem().toString();
        String edificio_localidad = editedificio.getText().toString();
        String nombre_localidad = editnombrelocal.getText().toString();
        String capacidad_localidad = editcupolocal.getText().toString();
        String regInsertados;

        Local local= new Local();
        local.setId_localidad(Integer.valueOf(id_localidad));
        local.setEdificio_localidad(edificio_localidad);
        local.setNombre_localidad(nombre_localidad);
        local.setCapacidad_localidad(Integer.valueOf(capacidad_localidad));

        helper.abrir();

        regInsertados = helper.insertar(local);

        helper.cerrar();

        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTextoLocal(View v) {
        editidlocal.setText("");
        editedificio.setText("");
        editnombrelocal.setText("");
        editcupolocal.setText("");
    }
}




