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
public class MenuVentas {
    private final Concesionaria concesionaria;
    private final EntradaConsola entrada;
    private final MenuVehiculos vehiculos;
    private final MenuVendedores vendedores;

    public MenuVentas(Concesionaria concesionaria, EntradaConsola entrada, MenuVehiculos vehiculos, MenuVendedores vendedores) {
        this.concesionaria = concesionaria;
        this.entrada = entrada;
        this.vehiculos = vehiculos;
        this.vendedores = vendedores;
    }

    void realizarVenta() {
        System.out.println("\n--- Realizar venta ---");
        if (concesionaria.getInventario().isEmpty() || concesionaria.getVendedores().isEmpty()) {
            System.out.println("Se necesita al menos un vehículo y un vendedor registrados.");
            return;
        }
        vehiculos.listarVehiculos();
        int idVehiculo = entrada.leerEntero("ID del vehículo: ");
        Vehiculo seleccionado = null;
        for (Vehiculo vehiculo : concesionaria.getInventario()) {
            if (vehiculo.getId() == idVehiculo) {
                seleccionado = vehiculo;
                break;
            }
        }
        if (seleccionado == null) {
            System.out.println("Vehículo no disponible.");
            return;
        }
        int idCliente = entrada.leerEntero("ID del cliente: ");
        Cliente cliente = concesionaria.buscarClientePorId(idCliente);
        vendedores.listarVendedores();
        int idVendedor = entrada.leerEntero("ID del vendedor: ");
        Vendedor vendedor = concesionaria.buscarVendedorPorId(idVendedor);
        MetodoPago pago = entrada.elegirMetodoPago();
        System.out.printf("Precio final: %.2f | Saldo del cliente: %.2f%n",
                seleccionado.precioFinal(), cliente.getDinero());
        String confirma = entrada.leerTexto("Confirmar venta (S/N): ");
        if (!confirma.equalsIgnoreCase("S")) {
            System.out.println("Venta cancelada.");
            return;
        }
        Venta venta = concesionaria.realizarVenta(cliente, vendedor, seleccionado, pago);
        System.out.println("Venta realizada. ID de venta: " + venta.getId());
    }

    void mostrarVentas(List<Venta> ventas) {
        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }
        for (Venta venta : ventas) {
            System.out.printf("Venta #%d | %s | Cliente: %s | Vendedor: %s | Vehículo #%d | %.2f | %s%n",
                    venta.getId(), venta.getFecha(), venta.getCliente().getNombre(),
                    venta.getVendedor().getNombre(), venta.getVehiculo().getId(),
                    venta.getPrecioVenta(), venta.getMetodoPago());
        }
    }

    void listarVentas() {
        System.out.println("\n--- Historial de ventas ---");
        mostrarVentas(concesionaria.getVentas());
    }

}
