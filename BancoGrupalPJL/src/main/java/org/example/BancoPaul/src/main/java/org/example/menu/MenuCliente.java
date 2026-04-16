package org.example.menu;
import org.example.Banco;
import org.example.Modelo.Cuenta;
import org.example.Sucursal;

import java.util.Scanner;

public class MenuCliente {
    private Banco banco;
    private Scanner sc;

    public MenuCliente(Banco banco, Scanner sc) {
        this.banco = banco;
        this.sc = sc;
    }

    public void iniciar() {
        banco.mostrarSucursales();
        System.out.print("Ingrese sucursal: ");
        String nombre = sc.next();

        Sucursal sucursal = banco.buscarSucursal(nombre);

        if (sucursal == null) {
            System.out.println("Sucursal no existe");
            return;
        }

        System.out.print("Ingrese DNI: ");
        int dni = sc.nextInt();

        Cuenta cuenta = sucursal.buscarPorDni(dni);

        if (cuenta == null) {
            System.out.println("Cuenta no encontrada");
            return;
        }

        int opcion = 0;

        while (opcion != 5) {

            System.out.println("\n=== MENU CLIENTE ===");
            System.out.println("1. Ver saldo");
            System.out.println("2. Depositar");
            System.out.println("3. Extraer saldo");
            System.out.println("4. Transferir");
            System.out.println("5. Salir");
            System.out.print("\nSeleccione una opcion: ");
            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
                switch (opcion)
                {
                    case 1:
                        cuenta.mostrar();
                        break;

                    case 2:
                        System.out.print("Monto: ");
                        double monto = sc.nextDouble();
                        cuenta.depositar(monto);
                        break;

                    case 3:
                        System.out.print("Monto: ");
                        double montoExtaer = sc.nextDouble();
                        cuenta.extraerSaldo(montoExtaer);
                        break;
                    case 4:
                        System.out.print("DNI destino: ");
                        int dniDestino = sc.nextInt();

                        Cuenta destino = sucursal.buscarPorDni(dniDestino);

                        if (destino != null) {
                            System.out.print("Monto: ");
                            double montoT = sc.nextDouble();
                            cuenta.transferirCuenta(destino, montoT);
                        }
                        break;
                    case 5:
                        System.out.println("Saliendo del menu cliente...");
                        break;
                    default:
                        System.out.println("Opcion invalida.");
                }
            }else {
                System.out.println("Error: Debes ingresar un número.");
                sc.next();
            }
        }
    }
}
