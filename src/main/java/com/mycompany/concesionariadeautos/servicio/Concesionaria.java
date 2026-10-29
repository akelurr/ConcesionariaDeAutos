/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.concesionariadeautos.servicio;

import com.mycompany.concesionariadeautos.modelo.*;
import com.mycompany.concesionariadeautos.interfaces.Rankeable;
import com.mycompany.concesionariadeautos.enums.MetodoPago;
import com.mycompany.concesionariadeautos.excepciones.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;



/**
 *
 * @author alfuxuns
 */
public class Concesionaria implements Serializable {

    private static long serialVersionUID = 1L;

    private final ArrayList<Vehiculo> inventario;
    private final ArrayList<Cliente> clientes;
    private final Map<Integer, Cliente> clientesPorId;
    private final ArrayList<Vendedor> vendedores;
    private final ArrayList<Venta> ventas;
    


    public Concesionaria(){
        inventario = new ArrayList<>();
        clientes = new ArrayList<>();
        clientesPorId = new HashMap<>();
        vendedores = new ArrayList<>();
        ventas = new ArrayList<>();

    }

    // Agregar un nuevo vehiculo al inventario
    public void registrarVehiculo(Vehiculo vehiculo){
        if (vehiculo == null){
            throw new IllegalArgumentException("El vehículo no puede ser nulo");
        }

        if (inventario.contains(vehiculo) || fueVendido(vehiculo)){ 
            throw new IllegalArgumentException("El vehículo ya fue registrado o vendido");
        }

        inventario.add(vehiculo);
    }

    // Crea y registra un cliente (devuelve su referencia)
    public Cliente registarCliente(String nombre, int edad, double dinero, double ingresoMensual){

        validarNombre(nombre);

        if (edad < 0 || !Double.isFinite(dinero) || dinero < 0 || !Double.isFinite(ingresoMensual) || ingresoMensual < 0){
            throw new IllegalArgumentException("Datos numéricos del cliente inválidos");
        }
        Cliente cliente = new Cliente(nombre, edad, dinero, ingresoMensual, new ArrayList<Venta>());

        clientes.add(cliente);
        clientesPorId.put(cliente.getId(), cliente);
        return cliente;
    }

    // Registra un cliente ya creado (sin duplicar su ID)
    public void registrarCliente (Cliente cliente) {
        if (cliente == null){
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }

        if (clientesPorId.containsKey(cliente.getId())){
            throw new IllegalArgumentException("Ya existe un cliente con ese ID");
        }

        clientes.add(cliente);
        clientesPorId.put(cliente.getId(), cliente);
    }

    // Crea un registra un vendedor
    public Vendedor registrarVendedor(String nombre, double sueldoBase){
        validarNombre(nombre);
        if(!Double.isFinite(sueldoBase) || sueldoBase < 0){
            throw new IllegalArgumentException("El sueldo base debe ser válido y no negativo");
        } 

        Vendedor vendedor = new Vendedor(nombre.trim(), sueldoBase);
        vendedores.add(vendedor);
        return vendedor;
    }


    // Registra un vendedor ya creado (sin duplicar su ID)
    public void registrarVendedor(Vendedor vendedor) {
        if (vendedor == null){
            throw new IllegalArgumentException("El vendedor no puede ser nulo");
        }
        for (Vendedor existente : vendedores){
            if (existente.getId() == vendedor.getId()){
                throw new IllegalArgumentException("Ya existe un vendedor con ese ID");
            }

        }
        vendedores.add(vendedor);
    }

    // Buscar un cliente por ID 
    public Cliente buscarClientePorId(int id){
        Cliente cliente = clientesPorId.get(id);
        if (cliente == null){
            throw new ClienteNoEncontradoException("No existe un cliente con ID " + id);
        }
        return cliente;
    } 
    


    // Buscar cliente por nombre
    public List<Cliente> buscarClientesPorNombre(String texto){
        validarNombre(texto);
        ArrayList<Cliente> resultado = new ArrayList<>();
        String consulta = texto.trim().toLowerCase();

        for (Cliente cliente : clientes){
            if (cliente.getNombre()!= null && cliente.getNombre().toLowerCase().contains(consulta)){
                resultado.add(cliente);
            }
        }
        return resultado;
    }
    
    // Buscar vendedor por ID
    
    public Vendedor buscarVendedorPorId(int id){
        for (Vendedor vendedor : vendedores){
            if (vendedor.getId() == id){
                return vendedor;
            }
        }
        throw new IllegalArgumentException("No existe un vendedor con ID " + id);
    }

    // Buscar vendedores por nombre
    public List<Vendedor> buscarVendedorPorNombre(String texto){
        validarNombre(texto);
        ArrayList<Vendedor> resultado = new ArrayList<>();

        String consulta = texto.trim().toLowerCase();

        for (Vendedor vendedor : vendedores){
            if (vendedor.getNombre() != null && vendedor.getNombre().toLowerCase().contains(consulta)){
                resultado.add(vendedor);
            }
        }
        return resultado;
    }

