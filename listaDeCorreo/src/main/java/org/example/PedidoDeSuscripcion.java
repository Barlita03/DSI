package org.example;

import org.example.listasdecorreo.ListaDeCorreo;

public class PedidoDeSuscripcion {
  private final ListaDeCorreo lista;
  private final Usuario usuario;

  // --- Constructor ---

  public PedidoDeSuscripcion(ListaDeCorreo lista, Usuario usuario) {
    this.lista = lista;
    this.usuario = usuario;
  }

  // --- Metodos ---

  public void serAceptado() {
    lista.agregarMiembro(usuario);
    notificarUsuario(
        "Solicitud de suscripcion aceptada",
        "Tu solicitud de suscripcion a " + lista.getDireccion() + " a sido aceptada");

    salirDeLaEspera();
  }

  public void serRechazado() {
    notificarUsuario(
        "Solicitud de suscripcion rechazada",
        "Tu solicitud de suscripcion a " + lista.getDireccion() + " a sido rechazada");
    salirDeLaEspera();
  }

  public void salirDeLaEspera() {
    lista.sacarDeLaEspera(this);
  }

  public void notificarUsuario(String titulo, String texto) {
    lista.getMailSender().send(new Mail(new Mensaje(null, titulo, texto), usuario));
  }
}
