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
public class ServicioWeb6Activity extends AppCompatActivity {

    EditText idCicloTxt;
    EditText cicloTxt;
    EditText anioTxt;

    private final String urlLocal = "http://192.168.1.15/ws_ciclo_insert.php";
    private final String urlHostingGratuito = "https://hs19011pdm115.000webhostapp.com/ws_ciclo_insert.php";

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio_web6);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        idCicloTxt = (EditText) findViewById(R.id.editIdCicloWeb);
        cicloTxt = (EditText) findViewById(R.id.editCicloWeb);
        anioTxt = (EditText) findViewById(R.id.editAnioWeb);
    }

    public void insertarCiclo(View v) {
        if (idCicloTxt.getText().toString().isEmpty() ||
                cicloTxt.getText().toString().isEmpty() ||
                anioTxt.getText().toString().isEmpty()) {

            Toast.makeText(this, getResources().getString(R.string.vacio), Toast.LENGTH_SHORT).show();
        } else {

            String idCiclo = idCicloTxt.getText().toString();
            String ciclo = cicloTxt.getText().toString();
            String anio = anioTxt.getText().toString();

            SimpleDateFormat dtf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
            Calendar calendar = Calendar.getInstance();

            Date dateObj = calendar.getTime();

            String fechaHoy = "'" + dtf.format(dateObj) + "'";

            String url = null;

            JSONObject datosCiclo = new JSONObject();
            JSONObject cicloJSON = new JSONObject();

            switch (v.getId()) {
                case R.id.btn_escuelaLocal:
                    url = urlLocal + "?id_ciclo=" + URLEncoder.encode(idCiclo) + "&ciclo=" + URLEncoder.encode(ciclo) + "&anio=" + URLEncoder.encode(anio) + "&fecha_modificado=" + URLEncoder.encode(fechaHoy);

                    ControladorServicio.insertarCicloW(url, this);

                    break;
                case R.id.btn_escuelaExterno:
                    url = urlHostingGratuito + "?id_ciclo=" + URLEncoder.encode(idCiclo) + "&ciclo=" + URLEncoder.encode(ciclo) + "&anio=" + URLEncoder.encode(anio) + "&fecha_modificado=" + URLEncoder.encode(fechaHoy);

                    ControladorServicio.insertarCicloW(url, this);

                    break;
            }
            Log.v("Errrrrrooooooorrrr: ", url);
        }
    }
}