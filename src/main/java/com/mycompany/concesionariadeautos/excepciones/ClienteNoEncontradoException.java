/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package com.mycompany.concesionariadeautos.excepciones;

/**
 *
 * @author alfuxuns
 */
public class ClienteNoEncontradoException extends RuntimeException {
    
 
    /**
     * Creates a new instance of <code>ClienteNoEncontradoException</code>
     * without detail message.
     */
    public ClienteNoEncontradoException() {
      

    }

    /**
     * Constructs an instance of <code>ClienteNoEncontradoException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public ClienteNoEncontradoException(String msg) {
        super(msg);
    }
}
