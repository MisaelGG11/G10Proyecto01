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
public class ServicioWeb4Activity extends AppCompatActivity {
    EditText idLocalidadTxt, edificioTxt, nombreLocalTxt, capacidadTxt;

    private final String urlLocal = "http://192.168.0.11/ws_db_localidad_insert.php";

    private final String urlHostingGratuito = "https://hs19011pdm115.000webhostapp.com/ws_db_localidad_insert.php";

    @SuppressLint("NewApi")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio_web4);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        idLocalidadTxt = (EditText) findViewById(R.id.editIdlocal);
        edificioTxt = (EditText) findViewById(R.id.editEdificio);
        nombreLocalTxt = (EditText) findViewById(R.id.editNombrelocal);
        capacidadTxt = (EditText) findViewById(R.id.editcupo);
    }

    public void insertarLocalidad(View v) {
        if (idLocalidadTxt.getText().toString().isEmpty() ||
                edificioTxt.getText().toString().isEmpty() ||
                capacidadTxt.getText().toString().isEmpty() ||
                nombreLocalTxt.getText().toString().isEmpty()) {

            Toast.makeText(this, getResources().getString(R.string.vacio), Toast.LENGTH_SHORT).show();
        } else {

            String idLocalidad = idLocalidadTxt.getText().toString();
            String edificio = edificioTxt.getText().toString();
            String nombreL = nombreLocalTxt.getText().toString();
            String cupo = capacidadTxt.getText().toString();

            SimpleDateFormat dtf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
            Calendar calendar = Calendar.getInstance();

            Date dateObj = calendar.getTime();

            String fechaHoy = "'" + dtf.format(dateObj) + "'";

            String url = null;

            JSONObject datosLocalidad = new JSONObject();
            JSONObject localidad = new JSONObject();

            switch (v.getId()) {
                case R.id.btn_Local_Local:

                    url = urlLocal + "?id_localidad=" + URLEncoder.encode(idLocalidad) + "&edificio_localidad=" + URLEncoder.encode(edificio) + "&nombre_localidad=" + URLEncoder.encode(nombreL)+ "&capacidad_localidad=" + URLEncoder.encode(cupo) + "&fecha_modificacion_localidad=" + URLEncoder.encode(fechaHoy);

                    ControladorServicio.insertarLocalidadW(url, this);

                    break;
                case R.id.btn_LocalExterno:
                    url = urlHostingGratuito + "?id_localidad=" + URLEncoder.encode(idLocalidad) + "&edificio_localidad=" + URLEncoder.encode(edificio) + "&nombre_localidad=" + URLEncoder.encode(nombreL)+ "&capacidad_localidad=" + URLEncoder.encode(cupo) + "&fecha_modificacion_localidad=" + URLEncoder.encode(fechaHoy);

                    ControladorServicio.insertarLocalidadW(url, this);

                    break;
            }
        }
    }
}

