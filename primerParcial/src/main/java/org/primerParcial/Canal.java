package org.primerParcial;

import java.util.ArrayList;
import java.util.List;

public class Canal {
  private final List<Canal> suscriptores = new ArrayList<>();
  private final List<Integer> muestrasDeApoyo = new ArrayList<>();
  private final List<Transmision> historicoTransmisiones = new ArrayList<>();

  // --- Constructor ---

  public Canal() {
    GestorDeCanales.agregarCanal(this);
  }

  // --- Metodos ---

  public void recibirSuscripcion(Canal canal) {
    suscriptores.add(canal);
  }

  public void perderSuscripcion(Canal canal) {
    suscriptores.remove(canal);
  }

  public void recibirApoyo(Integer numero) {
    if (numero < 1 || numero > 10) {
      throw new RuntimeException("El apoyo debe estar entre 1 y 10");
    }

    muestrasDeApoyo.add(numero);
  }

  public void agregarAlHistorico(Transmision transmision) {
    historicoTransmisiones.add(transmision);
  }

  // --- Getters ---

  public List<Transmision> getHistoricoTransmisiones() {
    return new ArrayList<>(historicoTransmisiones);
  }

  public List<Canal> getSuscriptores() {
    return new ArrayList<>(suscriptores);
  }

  public List<Integer> getMuestrasDeApoyo() {
    return new ArrayList<>(muestrasDeApoyo);
  }
}
