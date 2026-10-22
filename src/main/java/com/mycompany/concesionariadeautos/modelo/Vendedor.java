/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.concesionariadeautos.modelo;

import com.mycompany.concesionariadeautos.interfaces.Registrable;
import com.mycompany.concesionariadeautos.interfaces.Rankeable;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 *
 * @author alfuxuns
 */


public class Vendedor implements Registrable, Rankeable {

    private static int siguienteId = 1;
    private static final double COMISION = 0.05;

    // Atributos
    private final int id;
    private String nombre;
    private double sueldoBase;
    private final ArrayList<Venta> historialVentas;

    public Vendedor(String nombre, double sueldoBase) {


        this.id = siguienteId++;
        this.nombre = nombre;
        this.sueldoBase = sueldoBase;
        this.historialVentas = new ArrayList<>();
    }

    // Registrar una venta realizada por este vendedor
    public void registrarVenta(Venta venta) {

        historialVentas.add(venta);
    }

    // Consultar historial sin modificarlo
    public List<Venta> getHistorialVentas() {
        return Collections.unmodifiableList(historialVentas);
    }

    // Cantidad de vehículos vendidos
    public int getCantidadVentas() {
        return historialVentas.size();
    }

    // Suma de todos los precios de venta
    public double getTotalVendido() {
        double total = 0;

        for (Venta venta : historialVentas) {
            total += venta.getPrecioVenta();
        }

        return total;
    }

    public double calcularComision() {
        return getTotalVendido() * COMISION;
    }

    public double calcularIngresoTotal() {
        return sueldoBase + calcularComision();
    }

    // Interfaz Registrable
    @Override
    public int getId() {
        return id;
    }

    @Override
    public String obtenerDatosRegistro() {
        return "Vendedor #" + id + " - " + nombre;
    }

    // Interfaz Rankeable
    @Override
    public double obtenerPuntajeRanking() {
        return getTotalVendido();
    }

    @Override
    public String obtenerDescripcionRanking() {
        return nombre + " | Ventas: " + getCantidadVentas()
            + " | Total vendido: " + getTotalVendido();
    }

    public String getNombre() {
        return nombre;
    }

    public double getSueldoBase() {
        return sueldoBase;
    }

    @Override
    public String toString() {
        return obtenerDatosRegistro()
            + "\nCantidad de ventas: " + getCantidadVentas()
            + "\nTotal vendido: " + getTotalVendido()
            + "\nComisión: " + calcularComision();
    }
}
