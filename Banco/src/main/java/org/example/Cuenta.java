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

    public void transferir(Cuenta cuentaDestino, double monto) {
        if (this.saldo >= monto)
        {
            this.saldo -= monto;
            cuentaDestino.depositar(monto);
            System.out.println("Transferencia realizada");
        } else {
            System.out.println("Saldo insuficiente");
        }
    }


    public void mostrar() {
        System.out.println("Nombre: " + this.nombre);
        //System.out.println("Direccion: " + this.direccion);
        //System.out.println("Tipo de cuenta: " + this.tipoCuenta);
        System.out.println("Saldo: $" + this.saldo);
        System.out.println("----------------------");
    }


}
