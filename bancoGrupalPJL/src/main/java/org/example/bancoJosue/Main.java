package org.example.bancoJosue;

public class Main {

    public static void main(String[] args) {

        Banco banco = new Banco("Banco");
        Sesion sesion = new Sesion();
        Menu menu = new Menu(sesion);

        Autenticacion autenticacion = new Autenticacion(banco);
        GestorCuenta gestorCuenta = new GestorCuenta(banco);

        Controlador controlador = new Controlador(
                banco,
                sesion,
                menu,
                autenticacion,
                gestorCuenta
        );

        controlador.iniciar();
    }
}