/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.concesionariadeautos.modelo;

import java.util.Scanner;

/**
 *
 * @author alfuxuns
 */
 abstract class Auto implements PrecioBase{
        protected Marca marca;
        protected Modelo modelo;
        protected int caballospotencia;
        protected int kilometraje;
        protected boolean frenosmalos;
        protected boolean neumaticosmalos;
        protected String dueno;
        protected String concesionaria;
        protected boolean nuevo;
        protected int valor;
        protected String matricula;
        
        
        public Auto(Marca marca, Modelo modelo, int caballospotencia){
            this.marca = marca;
            this.modelo = modelo;
            this.caballospotencia = caballospotencia;
            this.kilometraje =  0;
            this.frenosmalos = false;
            this.neumaticosmalos = false;
            this.dueno = null;
            this.concesionaria = null;
            this.nuevo = true;
            this.valor = 0;
            this.matricula = null;
        }  //Constructor

        private void conducir(){
            if(dueno == null){
                System.out.print("todavía no puedes conducir"); // El auto no es utilizable 
            }
            else {
                System.out.println("¿ a cuantos kilómetros quieres conducir?");
                Scanner sc = new Scanner(System.in);
                int conduccion = Integer.parseInt(sc.nextLine());
                sc.close();
                this.kilometraje = this.kilometraje + conduccion;
                nuevo = false;
                conduccion = conduccion / 10; //cunduccion debe ser mallor a 10km
                if(conduccion > this.caballospotencia){ //Estas sobrepasando la potencia permitida
                    this.frenosmalos = true;
                    this.neumaticosmalos = true;
                    System.out.print("deberías revisar tu auto");
                }
        }
}
        
    }