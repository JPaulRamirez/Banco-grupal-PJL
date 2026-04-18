package org.example.bancoPaul;
import org.example.bancoPaul.Modelo.Cuenta;
import org.example.bancoPaul.Modelo.Admin;
import org.example.bancoPaul.enums.Rol;
import org.example.bancoPaul.enums.TipoCuenta;
import org.example.bancoPaul.strategy.CuentaAhorro;
import org.example.bancoPaul.strategy.CuentaCorriente;

import java.util.List;
import java.util.ArrayList;

public class Banco {
    private final List<Sucursal> sucursales = new ArrayList<>();

    public void inicializarBanco() {

        Admin admin1 = new Admin("Paul", 12345678, Rol.ADMIN, "paul@mail.com", "Paul", "Paul123" );
        Admin admin2 = new Admin( "Hector", 87654321, Rol.ADMIN, "ana@mail.com", "Hector", "Hector123" );
        Sucursal suc1 = new Sucursal("Centro", admin1);
        Sucursal suc2 = new Sucursal("Norte", admin2);
        // Sucursal Centro
        suc1.registrarCuenta(new Cuenta("Pepito", "Calle 123", TipoCuenta.AHORRO, 1, new CuentaAhorro()));
        suc1.registrarCuenta(new Cuenta("Ana", "Calle 456", TipoCuenta.CORRIENTE, 2, new CuentaCorriente()));

        // Sucursal Norte
        suc2.registrarCuenta(new Cuenta("Luis", "Calle 789", TipoCuenta.AHORRO, 3, new CuentaAhorro()));
        suc2.registrarCuenta(new Cuenta("Maria", "Calle 123", TipoCuenta.CORRIENTE, 4, new CuentaCorriente()));

        sucursales.add(suc1);
        sucursales.add(suc2);

        System.out.println("Banco inicializado con 2 sucursales");
    }

    public void cargarDatosPrueba() {
        if (buscarSucursal("Centro") == null) {
            inicializarBanco();
        }

        buscarSucursal("Centro").depositar(1, 7000);
        buscarSucursal("Centro").depositar(2, 5000);
        buscarSucursal("Norte").depositar(3, 9000);
        buscarSucursal("Norte").depositar(4, 2000);
    }

    public Sucursal buscarSucursal(String nombre) {
        for (Sucursal s : sucursales) {
            if (s.nombre.equalsIgnoreCase(nombre)) {
                return s;
            }
        }
        return null;
    }

    public boolean existeCliente(String sucursal, int dni) {
        Sucursal s = buscarSucursal(sucursal);
        if (s == null) return false;
        return s.buscarPorDni(dni) != null;
    }

    public double consultarSaldo(String sucursal, int dni) {
        Sucursal s = buscarSucursal(sucursal);
        if (s == null) throw new IllegalArgumentException("Sucursal no encontrada");
        Cuenta c = s.buscarPorDni(dni);
        if (c == null) throw new IllegalArgumentException("Cliente no encontrado");
        return c.saldo;
    }

    public void acreditar(String sucursal, int dni, double monto) {
        Sucursal s = buscarSucursal(sucursal);
        if (s == null) throw new IllegalArgumentException("Sucursal no encontrada");
        Cuenta c = s.buscarPorDni(dni);
        if (c == null) throw new IllegalArgumentException("Cliente no encontrado");
        c.saldo += monto;
    }

    public void debitar(String sucursal, int dni, double monto) {
        Sucursal s = buscarSucursal(sucursal);
        if (s == null) throw new IllegalArgumentException("Sucursal no encontrada");
        Cuenta c = s.buscarPorDni(dni);
        if (c == null) throw new IllegalArgumentException("Cliente no encontrado");
        if (c.saldo < monto) throw new IllegalArgumentException("Saldo insuficiente");
        c.saldo -= monto;
    }

    public void mostrarSucursales() {
        System.out.println("=== SUCURSALES DISPONIBLES ===");

        for (Sucursal s : sucursales) {
            System.out.println("- " + s.nombre);
        }
    }
    public Cuenta buscarCuentaPorDni(int dni) {

        for (Sucursal s : sucursales) {
            Cuenta c = s.buscarPorDni(dni);
            if (c != null) {
                return c;
            }
        }
        return null;
    }

}
