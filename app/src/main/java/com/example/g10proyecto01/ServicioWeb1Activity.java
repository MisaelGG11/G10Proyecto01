package com.example.g10proyecto01;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

@SuppressLint("NewApi")
public class ServicioWeb1Activity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    ControlG10Proyecto01 db;
    static List<Escuela> listaEscuelas;
    static List<String> nombreEscuelas;
    EditText fechaTxt;
    ListView listViewEscuelas;
    private final String urlLocal = "http://192.168.1.3/ws_db_escuela_fecha.php";
    private final String urlHostingGratuito = "https://hs19011pdm115.000webhostapp.com/ws_db_escuela_fecha.php";

    @SuppressLint("NewApi")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio_web1);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        db = new ControlG10Proyecto01(this);

        listaEscuelas = new ArrayList<Escuela>();
        nombreEscuelas = new ArrayList<String>();

        fechaTxt = (EditText) findViewById(R.id.editText_fecha);
        listViewEscuelas = (ListView) findViewById(R.id.listView1);

        fechaTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener la fecha actual para establecerla como fecha predeterminada en el selector
                Calendar now = Calendar.getInstance();

                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        ServicioWeb1Activity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );

                dpd.show(getSupportFragmentManager(), "DatePickerDialog");
            }
        });

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        // El usuario ha seleccionado una fecha
        String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
        fechaTxt.setText(selectedDate);
    }

    public void servicioPHP(View v) {
        String validarFecha = "^([1-9]|1\\d|2\\d|3[01])/([1-9]|1[0-2])/(\\d{4})$";

        if (fechaTxt.getText().toString().isEmpty()){
            Toast.makeText(this, getResources().getString(R.string.vacio), Toast.LENGTH_SHORT).show();
        } else if (fechaTxt.getText().toString().matches(validarFecha)){
            String[] fecha = fechaTxt.getText().toString().split("/");

            listaEscuelas.removeAll(listaEscuelas);

            actualizarListView();

            if (Integer.valueOf(fecha[2]) >= 1900 && Integer.valueOf(fecha[2]) <= 2100 ) {

                String url = "";

                switch (v.getId()) {
                    case R.id.button1:
                        // it was the first button
                        url = urlLocal + "?day=" + fecha[0] + "&month=" + fecha[1] + "&year=" + fecha[2];
                        break;
                    case R.id.button2:
                        // it was the second button
                        url = urlHostingGratuito + "?day=" + fecha[0] + "&month=" + fecha[1] + "&year=" + fecha[2];
                        break;
                }

                String escuelas = ControladorServicio.obtenerRespuestaPeticion(url, this);

                try {
                    listaEscuelas.addAll(ControladorServicio.obtenerEscuelas(escuelas, this));

                    actualizarListView();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(this, getResources().getString(R.string.el_anio), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, getResources().getString(R.string.el_formato) + getResources().getString(R.string.formato_fecha), Toast.LENGTH_SHORT).show();
        }
    }

    public void guardar(View v) {
        db.abrir();

        if (listaEscuelas.size() == 0) {
            Toast.makeText(this, getResources().getString(R.string.no_hay), Toast.LENGTH_LONG).show();
        } else {

            for (int i = 0; i < listaEscuelas.size(); i++) {
                Log.v("guardar", db.insertar(listaEscuelas.get(i)));
            }

            db.cerrar();

            Toast.makeText(this, getResources().getString(R.string.si_hay), Toast.LENGTH_LONG).show();

            listaEscuelas.removeAll(listaEscuelas);

            actualizarListView();
        }
    }

    private void actualizarListView() {
        String dato = "";

        nombreEscuelas.clear();

        for (int i = 0; i < listaEscuelas.size(); i++) {
            dato = listaEscuelas.get(i).getId_escuela() + " " + listaEscuelas.get(i).getAcronimo() + " \n" + listaEscuelas.get(i).getNombre();
            nombreEscuelas.add(dato);
        }

        eliminarElementosDuplicados();

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombreEscuelas);
        listViewEscuelas.setAdapter(adaptador);
    }

    private void eliminarElementosDuplicados() {
        HashSet<Escuela> conjuntoEscuela = new HashSet<Escuela>();

        conjuntoEscuela.addAll(listaEscuelas);

        listaEscuelas.clear();

        listaEscuelas.addAll(conjuntoEscuela);

        HashSet<String> conjuntoNombre = new HashSet<String>();

        conjuntoNombre.addAll(nombreEscuelas);

        nombreEscuelas.clear();

        nombreEscuelas.addAll(conjuntoNombre);
    }
}