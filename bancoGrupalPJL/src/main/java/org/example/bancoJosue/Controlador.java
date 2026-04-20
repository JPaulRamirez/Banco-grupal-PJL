package org.example.bancoJosue;

import java.util.Scanner;

public class Controlador {

    private Banco banco;
    private Sesion sesion;
    private Menu menu;
    private Autenticacion autenticacion;
    private GestorCuenta gestorCuenta;

    private Scanner sc = new Scanner(System.in);

    public Controlador(Banco banco, Sesion sesion, Menu menu,
                       Autenticacion autenticacion,
                       GestorCuenta gestorCuenta) {

        this.banco = banco;
        this.sesion = sesion;
        this.menu = menu;
        this.autenticacion = autenticacion;
        this.gestorCuenta = gestorCuenta;
    }



    public void iniciar() {
        int opcion;

        do {
            menu.mostrar();
            System.out.print("Opción: ");
            opcion = leerInt();
            manejarOpcion(opcion);

        } while (opcion != 0);
    }


    // CONTROL GENERAL
    private void manejarOpcion(int opcion) {

        if (opcion == 0) {
            System.out.println("¡Hasta luego!");
            return;
        }

        if (opcion == 9 && sesion.logueado) {
            sesion.cerrar();
            return;
        }

        if (!sesion.logueado) {
            manejarSinLoguear(opcion);

        } else if (sesion.esAdmin()) {
            manejarAdmin(opcion);

        } else if (sesion.esUsuario()) {
            manejarUsuario(opcion);
        }
    }


    // SIN LOGIN

    private void manejarSinLoguear(int opcion) {
        switch (opcion) {
            case 1: loginUsuario(); break;
            case 2: loginAdmin(); break;
            case 3: banco.mostrarSoloSucursales(); break;
            case 4: crearCuenta(); break;
            default: System.out.println("Opción inválida.");
        }
    }



    private void manejarAdmin(int opcion) {
        switch (opcion) {
            case 1: menuCuentas(); break;
            case 2: banco.mostrarTodo(); break;
            default: System.out.println("Opción inválida.");
        }
    }

    private void menuCuentas() {
        System.out.println("\n-- CUENTAS --");
        System.out.println("1. Crear  2. Dar de baja");
        System.out.print("Opción: ");

        switch (leerInt()) {
            case 1: crearCuenta(); break;
            case 2: darDeBajaCuenta(); break;
            default: System.out.println("Opción inválida.");
        }
    }



    private void manejarUsuario(int opcion) {
        Cuenta cuenta = sesion.cuentaActiva;

        switch (opcion) {
            case 1:
                cuenta.verEstado();
                break;

            case 2:
                System.out.print("Monto a depositar: $");
                cuenta.depositar(leerDouble());
                break;

            case 3:
                System.out.print("Monto a retirar: $");
                cuenta.retirar(leerDouble());
                break;

            case 4:
                transferir(cuenta);
                break;

            default:
                System.out.println("Opción inválida.");
        }
    }


    // LOGIN

    private void loginUsuario() {
        System.out.print("DNI: ");
        int dni = leerInt();          // ← en lugar de sc.nextInt()

        System.out.print("Contraseña: ");
        String pass = sc.nextLine();

        Cuenta cuenta = autenticacion.loginUsuario(dni, pass);

        if (cuenta == null) {
            System.out.println("Credenciales inválidas.");
            return;
        }

        sesion.iniciarComoUsuario(cuenta);
        System.out.println("Bienvenido " + cuenta.titular.nombre);
    }

    private void loginAdmin() {
        System.out.print("Usuario: ");
        String user = sc.nextLine();

        System.out.print("Contraseña: ");
        String pass = sc.nextLine();

        if (!autenticacion.loginAdmin(user, pass)) {
            System.out.println("Credenciales incorrectas.");
            return;
        }

        sesion.iniciarComoAdmin();
        System.out.println("Bienvenido Admin");
    }



    private void crearCuenta() {
        banco.mostrarSoloSucursales();

        System.out.print("Sucursal: ");
        String sucursal = sc.nextLine();

        System.out.print("DNI: ");
        int dni = leerInt();

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Apellido: ");
        String apellido = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Contraseña: ");
        String pass = sc.nextLine();

        Usuario usuario = new Usuario(nombre, apellido, email, pass);

        boolean ok = gestorCuenta.crearCuenta(sucursal, dni, usuario);

        if (!ok) {
            System.out.println("No se pudo crear la cuenta.");
        } else {
            System.out.println("Cuenta creada.");
        }
    }

    private void darDeBajaCuenta() {
        banco.mostrarTodo();

        System.out.print("DNI de la cuenta: ");
        int dni = sc.nextInt();

        boolean ok = gestorCuenta.darDeBaja(dni);

        if (!ok) {
            System.out.println("Cuenta no encontrada.");
        } else {
            System.out.println("Cuenta dada de baja.");
        }
    }

    private void transferir(Cuenta origen) {
        System.out.print("DNI destino: ");
        Cuenta destino = gestorCuenta.buscarCuenta(sc.nextInt());

        if (destino == null) {
            System.out.println("Cuenta destino no encontrada.");
            return;
        }

        if (destino.dni == (origen.dni)) {
            System.out.println("No puede transferirse a sí mismo.");
            return;
        }

        System.out.print("Monto: $");
        origen.transferir(destino, leerDouble());
    }



    private int leerInt() {
        return Integer.parseInt(sc.nextLine());
    }

    private double leerDouble() {
        return Double.parseDouble(sc.nextLine());
    }
}