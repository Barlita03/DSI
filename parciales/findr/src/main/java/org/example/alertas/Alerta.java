package org.example.alertas;

import org.example.filtros.Tipo;
import org.example.usuarios.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Alerta {
  private final List<Usuario> interesados = new ArrayList<>();
  private final Tipo tipo;
  private final double distancia;

  public Alerta(Tipo tipo, double distancia) {
    this.tipo = tipo;
    this.distancia = distancia;
  }

  public void agregarInteresado(Usuario usuario) {
    interesados.add(usuario);
  }

  public void removerInteresado(Usuario usuario) {
    interesados.remove(usuario);
  }

  public void notificarInteresados(Usuario usuario) {
    interesados.stream().filter(u -> u.distanciaCon(usuario) <= distancia).toList().forEach(u -> u.recibirNotificacion("Hay un usuario de tu interes conectado en tu zona"));
  }

  public Tipo getTipo() {
    return tipo;
  }
}
