package com.example.tpdm_u2_practica1_missael;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainInsertar extends AppCompatActivity {
    EditText descripcion,ubicacion,fecha,presupuesto;
    Button insertar,regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_insertar);
        descripcion = findViewById(R.id.descripcion);
        ubicacion = findViewById(R.id.ubicacion);
        fecha = findViewById(R.id.fecha);
        presupuesto = findViewById(R.id.presupuesto);
        insertar = findViewById(R.id.insertar);
        regresar = findViewById(R.id.regresar);

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertar();
            }
        });

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void insertar(){
        String mensaje;
        Proyectos p = new Proyectos(this);
        boolean respuesta = p.insertar(new Proyectos(0,descripcion.getText().toString(),ubicacion.getText().toString(),
                fecha.getText().toString(),Float.parseFloat(presupuesto.getText().toString())));
        if(respuesta){
            mensaje = "Se puedo inserta el nuevo Proyecto: "+descripcion.getText().toString();
            limpiar();
        }else{
            mensaje = "Error al querer insertar proyecto, respuesta SQLITE: "+p.error;
        }

        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("ATENCION");
        alerta.setMessage(mensaje);
        alerta.setPositiveButton("OK",null);
        alerta.show();
    }

    private void limpiar(){
        descripcion.setText("");
        ubicacion.setText("");
        fecha.setText("");
        presupuesto.setText("");
    }

}
