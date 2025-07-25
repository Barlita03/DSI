package org.example.listasdecorreo;

import java.util.ArrayList;
import java.util.List;
import org.example.Mail;
import org.example.Mensaje;
import org.example.PedidoDeSuscripcion;
import org.example.Usuario;
import org.example.listasdecorreo.modosdesuscripcion.ModoDeSuscripcion;
import org.example.listasdecorreo.privacidad.Privacidad;
import org.example.servicios.MailSender;

public class ListaDeCorreo {
  private final String direccion;
  private final List<Usuario> miembros = new ArrayList<>();
  private final List<PedidoDeSuscripcion> listaDeEspera = new ArrayList<>();
  private MailSender mailSender;
  private Privacidad privacidad;
  private ModoDeSuscripcion modoDeSuscripcion;

  // --- Constructor ---

  public ListaDeCorreo(
      String direccion,
      List<Usuario> miembros,
      Privacidad privacidad,
      ModoDeSuscripcion modoDeSuscripcion,
      MailSender mailSender) {
    this.direccion = direccion;
    this.privacidad = privacidad;
    this.modoDeSuscripcion = modoDeSuscripcion;
    this.miembros.addAll(miembros);
    this.mailSender = mailSender;
  }

  // --- Metodos ---

  public List<Usuario> getMiembros() {
    return new ArrayList<>(miembros);
  }

  public String getDireccion() {
    return direccion;
  }

  public MailSender getMailSender() {
    return mailSender;
  }

  public List<PedidoDeSuscripcion> getListaDeEspera() {
    return new ArrayList<>(listaDeEspera);
  }

  public void vaciarMiembros() {
    miembros.clear();
  }

  public void suscribirse(Usuario usuario) {
    modoDeSuscripcion.suscribirse(this, usuario);
  }

  public void agregarEspera(PedidoDeSuscripcion pedido) {
    listaDeEspera.add(pedido);
  }

  public void sacarDeLaEspera(PedidoDeSuscripcion pedido) {
    listaDeEspera.remove(pedido);
  }

  public void agregarMiembro(Usuario usuario) {
    miembros.add(usuario);
  }

  public void quitarMiembro(Usuario usuario) {
    miembros.remove(usuario);
  }

  public void recibirMensaje(Mensaje mensaje) {
    privacidad.recibirMensaje(this, mensaje);
  }

  public void enviarTodosLosMiembros(Mensaje mensaje) {
    miembros.forEach(u -> mailSender.send(new Mail(mensaje, u)));
  }

  public boolean esMiembro(Usuario usuario) {
    return miembros.contains(usuario);
  }

  public void cambiarPrivacidad(Privacidad privacidad) {
    this.privacidad = privacidad;
  }

  public void cambiarModoDeSuscripcion(ModoDeSuscripcion modoDeSuscripcion) {
    this.modoDeSuscripcion = modoDeSuscripcion;
  }
}
