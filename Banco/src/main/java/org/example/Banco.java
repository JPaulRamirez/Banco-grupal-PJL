package org.example;

public class Banco {
    Cuenta cuenta1;
    Cuenta cuenta2;
    Cuenta cuenta3;

    public void crearCuentas()
    {
         cuenta1 = new Cuenta("Paul","Calle falsa 123","Ahorro");
         cuenta2 = new Cuenta("Leonel","Calle falsa 444","Ahorro");
         cuenta3 = new Cuenta("Ariana","Calle falsa 233","Corriente");
        System.out.println("----------- Cuentas creadas -----------");
    }
    public void modificarCuenta(Cuenta cuentaElegida,String nombre,String nuevaDirreccion)
    {
        cuentaElegida.nombre = nombre;
        cuentaElegida.direccion = nuevaDirreccion;
        System.out.println("----------- Cuenta modificada -----------");
    }

    public void mostrarBalance() {
        System.out.println("----- BALANCE GENERAL ----- ");
        cuenta1.mostrar();
        cuenta2.mostrar();
        cuenta3.mostrar();
    }
}
