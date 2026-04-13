package org.example;

public class Cuenta {
    public String nombre;
    public String direccion;
    public String tipoCuenta;
    public double saldo;

    public Cuenta(String nombre, String direccion, String tipoCuenta) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.tipoCuenta = tipoCuenta;
        this.saldo = 0;
    }

    public void depositar(double monto) {
        saldo += monto;
    }

    public void transferir(Cuenta destino, double monto) {
        if (this.saldo >= monto) {
            this.saldo -= monto;
            destino.saldo += monto;
            System.out.println("Transferencia realizada");
        } else {
            System.out.println("Saldo insuficiente");
        }
    }

    public void mostrar() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Direccion: " + direccion);
        System.out.println("Tipo: " + tipoCuenta);
        System.out.println("Saldo: $" + saldo);
        System.out.println("----------------------");
    }
}
