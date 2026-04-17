package org.example.bancoLourdes;

public class Persona {
    String nombre;
    String dni;
    String domicilio;

    private Persona(Builder builder) {
        this.nombre = builder.nombre;
        this.dni = builder.dni;
        this.domicilio = builder.domicilio;
    }

    public static class Builder {
        private String nombre;
        private String dni;
        private String domicilio;

        public Builder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder setDni(String dni) {
            this.dni = dni;
            return this;
        }

        public Builder setDomicilio(String domicilio) {
            this.domicilio = domicilio;
            return this;
        }

        public Persona build() {
            return new Persona (this);
        }
    }

    @Override
    public String toString() {

        return nombre + " (DNI: " + dni + " - Domicilio: " + domicilio + ") ";
    }
}