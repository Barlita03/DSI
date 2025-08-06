package org.example.usuarios;

import org.example.filtros.Filtro;

import java.util.ArrayList;
import java.util.List;

public class GestorDeUsuarios {
  private static final GestorDeUsuarios instancia = new GestorDeUsuarios();
  private static final List<Usuario> usuarios = new ArrayList<>();

  public static GestorDeUsuarios getInstancia() {
    return instancia;
  }

  public List<Usuario> mostrarGrilla(Usuario usuario) {
    return null; // TODO
  }

  public List<Usuario> filtrar(List<Filtro> filtros) {
    return null; // TODO
  }

  public List<Usuario> mostrarMisTipos(Usuario usuario) {
    return null; // TODO
  }

  // Se ejecuta con un Cron cada 5 minutos
  public void actualizarUbicaciones() {
    usuarios.forEach(Usuario::actualizarUbicacion);
  }
}
