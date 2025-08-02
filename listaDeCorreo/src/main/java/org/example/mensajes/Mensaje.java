package org.example.mensajes;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.util.Base64;
import javax.crypto.Cipher;
import org.example.Usuario;

public class Mensaje {
  @SuppressFBWarnings(value = "EI_EXPOSE_REP2")
  private final Usuario origen;

  @SuppressFBWarnings(value = "EI_EXPOSE_REP2")
  private final Usuario destinatario;

  private final String titulo;
  private String texto;

  // --- Constructor ---

  public Mensaje(Borrador borrador, Usuario destinatario) {
    this.origen = borrador.getOrigen();
    this.titulo = borrador.getTitulo();
    this.destinatario = destinatario;
    this.texto = encriptarTexto(borrador.getTexto());
  }

  private Mensaje(Mensaje mensaje, String texto) {
    this.origen = mensaje.getOrigen();
    this.destinatario = mensaje.getDestinatario();
    this.titulo = mensaje.getTitulo();
    this.texto = texto;
  }

  // --- Getters ---

  @SuppressFBWarnings(value = "EI_EXPOSE_REP")
  public Usuario getOrigen() {
    return origen;
  }

  public String getTitulo() {
    return titulo;
  }

  public String getTexto() {
    return texto;
  }

  @SuppressFBWarnings(value = "EI_EXPOSE_REP")
  public Usuario getDestinatario() {
    return destinatario;
  }

  private String encriptarTexto(String texto) {
    Cipher cipher = null;
    byte[] bytesCifrados = null;

    try {
      cipher = Cipher.getInstance("RSA");
      cipher.init(Cipher.ENCRYPT_MODE, destinatario.getClavePublica());
      bytesCifrados = cipher.doFinal(texto.getBytes(StandardCharsets.UTF_8));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return Base64.getEncoder().encodeToString(bytesCifrados);
  }

  public Mensaje desencriptarTexto(PrivateKey clavePrivada) {
    Cipher cipher = null;
    byte[] bytesDescifrados = null;

    try {
      cipher = Cipher.getInstance("RSA");
      cipher.init(Cipher.DECRYPT_MODE, clavePrivada);
      bytesDescifrados = cipher.doFinal(Base64.getDecoder().decode(texto));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return new Mensaje(this, new String(bytesDescifrados, StandardCharsets.UTF_8));
  }
}
