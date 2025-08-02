package org.example.listasdecorreo;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.example.Usuario;
import org.example.mensajes.Borrador;
import org.example.mensajes.Mensaje;

public class PedidoDeSuscripcion {
  @SuppressFBWarnings(value = "EI_EXPOSE_REP2")
  private final ListaDeCorreo lista;

  @SuppressFBWarnings(value = "EI_EXPOSE_REP2")
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
    usuario.recibirMensaje(
        new Mensaje(new Borrador(lista.getAdministradores().get(0), titulo, texto), usuario));
  }
}
