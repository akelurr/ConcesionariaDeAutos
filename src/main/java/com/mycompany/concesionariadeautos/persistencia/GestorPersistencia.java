/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.concesionariadeautos.persistencia;

import com.mycompany.concesionariadeautos.servicio.Concesionaria;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author alfuxuns
 */
public class GestorPersistencia {


    // Ubicacion del archivo de datos.
    private static final String RUTA_ARCHIVO = "data/concesionaria.dat";

    //Guarda el estado de la concesionaria en un archivo.
    public static void guardar(Concesionaria concesionaria)
            throws IOException {

        if (concesionaria == null) {
            throw new IllegalArgumentException("La concesionaria no puede ser nula.");
        }

        File archivo = new File(RUTA_ARCHIVO);

        // Crear el directorio si no existe.
        File directorio = archivo.getParentFile();

        if (!directorio.exists() && !directorio.mkdirs()) {
            throw new IOException("No se pudo crear el directorio de datos.");
        }

        // Guardar el objeto completo.
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(archivo))) {
            salida.writeObject(concesionaria);
        }
    }

    // Recupera el estado de la concesionaria.
    //
    // Si no existe el archivo, crea una concesionaria
    // nueva y vacia.

    public static Concesionaria cargar()
            throws IOException, ClassNotFoundException {

        File archivo = new File(RUTA_ARCHIVO);

        // Primera ejecucion: aun no existen datos.
        if (!archivo.exists()) {
            return new Concesionaria();
        }

        // Recuperar el objeto almacenado.
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivo))) {

            Object objeto = entrada.readObject();

            if (!(objeto instanceof Concesionaria)) {
                throw new IOException("El archivo no contiene una concesionaria valida.");
            }

            return (Concesionaria) objeto;
        }
    }

    //Comprueba si existe un archivo de datos.
    public static boolean existenDatos() {
        File archivo = new File(RUTA_ARCHIVO);
        return archivo.isFile();
    }
}
