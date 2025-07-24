package org.example;

public class PedidoDeSuscripcion {
  private final ListaDeCorreo lista;
  private final Usuario usuario;

  // --- Constructor ---

  public PedidoDeSuscripcion(ListaDeCorreo lista, Usuario usuario) {
    this.lista = lista;
    this.usuario = usuario;
  }

  // --- Metodos ---

  public void serAceptada() {
    lista.agregarMiembro(usuario);
    // TODO: Avisar al usuario
  }

  public void serRechazada() {
    // TODO: Avisar al usuario
  }
}
