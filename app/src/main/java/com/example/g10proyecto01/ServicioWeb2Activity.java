package com.example.g10proyecto01;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressLint("NewApi")
public class ServicioWeb2Activity extends AppCompatActivity {
    EditText idEscuelaTxt;
    EditText acronimoTxt;
    EditText nombreTxt;

    private final String urlLocal = "http://192.168.1.3/ws_escuela_insert.php";
    private final String urlHostingGratuito = "https://hs19011pdm115.000webhostapp.com/ws_escuela_insert.php";

    @SuppressLint("NewApi")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio_web2);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        idEscuelaTxt = (EditText) findViewById(R.id.editIdEscuela);
        acronimoTxt = (EditText) findViewById(R.id.editAcronimo);
        nombreTxt = (EditText) findViewById(R.id.editNombreEscuela);
    }

    public void insertarEscuela(View v) {
        if (idEscuelaTxt.getText().toString().isEmpty() ||
                acronimoTxt.getText().toString().isEmpty() ||
                nombreTxt.getText().toString().isEmpty()) {

            Toast.makeText(this, getResources().getString(R.string.vacio), Toast.LENGTH_SHORT).show();
        } else {

            String idEscuela = idEscuelaTxt.getText().toString();
            String acronimo = acronimoTxt.getText().toString();
            String nombre = nombreTxt.getText().toString();

            SimpleDateFormat dtf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
            Calendar calendar = Calendar.getInstance();

            Date dateObj = calendar.getTime();

            String fechaHoy = "'" + dtf.format(dateObj) + "'";

            String url = null;

            JSONObject datosEscuela = new JSONObject();
            JSONObject escuela = new JSONObject();

            switch (v.getId()) {
                case R.id.btn_escuelaLocal:
                    url = urlLocal + "?id_escuela=" + URLEncoder.encode(idEscuela) + "&acronimo=" + URLEncoder.encode(acronimo) + "&nombre=" + URLEncoder.encode(nombre) + "&fecha_modificado=" + URLEncoder.encode(fechaHoy);

                    ControladorServicio.insertarEscuelaW(url, this);

                    break;
                case R.id.btn_escuelaExterno:
                    url = urlHostingGratuito + "?id_escuela=" + URLEncoder.encode(idEscuela) + "&acronimo=" + URLEncoder.encode(acronimo) + "&nombre=" + URLEncoder.encode(nombre) + "&fecha_modificado=" + URLEncoder.encode(fechaHoy);

                    ControladorServicio.insertarEscuelaW(url, this);

                    break;
            }
        }
    }
}