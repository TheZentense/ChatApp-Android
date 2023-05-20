package com.example.chatapp;

public class MensajeModel {
    private String mensajeId;
    private String enviarId;
    private String mensaje;

    public MensajeModel(String mensajeId, String enviarId, String mensaje) {
        this.mensajeId = mensajeId;
        this.enviarId = enviarId;
        this.mensaje = mensaje;
    }

    public MensajeModel() {
    }

    public String getMensajeId() {
        return mensajeId;
    }

    public void setMensajeId(String mensajeId) {
        this.mensajeId = mensajeId;
    }

    public String getEnviarId() {
        return enviarId;
    }

    public void setEnviarId(String enviarId) {
        this.enviarId = enviarId;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
