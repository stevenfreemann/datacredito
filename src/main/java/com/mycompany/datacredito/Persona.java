/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.datacredito;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Steven
 */
public class Persona implements Serializable{

    private int cedula;
    private String nombre;
    private String apellido;
    
    HashMap<Integer,Record>record;

    public Persona(int cedula, String nombre, String apellido) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        record=new HashMap();
    }

 
    

   


    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public HashMap<Integer, Record> getRecord() {
        return record;
    }

    public void setRecord(HashMap<Integer, Record> record) {
        this.record = record;
    }

  

    
    
    
    
}
