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
    public class AutoElectrico extends Vehiculo{
        
        public AutoElectrico(Marca marca, Modelo modelo, int caballospotencia) {
            super(marca, modelo, caballospotencia);
        }
        @Override
        public double precioDef(){
            if (valor == 0){
                 if (modelo == Modelo.EXCLUSIVO){
                this.valor = valor + 30000;
                }
                else{
                    this.valor = valor + 15000;
                }
                 valor = valor + 45000;
            }
            return valor;
        }


    }
