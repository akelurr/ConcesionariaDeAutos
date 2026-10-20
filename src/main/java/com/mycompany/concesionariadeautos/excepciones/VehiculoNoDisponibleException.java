/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package com.mycompany.concesionariadeautos.excepciones;

/**
 *
 * @author alfuxuns
 */
public class VehiculoNoDisponibleException extends RuntimeException {

    /**
     * Creates a new instance of <code>VehiculoNoDisponibleException</code>
     * without detail message.
     */
    public VehiculoNoDisponibleException() {
        
    }

    /**
     * Constructs an instance of <code>VehiculoNoDisponibleException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public VehiculoNoDisponibleException(String msg) {
        super(msg);
        
        
    }
}
