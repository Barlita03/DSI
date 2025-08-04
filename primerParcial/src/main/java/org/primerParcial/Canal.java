package org.primerParcial;

import java.util.ArrayList;
import java.util.List;

public class Canal {
  private final List<Canal> suscriptores = new ArrayList<>();
  private final List<Integer> muestrasDeApoyo = new ArrayList<>();
  private final List<Transmision> historicoTransmisiones = new ArrayList<>();
  private Transmision transmisionEnCurso = null;

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
    validarApoyo(numero);
    muestrasDeApoyo.add(numero);
  }

  public void agregarAlHistorico(Transmision transmision) {
    historicoTransmisiones.add(transmision);
  }

  private void validarApoyo(Integer numero) {
    if (numero < 1 || numero > 10) {
      throw new RuntimeException("El apoyo debe estar entre 1 y 10");
    }
  }

  public boolean puedeTransmitir() {
    return transmisionEnCurso == null;
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

  public Transmision getTransmisionEnCurso() {
    return transmisionEnCurso;
  }

  // --- Setters ---

  public void setTransmisionEnCurso(Transmision transmisionEnCurso) {
    this.transmisionEnCurso = transmisionEnCurso;
  }
}