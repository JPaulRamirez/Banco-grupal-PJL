package org.example.bancoJosue;

import java.util.ArrayList;
import java.util.List;

public class Banco {



    String nombre;
    List<Sucursal> sucursales;


    public Banco(String nombre) {
        this.nombre     = nombre;
        this.sucursales = new ArrayList<Sucursal>();

        sucursales.add(new Sucursal("Sucursal 1"));
        sucursales.add(new Sucursal("Sucursal 2"));
        sucursales.add(new Sucursal("Sucursal 3"));


        Sucursal s1 = sucursales.get(0);
        Sucursal s2 = sucursales.get(1);

        s1.cuentas.add(new Cuenta(
                11111111,
                "Ahorro",
                s1.nombre,
                new Usuario("Juan", "Perez", "mail", "1234")
        ));

        s2.cuentas.add(new Cuenta(
                22222222,
                "Corriente",
                s2.nombre,
                new Usuario("Ana", "Gomez", "mail", "1234")
        ));

        // opcional: darles saldo
        s1.cuentas.get(0).saldo = 2000;
        s2.cuentas.get(0).saldo = 3000;
    }




    public Sucursal buscarSucursal(String nombre) {
        for (Sucursal s : sucursales) {
            if (s.nombre.equalsIgnoreCase(nombre)) {
                return s;
            }
        }
        return null;
    }


    public Cuenta buscarCuenta(int dni) {
        for (Sucursal s : sucursales) {
            Cuenta c = s.buscarCuenta(dni);   // Le pregunta a cada sucursal
            if (c != null) return c;           // Si la encontró, la devuelve
        }
        return null;   // No se encontró en ninguna sucursal
    }



    public void mostrarSoloSucursales() {
        System.out.println("===== " + nombre + " =====");
        System.out.println("Sucursales disponibles:");
        for (Sucursal s : sucursales) {
            System.out.println("  • " + s.nombre);
        }
    }


    public void mostrarTodo() {
        System.out.println("===== " + nombre + " =====");

        if (sucursales.isEmpty()) {
            System.out.println("Sin sucursales registradas.");
            return;
        }

        for (Sucursal s : sucursales) {
            s.mostrar();
        }
    }
}