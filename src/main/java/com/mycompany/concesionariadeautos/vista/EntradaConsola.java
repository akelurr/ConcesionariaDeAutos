/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.concesionariadeautos.vista;

import com.mycompany.concesionariadeautos.enums.*;
import com.mycompany.concesionariadeautos.modelo.*;
import com.mycompany.concesionariadeautos.servicio.Concesionaria;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author alfuxuns
 */
public class EntradaConsola {
    private final Scanner teclado;

    public EntradaConsola() {
        this.teclado = new Scanner(System.in);
    }

    int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(teclado.nextLine().trim());
            } catch (NumberFormatException ex) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
    }

    double leerDecimal(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double valor = Double.parseDouble(teclado.nextLine().trim().replace(',', '.'));
                if (Double.isFinite(valor)) {
                    return valor;
                }
            } catch (NumberFormatException ex) {
                // Se informa el error debajo y se repite la lectura.
            }
            System.out.println("Ingrese un número decimal válido y finito.");
        }
    }

    String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = teclado.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("Este campo no puede quedar vacío.");
        }
    }

    /** Muestra y permite escoger una marca sin utilizar genéricos. */
    Marca elegirMarca() {
        Marca[] opciones = Marca.values();
        System.out.println("\nMarcas disponibles:");
        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i + 1) + ". " + opciones[i]);
        }
        while (true) {
            int opcion = leerEntero("Seleccione una marca: ");
            if (opcion >= 1 && opcion <= opciones.length) {
                return opciones[opcion - 1];
            }
            System.out.println("Marca inválida. Intente nuevamente.");
        }
    }

    /** Muestra y permite escoger un modelo. */
    Modelo elegirModelo() {
        Modelo[] opciones = Modelo.values();
        System.out.println("\nModelos disponibles:");
        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i + 1) + ". " + opciones[i]);
        }
        while (true) {
            int opcion = leerEntero("Seleccione un modelo: ");
            if (opcion >= 1 && opcion <= opciones.length) {
                return opciones[opcion - 1];
            }
            System.out.println("Modelo inválido. Intente nuevamente.");
        }
    }

    /** Muestra y permite escoger un método de pago. */
    MetodoPago elegirMetodoPago() {
        MetodoPago[] opciones = MetodoPago.values();
        System.out.println("\nMétodos de pago disponibles:");
        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i + 1) + ". " + opciones[i]);
        }
        while (true) {
            int opcion = leerEntero("Seleccione un método de pago: ");
            if (opcion >= 1 && opcion <= opciones.length) {
                return opciones[opcion - 1];
            }
            System.out.println("Método de pago inválido. Intente nuevamente.");
        }
    }

}
