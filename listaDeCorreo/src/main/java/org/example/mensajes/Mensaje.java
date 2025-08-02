package org.example.mensajes;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Cipher;
import org.example.Usuario;

public class Mensaje {
  @SuppressFBWarnings(value = "EI_EXPOSE_REP2")
  private final Usuario origen;

  @SuppressFBWarnings(value = "EI_EXPOSE_REP2")
  private final Usuario destinatario;

  private final String titulo;
  private final String texto;

  // --- Constructor ---

  public Mensaje(Borrador borrador, Usuario destinatario) throws Exception {
    this.origen = borrador.getOrigen();
    this.titulo = borrador.getTitulo();
    this.destinatario = destinatario;
    this.texto = encriptarTexto(borrador.getTexto());
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

  private String encriptarTexto(String texto) throws Exception {
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.ENCRYPT_MODE, destinatario.getClavePublica());
    byte[] bytesCifrados = cipher.doFinal(texto.getBytes(StandardCharsets.UTF_8));
    return Base64.getEncoder().encodeToString(bytesCifrados);
  }
}
