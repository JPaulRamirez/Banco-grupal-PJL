package org.example.bancoPaul.Modelo;
import org.example.bancoPaul.enums.Rol;
import org.example.bancoPaul.enums.TipoCuenta;
import org.example.bancoPaul.strategy.TipoCuentaStrategy;

public class Cuenta extends Usuario{
    public String direccion;
    public TipoCuenta tipoCuenta;
    public double saldo;
    private TipoCuentaStrategy estrategia;

    public Cuenta(String nombre, String direccion, TipoCuenta tipoCuenta, int dni, TipoCuentaStrategy estrategia) {
        super(nombre, dni, Rol.CLIENTE);

        this.direccion = direccion;
        this.tipoCuenta = tipoCuenta;
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
    public void extraerSaldo(double monto){
        if(monto<=0){
            System.out.println("Monto invalido");
            return;
        }
        if(!estrategia.puedeTransferir(saldo,monto))
        {
            System.out.println("No esta permitido o saldo insuficiente");
        }
        saldo-=monto;
        System.out.println("Extracion realizada : $"+monto);
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
