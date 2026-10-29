package com.mycompany.concesionariadeautos;

import com.mycompany.concesionariadeautos.vista.MenuConsola;
import com.mycompany.concesionariadeautos.modelo.Cliente;
import com.mycompany.concesionariadeautos.servicio.Concesionaria;
import com.mycompany.concesionariadeautos.persistencia.GestorPersistencia;
import java.io.IOException;


/**
 *
 * @author alfuxuns
 */
public class ConcesionariaDeAutos{
        public static void main(String[] args){

            try{

            Concesionaria japan = GestorPersistencia.cargar();

            


            MenuConsola menu = new MenuConsola(japan);

            menu.ejecutar();


            } catch (IOException | ClassNotFoundException ex){
                System.err.println("Error al cargar datos: " + ex.getMessage());
            }
    }  
}
