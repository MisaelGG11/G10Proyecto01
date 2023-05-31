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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

import android.widget.SimpleAdapter;
import java.util.HashMap;
import java.util.Map;
import android.graphics.Typeface;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;


@SuppressLint("NewApi")
public class ServicioWeb3Activity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    ControlG10Proyecto01 db;
    static List<Localidad> listalocales;
    static List<String> nombrelocales;
    EditText fechaTxt;
    ListView listViewLocales;

    private final String urlLocal = "http://192.168.0.11/ws_db_localidad_fecha.php";

    private final String urlHostingGratuito = "https://hs19011pdm115.000webhostapp.com/ws_db_localidad_fecha.php";

    @SuppressLint("NewApi")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio_web3);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        db = new ControlG10Proyecto01(this);

        listalocales = new ArrayList<Localidad>();
        nombrelocales = new ArrayList<String>();

        fechaTxt = (EditText) findViewById(R.id.editText_fechaL);
        listViewLocales = (ListView) findViewById(R.id.listViewlocales);

        fechaTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener la fecha actual para establecerla como fecha predeterminada en el selector
                Calendar now = Calendar.getInstance();

                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        ServicioWeb3Activity.this,
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

    public void LservicioPHP(View v) {
        String validarFecha = "^([1-9]|1\\d|2\\d|3[01])/([1-9]|1[0-2])/(\\d{4})$";

        if (fechaTxt.getText().toString().isEmpty()){
            Toast.makeText(this, getResources().getString(R.string.vacio), Toast.LENGTH_SHORT).show();
        } else if (fechaTxt.getText().toString().matches(validarFecha)){
            String[] fecha = fechaTxt.getText().toString().split("/");

            listalocales.removeAll(listalocales);

            actualizarListViewL();

            if (Integer.valueOf(fecha[2]) >= 1900 && Integer.valueOf(fecha[2]) <= 2100 && Integer.valueOf(fecha[1]) >= 01 && Integer.valueOf(fecha[1]) <= 12 && Integer.valueOf(fecha[0]) >= 01 && Integer.valueOf(fecha[1]) <= 31) {

                String url = "";

                switch (v.getId()) {
                    case R.id.btn_Local_Local:
                        // it was the first button
                        url = urlLocal + "?day=" + fecha[0] + "&month=" + fecha[1] + "&year=" + fecha[2];
                        break;
                    case R.id.btn_Local_Externo:
                        // it was the second button
                        url = urlHostingGratuito + "?day=" + fecha[0] + "&month=" + fecha[1] + "&year=" + fecha[2];
                        break;
                }

                String localidades = ControladorServicio.obtenerRespuestaPeticion(url, this);

                try {
                    listalocales.addAll(ControladorServicio.obtenerLocalidad(localidades, this));

                    actualizarListViewL();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(this, getResources().getString(R.string.el_anio2), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, getResources().getString(R.string.el_formato) + getResources().getString(R.string.formato_fecha), Toast.LENGTH_SHORT).show();
        }
    }

    public void guardar(View v) {
        db.abrir();

        if (listalocales.size() == 0) {
            Toast.makeText(this, getResources().getString(R.string.no_hay), Toast.LENGTH_LONG).show();
        } else {

            for (int i = 0; i < listalocales.size(); i++) {
                Log.v("guardar", db.insertar(listalocales.get(i)));
            }

            db.cerrar();

            Toast.makeText(this, getResources().getString(R.string.si_hay), Toast.LENGTH_LONG).show();

            listalocales.removeAll(listalocales);

            actualizarListViewL();
        }
    }

   private void actualizarListViewL() {
       List<Map<String, String>> datosFormato = new ArrayList<>();

       for (int i = 0; i < listalocales.size(); i++) {
           String dato1 = "ID: " + listalocales.get(i).getId_localidad() + "    " + listalocales.get(i).getNombre_localidad();
           String dato2 = listalocales.get(i).getEdificio_localidad() + "  Capacidad: " + listalocales.get(i).getCapacidad_localidad();

           Map<String, String> datoMap = new HashMap<>();
           datoMap.put("dato1", dato1);
           datoMap.put("dato2", dato2);
           datosFormato.add(datoMap);
       }
       eliminarElementosDuplicados();
       SimpleAdapter adaptador = new SimpleAdapter(this, datosFormato, android.R.layout.simple_list_item_2, new String[]{"dato1", "dato2"}, new int[]{android.R.id.text1, android.R.id.text2}) {
           @Override
           public View getView(int position, View convertView, ViewGroup parent) {
               View view = super.getView(position, convertView, parent);
               TextView textView1 = view.findViewById(android.R.id.text1);
               TextView textView2 = view.findViewById(android.R.id.text2);

               textView1.setTypeface(null, Typeface.BOLD);

               return view;
           }
       };

       listViewLocales.setAdapter(adaptador);
   }



    private void eliminarElementosDuplicados() {
        HashSet<Localidad> conjuntoLocal = new HashSet<Localidad>();

        conjuntoLocal.addAll(listalocales);

        listalocales.clear();

        listalocales.addAll(conjuntoLocal);

        HashSet<String> conjuntoNombre = new HashSet<String>();

        conjuntoNombre.addAll(nombrelocales);

        nombrelocales.clear();

        nombrelocales.addAll(conjuntoNombre);
    }
}