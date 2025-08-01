package org.example;

import java.util.ArrayList;
import java.util.List;
import org.example.mensajes.Mensaje;
import org.example.mensajes.mensajeadores.Mensajeador;

public class Usuario {
  private final String emailPrincipal;
  private final String telefono;
  private final List<String> emails = new ArrayList<>();
  private Mensajeador mensajeador;

  // --- Constructor ---

  public Usuario(String emailPrincipal, String telefono) {
    this.emailPrincipal = emailPrincipal;
    this.telefono = telefono;
    emails.add(emailPrincipal);
  }

  // --- Metodos ---

  public void agregarMail(String email) {
    emails.add(email);
  }

  public void quitarMail(String email) {
    emails.remove(email);
  }

  public void recibirMensaje(Mensaje mensaje) {
    mensajeador.enviarMensaje(mensaje, this);
  }

  public void setMensajeador(Mensajeador mensajeador) {
    this.mensajeador = mensajeador;
  }

  public String getTelefono() {
    return telefono;
  }

  public String getEmailPrincipal() {
    return emailPrincipal;
  }
}
