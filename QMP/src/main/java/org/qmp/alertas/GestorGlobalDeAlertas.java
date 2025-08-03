package org.qmp.alertas;

import java.util.ArrayList;
import java.util.List;

public class GestorGlobalDeAlertas {
  private static final GestorGlobalDeAlertas instancia = new GestorGlobalDeAlertas();
  private static final List<GestorDeAlertas> gestores = new ArrayList<>();

  public static GestorGlobalDeAlertas getInstancia() {
    return instancia;
  }

  public static void agregarGestor(GestorDeAlertas gestor) {
    gestores.add(gestor);
  }

  public void quitarGestor(GestorDeAlertas gestor) {
    gestores.remove(gestor);
  }

  public static void lanzarAlertas() {
    gestores.forEach(GestorDeAlertas::lanzarAlertas);
  }
}
