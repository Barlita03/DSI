package org.primerParcial;

import java.util.ArrayList;
import java.util.List;

public class Canal {
  private List<Canal> suscriptores = new ArrayList<>();
  private List<Byte> muestrasDeApoyo = new ArrayList<>();
  private List<Transmision> historicoTransmisiones = new ArrayList<>();

  // --- Constructor ---

  public Canal() {
    GestorDeCanales.agregarCanal(this);
  }

  // --- Metodos ---

  public void suscribirseA(Canal canal) {
    canal.recibirSuscripcion(this);
  }

  public void recibirSuscripcion(Canal canal) {
    suscriptores.add(canal);
  }

  public void darMuestraDeApoyo(Canal canal, Byte numero) {
    if (canal.equals(this)) {
      throw new RuntimeException("No podes enviarte muestras de apoyo a vos mismo");
    }

    canal.recibirApoyo(numero);
  }

  public void visualizarTransmision(Transmision transmision) {
    transmision.agregarVisualizador(this);
  }

  public void recibirApoyo(Byte numero) {
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

  public List<Byte> getMuestrasDeApoyo() {
    return new ArrayList<>(muestrasDeApoyo);
  }
}
