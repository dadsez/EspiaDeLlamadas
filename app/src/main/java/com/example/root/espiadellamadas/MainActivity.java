package com.example.root.espiadellamadas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private List<String> m_tareas;
    private ManejadorArchivos m_manejadorArchivos;
    private ListView m_vistaLista;
    private ArrayAdapter<String> m_adapter;
    private EditText m_tareaNueva;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_manejadorArchivos = ManejadorArchivos.getInstancia( this );
        m_tareas = m_manejadorArchivos.leerArchivo();
        m_vistaLista = (ListView)findViewById(R.id.lv_tareas);
        m_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,m_tareas);
        m_tareaNueva = (EditText)findViewById(R.id.txt_nuevaTarea);

        m_vistaLista.setAdapter(m_adapter);

        m_vistaLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                m_tareas.remove(position);
                m_adapter.notifyDataSetChanged();
                m_manejadorArchivos.escribirArchivo(m_tareas);
                return true;
            }
        });
    }

    public void onClick( View v )
    {
        String tareaNueva = m_tareaNueva.getText().toString();

        if( !tareaNueva.trim().isEmpty() )
        {
            m_tareas.add(tareaNueva);
            m_adapter.notifyDataSetChanged();
            m_manejadorArchivos.escribirArchivo(m_tareas);
        }
    }
}
