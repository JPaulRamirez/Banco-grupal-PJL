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
