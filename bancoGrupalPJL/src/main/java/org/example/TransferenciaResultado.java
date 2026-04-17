package org.example;

public class TransferenciaResultado {
    private final boolean exitosa;
    private final String mensaje;

    private TransferenciaResultado(boolean exitosa, String mensaje) {
        this.exitosa = exitosa;
        this.mensaje = mensaje;
    }

    public static TransferenciaResultado ok(String mensaje) {
        return new TransferenciaResultado(true, mensaje);
    }

    public static TransferenciaResultado error(String mensaje) {
        return new TransferenciaResultado(false, mensaje);
    }

    public boolean isExitosa() {
        return exitosa;
    }

    public String getMensaje() {
        return mensaje;
    }
}
