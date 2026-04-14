package org.example;
import org.example.strategy.TipoCuentaStrategy;

public class Cuenta {
    public String nombre;
    public String direccion;
    public String tipoCuenta;
    public int dni;
    public double saldo;
    private TipoCuentaStrategy estrategia;

    public Cuenta(String nombre, String direccion, String tipoCuenta, int dni, TipoCuentaStrategy estrategia) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.tipoCuenta = tipoCuenta;
        this.dni = dni;
        this.saldo = 0;
        this.estrategia = estrategia;
    }

    public void depositar(double monto) {
        if(monto>0)
        {
            this.saldo += monto;
            System.out.println("Te despositaron: "+monto +" Cuenta: "+this.nombre);
            System.out.println("----------------------");
        }
        else{
            System.out.println("El monto debe ser mayor a 0");
        }

    }

    public void transferirCuenta(Cuenta destino, double monto) {

        if (monto <= 0) {
            System.out.println("Monto inválido");
            return;
        }
        if (!estrategia.puedeTransferir(saldo, monto)) {
            System.out.println("No permitido según tipo de cuenta o supera el limite de descubierto");
            return;
        }

        saldo -= monto;
        destino.saldo += monto;

        System.out.println("Transferencia realizada");
    }


    public void mostrar() {
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Direccion: " + this.direccion);
        System.out.println("Tipo de cuenta: " + this.tipoCuenta);
        System.out.println("Dni: " + this.dni);
        System.out.println("Saldo: $" + this.saldo);
        System.out.println("----------------------");
    }


}
