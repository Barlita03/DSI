package org.example.alertas;

import org.example.usuarios.Usuario;

import java.util.ArrayList;
import java.util.List;

public class GestorDeAlertas {
  private static final GestorDeAlertas instancia = new GestorDeAlertas();
  private static final List<Alerta> alertas = new ArrayList<>();

  public static GestorDeAlertas getInstancia() {
    return instancia;
  }

  private void lanzarAlertas(List<Alerta> alertas, Usuario usuario) {
    alertas.forEach(a -> a.notificarInteresados(usuario));
  }

  public void seConecto(Usuario usuario) {
    lanzarAlertas(alertas.stream().filter(a -> a.getTipo().cumple(usuario)).toList(), usuario);
  }
}
