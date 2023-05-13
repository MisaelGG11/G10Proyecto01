package com.example.g10proyecto01;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class GG20031Activity extends ListActivity {
    String[] activities={"EmpleadoUESMenuActivity","DocenteMenuActivity","TipoEmpleadoMenuActivity"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] menu={ getResources().getString(R.string.tablaEmpleadoUES),
                        getResources().getString(R.string.tablaDocente),
                        getResources().getString(R.string.tablaTipoDeEmpleado)};
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu));
        ListView listView = getListView();
        listView.setBackgroundColor(Color.parseColor("#99c9bd"));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, menu);
        setListAdapter(adapter);
        //Toast.makeText(this, "Bienvenido " + userPermisos, Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onListItemClick(ListView l,View v,int position,long id){
        super.onListItemClick(l, v, position, id);

        String nombreValue=activities[position];

        l.getChildAt(position).setBackgroundColor(Color.parseColor("#99c9bd"));

        try{
            Class<?> clase=Class.forName("com.example.g10proyecto01." + nombreValue);
            Intent inte = new Intent(this,clase);
            this.startActivity(inte);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}

