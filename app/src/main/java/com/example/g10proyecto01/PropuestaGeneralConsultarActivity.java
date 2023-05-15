package com.example.g10proyecto01;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PropuestaGeneralConsultarActivity extends Activity {
    ControlG10Proyecto01 helper;
    Spinner spinIdPropuestaGeneral;
    List<Integer> opcionesSpinner = new ArrayList<>();
    ArrayList<PropuestaEspecificaDetalle> propuestas = new ArrayList<>();
    TextView txtEstado;
    LinearLayout propuestasEspecificaLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propuesta_general_consultar);
        helper = new ControlG10Proyecto01(this);
        spinIdPropuestaGeneral = findViewById(R.id.spinConsultarIdPropuestaGeneral);
        propuestasEspecificaLayout = findViewById(R.id.mostrarPropuestaEspecificas);
        txtEstado = findViewById(R.id.txtEstadoPropuestaGeneral);
        llenarSpinner();

    }

    public void llenarSpinner(){
        String query = "SELECT id_propuesta FROM Propuesta_General";
        Cursor cursor = helper.llenarSpinner(query);
        while(cursor.moveToNext()){
            @SuppressLint("Range")
            int idUsuario = cursor.getInt(cursor.getColumnIndex("id_propuesta"));
            opcionesSpinner.add(idUsuario);
        }
        ArrayAdapter<Integer> adapterOpcionesSpinner = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,opcionesSpinner);
        adapterOpcionesSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinIdPropuestaGeneral.setAdapter(adapterOpcionesSpinner);
    }

    public void ConsultarPropuestasGeneral(View v){
        propuestasEspecificaLayout.removeAllViews();
        String idPropuestaGeneral = spinIdPropuestaGeneral.getSelectedItem().toString();

        helper.abrir();
        propuestas = helper.obtenerDetallePropuestaEspecifica(idPropuestaGeneral);
        PropuestaGeneral propuestaGeneral = helper.obtenerPropuestaGeneral(idPropuestaGeneral);
        helper.cerrar();
        txtEstado.setText(propuestaGeneral.getEstado_propuesta());
        if(propuestas.size() == 0){
            TextView notFoundPropuesta= new TextView(PropuestaGeneralConsultarActivity.this);
            notFoundPropuesta.setText(R.string.registro_no_encontrado);
            propuestasEspecificaLayout.addView(notFoundPropuesta);
            return;
        }


        for (int i = 0; i < propuestas.size(); i++) {

            PropuestaEspecificaDetalle propuestaEspecificaDetalle = propuestas.get(i);
            TextView txtPropuresta = new TextView(PropuestaGeneralConsultarActivity.this);
            txtPropuresta.setText(propuestaEspecificaDetalle.toString());
            propuestasEspecificaLayout.addView(txtPropuresta);
            View separador = new View(PropuestaGeneralConsultarActivity.this);
            separador.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 3));
            separador.setBackgroundColor(Color.GRAY);

            // Agregar la línea de separación al LinearLayout
            propuestasEspecificaLayout.addView(separador);

        }
    }


    public void LimpiarTexto(View v){
        propuestasEspecificaLayout.removeAllViews();
        txtEstado.setText("");
    }
}