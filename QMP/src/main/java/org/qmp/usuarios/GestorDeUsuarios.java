package org.qmp.usuarios;

import java.util.ArrayList;
import java.util.List;

public class GestorDeUsuarios {
  public static GestorDeUsuarios instancia = new GestorDeUsuarios();
  public static List<Usuario> usuarios = new ArrayList<>();

  // --- Getters ---

  public static GestorDeUsuarios getInstancia() {
    return instancia;
  }

  public List<Usuario> getUsuarios() {
    return new ArrayList<>(usuarios);
  }

  // --- Metodos ---

  public static void agregarUsuario(Usuario usuario) {
    usuarios.add(usuario);
  }

  public static void removerUsuario(Usuario usuario) {
    usuarios.remove(usuario);
  }

  public static void actualizarSugerenciasDiarias() {
    usuarios.forEach(Usuario::sugerenciasDiarias);
  }

  public static void limpiarLista() {
    usuarios.clear();
  }
}
