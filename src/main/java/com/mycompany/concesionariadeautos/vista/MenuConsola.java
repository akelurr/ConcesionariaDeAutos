/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.concesionariadeautos.vista;

import com.mycompany.concesionariadeautos.enums.Marca;
import com.mycompany.concesionariadeautos.enums.MetodoPago;
import com.mycompany.concesionariadeautos.enums.Modelo;
import com.mycompany.concesionariadeautos.modelo.AutoDiesel;
import com.mycompany.concesionariadeautos.modelo.AutoElectrico;
import com.mycompany.concesionariadeautos.modelo.AutoGasolina;
import com.mycompany.concesionariadeautos.modelo.Cliente;
import com.mycompany.concesionariadeautos.modelo.Vehiculo;
import com.mycompany.concesionariadeautos.modelo.Vendedor;
import com.mycompany.concesionariadeautos.modelo.Venta;
import com.mycompany.concesionariadeautos.persistencia.GestorPersistencia;
import com.mycompany.concesionariadeautos.servicio.Concesionaria;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author alfuxuns
 */

public class MenuConsola {
    private final Concesionaria concesionaria;
    private final EntradaConsola entrada;
    private final MenuVehiculos vehiculos;
    private final MenuClientes clientes;
    private final MenuVendedores vendedores;
    private final MenuVentas ventas;
    private final MenuReportes reportes;

    public MenuConsola(Concesionaria concesionaria) {
        if (concesionaria == null) {
            throw new IllegalArgumentException("La concesionaria no puede ser nula.");
        }
        this.concesionaria = concesionaria;
        this.entrada = new EntradaConsola();
        this.vehiculos = new MenuVehiculos(concesionaria, entrada);
        this.vendedores = new MenuVendedores(concesionaria, entrada);
        this.ventas = new MenuVentas(concesionaria, entrada, vehiculos, vendedores);
        this.clientes = new MenuClientes(concesionaria, entrada, ventas);
        this.reportes = new MenuReportes(concesionaria);
    }

    public void ejecutar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = entrada.leerEntero("Opción: ");
            try {
                switch (opcion) {
                    case 1: vehiculos.registrarVehiculo(); break;
                    case 2: vehiculos.listarVehiculos(); break;
                    case 3: vehiculos.buscarVehiculos(); break;
                    case 4: vehiculos.listarVehiculosPorPrecio(); break;
                    case 5: clientes.registrarCliente(); break;
                    case 6: clientes.consultarCliente(); break;
                    case 7: vendedores.registrarVendedor(); break;
                    case 8: vendedores.listarVendedores(); break;
                    case 9: ventas.realizarVenta(); break;
                    case 10: ventas.listarVentas(); break;
                    case 11: reportes.mostrarRanking(); break;
                    case 12: reportes.mostrarReporte(); break;
                    case 0: guardarAlSalir(); break;
                    default: System.out.println("Opción inexistente.");
                }
            } catch (IllegalArgumentException | IllegalStateException ex) {
                System.out.println("Operación rechazada: " + ex.getMessage());
            }
        } while (opcion != 0);
    }

    void mostrarMenu() {
        System.out.println("\n========== MENÚ PRINCIPAL ==========");
        System.out.println("1. Registrar vehículo");
        System.out.println("2. Listar vehículos");
        System.out.println("3. Buscar vehículos");
        System.out.println("4. Listar vehículos por precio");
        System.out.println("5. Registrar cliente");
        System.out.println("6. Buscar cliente y ver historial");
        System.out.println("7. Registrar vendedor");
        System.out.println("8. Listar vendedores");
        System.out.println("9. Realizar venta");
        System.out.println("10. Ver historial de ventas");
        System.out.println("11. Ranking de vendedores");
        System.out.println("12. Reporte general");
        System.out.println("0. Salir y guardar");
        System.out.println("====================================");

    }

    void guardarAlSalir() {
        try {
            GestorPersistencia.guardar(concesionaria);
            System.out.println("Datos guardados. Hasta pronto.");
        } catch (IOException ex) {
            System.out.println("No fue posible guardar los datos: " + ex.getMessage());
            System.out.println("Advertencia: los cambios de esta sesión podrían perderse.");
        }
    }
}

