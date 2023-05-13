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

public class LocalAdministradoConsultarActivity extends Activity {
    ControlG10Proyecto01 helper;
    EditText edittIDlocal,edittIdEncargado;
    List<Integer> idLA = new ArrayList<>();
    Spinner spinnerlocalA;
    final String ID_LOCAL_ADMIN = "id_local_admin";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_administrado_consultar);

        helper = new ControlG10Proyecto01(this);

        spinnerlocalA = findViewById(R.id.spinIdlocal);
        edittIDlocal = findViewById(R.id.editIDlocal);
        edittIdEncargado = findViewById(R.id.editIdEncargado);

        String query = "SELECT id_local_admin FROM Local_Administrado";
        Cursor cursor = helper.llenarSpinner(query);
        while(cursor.moveToNext()){
            @SuppressLint("Range")
            int id = cursor.getInt(cursor.getColumnIndex(ID_LOCAL_ADMIN));
            idLA.add(id);
        }
        ArrayAdapter<Integer> adapterLocal = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,idLA);
        adapterLocal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerlocalA.setAdapter(adapterLocal);
    }

    public void consultarLocalAdmin(View v) {
        String id_Lo = spinnerlocalA.getSelectedItem().toString();
        helper.abrir();
        LocalAdministrado localAdministrado = helper.consultarlocalAdmin(id_Lo);
        helper.cerrar();
        if(localAdministrado == null) {
            Toast.makeText(this, "Registro no encontrado", Toast.LENGTH_LONG).show();
        }
        edittIDlocal.setText(String.valueOf(localAdministrado.getId_local()));
        edittIdEncargado.setText(String.valueOf(localAdministrado.getId_empleadoadministrador()));
    }
    public void limpiarTextoLocAdmin(View v){
        edittIdEncargado.setText("");
        edittIDlocal.setText("");
    }
}