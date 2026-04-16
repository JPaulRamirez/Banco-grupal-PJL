package org.example.menu;

import org.example.Banco;

import java.util.Scanner;
public class MenuPrincipal {
    private Banco banco;
    private Scanner sc;

    public MenuPrincipal() {
        this.banco = new Banco();
        this.banco.inicializarBanco();
        this.sc = new Scanner(System.in);
    }
    public void iniciar() {

        int opcion = 0;

        while (opcion != 3) {

            System.out.println("\n=== SISTEMA BANCO ===");
            System.out.println("1. Ingresar como ADMIN");
            System.out.println("2. Ingresar como CLIENTE");
            System.out.println("3. Salir");

            System.out.print("\nSeleccione una opcion: ");

            if (sc.hasNextInt()) {
                opcion = sc.nextInt();

                switch (opcion) {
                    case 1:
                        MenuAdmin menuAdmin = new MenuAdmin(banco, sc);
                        menuAdmin.iniciar();
                        break;
                    case 2:
                        MenuCliente menuCliente = new MenuCliente(banco, sc);
                        menuCliente.iniciar();
                        break;
                    case 3:
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opcion invalida.");
                }

            } else {
                System.out.println("Error: Debes ingresar un numero.");
                sc.next();
            }
        }
        sc.close();
    }
}
