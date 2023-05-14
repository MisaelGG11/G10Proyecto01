package com.example.g10proyecto01;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class GrupoHorarioEliminarActivity extends Activity {

    EditText editidgh3;
    ControlG10Proyecto01 controlhelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_horario_eliminar);
        controlhelper = new ControlG10Proyecto01(this);
        editidgh3 = (EditText) findViewById(R.id.editidgh3);
    }

    public void eliminarGrupoHorario(View v) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(GrupoHorarioEliminarActivity.this);
        alerta.setCancelable(false);
        alerta.setMessage("¿Desea eliminar este registro?");

        alerta.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String regEliminadas;
                GrupoHorario grupohorario = new GrupoHorario();
                grupohorario.setId_gh(Integer.parseInt(editidgh3.getText().toString()));
                controlhelper.abrir();
                regEliminadas = controlhelper.eliminar(grupohorario);
                controlhelper.cerrar();
                Toast.makeText(GrupoHorarioEliminarActivity.this, regEliminadas, Toast.LENGTH_SHORT).show();
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
