/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.datacredito;

import java.io.Serializable;

/**
 *
 * @author Steven
 */
public class Record implements Serializable{
    private int codigo;
    private String empresa;
    private String estado;
    private float valor;

    public Record(int codigo, String empresa, String estado, float valor) {
        this.codigo = codigo;
        this.empresa = empresa;
        this.estado = estado;
        this.valor = valor;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
    
}
