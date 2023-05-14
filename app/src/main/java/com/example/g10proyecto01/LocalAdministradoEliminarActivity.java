package com.example.g10proyecto01;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
public class LocalAdministradoEliminarActivity extends Activity {

    ControlG10Proyecto01 helper;
    List<Integer> LocSpinner = new ArrayList<>();
    Spinner spinnerIdLoc;
    final String ID_LOCAL_ADMIN = "id_local_admin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_administrado_eliminar);
        helper = new ControlG10Proyecto01(this);
        spinnerIdLoc = findViewById(R.id.spinIdLocalA);
        String query = "SELECT id_local_admin FROM Local_Administrado";
        Cursor cursor = helper.llenarSpinner(query);
        while(cursor.moveToNext()){
            @SuppressLint("Range")
            int id = cursor.getInt(cursor.getColumnIndex(ID_LOCAL_ADMIN));
            LocSpinner.add(id);
        }
        ArrayAdapter<Integer> adapterLOCSpinner = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,LocSpinner);
        adapterLOCSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdLoc.setAdapter(adapterLOCSpinner);
    }

    public void eliminarLocaladmin(View v){
        AlertDialog.Builder alerta = new AlertDialog.Builder(LocalAdministradoEliminarActivity.this);
        alerta.setCancelable(false);
        alerta.setMessage("¿Desea eliminar este registro?");
        alerta.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //ACCIONES SI RESPONDE QUE SI A LA ALERTA
                String regEliminadas;
                LocalAdministrado localAdministrado= new LocalAdministrado();
                int id_Loc;
                id_Loc =LocSpinner.get(spinnerIdLoc.getSelectedItemPosition());
                localAdministrado.setId_local_admin(id_Loc);
                helper.abrir();
                regEliminadas=helper.eliminar(localAdministrado);
                helper.cerrar();
                Toast.makeText(LocalAdministradoEliminarActivity.this, regEliminadas, Toast.LENGTH_SHORT).show();
            }
        });
        alerta.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alerta.show();
    }

   /* public void eliminarLocaladmin(View v){
        AlertDialog.Builder alerta = new AlertDialog.Builder(LocalAdministradoEliminarActivity.this);
        alerta.setCancelable(false);
        alerta.setMessage("¿Desea eliminar este registro?");
        alerta.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //ACCIONES SI RESPONDE QUE SI A LA ALERTA
                String regEliminadas;
                LocalAdministrado localAdministrado= new LocalAdministrado();
                int id_Loc;
                id_Loc =LocSpinner.get(spinnerIdLoc.getSelectedItemPosition());
                localAdministrado.setId_local_admin(id_Loc);
                helper.abrir();
                regEliminadas=helper.eliminar(localAdministrado);
                helper.cerrar();
                Toast.makeText(LocalAdministradoEliminarActivity.this, regEliminadas, Toast.LENGTH_SHORT).show();
            }
        });
        alerta.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alerta.show();
    } */
}
