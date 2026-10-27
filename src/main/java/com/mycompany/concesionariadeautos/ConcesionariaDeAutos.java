package com.mycompany.concesionariadeautos;

import com.mycompany.concesionariadeautos.vista.MenuConsola;
import com.mycompany.concesionariadeautos.modelo.Cliente;
import com.mycompany.concesionariadeautos.servicio.Concesionaria;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author alfuxuns
 */
public class ConcesionariaDeAutos{
        public static void main(String[] args){

            Concesionaria japan = new Concesionaria();

            MenuConsola menu = new MenuConsola(japan);

            menu.ejecutar();
    }  
}
