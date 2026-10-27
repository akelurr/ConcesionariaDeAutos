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
public class MenuVehiculos {
    private final Concesionaria concesionaria;
    private final EntradaConsola entrada;

    public MenuVehiculos(Concesionaria concesionaria, EntradaConsola entrada) {
        this.concesionaria = concesionaria;
        this.entrada = entrada;
    }

    void registrarVehiculo() {
        System.out.println("\n--- Registrar vehículo ---");
        int tipo;
        do {
            System.out.println("1. Diésel  2. Eléctrico  3. Gasolina");
            tipo = entrada.leerEntero("Tipo: ");
        } while (tipo < 1 || tipo > 3);

        Marca marca = entrada.elegirMarca();
        Modelo modelo = entrada.elegirModelo();
        int potencia = entrada.leerEntero("Caballos de potencia: ");
        if (potencia <= 0) {
            System.out.println("La potencia debe ser positiva.");
            return;
        }

        Vehiculo vehiculo;
        switch (tipo) {
            case 1: vehiculo = new AutoDiesel(marca, modelo, potencia); break;
            case 2: vehiculo = new AutoElectrico(marca, modelo, potencia); break;
            default: vehiculo = new AutoGasolina(marca, modelo, potencia);
        }
        concesionaria.registrarVehiculo(vehiculo);
        System.out.println("Vehículo registrado: " + vehiculo.obtenerDatosRegistro());
    }

    void mostrarVehiculos(List<Vehiculo> vehiculos) {
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehículos para mostrar.");
            return;
        }
        for (Vehiculo vehiculo : vehiculos) {
            System.out.printf("ID: %d | Tipo: %s | Marca: %s | Modelo: %s | Precio: %.2f%n",
                    vehiculo.getId(), vehiculo.getClass().getSimpleName(),
                    vehiculo.getMarca(), vehiculo.getModelo(), vehiculo.precioFinal());
        }
    }

    void listarVehiculos() {
        System.out.println("\n--- Inventario disponible ---");
        mostrarVehiculos(concesionaria.getInventario());
    }

    void buscarVehiculos() {
        System.out.println("\n1. Por ID  2. Por tipo  3. Por marca");
        int opcion = entrada.leerEntero("Opción: ");
        if (opcion == 1) {
            int id = entrada.leerEntero("ID del vehículo: ");
            for (Vehiculo vehiculo : concesionaria.getInventario()) {
                if (vehiculo.getId() == id) {
                    System.out.println(vehiculo);
                    return;
                }
            }
            System.out.println("Vehículo no encontrado en el inventario.");
        } else if (opcion == 2) {
            System.out.println("1. Diésel  2. Eléctrico  3. Gasolina");
            int tipo = entrada.leerEntero("Tipo: ");
            if (tipo < 1 || tipo > 3) {
                System.out.println("Tipo inválido.");
                return;
            }
            mostrarVehiculos(concesionaria.buscarVehiculosPortipo(tipo));
        } else if (opcion == 3) {
            Marca marca = entrada.elegirMarca();
            boolean encontrado = false;
            for (Vehiculo vehiculo : concesionaria.getInventario()) {
                if (vehiculo.getMarca() == marca) {
                    System.out.println(vehiculo.obtenerDatosRegistro()
                            + " | Precio: " + vehiculo.precioFinal());
                    encontrado = true;
                }
            }
            if (!encontrado) System.out.println("No hay vehículos de esa marca.");
        } else {
            System.out.println("Opción inválida.");
        }
    }

    void listarVehiculosPorPrecio() {
        System.out.println("\n--- Vehículos por precio ascendente ---");
        mostrarVehiculos(concesionaria.obtenerInventarioPorPrecio());
    }

}
