package org.example;
import org.example.Modelo.Admin;
import org.example.Modelo.Cuenta;
import java.util.ArrayList;
import java.util.List;

public class Sucursal {
    public String nombre;
    public Admin admin;
    public List<Cuenta> cuentas;

    public Sucursal(String nombre, Admin admin) {
        this.nombre = nombre;
        this.admin = admin;
        this.cuentas = new ArrayList<>();
    }

    public boolean login(String user, String contrasena) {
        if (admin.login(user, contrasena)) {
            System.out.println("Login exitoso en sucursal " + nombre);
            return true;
        } else {
            System.out.println("Credenciales incorrectas");
            return false;
        }
    }

    public void registrarCuenta(Cuenta cuenta) {
        Cuenta cuentaExistente = buscarPorDni(cuenta.dni);

        if (cuentaExistente != null) {
            System.out.println("Existe una cuenta con este DNI");
        } else {
            cuentas.add(cuenta);
            System.out.println("Cuenta creada en sucursal " + nombre);
        }
    }

    public void eliminarCuenta(Cuenta cuenta) {
        if (cuentas.remove(cuenta)) {
            System.out.println("Cuenta eliminada");
        } else {
            System.out.println("Cuenta no encontrada");
        }
    }

    public Cuenta buscarPorDni(int dni) {
        for (Cuenta c : cuentas) {
            if (c.dni == dni) {
                return c;
            }
        }
        return null;
    }

    public void depositar(int dni, double monto) {
        Cuenta cuenta = buscarPorDni(dni);

        if (cuenta != null) {
            cuenta.depositar(monto);
        } else {
            System.out.println("Cuenta no encontrada");
        }
    }

    public void transferir(int dniOrigen, int dniDestino, double monto) {

        Cuenta origen = buscarPorDni(dniOrigen);
        Cuenta destino = buscarPorDni(dniDestino);

        if (origen == null || destino == null) {
            System.out.println("Cuenta no encontrada");
            return;
        }

        origen.transferirCuenta(destino, monto);
    }
    public double consultarSaldoTotal() {
        double total = 0;

        for (Cuenta c : cuentas) {
            total += c.saldo;
        }

        return total;
    }

    public void mostrarCuentas() {
        System.out.println("=== Sucursal: " + nombre + " ===");

        for (Cuenta c : cuentas) {
            c.mostrar();
        }
    }

}
