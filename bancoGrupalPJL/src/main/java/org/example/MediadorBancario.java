package org.example;
import java.util.HashMap;
import java.util.Map;

public class MediadorBancario {
    private Map<String, IBanco> bancosReg = new HashMap<>();

    public void registrarBanco(IBanco banco) {
        bancosReg.put(banco.getNombre().toLowerCase(), banco);
    }

    public void enviarDinero(String nombreB, String suc, String cbu, double monto) {

        IBanco destino = bancosReg.get(nombreB.toLowerCase());
        if (destino != null) {
            destino.recibirTransferencia(suc, cbu, monto);
        } else {
            System.out.println("\n[ERROR]: Banco no encontrado.");
        }
    }
}
