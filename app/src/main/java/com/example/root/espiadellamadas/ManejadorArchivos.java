package com.example.root.espiadellamadas;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dadsez on 10/02/16.
 */
public class ManejadorArchivos
{
    public static final String NOMBRE_ARCHIVO = "tareas.txt";
    private static ManejadorArchivos m_instancia;
    private Context m_contexto;

    private ManejadorArchivos( Context contexto )
    {
        m_contexto = contexto;
    }

    public static ManejadorArchivos getInstancia( Context contexto )
    {
        if( m_instancia == null )
            m_instancia = new ManejadorArchivos( contexto );

        return m_instancia;
    }

    public List<String> leerArchivo(){
        File directorio = m_contexto.getFilesDir();
        Log.wtf("DIR TAREAS", directorio.getPath());
        File archivoTareas = new File(directorio,NOMBRE_ARCHIVO);
        List<String> tareas = null;

        try{
            tareas = FileUtils.readLines(archivoTareas);
        }catch (IOException ex){
           if(ex instanceof FileNotFoundException)
                tareas = new ArrayList<>();
        }
        return tareas;
    }

    public void escribirArchivo(List<String> tareas){
        File directorio = m_contexto.getFilesDir();
        File archivoTareas = new File(directorio,NOMBRE_ARCHIVO);
        try{
            FileUtils.writeLines(archivoTareas, tareas);
        }catch (IOException ex){
            Toast.makeText(m_contexto, "Error al escribir en tareas.txt", Toast.LENGTH_SHORT).show();
        }
    }
}
