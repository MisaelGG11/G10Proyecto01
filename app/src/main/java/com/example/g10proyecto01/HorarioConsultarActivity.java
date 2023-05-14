package com.example.g10proyecto01;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class HorarioConsultarActivity extends Activity {
    ControlG10Proyecto01 helper;
    EditText editidgh2;
    EditText editidhorario6;
    EditText editidgrupo6;
    EditText edithorafin2;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_consultar);
        helper = new ControlG10Proyecto01(this);
        editidgh2 = (EditText) findViewById(R.id.editidgh2);
        editidhorario6 = (EditText) findViewById(R.id.editidhorario6);
        editidgrupo6 = (EditText) findViewById(R.id.editidgrupo6);
        edithorafin2 = (EditText) findViewById(R.id.edithorafin2);
    }

    public void consultarHorario(View v) {
        helper.abrir();
        Horario horario =
                helper.consultarHorario(editidgh2.getText().toString());
        helper.cerrar();
        if (horario == null)
            Toast.makeText(this, "Horario con ID " + editidgh2.getText().toString() + " no encontrado.", Toast.LENGTH_LONG).show();
        else {
            editidgh2.setText(String.valueOf(horario.getId_evento()));
            editidhorario6.setText(String.valueOf(horario.getHora_inicio()));
            editidgrupo6.setText(String.valueOf(horario.getHora_finalizacion()));
        }
    }

    public void limpiarTexto(View v) {
        editidgh2.setText("");
        editidhorario6.setText("");
        editidgrupo6.setText("");
        edithorafin2.setText("");
    }
}
