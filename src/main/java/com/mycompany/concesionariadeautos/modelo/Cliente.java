/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.concesionariadeautos.modelo;

/**
 *
 * @author alfuxuns
 */
public class Cliente {
    private String nombre;
    private int edad;
    private double dinero = 0;
    private double saldo = edad * 25000;

    public Cliente(String nombre, int edad){
        this.nombre = nombre;
        this.edad = edad;
        this.dinero = 0;
        this.saldo = edad * 25000;
    }

    public void trabajar(){
        this.dinero = dinero + saldo;
    }


    public String getNombre(){
        return nombre;
    }

    public int getEdad(){
        return edad;
    }

    public double getDinero(){
        return dinero;
    }

    public void setEdad(int edad){
        this.edad = edad;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
}
