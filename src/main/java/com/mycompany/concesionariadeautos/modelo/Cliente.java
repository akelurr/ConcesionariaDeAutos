/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package com.mycompany.concesionariadeautos.modelo;
import com.mycompany.concesionariadeautos.interfaces.Registrable;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;



/**
 *
 * @author alfuxuns
 */ public class Cliente implements Registrable{

    private static int siguienteId = 1;

    // Atributos de cliente

    private final int id;
    private String nombre;
    private int edad;
    private double dinero;
    private double ingresoMensual;
    private ArrayList<Venta> historialCompras;

    public Cliente(String nombre, int edad, double dinero, double ingresoMensual, ArrayList<Venta> historialCompras){
        this.nombre = nombre;
        this.edad = edad;
        this.dinero = dinero;
        this.ingresoMensual = ingresoMensual;
        this.historialCompras = new ArrayList<>();

        this.id = siguienteId++;


    }

    private static void validarMonto(double monto) {
        if (monto < 0){
            throw new IllegalAccessError(
                    "El monto debe ser finito y no negativo"
                    );
        }
    }

    public void trabajar(){
        depositar(ingresoMensual);
    }

    public void depositar(double monto){
        validarMonto(monto);

        double nuevoSaldo = dinero + monto;
        dinero = nuevoSaldo;
    }
    public boolean puedePagar(double monto){
        validarMonto(monto);
        return dinero >= monto;
    }

    public void descontarDinero(double monto){
        validarMonto(monto);
        if (!puedePagar(monto)){
            throw new IllegalAccessError(
                    "El cliente no tiene dinero suficiente"
                    );
        }
        dinero -= monto;
    
    }

    public void registrarCompra(Venta venta){
        if (venta == null){
            throw new IllegalArgumentException(
                    "La venta no puede ser nula."
                    );
        }

        if (venta.getCliente() != this){
            throw new IllegalAccessError(
                    "La venta corresponde a otro cliente"
                    );
        }

        if (historialCompras.contains(venta)){
            throw new IllegalArgumentException(
                    "La venta ya fue registrada. "
                    );
        }
        historialCompras.add(venta);
    }

    public List<Venta> getHistorialCompras(){
        return Collections.unmodifiableList(
                historialCompras
                );
    }

    public int getCantidadCompras(){
        return historialCompras.size();
    }
    
    public double getTotalGastado(){
        double precioTotal = 0;

        for (Venta venta: historialCompras){
            precioTotal += venta.getPrecioVenta();
        }
        
        return precioTotal;
    }

    // Métodos de interfaz Registrable

    @Override
    public int getId(){
        return id;
    }
    
    @Override
    public String obtenerDatosRegistro(){
        return "Cliente #" + id
            + " - " + nombre
            + " (" + edad  + " anios)";
    }
    
    @Override
    public String toString(){
        return "Cliente #" + id
            + "\nNombre: " + nombre
            + "\nEdad: " + edad
            + "\nDinero: " + dinero 
            + "\nIngreso mensual: " + ingresoMensual
            + "\nHistorial de compras: " + historialCompras;
    }
    

    // Getters y setters convencionales


    public String getNombre(){
        return nombre;
    }

    public int getEdad(){
        return edad;
    }

    public double getDinero(){
        return dinero;
    }

    public void setEdad(int edad){
        this.edad = edad;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    


    
}
