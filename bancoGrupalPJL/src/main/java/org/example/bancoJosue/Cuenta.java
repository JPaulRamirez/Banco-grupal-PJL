package org.example.bancoJosue;

import java.util.ArrayList;
import java.util.List;

public class Cuenta {
    int dni;
    String tipo;
    double saldo;
    boolean activa;
    String nombreSucursal;
    Usuario titular;

    List<String> movimientos = new ArrayList<String>();

    public Cuenta(int dni, String tipo, String nombreSucursal, Usuario titular) {
        this.dni = dni;
        this.tipo = tipo;
        this.saldo = 0;
        this.nombreSucursal = nombreSucursal;
        this.titular = titular;
        this.activa = true;
        movimientos.add("Apertura de cuenta.");
    }

    public void depositar(double monto) {
        if (!activa) {
            System.out.println("La cuenta esta dada de baja. No se puede operar.");
            return;
        }
        saldo += monto;
        movimientos.add("Deposito: +$" + monto + "  |  Saldo: $" + saldo);
        System.out.println("Deposito exitoso. Saldo actual: $" + saldo);
    }

    public void acreditar(double monto) {
        if (!activa) {
            throw new IllegalStateException("La cuenta destino esta dada de baja.");
        }
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a acreditar debe ser mayor que cero.");
        }
        saldo += monto;
        movimientos.add("Transferencia interbancaria recibida: +$" + monto + "  |  Saldo: $" + saldo);
    }

    public void debitar(double monto) {
        if (!activa) {
            throw new IllegalStateException("La cuenta origen esta dada de baja.");
        }
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a debitar debe ser mayor que cero.");
        }
        if (monto > saldo) {
            throw new IllegalStateException("Saldo insuficiente para realizar la transferencia.");
        }
        saldo -= monto;
        movimientos.add("Transferencia interbancaria enviada: -$" + monto + "  |  Saldo: $" + saldo);
    }

    public void retirar(double monto) {
        if (!activa) {
            System.out.println("La cuenta esta dada de baja. No se puede operar.");
            return;
        }
        if (monto > saldo) {
            System.out.println("Saldo insuficiente. Saldo actual: $" + saldo);
            return;
        }
        saldo -= monto;

        movimientos.add("Retiro: -$" + monto + "  |  Saldo: $" + saldo);
        System.out.println("Retiro exitoso. Saldo actual: $" + saldo);
    }

    public void transferir(Cuenta destino, double monto) {
        if (!activa) {
            System.out.println("La cuenta origen esta dada de baja. No se puede transferir.");
            return;
        }
        if (!destino.activa) {
            System.out.println("La cuenta destino esta dada de baja. No se puede transferir.");
            return;
        }
        if (monto > saldo) {
            System.out.println("Saldo insuficiente para transferir.");
            return;
        }

        saldo -= monto;
        destino.saldo += monto;

        movimientos.add("Transferencia a DNI " + destino.dni + ": -$" + monto + "  |  Saldo: $" + saldo);
        destino.movimientos.add("Transferencia de DNI " + dni + ": +$" + monto + "  |  Saldo: $" + destino.saldo);

        System.out.println("Transferencia exitosa de $" + monto + " a DNI " + destino.dni);
    }

    public void darDeBaja() {
        if (!activa) {
            System.out.println("La cuenta ya estaba dada de baja.");
            return;
        }
        activa = false;
        movimientos.add("Cuenta dada de baja.");
        System.out.println("Cuenta de DNI " + dni + " dada de baja.");
    }

    public void verEstado() {
        System.out.println("-----------------------------");
        System.out.println("DNI      : " + dni);
        System.out.println("Sucursal : " + nombreSucursal);
        System.out.println("Tipo     : " + tipo);
        System.out.println("Saldo    : $" + saldo);
        System.out.println("Estado   : " + (activa ? "Activa" : "Dada de baja"));
        titular.mostrar();
        System.out.println("Movimientos:");
        for (String m : movimientos) {
            System.out.println("  - " + m);
        }
        System.out.println("-----------------------------");
    }

    public void verResumen() {
        System.out.println("  [DNI: " + dni + "] " + tipo + " | $" + saldo
                + " | " + titular.nombre + " " + titular.apellido
                + " | " + (activa ? "Activa" : "BAJA"));
    }
}
