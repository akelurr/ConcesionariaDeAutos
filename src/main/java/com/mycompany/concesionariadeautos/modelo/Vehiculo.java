/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.concesionariadeautos.modelo;

import com.mycompany.concesionariadeautos.enums.Marca;
import com.mycompany.concesionariadeautos.enums.Modelo;
import com.mycompany.concesionariadeautos.enums.CondicionVehiculo;

/**
 *
 * @author alfuxuns
 */
 abstract class Vehiculo{


        protected Marca marca;
        protected Modelo modelo;
        protected int caballospotencia;
        protected int kilometraje;
        protected boolean frenosmalos;
        protected boolean neumaticosmalos;
        protected String dueno;
        protected String concesionaria;
        protected CondicionVehiculo condicion;
        protected int valor;
        protected String matricula;
        
        
        public Vehiculo(Marca marca, Modelo modelo, int caballospotencia){
            this.marca = marca;
            this.modelo = modelo;
            this.caballospotencia = caballospotencia;
            this.kilometraje =  0;
            this.frenosmalos = false;
            this.neumaticosmalos = false;
            this.dueno = null;
            this.concesionaria = null;
            this.condicion = CondicionVehiculo.NUEVO;
            this.valor = 0;
            this.matricula = null;
        }  //Constructor

        public void conducir(int kilometros){
            if(dueno == null){
                System.out.print("Todavía no puedes conducir"); // El auto no es utilizable 
            }
            else {

                // 
                //conduccion = conduccion / 10; //conduccion debe ser mallor a 10km
                

                /* NOTA: Revisar si algunos atributos de Vehiculo tienen sentido en el proyecto.
                if(conduccion > this.caballospotencia){ //Estas sobrepasando la potencia permitida
                    this.frenosmalos = true;
                    this.neumaticosmalos = true;
                    System.out.print("Deberías revisar tu auto");
                }
                */
                this.kilometraje += kilometros; 
                this.condicion = CondicionVehiculo.USADO;
            

        }
    }

        public abstract double precioDef();        
}
