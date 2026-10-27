/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.concesionariadeautos.vista;
import com.mycompany.concesionariadeautos.enums.*;
import com.mycompany.concesionariadeautos.modelo.*;
import com.mycompany.concesionariadeautos.servicio.Concesionaria;
import java.util.List;

/**
 *
 * @author alfuxuns
 */
public class MenuClientes {
    private final Concesionaria concesionaria;
    private final EntradaConsola entrada;
    private final MenuVentas ventas;

    public MenuClientes(Concesionaria concesionaria, EntradaConsola entrada, MenuVentas ventas) {
        this.concesionaria = concesionaria;
        this.entrada = entrada;
        this.ventas = ventas;
    }

    void registrarCliente() {
        System.out.println("\n--- Registrar cliente ---");
        String nombre = entrada.leerTexto("Nombre: ");
        int edad = entrada.leerEntero("Edad: ");
        double dinero = entrada.leerDecimal("Dinero disponible: ");
        double ingreso = entrada.leerDecimal("Ingreso mensual: ");
        Cliente cliente = concesionaria.registarCliente(nombre, edad, dinero, ingreso);
        System.out.println("Registrado: " + cliente.obtenerDatosRegistro());
    }

    void consultarCliente() {
        System.out.println("\n1. Buscar por ID  2. Buscar por nombre");
        int opcion = entrada.leerEntero("Opción: ");
        if (opcion == 1) {
            int id = entrada.leerEntero("ID del cliente: ");
            Cliente cliente = concesionaria.buscarClientePorId(id);
            mostrarPerfilCliente(cliente);
        } else if (opcion == 2) {
            String texto = entrada.leerTexto("Nombre o fragmento: ");
            List<Cliente> encontrados = concesionaria.buscarClientesPorNombre(texto);
            if (encontrados.isEmpty()) System.out.println("Sin coincidencias.");
            for (Cliente cliente : encontrados) mostrarPerfilCliente(cliente);
        } else {
            System.out.println("Opción inválida.");
        }
    }

    void mostrarPerfilCliente(Cliente cliente) {
        System.out.println("\n" + cliente.obtenerDatosRegistro());
        System.out.printf("Saldo: %.2f | Compras: %d | Total gastado: %.2f%n",
        cliente.getDinero(), cliente.getCantidadCompras(), cliente.getTotalGastado());
        ventas.mostrarVentas(cliente.getHistorialCompras());
    }

}
