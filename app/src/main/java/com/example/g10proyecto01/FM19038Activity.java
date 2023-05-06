package com.example.g10proyecto01;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
public class FM19038Activity extends ListActivity {

    String[] activities={"PropuestaGeneralMenuActivity","PropuestaEspecificaMenuActivity","UsuarioMenuActivity", "OpcionCrudMenuActivity"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] menu={ getResources().getString(R.string.tablaPropuestaGeneral),
                getResources().getString(R.string.tablaPropuestaEspecifica),
                getResources().getString(R.string.tablaUsuario),
                getResources().getString(R.string.tablaOpcionCrud) };
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu));
        ListView listView = getListView();
        listView.setBackgroundColor(Color.parseColor("#ee7f27"));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, menu);
        setListAdapter(adapter);
    }
    @Override
    protected void onListItemClick(ListView l,View v,int position,long id){
        super.onListItemClick(l, v, position, id);

        String nombreValue=activities[position];

        l.getChildAt(position).setBackgroundColor(Color.parseColor("#ee7f27"));

        try{
            Class<?> clase=Class.forName("com.example.g10proyecto01." + nombreValue);
            Intent inte = new Intent(this,clase);
            this.startActivity(inte);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}