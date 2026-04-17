package org.example;

import java.util.HashMap;
import java.util.Map;

public class MediadorBancario {
    private final Map<String, IBanco> bancosRegistrados = new HashMap<>();

    public void registrarBanco(IBanco banco) {
        bancosRegistrados.put(normalizarClave(banco.getNombre()), banco);
    }

    public TransferenciaResultado transferir(
            String bancoOrigen,
            String sucursalOrigen,
            int dniOrigen,
            String bancoDestino,
            String sucursalDestino,
            int dniDestino,
            double monto
    ) {
        if (monto <= 0) {
            return TransferenciaResultado.error("El monto debe ser mayor que cero.");
        }

        IBanco origen = bancosRegistrados.get(normalizarClave(bancoOrigen));
        IBanco destino = bancosRegistrados.get(normalizarClave(bancoDestino));

        if (origen == null) {
            return TransferenciaResultado.error("El banco origen no esta registrado.");
        }
        if (destino == null) {
            return TransferenciaResultado.error("El banco destino no esta registrado.");
        }
        if (!origen.existeCliente(sucursalOrigen, dniOrigen)) {
            return TransferenciaResultado.error("No se encontro el cliente origen en " + bancoOrigen + ".");
        }
        if (!destino.existeCliente(sucursalDestino, dniDestino)) {
            return TransferenciaResultado.error("No se encontro el cliente destino en " + bancoDestino + ".");
        }

        try {
            origen.debitar(sucursalOrigen, dniOrigen, monto);
            destino.acreditar(sucursalDestino, dniDestino, monto);
        } catch (IllegalArgumentException | IllegalStateException ex) {
            return TransferenciaResultado.error(ex.getMessage());
        }

        String mensaje = String.format(
                "Transferencia exitosa de $%.2f desde %s hacia %s.",
                monto,
                origen.describirCliente(sucursalOrigen, dniOrigen),
                destino.describirCliente(sucursalDestino, dniDestino)
        );
        return TransferenciaResultado.ok(mensaje);
    }

    public double consultarSaldo(String banco, String sucursal, int dni) {
        IBanco bancoConsultado = bancosRegistrados.get(normalizarClave(banco));
        if (bancoConsultado == null) {
            throw new IllegalArgumentException("El banco indicado no esta registrado.");
        }
        return bancoConsultado.consultarSaldo(sucursal, dni);
    }

    private String normalizarClave(String valor) {
        return valor.trim().toLowerCase();
    }
}
