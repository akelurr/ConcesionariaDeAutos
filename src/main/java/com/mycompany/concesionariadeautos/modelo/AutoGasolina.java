/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.concesionariadeautos.modelo;

import com.mycompany.concesionariadeautos.enums.Marca;
import com.mycompany.concesionariadeautos.enums.Modelo;

/**
 *
 * @author alfuxuns
 */
    public class AutoGasolina extends Vehiculo{
        
        public AutoGasolina(Marca marca, Modelo modelo, int caballospotencia) {
            super(marca, modelo, caballospotencia);
        }
        @Override
        public double precioDef(){
            return precioModelo() + 35000;
        }
        
    }
