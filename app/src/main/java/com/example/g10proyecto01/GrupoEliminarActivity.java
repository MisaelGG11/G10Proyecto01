package com.example.g10proyecto01;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class GrupoEliminarActivity extends Activity {
    EditText editidgrupo4;
    ControlG10Proyecto01 controlhelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_eliminar);
        controlhelper = new ControlG10Proyecto01(this);
        editidgrupo4 = (EditText) findViewById(R.id.editidgh3);
    }

    public void eliminarGrupo(View v) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(GrupoEliminarActivity.this);
        alerta.setCancelable(false);
        alerta.setMessage("¿Desea eliminar este registro?");

        alerta.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String regEliminadas;
                Grupo grupo = new Grupo();
                grupo.setId_grupo(Integer.parseInt(editidgrupo4.getText().toString()));
                controlhelper.abrir();
                regEliminadas = controlhelper.eliminar(grupo);
                controlhelper.cerrar();
                Toast.makeText(GrupoEliminarActivity.this, regEliminadas, Toast.LENGTH_SHORT).show();
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

}
