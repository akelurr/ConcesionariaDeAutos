/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.concesionariadeautos.modelo;
import com.mycompany.concesionariadeautos.enums.Modelo;
import com.mycompany.concesionariadeautos.enums.Marca;
/**
 *
 * @author alfuxuns
 */
public class AutoDiesel extends Vehiculo {

    public AutoDiesel(Marca marca, Modelo modelo, int caballosPotencia){
        super(marca, modelo, caballosPotencia);

    }

    @Override
    public double precioDef(){
        return precioModelo() + 40000;
    }

    
    
}
