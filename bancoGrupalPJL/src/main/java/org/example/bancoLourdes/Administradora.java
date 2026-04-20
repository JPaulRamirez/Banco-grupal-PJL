package org.example.bancoLourdes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class
Administradora {
    Map<String, Cuenta> mapaCuentas = new HashMap<>();
    ArrayList<String> historial = new ArrayList<>();
    List<SucursalSimple> sucursales = new ArrayList<>();

    public void registrarCuenta(Persona p, String tipo, String cbu) {
        Cuenta nueva = new Cuenta(p, tipo, cbu);
        mapaCuentas.put(cbu, nueva);
        System.out.println(">>> Cuenta dada de alta para: " + p.nombre);
    }

    public void transferir(String cbuOrigen, String cbuDestino, double monto) {
        Cuenta origen = buscar(cbuOrigen);
        Cuenta destino = buscar(cbuDestino);

        if (origen != null && destino != null) {
            if (origen.saldo >= monto) {
                origen.saldo -= monto;
                destino.saldo += monto;
                historial.add("Transferencia: $" + monto + " de " + origen.titular.nombre + " a " + destino.titular.nombre);
                System.out.println(">>> Transferencia exitosa.");
            } else {
                System.out.println("No hay fondos suficientes");
            }
        } else {
            System.out.println("Error: Uno de los CBUs no existe.");
        }
    }

    public void balance() {
        System.out.println("\n--- BALANCE GENERAL DE CUENTAS ---");
        for (Cuenta c : mapaCuentas.values()) {
            System.out.println(c);
        }
        System.out.println("\n--- HISTORIAL DE MOVIMIENTOS ---");
        if (historial.isEmpty()) {
            System.out.println("- No hay transferencias registradas.");
        } else {
            for (String h : historial) {
                System.out.println("- " + h);
            }
        }
    }

    public Cuenta buscar(String cbu) {

        return mapaCuentas.get(cbu);
    }

    public void cargarDatosPrueba() {
        mapaCuentas.clear();
        historial.clear();
        sucursales.clear();

        SucursalSimple Centro = new SucursalSimple("Centro");
        SucursalSimple Norte = new SucursalSimple("Norte");

        Persona p1 = new Persona.Builder().setNombre("Lourdes").setDni("2001").setDomicilio("Calle 1").build();
        Persona p2 = new Persona.Builder().setNombre("Ana").setDni("2002").setDomicilio("Calle 2").build();

        Cuenta c1 = new Cuenta(p1, "Ahorro", "001");
        Cuenta c2 = new Cuenta(p2, "Corriente", "002");

        c1.saldo = 4000;
        c2.saldo = 7500;

        Centro.cuentas.add(c1);
        Norte.cuentas.add(c2);

        mapaCuentas.put("001", c1);
        mapaCuentas.put("002", c2);

        sucursales.add(Centro);
        sucursales.add(Norte);
    }

    public SucursalSimple buscarSucursal(String nombre) {
        for (SucursalSimple s : sucursales) {
            if (s.nombre.equalsIgnoreCase(nombre)) {
                return s;
            }
        }
        return null;
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
        if (s == null) throw new IllegalArgumentException("Sucursal no encontrada");
        Cuenta c = s.buscar(dni);
        if (c == null) throw new IllegalArgumentException("Cliente no encontrado");
        c.saldo += monto;
    }

    public void debitar(String sucursal, int dni, double monto) {
        SucursalSimple s = buscarSucursal(sucursal);
        if (s == null) throw new IllegalArgumentException("Sucursal no encontrada");
        Cuenta c = s.buscar(dni);
        if (c == null) throw new IllegalArgumentException("Cliente no encontrado");
        if (c.saldo < monto) throw new IllegalArgumentException("Saldo insuficiente");
        c.saldo -= monto;
    }
    public String getDatos() {
        String texto = "BancoLourdes: ";

        for (int i = 0; i < sucursales.size(); i++) {
            SucursalSimple sucursal = sucursales.get(i);
            texto += sucursal.nombre + " -> DNI ";

            for (int j = 0; j < sucursal.cuentas.size(); j++) {
                texto += sucursal.cuentas.get(j).titular.dni;
                if (j < sucursal.cuentas.size() - 1) {
                    texto += ", ";
                }
            }

            if (i < sucursales.size() - 1) {
                texto += " | ";
            }
        }

        return texto;
    }
}
