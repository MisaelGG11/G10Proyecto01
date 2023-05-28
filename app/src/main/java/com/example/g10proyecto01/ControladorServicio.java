package com.example.g10proyecto01;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ControladorServicio {
    public static String obtenerRespuestaPeticion(String url, Context ctx) {
        String respuesta = " ";

        // Estableciendo tiempo de espera del servicio
        HttpParams parametros = new BasicHttpParams();

        HttpConnectionParams.setConnectionTimeout(parametros, 3000);
        HttpConnectionParams.setSoTimeout(parametros, 5000);

        // Creando objetos de conexion
        HttpClient cliente = new DefaultHttpClient(parametros);

        HttpGet httpGet = new HttpGet(url);

        try {
            HttpResponse httpRespuesta = cliente.execute(httpGet);

            StatusLine estado = httpRespuesta.getStatusLine();

            int codigoEstado = estado.getStatusCode();

            String esta=String.valueOf(codigoEstado);

            Toast.makeText(ctx,esta,Toast.LENGTH_LONG).show();

            if (codigoEstado == 200) {
                HttpEntity entidad = httpRespuesta.getEntity();
                respuesta = EntityUtils.toString(entidad);
            }
        } catch (Exception e) {

            Toast.makeText(ctx, "Error en la conexion: " + e.getMessage(), Toast.LENGTH_LONG).show();

            // Desplegando el error en el LogCat
            Log.v("Error de Conexion", e.toString());
        }
        return respuesta;
    }

    public static List<Escuela> obtenerEscuelas(String json, Context ctx) {
        List<Escuela> listaEscuelas = new ArrayList<Escuela>();

        try {
            JSONArray escuelasJSON = new JSONArray(json);

            for (int i = 0; i < escuelasJSON.length(); i++) {
                JSONObject obj = escuelasJSON.getJSONObject(i);

                Escuela escuela = new Escuela();

                escuela.setId_escuela(obj.getInt("id_escuela"));
                escuela.setAcronimo(obj.getString("acronimo"));
                escuela.setNombre(obj.getString("nombre"));

                listaEscuelas.add(escuela);
            }

            return listaEscuelas;
        } catch (Exception e) {
            Toast.makeText(ctx, "Error en parseOO de JSON: " + e.getMessage(), Toast.LENGTH_LONG ).show();
            return null;
        }
    }

    public static void insertarEscuelaW(String peticion, Context ctx) {
        String json = obtenerRespuestaPeticion(peticion, ctx);

        try {
            JSONObject resultado = new JSONObject(json);

            int respuesta = resultado.getInt("resultado");

            if (respuesta == 1)
                Toast.makeText(ctx, "Registro ingresado", Toast.LENGTH_LONG).show();

        } catch (JSONException e) {
            e.printStackTrace();

            if (e.getMessage().contains("Duplicate"))
                Toast.makeText(ctx, "Error registro duplicado", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(ctx, "Error en parseo de JSON: " + e.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }
}
