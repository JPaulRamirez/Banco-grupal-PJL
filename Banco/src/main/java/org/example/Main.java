package org.example;

public class Main {
    public static void main( String[] args) {
        Banco banco = new Banco();
        banco.crearCuentas();
        System.out.println("Cuentas creadas");

        banco.cuenta1.depositar(1000);
        banco.cuenta2.depositar(500);
        banco.cuenta3.depositar(2000);
        System.out.println("Depósitos realizados");


        banco.cuenta1.transferir(banco.cuenta2, 1000);

        banco.mostrarBalance();

        banco.modificarCuenta(banco.cuenta2, "Juan", "Calle real 234");
        System.out.println("Cuenta modificada");


        banco.mostrarBalance();
    }
}
