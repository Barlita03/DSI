package org.primerParcial;

import java.util.ArrayList;
import java.util.List;

public class GestorDeCanales {
  private static final GestorDeCanales instancia = new GestorDeCanales();
  private static final List<Canal> canales = new ArrayList<>();

  // --- Metodos ---
  public static GestorDeCanales getInstancia() {
    return instancia;
  }

  public List<Canal> getCanales() {
    return new ArrayList<>(canales);
  }

  public static void agregarCanal(Canal canal) {
    canales.add(canal);
  }

  public static void eliminarCanal(Canal canal) {
    canales.remove(canal);
  }

  public static void limpiarLista() {
    canales.clear();
  }
}
