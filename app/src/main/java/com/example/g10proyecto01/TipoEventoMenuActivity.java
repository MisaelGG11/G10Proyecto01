package com.example.g10proyecto01;


import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

    public class TipoEventoMenuActivity extends ListActivity {

        String[] activities={"TipoEventoInsertarActivity","TipoEventoConsultarActivity","TipoEventoActualizarActivity", "TipoEventoEliminarActivity"};
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            String[] menu={ getResources().getString(R.string.insertar_registro),
                    getResources().getString(R.string.consultar_registro),
                    getResources().getString(R.string.actualizar_registro),
                    getResources().getString(R.string.eliminar_registro)};
            setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu));
            ListView listView = getListView();
            listView.setBackgroundColor(Color.parseColor("#B67291"));
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, menu);
            setListAdapter(adapter);
        }
        @Override
        protected void onListItemClick(ListView l, View v, int position, long id){
            super.onListItemClick(l, v, position, id);

            String nombreValue=activities[position];

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