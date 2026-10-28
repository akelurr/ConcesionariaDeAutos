/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.concesionariadeautos.modelo;
import com.mycompany.concesionariadeautos.interfaces.Registrable;
import com.mycompany.concesionariadeautos.enums.Marca;
import com.mycompany.concesionariadeautos.enums.Modelo;
import com.mycompany.concesionariadeautos.enums.CondicionVehiculo;
import com.mycompany.concesionariadeautos.enums.EstadoVehiculo;
import java.io.Serializable;


/**
 *
 * @author alfuxuns
 */
public abstract class Vehiculo implements Serializable, Registrable {
        private static final long serialVersionUID = 1L;

        private static int siguienteId = 1;

        private final int id;
        protected Marca marca;
        protected Modelo modelo;
        protected int caballosPotencia;
        protected int kilometraje;
        protected boolean frenosMalos;
        protected boolean neumaticosMalos;
        protected EstadoVehiculo estado;
        protected CondicionVehiculo condicion;
        protected String matricula;
        
        public Vehiculo(Marca marca, Modelo modelo, int caballospotencia){
            this.marca = marca;
            this.modelo = modelo;
            this.caballosPotencia = caballospotencia;
            this.kilometraje =  0;
            this.frenosMalos = false;
            this.neumaticosMalos = false;
            this.estado = EstadoVehiculo.DISPONIBLE;
            this.condicion = CondicionVehiculo.NUEVO;
            this.matricula = null;
            this.id = siguienteId++;
        }  //Constructor
        



        public void conducir(int kilometros){

            if (kilometros <= 0){
                throw new IllegalArgumentException("Los kilometros deben ser positivos");
            }

            if (estado != EstadoVehiculo.DISPONIBLE){
                throw new IllegalStateException("El vehiculo no esta disponible");
            }


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
        
        @Override
        public int getId(){
            return id;
        }

        @Override
        public String obtenerDatosRegistro(){
            return "Auto #" + id + " - " + marca;
        }

        @Override 
        public String toString(){
            return "Vehiculo #" + id
                + "\nMarca: " + marca
                + "\nModelo: " + modelo
                + "\nPotencia: " + caballosPotencia + " HP"
                + "\nKilometraje: " + kilometraje + " km"
                + "\nCondicion: " + condicion
                + "\nEstado: " + estado
                + "\nPrecio: " + precioFinal();
        }

        // Getters y setters convencionales

        public Marca getMarca(){
            return marca;
        }

        public Modelo getModelo(){
            return modelo;
        }

        public int getCaballosPotencia(){
            return caballosPotencia;
        }

        public int getKilometraje(){
            return kilometraje;
        } 

        public boolean getFrenosMalos(){
            return frenosMalos;
        }

        public void setFrenosMalos(boolean newFrenosMalos){
            frenosMalos = newFrenosMalos; 
        }

 
        public boolean getNeumaticosMalos(){
            return neumaticosMalos;
        }

        public void setNeumaticosMalos(boolean newNeumaticosMalos){
            neumaticosMalos = newNeumaticosMalos; 
        }

        public CondicionVehiculo getCondicion(){
            return condicion;
        }

        public String getMatricula(){
            return matricula;
        }

        public void setMatricula(String newMatricula){
            matricula = newMatricula; 
        }

    
}
