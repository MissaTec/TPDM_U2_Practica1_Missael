package com.example.tpdm_u2_practica1_missael;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainEliminarEditar extends AppCompatActivity {

    EditText descripcion,ubicacion,fecha,presupuesto;
    Button actualizar,borrar,regresar;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_eliminar_editar);
        descripcion = findViewById(R.id.descripcion);
        ubicacion = findViewById(R.id.ubicacion);
        fecha = findViewById(R.id.fecha);
        presupuesto = findViewById(R.id.presupuesto);
        actualizar = findViewById(R.id.actualizaproyecto);
        borrar = findViewById(R.id.eliminaproyecto);
        regresar = findViewById(R.id.regresaproyecto);

        Bundle parametros = getIntent().getExtras();
        descripcion.setText(parametros.getString("descripcion"));
        ubicacion.setText(parametros.getString("ubicacion"));
        fecha.setText(parametros.getString("fecha"));
        presupuesto.setText(""+parametros.getFloat("presupuesto"));
        id = parametros.getInt("id");

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizar();
                finish();
            }
        });

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar();
                finish();
            }
        });

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private void actualizar(){
        Proyectos p = new Proyectos(this);
        String mensaje;
        boolean respuesta = p.actualizar(new Proyectos(id,descripcion.getText().toString(),ubicacion.getText().toString(),
                fecha.getText().toString(),Float.parseFloat(presupuesto.getText().toString())));
        if(respuesta){
            mensaje = "Se actualizo registro con exito";
        }else{
            mensaje = "ERROR, ALGO SALIO MAL :( "+p.error;
        }
        Toast.makeText(this,mensaje,Toast.LENGTH_LONG).show();
    }

    private void eliminar(){
        Proyectos p = new Proyectos(this);
        String mensaje;
        boolean respuesta = p.eliminar(new Proyectos(id,descripcion.getText().toString(),ubicacion.getText().toString(),
                fecha.getText().toString(),Float.parseFloat(presupuesto.getText().toString())));
        if(respuesta){
            mensaje = "Registro eliminado con exito";
        }else{
            mensaje = "ERROR, ALGO SALIO MAL: "+p.error;
        }
        Toast.makeText(this,mensaje,Toast.LENGTH_LONG).show();
    }
}
