package org.example.bancoLourdes;

import java.util.ArrayList;
import java.util.List;

public class Sucursal {
    final String nombre;
    final List<Cuenta> cuentas = new ArrayList<>();

    public Sucursal(String nombre) {
        this.nombre = nombre;
    }

    public void registrarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    public Cuenta buscarPorDni(int dni) {
        for (Cuenta cuenta : cuentas) {
            if (Integer.parseInt(cuenta.titular.dni) == dni) {
                return cuenta;
            }
        }
        return null;
    }
}
