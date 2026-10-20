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
        protected int caballosPotencia;
        protected int kilometraje;
        protected boolean frenosMalos;
        protected boolean neumaticosMalos;
        protected String duenio;
        protected String concesionaria;
        protected CondicionVehiculo condicion;
        protected int valor;
        protected String matricula;
        
        public Vehiculo(Marca marca, Modelo modelo, int caballospotencia){
            this.marca = marca;
            this.modelo = modelo;
            this.caballosPotencia = caballospotencia;
            this.kilometraje =  0;
            this.frenosMalos = false;
            this.neumaticosMalos = false;
            this.duenio = null;
            this.concesionaria = null;
            this.condicion = CondicionVehiculo.NUEVO;
            this.valor = 0;
            this.matricula = null;
        }  //Constructor

        public void conducir(int kilometros){
            if(duenio == null){
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

        protected double precioModelo() {
            if (modelo == Modelo.EXCLUSIVO) {
                return 30000;
            }

            return 15000;

        }

        public double precioFinal() {
            double precio = precioDef();

            // Descuento por kilometraje
            if (kilometraje > 100000) {
                precio *= 0.80;
            } else if ( kilometraje > 50000){
                precio *= 0.90;
            }

            // Descuento por desperfectos

            if (frenosMalos) {
                precio -= 2000;
            }
            if (neumaticosMalos) {
                precio -= 1500;
            }

            return Math.max(0, precio);


        }
}
