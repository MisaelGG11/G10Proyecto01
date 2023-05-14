package com.example.g10proyecto01;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
public class LocalAdministradoActualizarActivity extends Activity {
    ControlG10Proyecto01 helper;
    List<Integer> LOCSpinner = new ArrayList<>();
    Spinner spinIdLocAdmin,spinIdLoc,spinEncargado;
    List<Integer> listIdLocal = new ArrayList<>();
    List<Integer> listIdEncargado = new ArrayList<>();

    final String ID_LOCAL_ADMIN = "id_local_admin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_administrado_actualizar);
        helper = new ControlG10Proyecto01(this);
        spinIdLocAdmin = findViewById(R.id.spinIdLocAdmin);
        spinIdLoc = findViewById(R.id.spinIdLoc);
        spinEncargado = findViewById(R.id.spinEncargado);

        String query = "SELECT id_local_admin FROM Local_Administrado";
        Cursor cursor = helper.llenarSpinner(query);
        while(cursor.moveToNext()){
            @SuppressLint("Range")
            int id = cursor.getInt(cursor.getColumnIndex(ID_LOCAL_ADMIN));
            LOCSpinner.add(id);
        }
        ArrayAdapter<Integer> adapterLOCSpinner = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,LOCSpinner);
        adapterLOCSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinIdLocAdmin.setAdapter(adapterLOCSpinner);
        SpinnerLoc();
        SpinnerEmpleado();
    }

    public void ActualizarLocAdmin(View v){
        String idLocalAd = spinIdLocAdmin.getSelectedItem().toString();
        String idLocal = spinIdLoc.getSelectedItem().toString();
        String IdEncargado = spinEncargado.getSelectedItem().toString();


        if (idLocalAd.isEmpty()||idLocal.isEmpty()||IdEncargado.isEmpty()) {
            Toast.makeText(LocalAdministradoActualizarActivity.this, "Ingresar datos obligatorios", Toast.LENGTH_SHORT).show();
        } else {
            LocalAdministrado localAdministrado = new LocalAdministrado(Integer.parseInt(idLocalAd),Integer.parseInt(idLocal),Integer.parseInt(IdEncargado));
            System.out.println(localAdministrado.toString());
            helper.abrir();
            String estado = helper.actualizar(localAdministrado);
            helper.cerrar();

            Toast.makeText(this,estado,Toast.LENGTH_LONG).show();
        }

    }

    public void SpinnerLoc(){
        String sql = "SELECT id_localidad FROM Localidad WHERE id_localidad NOT IN (SELECT id_localidad FROM Local_Administrado)";
        Cursor cursorL = helper.llenarSpinner(sql);
        while (cursorL.moveToNext()) {
            @SuppressLint("Range")
            int idLoc = cursorL.getInt(cursorL.getColumnIndex("id_localidad"));
            listIdLocal.add(idLoc);
        }
        ArrayAdapter<Integer> adapterL = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listIdLocal);
        adapterL.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinIdLoc.setAdapter(adapterL);

    }

    public void SpinnerEmpleado(){
        String sql = "SELECT id_empleado FROM Empleado_UES";
        Cursor cursorE = helper.llenarSpinner(sql);

        while (cursorE.moveToNext()) {
            @SuppressLint("Range")
            int idEn = cursorE.getInt(cursorE.getColumnIndex("id_empleado"));
            listIdEncargado.add(idEn);
        }
        ArrayAdapter<Integer> adapterE = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listIdEncargado);
        adapterE.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinEncargado.setAdapter(adapterE);
    }
}
