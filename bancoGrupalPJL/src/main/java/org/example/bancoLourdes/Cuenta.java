package org.example.bancoLourdes;

public class Cuenta {
    Persona titular;
    String tipo, cbu, estado;
    double saldo;

    public Cuenta(Persona titular, String tipo, String cbu) {
        this.titular = titular;
        this.tipo = tipo;
        this.cbu = cbu;
        this.saldo = 0.0;
        this.estado = "ACTIVA";
    }

    public void depositar(double monto) {
        this.saldo += monto;
        System.out.println(">>> Deposito exitoso.");
    }

    public void acreditar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a acreditar debe ser mayor que cero.");
        }
        this.saldo += monto;
    }

    public void debitar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a debitar debe ser mayor que cero.");
        }
        if (!"ACTIVA".equalsIgnoreCase(estado)) {
            throw new IllegalStateException("La cuenta no esta activa.");
        }
        if (monto > this.saldo) {
            throw new IllegalStateException("No hay fondos suficientes.");
        }
        this.saldo -= monto;
    }

    public void retirar(double monto) {
        if (monto <= this.saldo) {
            this.saldo -= monto;
            System.out.println(">>> Retiro confirmado.");
        } else {
            System.out.println("No hay fondos suficientes");
        }
    }

    @Override
    public String toString() {
        return "Tipo: " + tipo + " | ESTADO: " + estado + " | Titular: " + titular.toString() + " | CBU: " + cbu + " | Saldo: $" + saldo;
    }
}
