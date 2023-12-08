package com.example.calendariodeactividades;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;


import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView Fecha,VerEvento;
    Button Btn_Calendario, Btn_ActividadNueva;

    EditText NombreDeActividad;
    int dia, mes, ano;

    public Nodo inicio=null;
    public Nodo ultimo=null;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Btn_Calendario = findViewById(R.id.Btn_Calendario);
        Btn_ActividadNueva = findViewById(R.id.ActividadNueva);
        NombreDeActividad = findViewById(R.id.NombreDeActividad);

        Btn_Calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendario = Calendar.getInstance();
                dia = calendario.get(Calendar.DAY_OF_MONTH);
                mes = calendario.get(Calendar.MONTH);
                ano = calendario.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int AnoSelect, int MesSelect, int DiaSelect) {

                        String diaFormateado, mesFormateado;

                        //Dia
                        if (DiaSelect< 10){
                            diaFormateado = "0"+String.valueOf(DiaSelect);
                        }else{
                            diaFormateado = String.valueOf(DiaSelect);
                        }

                        //Mes
                        int Mes = MesSelect + 1;

                        if (Mes < 10){
                            mesFormateado = "0"+String.valueOf(Mes);
                        }else{
                            mesFormateado = String.valueOf(Mes);
                        }

                        //Fecha en TextView
                        Fecha = findViewById(R.id.Fecha);
                        Fecha.setText(diaFormateado+"/"+mesFormateado+"/"+AnoSelect);
                    }
                }
                ,ano,mes,dia);
                datePickerDialog.show();
            }
        });

        Btn_ActividadNueva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarNodos();
                actualizarSalida();
            }
        });
    }

    public void agregarNodos(){
        Nodo nuevoNodo = new Nodo(NombreDeActividad, Fecha);
        if(inicio == null){
            inicio = nuevoNodo;
            ultimo = nuevoNodo;
        }else{
            ultimo.siguiente = nuevoNodo;
            ultimo=nuevoNodo;
        }
        NombreDeActividad.setText("");
        Fecha.setText("");
    }
    private void actualizarSalida() {
        StringBuilder resultado = new StringBuilder();
        Nodo imp = inicio;
        while (imp!=null)
        {
            resultado.append("Evento: ").append(imp.texto).append(" --  Fecha: ").append(imp.fecha).append("\n");
            imp=imp.siguiente;
        }
        VerEvento = findViewById(R.id.VerEvento);
        VerEvento.setText(resultado.toString());
    }
}