    // Filtrar el inventario por un tipo concreto
    public List<Vehiculo> buscarVehiculosPortipo(int opcion){

        ArrayList<Vehiculo> resultado = new ArrayList<>();

        switch (opcion){
            case 1:
                for(Vehiculo vehiculo: inventario){
                    if (AutoDiesel.class.isInstance(vehiculo)){
                        resultado.add(vehiculo);
                    }
                
                }
                break;
            case 2:
                for(Vehiculo vehiculo: inventario){
                    if (AutoElectrico.class.isInstance(vehiculo)){
                        resultado.add(vehiculo);
                    }
                
                }
                break;
            case 3:
                for(Vehiculo vehiculo: inventario){
                    if (AutoGasolina.class.isInstance(vehiculo)){
                        resultado.add(vehiculo);
                    }
                
                }
                break;
            default:
                throw new IllegalArgumentException("Indice invalido");
        }

        return resultado;

    }
    
    // Realizar una venta
    public Venta realizarVenta(Cliente cliente, Vendedor vendedor, Vehiculo vehiculo, MetodoPago metodoPago){

        if(cliente == null || !clientes.contains(cliente)){
            throw new IllegalArgumentException("EL cliente no esta registrado.");
        }

        if(vendedor == null || !vendedores.contains(vendedor)){
            throw new IllegalArgumentException("EL vendedor no esta registrado.");
        }

        if(vehiculo == null || !inventario.contains(vehiculo)){
            throw new IllegalArgumentException("El vehiculo no esta disponible.");
        }

        if(metodoPago == null){
            throw new IllegalArgumentException("Debe indicar un metodo de pago.");
        }

        double precio = vehiculo.precioFinal();
        if (!Double.isFinite(precio) || precio <= 0){
            throw new IllegalArgumentException("EL precio del vehiculo no es valido");
        }
        if (!cliente.puedePagar(precio)){
            throw new IllegalArgumentException("El cliente no tiene saldo suficiente");
        }

        Venta venta = new Venta(cliente, vendedor, vehiculo, metodoPago);
        cliente.descontarDinero(venta.getPrecioVenta());
        cliente.registrarCompra(venta);
        vendedor.registrarVenta(venta);
        ventas.add(venta);
        inventario.remove(vehiculo);
        return venta;
    }   

    // Muestra el historial de operaciones asociado a un cliente
    public List<Venta> obtenerHistorialCliente(int idCliente){
        return buscarClientePorId(idCliente).getHistorialCompras();
    }


    // Muestra el histoiral de operaciones asociado a un vendedor
    public List<Venta> obtenerHistorialVendedor(int idVendedor){
        return buscarVendedorPorId(idVendedor).getHistorialVentas();
    }

    // Obtiene el ranking de vendedores por importe vendido, de mayor a menor
    

    public List<Vendedor> obtenerRankingVendedores(){

        ArrayList<Vendedor> ranking = new ArrayList<>(vendedores);

        // Ordenamiento por seleccion

        for (int i = 0; i < ranking.size() - 1 ; i++){
            int mejor = i;
            for (int j = 1 +i; j <ranking.size(); j++){
                Rankeable candidato = ranking.get(j);
                Rankeable actual = ranking.get(mejor);
                if (candidato.obtenerPuntajeRanking() > actual.obtenerPuntajeRanking()){
                    mejor = j;
                }
            }
            if (mejor != i){
                Vendedor temporal = ranking.get(i);
                ranking.set(i, ranking.get(mejor));
                ranking.set(mejor, temporal);
            }

 
        }
        return ranking;
    }

    // Obtiene una copia ordenada del inventario por precio final ascendente.

    public List<Vehiculo> obtenerInventarioPorPrecio(){
        ArrayList<Vehiculo> resultado = new ArrayList<>(inventario);
        for (int i = 0; i < resultado.size() - 1; i++){
            int menor = i;
            for (int j = i +1; j < resultado.size(); j++){
                if (resultado.get(j).precioFinal() < resultado.get(menor).precioFinal()){
                    menor = j;
                }
            }
            if (menor != i) {
                Vehiculo temporal = resultado.get(i);
                resultado.set(i, resultado.get(menor));
                resultado.set(menor, temporal);
            }
        }
        return resultado;

    }
    
    // Devuelve el inventario
    public List<Vehiculo> getInventario() {
        return Collections.unmodifiableList(inventario);
    }

    // Devuelve los vendedores registrados
    
    public List<Vendedor> getVendedores(){
        return Collections.unmodifiableList(vendedores);
    }

    // Devuelve el historial completo de ventas
    public List<Venta> getVentas() {
        return Collections.unmodifiableList(ventas);
    }

    // Total facturado durante la instancia de la concesionaria
    public double getTotalVendido(){
        double total = 0;
        for (Venta venta : ventas){
            total += venta.getPrecioVenta();
        } 
        return total;
    }

    // Comprueba si un vehiculo aparece en el historial de ventas
    private boolean fueVendido(Vehiculo vehiculo){
        for (Venta venta : ventas){
            if (venta.getVehiculo() == vehiculo){
                return true;
            }
        }
        return false;
    }


    // Validar textos
    private void validarNombre(String nombre){
        if (nombre == null || nombre.isBlank()){
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
    }

    // Restaurar contadores

    public void restaurarContadores(){

        for (Vehiculo vehiculo : inventario){
            Vehiculo.actualizarSiguienteId(vehiculo.getId());
        }

        for (Cliente cliente : clientes){
            Cliente.actualizarSiguienteId(cliente.getId());
        }

        for (Vendedor vendedor : vendedores){
            Vendedor.actualizarSiguienteId(vendedor.getId());
        }

        for (Venta venta: ventas){
            Venta.actualizarSiguienteId(venta.getId());
            Vehiculo.actualizarSiguienteId(venta.getVehiculo().getId());
        }
    }    
}
