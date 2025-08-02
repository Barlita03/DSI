package org.example.listasdecorreo;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.example.Usuario;
import org.example.filtros.Filtro;
import org.example.listasdecorreo.modosdesuscripcion.ModoDeSuscripcion;
import org.example.listasdecorreo.privacidad.Privacidad;
import org.example.mensajes.Borrador;

public class ListaDeCorreo {
  private String prefijo;
  private String pieDePagina;
  private Privacidad privacidad;
  private ModoDeSuscripcion modoDeSuscripcion;
  private final String direccion;
  private final List<Filtro> filtros = new ArrayList<>();
  private final List<Usuario> miembros = new ArrayList<>();
  private final List<Usuario> administradores = new ArrayList<>();
  private final List<Usuario> usuariosModerados = new ArrayList<>();
  private final List<Usuario> usuariosBloqueados = new ArrayList<>();
  private final List<MensajeModerado> mensajesEnEspera = new ArrayList<>();
  private final List<PedidoDeSuscripcion> listaDeEspera = new ArrayList<>();
  private final HashMap<Usuario, Integer> strikes = new HashMap<>();
  private final HashMap<Usuario, LocalDate> nuevosUsuarios = new HashMap<>();

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
    nuevosUsuarios.put(usuario, LocalDate.now());
  }

  public void quitarMiembro(Usuario usuario) {
    miembros.remove(usuario);
  }

  public void recibirMensaje(Borrador borrador) {
    if (filtros.stream().anyMatch(f -> f.cumple(borrador.getTexto()))) {
      enviarTodosLosAdministradores(
          new Borrador(
              administradores.get(0),
              "Violacion de los filtros detectada",
              "El usuario "
                  + borrador.getOrigen().getEmailPrincipal()
                  + " ah enviado un borrador que no cumple con los filtros establecidos"));

      strikes.put(borrador.getOrigen(), strikes.getOrDefault(borrador.getOrigen(), 0) + 1);

      if (strikes.get(borrador.getOrigen()) >= 5) {
        bloquearUsuario(borrador.getOrigen());
        strikes.put(borrador.getOrigen(), 0);
      }
    } else {
      modificarBorrador(borrador);
      privacidad.recibirMensaje(this, borrador);
    }
  }

  public void enviarTodosLosMiembros(Borrador borrador) {
    miembros.forEach(
        u -> {
          try {
            u.recibirMensaje(borrador.construirMensaje(u));
          } catch (Exception e) {
            throw new RuntimeException(e);
          }
        });
  }

  public void enviarTodosLosAdministradores(Borrador borrador) {
    administradores.forEach(
        u -> {
          try {
            u.recibirMensaje(borrador.construirMensaje(u));
          } catch (Exception e) {
            throw new RuntimeException(e);
          }
        });
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

  public void limpiarBloqueados() {
    usuariosBloqueados.clear();
  }

  public List<Filtro> getFiltros() {
    return new ArrayList<>(filtros);
  }

  public HashMap<Usuario, Integer> getStrikes() {
    return new HashMap<>(strikes);
  }

  public List<Usuario> getUsuariosBloqueados() {
    return new ArrayList<>(usuariosBloqueados);
  }

  public void setPrefijo(String prefijo) {
    this.prefijo = prefijo;
  }

  public void setPieDePagina(String pieDePagina) {
    this.pieDePagina = pieDePagina;
  }

  private void modificarBorrador(Borrador borrador) {
    if (prefijo != null) {
      borrador.setTexto(prefijo + "\n\n" + borrador.getTexto());
    }
    if (pieDePagina != null) {
      borrador.setTexto(borrador.getTexto() + "\n\n" + pieDePagina);
    }
  }

  public List<Usuario> getAdministradores() {
    return new ArrayList<>(administradores);
  }

  public void actualizarNuevos() {
    for (Map.Entry<Usuario, LocalDate> entry : nuevosUsuarios.entrySet()) {
      if (entry.getValue().plusDays(5).isBefore(LocalDate.now())) {
        nuevosUsuarios.remove(entry.getKey());
      }
    }
  }

  public void agregarMensajeAEspera(MensajeModerado mensaje) {
    mensajesEnEspera.add(mensaje);
  }

  public void quitarMensajeDeLaEspera(MensajeModerado mensaje) {
    mensajesEnEspera.remove(mensaje);
  }

  public List<MensajeModerado> getMensajesEnEspera() {
    return mensajesEnEspera;
  }

  @SuppressFBWarnings(value = "EI_EXPOSE_REP")
  public List<Usuario> getUsuariosModerados() {
    return usuariosModerados;
  }

  public void agregarUsuarioModerado(Usuario usuario) {
    usuariosModerados.add(usuario);
  }

  public void quitarUsuarioModerado(Usuario usuario) {
    usuariosModerados.remove(usuario);
  }

  public List<Usuario> getNuevosUsuarios() {
    return nuevosUsuarios.keySet().stream().toList();
  }
}
