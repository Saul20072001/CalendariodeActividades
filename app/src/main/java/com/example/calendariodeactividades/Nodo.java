package com.example.calendariodeactividades;
import android.widget.EditText;
import android.widget.TextView;

public class Nodo{
    String texto, fecha;
    Nodo siguiente;
    public Nodo(EditText NombreDeActividad, TextView Fecha){
        texto=NombreDeActividad.getText().toString();
        fecha=Fecha.getText().toString();
        siguiente=null;
    }
}
