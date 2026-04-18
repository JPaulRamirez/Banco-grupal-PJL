package org.example.bancoLourdes;

import java.util.ArrayList;
import java.util.List;

public class SucursalSimple {
    String nombre;
    List<Cuenta> cuentas;

    public SucursalSimple(String nombre) {
        this.nombre = nombre;
        this.cuentas = new ArrayList<>();
    }

    public Cuenta buscar(int dni) {
        for (Cuenta c : cuentas) {
            if (Integer.parseInt(c.titular.dni) == dni) {
                return c;
            }
        }
        return null;
    }
}
