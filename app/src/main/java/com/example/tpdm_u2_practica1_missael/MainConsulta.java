package com.example.tpdm_u2_practica1_missael;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainConsulta extends AppCompatActivity {

    ListView listaconsulta;
    EditText busqueda;
    Button consultar, cerrar;
    Proyectos[] proyectos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_consulta);
        listaconsulta = findViewById(R.id.listaconsultar);
        busqueda = findViewById(R.id.busqueda);
        consultar = findViewById(R.id.botonconsultar);
        cerrar = findViewById(R.id.botoncerrar);

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscar(busqueda.getText().toString());
                busqueda.setText("");
            }
        });

        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

   public void buscar(String clave){
        Proyectos p = new Proyectos(this);
        proyectos = p.consultar(clave);
        String[] datos = null;
        if(proyectos == null){
            datos = new String[1];
            datos[0] = "No se encontro ningun registro";
        }else{
            datos = new String[proyectos.length];
            for(int i=0; i<proyectos.length; i++){
                Proyectos temp = proyectos[i];
                datos[i] = temp.getDescripcion()+"\n"+
                        temp.getUbicacion()+"\n"+
                        temp.getPresupuesto();
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,datos);
        listaconsulta.setAdapter(adapter);
    }
}
