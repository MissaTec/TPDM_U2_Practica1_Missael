package com.example.tpdm_u2_practica1_missael;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView lista;
    Proyectos proyectos[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista = findViewById(R.id.lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                alertaEliminarEditar(position);
            }
        });
    }


    private void alertaEliminarEditar(final int pos){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("ATENCION");
        alerta.setMessage("Deseas eliminar o editar este proyecto?");
        alerta.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                invocarElininarEditar(pos);
            }
        });
        alerta.setNegativeButton("NO",null);
        alerta.show();
    }

    private void invocarElininarEditar(int pos){
        Intent eliminarEditar = new Intent(this,MainEliminarEditar.class);
        eliminarEditar.putExtra("id",proyectos[pos].getId());
        eliminarEditar.putExtra("descripcion",proyectos[pos].getDescripcion());
        eliminarEditar.putExtra("ubicacion",proyectos[pos].getUbicacion());
        eliminarEditar.putExtra("fecha",proyectos[pos].getFecha());
        eliminarEditar.putExtra("presupuesto",proyectos[pos].getPresupuesto());
        startActivity(eliminarEditar);
    }

    @Override
    protected void onStart() {
        Proyectos p = new Proyectos(this);
        proyectos = p.consultar();
        String[] datos = null;
        if(proyectos == null){
            datos = new String[1];
            datos[0] = "Noy hay proyectos ingresados";
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
        lista.setAdapter(adapter);
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menusito,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nuevoproyecto:
                Intent nuevoProyecto = new Intent(this,MainInsertar.class);
                startActivity(nuevoProyecto);
                break;
            case R.id.consultar:
                Intent consulta = new Intent(this,MainConsulta.class);
                startActivity(consulta);
        }
        return super.onOptionsItemSelected(item);
    }
}
