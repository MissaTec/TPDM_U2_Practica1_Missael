package com.example.tpdm_u2_practica1_missael;


import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class Proyectos {
    private BaseDatos base;
    private int id;
    private String descripcion, ubicacion, fecha;
    private float presupuesto;
    protected String error;

    public Proyectos(Activity activity){
        base = new BaseDatos(activity,"Archivo",null,1);
    }

    public Proyectos(int id, String descripcion, String ubicacion, String fecha, float presupuesto){
        this.id = id;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.fecha = fecha;
        this.presupuesto = presupuesto;
    }

    public boolean insertar(Proyectos proyecto){
        try{
            SQLiteDatabase insert = base.getWritableDatabase();
            ContentValues datos = new ContentValues();
            datos.put("DESCRIPCION",proyecto.getDescripcion());
            datos.put("UBICACION",proyecto.getUbicacion());
            datos.put("FECHA",proyecto.getFecha());
            datos.put("PRESUPUESTO",proyecto.getPresupuesto());

            long resultado = insert.insert("PROYECTOS","IDPROYECTO",datos);

            insert.close();
            if(resultado ==-1){
                return false;
            }

        }catch(SQLiteException e){
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public Proyectos[] consultar(String clave){
        Proyectos[] resultado = null;
        try{
            SQLiteDatabase consulta = base.getReadableDatabase();
            String SQL = "SELECT * FROM PROYECTOS WHERE IDPROYECTO ="+clave;

            Cursor c = consulta.rawQuery(SQL,null);
            if(c.moveToFirst()){
                resultado = new Proyectos[c.getCount()];
                int pos = 0;
                do{
                    resultado[pos] = new Proyectos(c.getInt(0),c.getString(1),
                            c.getString(2),c.getString(3),c.getFloat(4));
                    pos++;
                }while(c.moveToNext());
            }else{
                String SQL2 = "SELECT * FROM PROYECTOS WHERE DESCRIPCION = '"+clave+"'";
                c = consulta.rawQuery(SQL2,null);
                if(c.moveToFirst()){
                    resultado = new Proyectos[c.getCount()];
                    int pos = 0;
                    do{
                        resultado[pos] = new Proyectos(c.getInt(0),c.getString(1),
                                c.getString(2),c.getString(3),c.getFloat(4));
                        pos++;
                    }while(c.moveToNext());
                }

            }
            consulta.close();
        }catch(SQLiteException e){
            return null;
        }
        return resultado;
    }


    public Proyectos[] consultar(){
        Proyectos[] resultado = null;
        try{
            SQLiteDatabase consulta = base.getReadableDatabase();
            String SQL = "SELECT * FROM PROYECTOS";
            Cursor c = consulta.rawQuery(SQL,null);
            if(c.moveToNext()){
                resultado = new Proyectos[c.getCount()];
                int pos = 0;
                do{
                    resultado[pos] = new Proyectos(c.getInt(0),c.getString(1),
                            c.getString(2),c.getString(3),c.getFloat(4));
                    pos++;
                }while(c.moveToNext());
            }
            consulta.close();
        }catch(SQLiteException e){
            return null;
        }
        return resultado;
    }


    public boolean actualizar(Proyectos proyecto){
        try{
            SQLiteDatabase actualiza = base.getWritableDatabase();
            ContentValues datos = new ContentValues();
            datos.put("DESCRIPCION",proyecto.getDescripcion());
            datos.put("UBICACION",proyecto.getUbicacion());
            datos.put("FECHA",proyecto.getFecha());
            datos.put("PRESUPUESTO",proyecto.getPresupuesto());
            String id[] = {""+proyecto.getId()};
            long resultado = actualiza.update("PROYECTOS",datos,"IDPROYECTO=?",id);
            actualiza.close();
            if(resultado == -1){
                return false;
            }
        }catch(SQLiteException e){
            error = e.getMessage();
            return false;
        }
        return true;
    }


    public boolean eliminar (Proyectos proyecto){
        int resultado;
        try{
            SQLiteDatabase elimina = base.getWritableDatabase();
            String id[] = {""+proyecto.getId()};
            resultado = elimina.delete("PROYECTOS","IDPROYECTO=?",id);
            elimina.close();
        }catch(SQLiteException e){
            error = e.getMessage();
            return false;
        }
        return resultado>0;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public float getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(float presupuesto) {
        this.presupuesto = presupuesto;
    }
}
