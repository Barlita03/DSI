package org.firmadocs.procesos;

import org.firmadocs.usuarios.Usuario;

import java.util.LinkedList;

public class BorradorProceso {
  private final String documento;
  private final Usuario creador;
  private EstadoProceso estado;
  private final LinkedList<Usuario> firmadores = new LinkedList<>();
  private final LinkedList<Usuario> lectores = new LinkedList<>();

  public BorradorProceso(Usuario creador, String documento) {
    this.documento = documento;
    this.creador = creador;
  }

  public void agregarFirmador(Usuario usuario) {
    firmadores.add(usuario);
  }

  public void agregarLector(Usuario usuario) {
    lectores.add(usuario);
  }

  private Proceso crearOrdenIndistinto() {
    return creador.crearOrdenIndistinto(this);
  }

  private Proceso crearOrdenPrefijado() {
    return creador.crearOrdenPrefijado(this);
  }

  public String getDocumento() {
    return documento;
  }

  public Usuario getCreador() {
    return creador;
  }

  public LinkedList<Usuario> getFirmadores() {
    return firmadores;
  }

  public LinkedList<Usuario> getLectores() {
    return lectores;
  }
}
