package org.example.listasdecorreo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.example.Usuario;
import org.example.filtros.Filtro;
import org.example.listasdecorreo.modosdesuscripcion.ModoDeSuscripcion;
import org.example.listasdecorreo.privacidad.Privacidad;
import org.example.mensajes.Mensaje;

public class ListaDeCorreo {
  private Privacidad privacidad;
  private final String direccion;
  private ModoDeSuscripcion modoDeSuscripcion;
  private final List<Filtro> filtros = new ArrayList<>();
  private final List<Usuario> miembros = new ArrayList<>();
  private final List<Usuario> administradores = new ArrayList<>();
  private final List<Usuario> usuariosBloqueados = new ArrayList<>();
  private final List<PedidoDeSuscripcion> listaDeEspera = new ArrayList<>();
  private final HashMap<Usuario, Integer> strikes = new HashMap<Usuario, Integer>();

  // --- Constructor ---

  public ListaDeCorreo(
      String direccion,
      List<Usuario> administradores,
      List<Usuario> miembros,
      Privacidad privacidad,
      ModoDeSuscripcion modoDeSuscripcion) {
    this.direccion = direccion;
    this.privacidad = privacidad;
    this.modoDeSuscripcion = modoDeSuscripcion;
    this.miembros.addAll(miembros);
    this.administradores.addAll(administradores);
  }

  // --- Metodos ---

  public List<Usuario> getMiembros() {
    return new ArrayList<>(miembros);
  }

  public String getDireccion() {
    return direccion;
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
    if (filtros.stream().anyMatch(f -> f.cumple(mensaje.getTexto()))) {
      enviarTodosLosAdministradores(
          new Mensaje(
              null,
              "Violacion de los filtros detectada",
              "El usuario "
                  + mensaje.getOrigen().getEmailPrincipal()
                  + " ah enviado un mensaje que no cumple con los filtros establecidos"));

      strikes.put(mensaje.getOrigen(), strikes.getOrDefault(mensaje.getOrigen(), 0) + 1);

      if (strikes.get(mensaje.getOrigen()) >= 5) {
        bloquearUsuario(mensaje.getOrigen());
        strikes.put(mensaje.getOrigen(), 0);
      }
    } else {
      privacidad.recibirMensaje(this, mensaje);
    }
  }

  public void enviarTodosLosMiembros(Mensaje mensaje) {
      miembros.forEach(u -> u.recibirMensaje(mensaje));
  }

  public void enviarTodosLosAdministradores(Mensaje mensaje) {
    administradores.forEach(u -> u.recibirMensaje(mensaje));
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

  public void agregarFiltro(Filtro filtro) {
    filtros.add(filtro);
  }

  public void removerFiltro(Filtro filtro) {
    filtros.remove(filtro);
  }

  public void quitarTodosLosFiltros() {
    filtros.clear();
  }

  public void bloquearUsuario(Usuario usuario) {
    usuariosBloqueados.add(usuario);
  }

  public void desbloquearUsuario(Usuario usuario) {
    usuariosBloqueados.remove(usuario);
  }

  public List<Filtro> getFiltros() {
    return new ArrayList<>(filtros);
  }

  public HashMap<Usuario, Integer> getStrikes() {
    return new HashMap<>(strikes);
  }

  public List<Usuario> getUsuariosBloqueados() {
    return usuariosBloqueados;
  }
}
