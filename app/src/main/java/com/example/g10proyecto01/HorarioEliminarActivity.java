package com.example.g10proyecto01;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class HorarioEliminarActivity extends Activity {

    EditText editidgh3;
    ControlG10Proyecto01 controlhelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_eliminar);
        controlhelper = new ControlG10Proyecto01(this);
        editidgh3 = (EditText) findViewById(R.id.editidgh3);
    }

    public void eliminarHorario(View v) {
        String regEliminadas;
        Horario horario = new Horario();
        horario.setId_horario(Integer.parseInt(editidgh3.getText().toString()));
        controlhelper.abrir();
        regEliminadas = controlhelper.eliminar(horario);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
