package org.example;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
  private final String emailPrincipal;
  private final List<String> emails = new ArrayList<>();

  // --- Constructor ---

  public Usuario(String emailPrincipal) {
    this.emailPrincipal = emailPrincipal;
    emails.add(emailPrincipal);
  }

  // --- Metodos ---

  public void agregarMail(String email) {
    emails.add(email);
  }

  public void quitarMail(String email) {
    emails.remove(email);
  }
}
