package org.example;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import org.example.mensajes.Mensaje;
import org.example.mensajes.mensajeadores.Mensajeador;

public class Usuario {
  private final String emailPrincipal;
  private final String telefono;
  private final List<String> emails = new ArrayList<>();
  private Mensajeador mensajeador;
  private String firma;

  private final PrivateKey clavePrivada;
  private final PublicKey clavePublica;

  // --- Constructor ---

  public Usuario(String emailPrincipal, String telefono) throws Exception {
    this.emailPrincipal = emailPrincipal;
    this.telefono = telefono;
    emails.add(emailPrincipal);

    KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
    keyGen.initialize(2048);
    KeyPair pair = keyGen.generateKeyPair();
    this.clavePrivada = pair.getPrivate();
    this.clavePublica = pair.getPublic();
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

  public void setFirma(String firma) {
    this.firma = firma;
  }

  public String getFirma() {
    return firma;
  }

  public PublicKey getClavePublica() {
    return clavePublica;
  }
}
