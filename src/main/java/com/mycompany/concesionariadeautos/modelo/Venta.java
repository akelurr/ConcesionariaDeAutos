/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.concesionariadeautos.modelo;

import com.mycompany.concesionariadeautos.enums.MetodoPago;
import java.time.LocalDate;
/**
 *
 * @author alfuxuns
 */
public class Venta {

    private static int siguienteId = 1;

    // Atributos de la venta

    private final int id;
    private final Cliente cliente;
    private final Vendedor vendedor;
    private final Vehiculo vehiculo;
    private final LocalDate fecha;
    private final double precioVenta;
    private final MetodoPago metodoPago;

    public Venta(Cliente cliente, Vendedor vendedor, Vehiculo vehiculo, MetodoPago metodoPago){
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.vehiculo = vehiculo;
        this.metodoPago = metodoPago;

        this.id = siguienteId++;
        this.fecha = LocalDate.now();

        // Precio calculado según el tipo de vehículo
        

        this.precioVenta = vehiculo.precioFinal();
    }


    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }


    @Override
    public String toString(){
        return "Venta #" + id
            + "\nFecha: " + fecha
            + "\nCliente: " + cliente
            + "\nVendedor: " + vendedor
            + "\nVehiculo: " + vehiculo
            + "\nPrecio: " + precioVenta
            + "\nMetodo de pago: " + metodoPago;
    }
    
    
}
