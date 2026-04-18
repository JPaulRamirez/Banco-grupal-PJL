package org.example.bancoLourdes;

import org.example.IBanco;

import java.util.ArrayList;
import java.util.List;

public class BancoLourdesSimple implements IBanco {
    List<SucursalSimple> sucursales;

    public BancoLourdesSimple() {
        sucursales = new ArrayList<>();

        SucursalSimple centro = new SucursalSimple("Centro");
        SucursalSimple norte = new SucursalSimple("Norte");

        Persona p1 = new Persona.Builder().setNombre("Lourdes").setDni("2001").setDomicilio("Calle 1").build();
        Persona p2 = new Persona.Builder().setNombre("Ana").setDni("2002").setDomicilio("Calle 2").build();

        Cuenta c1 = new Cuenta(p1, "Ahorro", "001");
        Cuenta c2 = new Cuenta(p2, "Corriente", "002");

        c1.saldo = 4000;
        c2.saldo = 7500;

        centro.cuentas.add(c1);
        norte.cuentas.add(c2);

        sucursales.add(centro);
        sucursales.add(norte);
    }

    public String getNombre() {
        return "BancoLourdes";
    }

    public boolean existeCliente(String sucursal, int dni) {
        SucursalSimple s = buscarSucursal(sucursal);
        if (s == null) return false;
        return s.buscar(dni) != null;
    }

    public double consultarSaldo(String sucursal, int dni) {
        SucursalSimple s = buscarSucursal(sucursal);
        if (s == null) throw new IllegalArgumentException("Sucursal no encontrada");
        Cuenta c = s.buscar(dni);
        if (c == null) throw new IllegalArgumentException("Cliente no encontrado");
        return c.saldo;
    }

    public void acreditar(String sucursal, int dni, double monto) {
        SucursalSimple s = buscarSucursal(sucursal);
        Cuenta c = s.buscar(dni);
        c.saldo += monto;
    }

    public void debitar(String sucursal, int dni, double monto) {
        SucursalSimple s = buscarSucursal(sucursal);
        Cuenta c = s.buscar(dni);
        if (c.saldo < monto) throw new IllegalArgumentException("Saldo insuficiente");
        c.saldo -= monto;
    }

    public SucursalSimple buscarSucursal(String nombre) {
        for (SucursalSimple s : sucursales) {
            if (s.nombre.equalsIgnoreCase(nombre)) {
                return s;
            }
        }
        return null;
    }
}
