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
public class MenuReportes {
    private final Concesionaria concesionaria;

    public MenuReportes(Concesionaria concesionaria) {
        this.concesionaria = concesionaria;
    }

    void mostrarRanking() {
        System.out.println("\n--- Ranking de vendedores ---");
        List<Vendedor> ranking = concesionaria.obtenerRankingVendedores();
        if (ranking.isEmpty()) System.out.println("No hay vendedores registrados.");
        for (int i = 0; i < ranking.size(); i++) {
            System.out.println((i + 1) + ". " + ranking.get(i).obtenerDescripcionRanking());
        }
    }

    void mostrarReporte() {
        System.out.println("\n--- Reporte general ---");
        System.out.println("Vehículos disponibles: " + concesionaria.getInventario().size());
        System.out.println("Vendedores registrados: " + concesionaria.getVendedores().size());
        System.out.println("Ventas realizadas: " + concesionaria.getVentas().size());
        System.out.printf("Total facturado: %.2f%n", concesionaria.getTotalVendido());
    }

}

