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
public class MenuVendedores {
    private final Concesionaria concesionaria;
    private final EntradaConsola entrada;

    public MenuVendedores(Concesionaria concesionaria, EntradaConsola entrada) {
        this.concesionaria = concesionaria;
        this.entrada = entrada;
    }

    void registrarVendedor() {
        System.out.println("\n--- Registrar vendedor ---");
        String nombre = entrada.leerTexto("Nombre: ");
        double sueldo = entrada.leerDecimal("Sueldo base: ");
        Vendedor vendedor = concesionaria.registrarVendedor(nombre, sueldo);
        System.out.println("Registrado: " + vendedor.obtenerDatosRegistro());
    }

    void listarVendedores() {
        System.out.println("\n--- Vendedores ---");
        List<Vendedor> vendedores = concesionaria.getVendedores();
        if (vendedores.isEmpty()) System.out.println("No hay vendedores.");
        for (Vendedor vendedor : vendedores) {
            System.out.println(vendedor.obtenerDatosRegistro());
        }
    }

}
