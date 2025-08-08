package org.tatsy;

import java.util.ArrayList;
import java.util.List;

public class Flota {
  private static final Flota instancia = new Flota();
  private static final List<Vehiculo> vehiculos = new ArrayList<>();

  public static Flota getInstancia() {
    return instancia;
  }

  public static List<Vehiculo> getVehiculos() {
    return vehiculos;
  }
}
