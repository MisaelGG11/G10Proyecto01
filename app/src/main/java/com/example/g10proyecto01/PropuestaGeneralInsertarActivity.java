package com.example.g10proyecto01;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;

public class PropuestaGeneralInsertarActivity extends Activity {

    // initialize variables
    ControlG10Proyecto01 helper;
    TextView txtIdGenerado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propuesta_general_insertar);
        helper = new ControlG10Proyecto01(this);
        //txtIdGenerado = findViewById(R.id.txtIdGeneradoPropuestaGeneral);

    }

    public void crearPropuestaGeneral(View v){
        helper.abrir();
        String cto = helper.crearPropuestaGeneral();
        helper.cerrar();
        Toast.makeText(this, cto, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v){
        txtIdGenerado.setText("");
    }
}
