package com.example.g10proyecto01;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EventoEspecialMenuActivity extends ListActivity  {

    String userPermisos;
    ControlG10Proyecto01 helper;
    List<Integer> permisos = new ArrayList<>();
    List<String> menu = new ArrayList<>();
    List<String> activities = new ArrayList<>();
   //"","","", ""};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        userPermisos = intent.getStringExtra("usuarioPermisos");
        helper = new ControlG10Proyecto01(this);

        String sql = "SELECT id_opcion_crud FROM AccesoUsuario WHERE id_usuario = '"+ userPermisos+"'";
        Cursor cursor = helper.llenarSpinner(sql);
        while (cursor.moveToNext()) {
            @SuppressLint("Range")
            int permiso = cursor.getInt(cursor.getColumnIndex("id_opcion_crud"));
            permisos.add(permiso);
        }

        if (permisos.contains(1)){
            menu.add(getResources().getString(R.string.insertar_registro));
            activities.add("EventoEspecialInsertarActivity");
        }
        if (permisos.contains(4)) {
            menu.add(getResources().getString(R.string.consultar_registro));
            activities.add("EventoEspecialConsultarActivity");
        }
        if (permisos.contains(2)){
            menu.add(getResources().getString(R.string.actualizar_registro));
            activities.add("EventoEspecialActualizarActivity");
        }
        if (permisos.contains(3)){
            menu.add(getResources().getString(R.string.eliminar_registro));
            activities.add("EventoEspecialEliminarActivity");
        }

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu));
        ListView listView = getListView();
        listView.setBackgroundColor(Color.parseColor("#B67291"));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, menu);
        setListAdapter(adapter);
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);

        String nombreValue=activities.get(position);

        l.getChildAt(position).setBackgroundColor(Color.parseColor("#9a5071"));

        try{
            Class<?> clase=Class.forName("com.example.g10proyecto01." + nombreValue);
            Intent inte = new Intent(this,clase);
            this.startActivity(inte);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}