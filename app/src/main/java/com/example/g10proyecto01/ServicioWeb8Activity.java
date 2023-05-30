package com.example.g10proyecto01;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressLint("NewApi")
public class ServicioWeb8Activity extends AppCompatActivity {
    EditText idOpcionesTxt, descripcionTxt;

    private final String urlLocal = "http://192.168.0.11/ws_opcioncrud_insert.php";

    private final String urlHostingGratuito = "https://hs19011pdm115.000webhostapp.com/ws_opcioncrud_insert.php";

    @SuppressLint("NewApi")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio_web8);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        idOpcionesTxt = (EditText) findViewById(R.id.editIdOpcion);
        descripcionTxt = (EditText) findViewById(R.id.editDescripcion);
    }

    public void insertarOpcionCrud2(View v) {
        if (idOpcionesTxt.getText().toString().isEmpty() ||
                descripcionTxt.getText().toString().isEmpty()) {

            Toast.makeText(this, getResources().getString(R.string.vacio), Toast.LENGTH_SHORT).show();
        } else {

            String idOpciones = idOpcionesTxt.getText().toString();
            String descripcion = descripcionTxt.getText().toString();

            SimpleDateFormat dtf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
            Calendar calendar = Calendar.getInstance();

            Date dateObj = calendar.getTime();

            String fechaHoy = "'" + dtf.format(dateObj) + "'";

            String url = null;

            JSONObject datosOpciones = new JSONObject();
            JSONObject opciones = new JSONObject();

            switch (v.getId()) {
                case R.id.btn_Opciones_Local:

                    url = urlLocal + "?id_opcion=" + URLEncoder.encode(idOpciones) + "&des_opcion=" + URLEncoder.encode(descripcion) + "&fecha_modificado=" + URLEncoder.encode(fechaHoy);

                    ControladorServicio.insertarOpcionesW(url, this);

                    break;
                case R.id.btn_Opciones_Externo:
                    url = urlHostingGratuito + "?id_opcion=" + URLEncoder.encode(idOpciones) + "&des_opcion=" + URLEncoder.encode(descripcion) + "&fecha_modificado=" + URLEncoder.encode(fechaHoy);
                    ControladorServicio.insertarOpcionesW(url, this);

                    break;
            }
        }
    }
}
